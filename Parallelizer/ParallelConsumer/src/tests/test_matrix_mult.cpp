#include "test_matrix_mult.h"

#include <CL/cl.h>
#include <ctime>
#include <iostream>
#include <string>
#include <vector>
#include "../util.h"

namespace test_matrix_mult
{

// from http://gpgpu-computing4.blogspot.com.br/2009/09/matrix-multiplication-1.html
const unsigned int WA = 8; // max 10000x10000, 381 MB per matrix
const unsigned int HA = 4; // max 10000x10000, 381 MB per matrix

const unsigned int WB = 6; // max 10000x10000, 381 MB per matrix
const unsigned int HB = WA; // A columns must be equal to B rows

// the product is a A rows X B columns matrix
const unsigned int WC = WB;
const unsigned int HC = HA;

// abstraction utility i/o classes
// parallelRepeatStatement(Scope& _scope, Statement& _statement, Times& _times)
// where
//  Scope is memory accessibility,
//  Statement is one (or a block of) operation(s),
//  Times is the times to repeat

struct Scope
{
    std::vector<float> _left;
    std::vector<float> _right;
    std::vector<float> _output;
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
    std::cout << "__________matrix_mult_start_______" << std::endl;
    clock_t begin = clock();

    Scope scope;
    Statement statement;
    Times times;

    scope._left.resize(WA * HA);
    scope._right.resize(WB * HB);
    scope._output.resize(WC * HC);

    // init inputs
    {
        for(unsigned long i = 0; i < scope._left.size(); ++i)
        {
            scope._left[i] = i+1;
        }

        for(unsigned long i = 0; i < scope._right.size(); ++i)
        {
            scope._right[i] = 1;
        }
    }

    // init output for debug purposes
    {
        for (unsigned long i = 0; i < scope._output.size(); ++i)
        {
            scope._output[i] = 0;
        }
    }

    statement._kernel =
        "__kernel void matrix_mult(__global float* C,""\n"
        "                          __global float* A,""\n"
        "                          __global float* B,""\n"
        "                          unsigned int WA,""\n"
        "                          unsigned int WB,""\n"
        "                          unsigned int WC)""\n"
        "{""\n"
        "    unsigned int c_row = get_global_id(0); // for(unsigned int c_row = 0; c_row < HC; ++c_row)""\n"
        "    unsigned int c_col = get_global_id(1); // for(unsigned int c_col = 0; c_col < WC; ++c_col)""\n"
        "    // dot product of Arow[] * Bcol[] vectors""\n"
        "    // c(c_row, c_col) = 0;""\n"
        "    float sum = 0.0f;""\n"
        "\n"
        "    for(unsigned int a_col = 0; a_col < WA; ++a_col) // a_col == b_row""\n"
        "    {""\n"
        "        //c(c_row, c_col) += a(c_row, a_col) * b(a_col, c_col);""\n"
        "        // a_cols == b_rows""\n"
        "        // a_rows == c_rows""\n"
        "        sum"
        " += A[c_row * WA + a_col] * B[a_col * WB + c_col];""\n"
        "    }""\n"
        "    C[c_row * WC + c_col] = sum;""\n"
        "}""\n";


    std::cout << statement._kernel << std::endl;

    times._times = scope._output.size();

    std::cout << "Prepare data ends at: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;

    parallelRepeatStatement(scope, statement, times);

    std::cout << "Execution ends at: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;

    // print left
    for(unsigned long i=0; i < scope._left.size(); ++i)
    {
        std::cout << scope._left[i] << ((((i+1) % WA) == 0) ? '\n' : ' ');
    }

    std::cout << "\t*" << std::endl;

    // print right
    for(unsigned long i=0; i < scope._right.size(); ++i)
    {
        std::cout << scope._right[i] << ((((i+1) % WB) == 0) ? '\n' : ' ');
    }

    std::cout << "\t=" << std::endl;

    // print out
    for(unsigned long i=0; i < scope._output.size(); ++i)
    {
        std::cout << scope._output[i] << ((((i+1) % WC) == 0) ? '\n' : ' ');
    }

    std::vector<float> CPU_output;
    CPU_output.resize(WC * HC);

