#include <CL/cl.h>
#include <ctime>
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "Table.h"

//dependem de vector_add_kernel.cl
#define INPUT_PARAMETERS 2
#define OUTPUT_PARAMETERS 2
#define DATA_TYPE long

//utility i/o classes
typedef Table<DATA_TYPE> Input;
typedef Table<DATA_TYPE> Output;

void getInputData(Input& _input);
void getFunctionFromFile(std::string _file_name, std::string& _function);
void execute(std::string& _function, Input& _input, Output& _output);

int main(void)
{
    clock_t begin = clock();

    Input input;
    Output output;

    getInputData(input);

    output.setColumns(OUTPUT_PARAMETERS);
    output.setRows(input.getRows());

    std::string function;
    getFunctionFromFile("vector_add_kernel.cl", function);

    std::cout << "Prepare data ends at: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;

    execute(function, input, output);

    std::cout << "Execution ends at: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;

    for(unsigned long i = 0; i < 3; i++)
    {
        std::cout << input.m_data[0].m_data[i] << " + " << input.m_data[1].m_data[i]
                  << " = " << output.m_data[0].m_data[i] << " ||| "
                  << output.m_data[0].m_data[i] << " - " << input.m_data[0].m_data[i]
                  << " = " << output.m_data[1].m_data[i] << std::endl;
    }

    std::cout << "[...]" << std::endl;

    for(unsigned long i = input.getRows() - 3; i < output.getRows(); i++)
    {
        std::cout << input.m_data[0].m_data[i] << " + " << input.m_data[1].m_data[i]
                  << " = " << output.m_data[0].m_data[i] << " ||| "
                  << output.m_data[0].m_data[i] << " - " << input.m_data[0].m_data[i]
                  << " = " << output.m_data[1].m_data[i] << std::endl;
    }

    //_input.setRows(70000000); get 534 MB for vector, 1GB in + 1GB out + calc for only 3 seconds!
    std::cout << "Single vector memory size: " << input.getRows() * sizeof(DATA_TYPE)/1024/1024 << " MB" << std::endl;

    return 0;
}

void getInputData(Input& _input)
{
    _input.setColumns(INPUT_PARAMETERS);
    _input.setRows(70000000);

    for(unsigned long i = 0; i < _input.getRows(); i++)
    {
        _input.m_data[0].m_data[i] = i;
        _input.m_data[1].m_data[i] = _input.getRows() - i;
    }
}

void getFunctionFromFile(std::string _file_name, std::string& _function)
{
    std::ifstream file_stream(_file_name.c_str());

    if(!file_stream.is_open())
    {
        std::cerr << "Error reading " << _file_name;
        exit(1);
    }

    _function.assign(std::istreambuf_iterator<char>(file_stream), (std::istreambuf_iterator<char>()));
    file_stream.close();
}

void execute(std::string& _function, Input& _input, Output& _output)
{
    clock_t begin = clock();

    const char* source_str = _function.c_str();
    size_t source_size = _function.size();

    // Get platform and device information
    cl_platform_id platform_id = NULL;
    cl_device_id device_id = NULL;
    cl_uint ret_num_devices;
    cl_uint ret_num_platforms;
    cl_int ret = clGetPlatformIDs(1, &platform_id, &ret_num_platforms);
    ret = clGetDeviceIDs( platform_id, CL_DEVICE_TYPE_DEFAULT, 1,
                          &device_id, &ret_num_devices);

    // Create an OpenCL context
    cl_context context = clCreateContext( NULL, 1, &device_id, NULL, NULL, &ret);

    // Create a command queue
    cl_command_queue command_queue = clCreateCommandQueue(context, device_id, 0, &ret);

    // Create a program from the kernel source
    cl_program program = clCreateProgramWithSource(context, 1, (const char **)&source_str, &source_size, &ret);

    // Build the program
    ret = clBuildProgram(program, 1, &device_id, NULL, NULL, NULL);

    // Create the OpenCL kernel
    cl_kernel kernel = clCreateKernel(program, "vector_add", &ret);

    // Create memory buffers on the device for the input and copy the data
    std::vector<cl_mem> device_input_buffer(_input.getColumns());
    {
        for(unsigned long i = 0; i<device_input_buffer.size(); ++i)
        {
            device_input_buffer[i] = clCreateBuffer(context, CL_MEM_READ_ONLY, _input.m_data[i].getSize() * sizeof(DATA_TYPE), NULL, &ret);
            ret = clEnqueueWriteBuffer(command_queue, device_input_buffer[i], CL_TRUE, 0, _input.m_data[i].getSize() * sizeof(DATA_TYPE), &_input.m_data[i].m_data[0], 0, NULL, NULL);
        }
    }

    // Create memory buffers on the device for the output
    std::vector<cl_mem> device_output_buffer(_output.getColumns());
    {
        for(unsigned long i = 0; i<device_output_buffer.size(); ++i)
        {
            device_output_buffer[i] = clCreateBuffer(context, CL_MEM_READ_ONLY, _output.m_data[i].getSize() * sizeof(DATA_TYPE), NULL, &ret);
        }
    }

    // Set the arguments of the kernel
    {
        cl_uint n_parameters = 0;

        for(unsigned long i = 0; i<device_input_buffer.size(); ++i, ++n_parameters)
        {
            ret = clSetKernelArg(kernel, n_parameters, sizeof(cl_mem), (void *)&device_input_buffer[i]);
        }

        for(unsigned long i = 0; i<device_output_buffer.size(); ++i, ++n_parameters)
        {
            ret = clSetKernelArg(kernel, n_parameters, sizeof(cl_mem), (void *)&device_output_buffer[i]);
        }
    }

    std::cout << "Preparing opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
    begin = clock();

    // Execute the OpenCL kernel on the list
    {
        size_t global_item_size = _input.getRows(); // Process the entire lists
        size_t local_item_size = 64; // Divide work items into groups of 64
        ret = clEnqueueNDRangeKernel(command_queue, kernel, 1, NULL, &global_item_size, &local_item_size, 0, NULL, NULL);

        // Read the output memory buffer on the device to the output
        {
            for(unsigned long i = 0; i<device_input_buffer.size(); ++i)
            {
                ret = clEnqueueReadBuffer(command_queue, device_output_buffer[i], CL_TRUE, 0, _output.m_data[i].getSize() * sizeof(DATA_TYPE), &_output.m_data[i].m_data[0], 0, NULL, NULL);
            }
        }
    }

    // Clean up
    {
        ret = clFlush(command_queue);
        ret = clFinish(command_queue);
        ret = clReleaseKernel(kernel);
        ret = clReleaseProgram(program);

        std::cout << "Running opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
        begin = clock();

        {
            for(unsigned long i = 0; i<device_input_buffer.size(); ++i)
            {
                ret = clReleaseMemObject(device_input_buffer[i]);
            }

            device_input_buffer.clear();

            for(unsigned long i = 0; i<device_output_buffer.size(); ++i)
            {
                ret = clReleaseMemObject(device_output_buffer[i]);
            }

            device_output_buffer.clear();
        }

        ret = clReleaseCommandQueue(command_queue);
        ret = clReleaseContext(context);
    }
    std::cout << "Cleaning opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
}
