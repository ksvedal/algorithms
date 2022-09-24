#ifndef QUICKSORT_QUICKSORT_H
#define QUICKSORT_QUICKSORT_H

#endif //QUICKSORT_QUICKSORT_H

void singlePivotQuickSort(int arr[], int low, int high);
void dualPivotQuickSort(int* arr, int low, int high);
int partition(int *arr, int low, int high);
int partition(int* arr, int low, int high, int* lp);
int median3sort(int* arr, int low, int high);
void swap(int* a, int* b);
