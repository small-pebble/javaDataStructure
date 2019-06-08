package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/7
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class BubbleSort {
    public static void main(String[] args) {

        //int[] arr = {2,32,4,667,5,98,6,0,2,56,78,6,7,8,1};

        int[] arr = new int[2000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2000000);
        }
        long startTime = System.currentTimeMillis();
        bubbleSort(arr);
        //selectSort(arr);
        //InsertSort(arr);
        //shellSort(arr);
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

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

    /*public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int temp;
        int pivot = arr[right];
        while (l < r) {
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        arr[right] = arr[l];
        arr[l] = pivot;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }*/

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    public static void selectSort(int[] arr) {
        int min;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void InsertSort(int[] arr) {
        int temp;
        int index;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            index = i - 1;
            while (index >= 0 && arr[index] > temp) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[++index] = temp;
        }
    }

    public static void shellSort(int[] arr) {
        int temp;
        int index;
        for (int i = arr.length / 2; i >= 1; i /= 2) {
            for (int j = i; j < arr.length; j += i) {
                temp = arr[j];
                index = j - i;
                while (index >= 0 && arr[index] > temp) {
                    arr[index + i] = arr[index];
                    index -= i;
                }
                arr[index + i] = temp;
            }
        }
    }
}
