package com.atguigu.datastructures.search;

import java.util.Arrays;

/**
 * @Auther:smallPebble
 * @Date:2019/6/8
 * @Description:com.atguigu.datastructures.search
 * @version:1.0
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 32, 4, 667, 667, 667, 5, 98, 6, 0, 56, 78, 6, 7, 8, 1, 7, 7,0};
        Arrays.sort(arr);
        int[] ints = binarySearch2(arr, 0, arr.length - 1, 667);
        System.out.println(Arrays.toString(ints));
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

    public static int[] binarySearch2(int[] arr, int left, int right, int val) {
        int[] res = {-1, -1};
        if (left > right) {
            return res;
        }
        int mid = (left + right) / 2;
        if (val < arr[mid]) {
            return binarySearch2(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return binarySearch2(arr, mid + 1, right, val);
        } else {
            int temp = mid;

            while (temp - 1 > 0 && arr[temp - 1] == val) {
                temp--;
            }
            res[0] = temp;
            if(arr[0]==val){
                res[0] = 0;
            }
            temp = mid ;
            while (temp+1 < arr.length - 1 && arr[temp+1] == val) {
                temp++;
            }
            res[1] = temp;
            if(arr[arr.length-1]==val){
                res[1] =arr.length-1;
            }
            return res;
        }
    }
}
