#ifndef QUICKSORT_QUICKSORT_H
#define QUICKSORT_QUICKSORT_H

#endif //QUICKSORT_QUICKSORT_H

void SinglePivotQuickSort(int arr[], int low, int high);
void DualPivotQuickSort(int* arr, int low, int high);
int partition(int *arr, int low, int high);
int partition(int* arr, int low, int high, int* lp);
void swap(int* a, int* b);
