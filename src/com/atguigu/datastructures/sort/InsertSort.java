package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/7
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {2, 32, 4, 667, 5, 98, 6, 0, 2, 56, 78, 6, 7, 8, 1};
        int temp;
        int index;
        for(int i=1;i<arr.length;i++){
            temp = arr[i];
            index = i-1;
            while(index>=0&&arr[index]>temp){
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = temp;
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
