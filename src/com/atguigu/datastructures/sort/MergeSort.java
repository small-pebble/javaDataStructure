package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/7
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {2, 32, 4, 667, 5, 98, 6, 0, 2, 56, 78, 6, 7, 8, 1,63,80,0,9,8,7,1,2,3,4,5};
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2000000);
        }
        int[] temp = new int[arr.length];
        long startTime = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        /*for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }*/
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if(left>=right){
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        if(left>=right){
            return;
        }
        int l = left;
        int r = mid+1;
        int index = left;
        while (l <= mid && r <= right) {
            if(arr[l]<arr[r]){
                temp[index] = arr[l];
                l++;
                index++;
            }else{
                temp[index] = arr[r];
                r++;
                index++;
            }
        }
        while(l<=mid){
            temp[index] = arr[l];
            l++;
            index++;
        }
        while(r<=right){
            temp[index] = arr[r];
            r++;
            index++;
        }
        for(int i=left;i<index;i++){
            arr[i] = temp[i];
        }
    }
}
