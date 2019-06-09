package com.atguigu.datastructures.search;

/**
 * @Auther:smallPebble
 * @Date:2019/6/9
 * @Description:com.atguigu.datastructures.search
 * @version:1.0
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = i + 1;
        }
        long start = System.currentTimeMillis();
        //int index = insertValueSearch(arr, 0, arr.length - 1, 66);
        int index = binarySearch(arr, 0, arr.length - 1, 66);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        //System.out.println(index);
    }

    private static int insertValueSearch(int[] arr, int left, int right, int val) {
        if (left > right || val < arr[0] || val > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left+(right-left)*(val-arr[left])/(arr[right]-arr[left]);
        if(val<arr[mid]){
            return insertValueSearch(arr,left,mid-1,val);
        }else if(val>arr[mid]){
            return insertValueSearch(arr,mid+1,right,val);
        }else{
            return mid;
        }
    }

    public static int binarySearch(int[] arr, int left, int right, int val) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (val < arr[mid]) {
            return binarySearch(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return binarySearch(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }
}