    // CPU implementation
    for(unsigned int c_row = 0; c_row < HC; ++c_row)
    {
        for(unsigned int c_col = 0; c_col < WC; ++c_col)
        {
            // dot product of Arow[] * Bcol[] vectors
            // c(c_row, c_col) = 0;
            CPU_output[c_row * WC + c_col] = 0;

            for(unsigned int a_col = 0; a_col < WA; ++a_col) // a_col == b_row
            {
                //c(c_row, c_col) += a(c_row, a_col) * b(a_col, c_col);
                // a_cols == b_rows
                // a_rows == c_rows
                CPU_output[c_row * WC + c_col] += scope._left[c_row * WA + a_col] * scope._right[a_col * WB + c_col];
            }
        }
    }

    std::cout << std::endl << "\tCPU output:" << std::endl;

    // print CPU out
    for(unsigned long i=0; i < CPU_output.size(); ++i)
    {
        std::cout << CPU_output[i] << ((((i+1) % WC) == 0) ? '\n' : ' ');
    }

    std::cout << std::endl << "Test the result on http://wims.unice.fr/~wims/wims.cgi?session=VBBE21D621.2&+lang=en&+module=tool%2Flinear%2Fmatmult" << std::endl;
    std::cout << "Single vector memory size: " << (times._times * ((float)sizeof(float))/1024) << " KB" << std::endl;
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
    cl_kernel kernel = clCreateKernel(program, "matrix_mult", &ret);
    checkError(ret);

    // Create memory buffer on the device for the output
    //cl_mem device_output_buffer = clCreateBuffer(context, CL_MEM_WRITE_ONLY, _scope._output.size() * sizeof(float), NULL, &ret);
    // initializing for debug purposes
    cl_mem device_output_buffer = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, _scope._output.size() * sizeof(float), &_scope._output[0], &ret);
    checkError(ret);

    // Create memory buffers on the device for the input and copy the data
    cl_mem device_input_buffer_left = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, _scope._left.size() * sizeof(float), &_scope._left[0], &ret);
    checkError(ret);
    cl_mem device_input_buffer_right = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, _scope._right.size() * sizeof(float), &_scope._right[0], &ret);
    checkError(ret);

    // Set the arguments of the kernel
    {
        cl_uint n_parameters = 0;

        // output
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_output_buffer);
        checkError(ret);

        // inputs
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_input_buffer_left);
        checkError(ret);
        ret = clSetKernelArg(kernel, n_parameters++, sizeof(cl_mem), (void *)&device_input_buffer_right);
        checkError(ret);

        // adicional data
        {
            int wA = WA;
            int wB = WB;
            int wC = WC;
            ret = clSetKernelArg(kernel, n_parameters++, sizeof(int), (void *)&wA);
            checkError(ret);
            ret = clSetKernelArg(kernel, n_parameters++, sizeof(int), (void *)&wB);
            checkError(ret);
            ret = clSetKernelArg(kernel, n_parameters++, sizeof(int), (void *)&wC);
            checkError(ret);
        }
    }

    std::cout << "Preparing opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
    begin = clock();

    // Execute the OpenCL kernel on the list
    {
        // from http://www.nvidia.com/content/cudazone/download/OpenCL/NVIDIA_OpenCL_ProgrammingGuide.pdf
        // see also http://gpgpu-computing4.blogspot.com.br/2009/09/matrix-multiplication-1.html
        // check this benckmark http://www.bealto.com/gpu-gemv_conclusion.html
        cl_uint work_dim = 2; // bi-dimensional work
        size_t global_work_size[] = {WA, WB};
        size_t local_work_size[] = {1, 1};

        ret = clEnqueueNDRangeKernel(command_queue, kernel, work_dim, NULL, global_work_size, local_work_size, 0, NULL, NULL);
        checkError(ret);
        ret = clFlush(command_queue);
        checkError(ret);

        std::cout << "Executing opencl gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
        begin = clock();

        // Read the output memory buffer on the device to the output
        ret = clEnqueueReadBuffer(command_queue, device_output_buffer, CL_TRUE, 0, _scope._output.size() * sizeof(float), &_scope._output[0], 0, NULL, NULL);
        checkError(ret);
        ret = clFlush(command_queue);
        checkError(ret);

        std::cout << "Copy the output gets: " << int(1000 * double(clock() - begin) / CLOCKS_PER_SEC)/1000.0 << " sec." << std::endl;
        begin = clock();
    }

    // Clean up
    {
        ret = clFinish(command_queue);
        checkError(ret);
        ret = clReleaseKernel(kernel);
        checkError(ret);
        ret = clReleaseProgram(program);
        checkError(ret);

        {
            // free output device memory
            ret = clReleaseMemObject(device_output_buffer);
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
