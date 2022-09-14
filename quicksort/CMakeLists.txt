cmake_minimum_required(VERSION 3.1)

project(quicksort)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -Wall -Wextra")

add_executable(quicksort quicksort.cpp single_pivot.cpp dual_pivot.cpp)

MATH(EXPR stack_size "16 * 1024 * 1024 * 1024") # 16 Gb
set(CMAKE_EXE_LINKER_FLAGS "-Wl,--stack,${stack_size}")