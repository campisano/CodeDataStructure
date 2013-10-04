#include <stdio.h>
#include <conio.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#define N 10000 // Matrix dim = N x N
#define THREADSperBLOCK 1024

// Kernel: usando mais de um bloco (uso típico)
__global__ void vecAdd(const float *A, const float *B, float *C)
{
	unsigned x = blockIdx.x * blockDim.x + threadIdx.x;
	unsigned y = blockIdx.y * blockDim.y + threadIdx.y;

	int i = N * y + x;

	if(i < N * N)
	{
		C[i] = A[i] + B[i];
	}
}

int main(int argc, char** argv)
{
	// vars
	unsigned int VECTOR_SIZE = N * N;
	unsigned int size = VECTOR_SIZE * sizeof(float);
	float *h_A, *h_B, *h_C, *h_Cref;
	float *d_A, *d_B, *d_C;
	
	double startTime, elapsedTimeCPU, elapsedTimeGPU;

	// init random
	srand((unsigned int)time(NULL));

	// Allocate CPU
	{
		h_A = (float*) malloc(size);
		h_B = (float*) malloc(size);
		h_C = (float*) malloc(size);
		h_Cref = (float*) malloc(size);
	}

	// Initialize CPU
	{
		for(int i = 0; i < VECTOR_SIZE; ++i){
			h_A[i] = rand() / (float)RAND_MAX;
			h_B[i] = rand() / (float)RAND_MAX;
		}
	}

	// Compute CPU
	{
		startTime = clock();

		for (int i = 0; i < (VECTOR_SIZE); ++i)
		{
			h_Cref[i] = h_A[i] + h_B[i];
		}

		elapsedTimeCPU = (clock() - startTime) / CLOCKS_PER_SEC;
		printf("CPU = %f s\n",elapsedTimeCPU);
	}

	// Allocate GPU
	{
		cudaMalloc((void**)&d_A, size);
		cudaMalloc((void**)&d_B, size);
		cudaMalloc((void**)&d_C, size);
	}

	// Copy to GPU
	{
		cudaMemcpy(d_A, h_A, size, cudaMemcpyHostToDevice);
		cudaMemcpy(d_B, h_B, size, cudaMemcpyHostToDevice);
	}

	// Compute GPU
	{
		startTime = clock();
		vecAdd<<< (VECTOR_SIZE+THREADSperBLOCK-1)/THREADSperBLOCK, THREADSperBLOCK >>>(d_A, d_B, d_C);
		cudaThreadSynchronize(); // Aguarda fim da execução do kernel pela GPU
		elapsedTimeGPU = (clock() - startTime) / CLOCKS_PER_SEC;
		printf("GPU = %f s\n", elapsedTimeGPU);
	}

	printf("Speedup = %f X\n", (elapsedTimeCPU / elapsedTimeGPU));

	// CPU waits for kernel's run end (GPU)
	// Copy results from GPU
	cudaMemcpy(h_C, d_C, size, cudaMemcpyDeviceToHost);

	// Verify result
	{
		int i;
		for (i = 0; i < VECTOR_SIZE; ++i)
			if (fabs(h_C[i] - h_Cref[i]) > 1e-5)
				break;

		printf("%s\n", (i == VECTOR_SIZE) ? "PASSED!" : "FAILED!");
	}

	// free memory
	{
		cudaFree(d_A);
		cudaFree(d_B);
		cudaFree(d_C);
		free(h_A);
		free(h_B);
		free(h_C);
		free(h_Cref);
	}

	// pause
	getch();

	return 0;
}