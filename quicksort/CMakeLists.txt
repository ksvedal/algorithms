cmake_minimum_required(VERSION 3.1)

project(quicksort)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -Wall -Wextra")

add_executable(quicksort quicksort.cpp single_pivot.cpp dual_pivot.cpp)

set(CMAKE_CXX_STACK_SIZE ${CMAKE_CXX_STACK_SIZE} /STACK:640000000000)