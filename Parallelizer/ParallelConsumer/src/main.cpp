#include "tests/test_vector_add.h"
#include "tests/test_matrix_mult.h"

#include <iostream>

int main(void)
{
    test_vector_add::start();
    std::cout << "__________________________________" << std::endl;
    std::cout << "__________________________________" << std::endl;
    std::cout << "__________________________________" << std::endl;
    test_matrix_mult::start();

    return 0;
}
