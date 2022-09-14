#include "quicksort.h"
#include <iostream>
#include <array>
#include <chrono>

void printArray(int arr[], int size)
{
    for (int i = 0; i < size; i++)
    {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
}

int main() {
    int size = 500000;
    int SinglePivotArray[size];
    int DualPivotArray[size];

    for (int i = 0; i < size; ++i) {
        SinglePivotArray[i] = (rand() % 10 + 1);
    }

    std::copy(SinglePivotArray, SinglePivotArray + size, DualPivotArray);

    // std::cout << "Before sorting with single pivot: " << std::endl;
    // printArray(SinglePivotArray, size);
    auto start = std::chrono::high_resolution_clock::now();
    SinglePivotQuickSort(SinglePivotArray, 0, size - 1);
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> SinglePivotTime = end-start;
    // std::cout << "After sorting with single pivot: " << std::endl;
    // printArray(SinglePivotArray, size);

    // std::cout << "\nBefore sorting with dual pivot: " << std::endl;
    // printArray(DualPivotArray, size);
    auto start2 = std::chrono::high_resolution_clock::now();
    //DualPivotQuickSort(DualPivotArray, 0, size - 1);
    auto end2 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> DualPivotTime = end2-start2;
    // std::cout << "After sorting with dual pivot: " << std::endl;
    // printArray(DualPivotArray, size);

    std::cout << "time used by single pivot: " << SinglePivotTime.count() << std::endl;
    std::cout << "time used by dual pivot: " << DualPivotTime.count() << std::endl;

    return 0;
}