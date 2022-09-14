#include "quicksort.h"
#include <iostream>
#include <array>
#include <chrono>

// Both the dual pivot and single pivot algorithms have been copied over from geeksforgeeks.
// For a prosject comparing them.
// These are the links: https://www.geeksforgeeks.org/quick-sort/
//                      https://www.geeksforgeeks.org/dual-pivot-quicksort/

const int size = 100000000;
int SinglePivotArray[size];
int DualPivotArray[size];

int sum;
bool sorted = true;

int main() {
    for (int & i : SinglePivotArray) {
        i = (rand() % 10 + 1);
    }
    std::copy(SinglePivotArray, SinglePivotArray + size, DualPivotArray);

    // Print the sum before
    for (int & i : SinglePivotArray) {
        sum += i;
    }
    std::cout << "Sum before sorting with single pivot:     " << sum << std::endl;

    for (int i = 0; i < size-2; ++i) {
        if (SinglePivotArray[i+1] < SinglePivotArray[i]) {
            sorted = false;
            break;
        } else {
            sorted = true;
        }
    }
    if (sorted) {
        std::cout << "SinglePivotArray is sorted." << std::endl;
    } else {
        std::cout << "SinglePivotArray is not sorted." << std::endl;
    };

    auto start = std::chrono::high_resolution_clock::now();
    // SinglePivotQuickSort(SinglePivotArray, 0, size - 1);
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> SinglePivotTime = end-start;

    for (int i = 0; i < size-2; ++i) {
        if (SinglePivotArray[i+1] < SinglePivotArray[i]) {
            sorted = false;
            break;
        } else {
            sorted = true;
        }
    }
    if (sorted) {
        std::cout << "SinglePivotArray is sorted." << std::endl;
    } else {
        std::cout << "SinglePivotArray is not sorted." << std::endl;
    };

    // Print sum after
    sum = 0;
    for (int & i : SinglePivotArray) {
        sum += i;
    }
    std::cout << "Sum after sorting with single pivot:      " << sum << std::endl;

    // Print the sum before
    sum = 0;
    for (int & i : DualPivotArray) {
        sum += i;
    }
    std::cout << "Sum before sorting with dual pivot:       " << sum << std::endl;

    for (int i = 0; i < size-2; ++i) {
        if (DualPivotArray[i+1] < DualPivotArray[i]) {
            sorted = false;
            break;
        } else {
            sorted = true;
        }
    }
    if (sorted) {
        std::cout << "DualPivotArray is sorted." << std::endl;
    } else {
        std::cout << "DualPivotArray is not sorted." << std::endl;
    };

    auto start2 = std::chrono::high_resolution_clock::now();
    DualPivotQuickSort(DualPivotArray, 0, size - 1);
    auto end2 = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> DualPivotTime = end2-start2;

    for (int i = 0; i < size-2; ++i) {
        if (DualPivotArray[i+1] < DualPivotArray[i]) {
            sorted = false;
            break;
        } else {
            sorted = true;
        }
    }
    if (sorted) {
        std::cout << "DualPivotArray is sorted." << std::endl;
    } else {
        std::cout << "DualPivotArray is not sorted." << std::endl;
    };

    // Print the sum after
    sum = 0;
    for (int & i : DualPivotArray) {
        sum += i;
    }
    std::cout << "Sum after sorting with dual pivot:        " << sum << std::endl;

    std::cout << "\ntime used by single pivot:    " << SinglePivotTime.count() << std::endl;
    std::cout << "time used by dual pivot:      " << DualPivotTime.count() << std::endl;

    return 0;
}

// Single pivot quicksort
void SinglePivotQuickSort(int *arr, int low, int high)
{
    if (low < high)
    {
        int pivot = partition(arr, low, high);
        SinglePivotQuickSort(arr, low, pivot - 1);
        SinglePivotQuickSort(arr, pivot + 1, high);
    }
}

// Single pivot partitioning
int partition (int arr[], int low, int high)
{
    int pivot = arr[high]; // pivot
    int i = (low - 1); // Index of smaller element and indicates the right position of pivot found so far

    for (int j = low; j <= high - 1; j++)
    {
        // If current element is smaller than the pivot
        if (arr[j] < pivot)
        {
            i++; // increment index of smaller element
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

// Dual pivot quicksort
void DualPivotQuickSort(int* arr, int low, int high)
{
    if (low < high) {
        // lp means left pivot, and rp means right pivot.
        int lp, rp;
        rp = partition(arr, low, high, &lp);
        DualPivotQuickSort(arr, low, lp - 1);
        DualPivotQuickSort(arr, lp + 1, rp - 1);
        DualPivotQuickSort(arr, rp + 1, high);
    }
}

// Dual pivot partition
int partition(int* arr, int low, int high, int* lp)
{
    if (arr[low] > arr[high])
        swap(&arr[low], &arr[high]);

    // p is the left pivot, and q is the right pivot.
    int j = low + 1;
    int g = high - 1, k = low + 1, p = arr[low], q = arr[high];
    while (k <= g) {

        // if elements are less than the left pivot
        if (arr[k] < p) {
            swap(&arr[k], &arr[j]);
            j++;
        }

            // if elements are greater than or equal
            // to the right pivot
        else if (arr[k] >= q) {
            while (arr[g] > q && k < g)
                g--;
            swap(&arr[k], &arr[g]);
            g--;
            if (arr[k] < p) {
                swap(&arr[k], &arr[j]);
                j++;
            }
        }
        k++;
    }
    j--;
    g++;

    // bring pivots to their appropriate positions.
    swap(&arr[low], &arr[j]);
    swap(&arr[high], &arr[g]);

    // returning the indices of the pivots.
    *lp = j; // because we cannot return two elements
    // from a function.

    return g;
}

// Swap
void swap(int* a, int* b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}