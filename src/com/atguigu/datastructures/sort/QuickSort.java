package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/7
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2,32,4,667,5,98,6,0,2,56,78,6,7,8,1};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[right];
        int temp;
        while (l < r) {
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
        }
        arr[right] = arr[l];
        arr[l] = pivot;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);

    }
}
