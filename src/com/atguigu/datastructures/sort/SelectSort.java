package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/7
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {2,32,4,667,5,98,6,0,2,56,78,6,7,8,1};
        int min;
        int temp;
        for(int i=0;i<arr.length-1;i++){
            min = i;
            for(int j=i+1;j< arr.length;j++){
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
