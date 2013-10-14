#include "test_vector_add.h"

#include <CL/cl.h>
#include <ctime>
#include <iostream>
#include <string>
#include <vector>
#include "../util.h"

namespace test_vector_add
{

// abstraction utility i/o classes
// parallelRepeatStatement(Scope& _scope, Statement& _statement, Times& _times)
// where
//  Scope is memory accessibility,
//  Statement is one (or a block of) operation(s),
//  Times is the times to repeat

struct Scope
{
    std::vector<long> _left;
    std::vector<long> _right;
    std::vector<long> _output1;
    std::vector<long> _output2;
};

struct Statement
{
    std::string _kernel;
};

struct Times
{
    unsigned long _times;
};

void parallelRepeatStatement(Scope& _scope, Statement& _statement, Times& _times);

void start()
{
    std::cout << "__________vector_add_start________" << std::endl;
    clock_t begin = clock();

    Scope scope;
    Statement statement;
    Times times;

    statement._kernel =
        "__kernel void vector_add(__global long *C, __global long *D, __global long *A, __global long *B)\n"
        "{\n"
        "    // Get the index of the current element\n"
        "    size_t i = get_global_id(0);\n"
        "\n"
        "    // Do the operation\n"
        "    C[i] = A[i] + B[i];\n"
        "    D[i] = C[i] - A[i];\n"
        "}\n"
        "\n";
    std::cout << statement._kernel << std::endl;

    times._times = 7000000; // max 70000000, 534 MB per vector, 1GB in + 1GB out + calc for only 3 seconds!

    scope._left.resize(times._times);
    scope._right.resize(times._times);
    scope._output1.resize(times._times);
    scope._output2.resize(times._times);

    // init input
    for(unsigned long i = 0; i < times._times; ++i)
    {
        scope._left[i] = i;
        scope._right[i] = times._times - i;
    }

    // init outputs for debug purposes
    for (unsigned long i = 0; i < times._times; ++i)
    {
        scope._output1[i] = 0;
        scope._output2[i] = 0;
    }

    std::cout << "Prepare data ends at: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;

    parallelRepeatStatement(scope, statement, times);

    std::cout << "Execution ends at: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;

    for(unsigned long i = 0; i < 3; ++i)
    {
        std::cout << scope._left[i] << " + " << scope._right[i]
                  << " = " << scope._output1[i] << " ||| "
                  << scope._output1[i] << " - " << scope._left[i]
                  << " = " << scope._output2[i] << std::endl;
    }

    std::cout << "[...]" << std::endl;

    for(unsigned long i = times._times - 3; i < times._times; ++i)
    {
        std::cout << scope._left[i] << " + " << scope._right[i]
                  << " = " << scope._output1[i] << " ||| "
                  << scope._output1[i] << " - " << scope._left[i]
                  << " = " << scope._output2[i] << std::endl;
    }

    std::cout << "Single vector memory size: " << times._times * sizeof(long)/1024/1024 << " MB" << std::endl;
}

void parallelRepeatStatement(Scope& _scope, Statement& _statement, Times& _times)
{
    clock_t begin = clock();

    const char* source_str = _statement._kernel.c_str();
    size_t source_size = _statement._kernel.size();

    // Get platform and device information
    cl_platform_id platform_id = NULL;
    cl_device_id device_id = NULL;
    cl_uint ret_num_devices;
    cl_uint ret_num_platforms;
    cl_int ret;

    // Get platform and device information
    ret = clGetPlatformIDs(1, &platform_id, &ret_num_platforms);
    checkError(ret);
    ret = clGetDeviceIDs(platform_id, CL_DEVICE_TYPE_GPU, 1, &device_id, &ret_num_devices);
    checkError(ret);

    // Create an OpenCL context
    cl_context context = clCreateContext( NULL, 1, &device_id, NULL, NULL, &ret);
    checkError(ret);

    // Create a command queue
    cl_command_queue command_queue = clCreateCommandQueue(context, device_id, 0, &ret);
    checkError(ret);

    // Create a program from the kernel source
    cl_program program = clCreateProgramWithSource(context, 1, (const char **)&source_str, &source_size, &ret);
    checkError(ret);

    // Build the program
    ret = clBuildProgram(program, 1, &device_id, NULL, NULL, NULL);
    checkError(ret);

    // Create the OpenCL kernel
    cl_kernel kernel = clCreateKernel(program, "vector_add", &ret);
    checkError(ret);

    // Create memory buffers on the device for the output
    //cl_mem device_output_buffer_1 = clCreateBuffer(context, CL_MEM_WRITE_ONLY, _scope._output1.size() * sizeof(long), NULL, &ret);
    // initializing for debug purposes
    cl_mem device_output_buffer_1 = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, _scope._output1.size() * sizeof(long), &_scope._output1[0], &ret);
    checkError(ret);
    //cl_mem device_output_buffer_2 = clCreateBuffer(context, CL_MEM_WRITE_ONLY, _scope._output1.size() * sizeof(long), NULL, &ret);
    // initializing for debug purposes
    cl_mem device_output_buffer_2 = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, _scope._output2.size() * sizeof(long), &_scope._output2[0], &ret);
    checkError(ret);

    // Create memory buffers on the device for the input and copy the data
    cl_mem device_input_buffer_left = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, _scope._left.size() * sizeof(long), &_scope._left[0], &ret);
    checkError(ret);
    cl_mem device_input_buffer_right = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, _scope._right.size() * sizeof(long), &_scope._right[0], &ret);
    checkError(ret);

    // Set the arguments of the kernel
    {
        cl_uint n_parameters = 0;

        // output
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_output_buffer_1);
        checkError(ret);
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_output_buffer_2);
        checkError(ret);

        // inputs
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_input_buffer_left);
        checkError(ret);
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_input_buffer_right);
        checkError(ret);
    }

    std::cout << "Preparing opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
    begin = clock();

    // Execute the OpenCL kernel on the list
    {
        cl_uint work_dim = 1; // uni-dimensional work
        size_t global_work_size[work_dim], local_work_size[work_dim];
        global_work_size[0] = _times._times / work_dim; // Process the entire lists
        local_work_size[0] = 64; // Divide work items into groups of 64

        ret = clEnqueueNDRangeKernel(command_queue, kernel, work_dim, NULL, global_work_size, local_work_size, 0, NULL, NULL);
        checkError(ret);

        // Read the output memory buffer on the device to the output
        ret = clEnqueueReadBuffer(command_queue, device_output_buffer_1, CL_TRUE, 0, _scope._output1.size() * sizeof(long), &_scope._output1[0], 0, NULL, NULL);
        checkError(ret);
        ret = clEnqueueReadBuffer(command_queue, device_output_buffer_2, CL_TRUE, 0, _scope._output2.size() * sizeof(long), &_scope._output2[0], 0, NULL, NULL);
        checkError(ret);
    }

    // Clean up
    {
        ret = clFlush(command_queue);
        checkError(ret);
        ret = clFinish(command_queue);
        checkError(ret);
        ret = clReleaseKernel(kernel);
        checkError(ret);
        ret = clReleaseProgram(program);
        checkError(ret);

        std::cout << "Running opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
        begin = clock();

        {
            // free outputs device memory
            ret = clReleaseMemObject(device_output_buffer_1);
            checkError(ret);
            ret = clReleaseMemObject(device_output_buffer_2);
            checkError(ret);

            // free inputs device memory
            ret = clReleaseMemObject(device_input_buffer_left);
            checkError(ret);
            ret = clReleaseMemObject(device_input_buffer_right);
            checkError(ret);
        }

        ret = clReleaseCommandQueue(command_queue);
        checkError(ret);
        ret = clReleaseContext(context);
        checkError(ret);
    }
    std::cout << "Cleaning opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
}

}
