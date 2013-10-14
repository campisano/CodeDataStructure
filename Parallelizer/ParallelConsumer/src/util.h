#ifndef _UTIL_H
#define _UTIL_H

#include <string>

void getFunctionFromFile(std::string _file_name, std::string& _function);
void checkError(long _result);
const char* clErrorString(long err);

#endif
