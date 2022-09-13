#include <algorithm>
#include "quicksort.h"
using namespace std;

int partition(int arr[], int low, int high)
{
    int i = low;
    int j = high;
    int pivot = arr[low];
    while (i < j)
    {
        while (pivot >= arr[i])
            i++;
        while (pivot < arr[j])
            j--;
        if (i < j)
            swap(arr[i], arr[j]);
    }
    swap(arr[low], arr[j]);
    return j;
}

void SinglePivotQuickSort(int arr[], int low, int high)
{
    if (low < high)
    {
        int pivot = partition(arr, low, high);
        SinglePivotQuickSort(arr, low, pivot - 1);
        SinglePivotQuickSort(arr, pivot + 1, high);
    }
}