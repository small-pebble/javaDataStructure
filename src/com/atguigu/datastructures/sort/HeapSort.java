package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/14
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class HeapSort {
    public static void main(String[] args) {
        //升序排列
        //int[] arr = {4, 6, 8, 5, 9,18,23,16,80,67,56,-1,0,-26,90};
        /*bigHeap(arr,1,5);
        System.out.println(Arrays.toString(arr));
        bigHeap(arr,0,5);
        System.out.println(Arrays.toString(arr));*/

        //System.out.println(Arrays.toString(arr));

        int[] arr = new int[2000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20000000);
        }
        long startTime = System.currentTimeMillis();
        heapSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    //堆排序
    /*public static void heapSort(int[] arr) {
        int len = arr.length;
        int temp;
        //建立大顶堆
        for (int i = (len - 2) / 2; i >= 0; i--) {
            bigHeap(arr, i, len);
        }
        //将堆顶元素与末尾元素交换，并从堆顶再次调整成大顶堆
        for (int index = len - 1; index > 0; index--) {
            temp = arr[index];
            arr[index] = arr[0];
            arr[0] = temp;
            bigHeap(arr, 0, index);
        }
    }*/

    public static void heapSort(int[] arr){
        int len = arr.length;
        //调整成大顶堆。从倒数第一个非叶子节点开始
        for(int cur = (len-2)/2;cur>=0;cur--){
            bigHeap(arr,cur,len);
        }
        //将大顶堆第一个元素与最后一个元素交换
        for(int adjustLen = len-1;adjustLen>0;adjustLen--){
            int temp = arr[0];
            arr[0] = arr[adjustLen];
            arr[adjustLen] = temp;
            bigHeap(arr,0,adjustLen);
        }
    }

    //将start的那棵树，调成大顶堆，并且其下面的树已经是大顶堆，所以只能从下往上排
    //start表示从第几个元素开始将其调为大顶堆
    //length表示将数组的前多少元素调为大顶堆
    public static void bigHeap(int[] arr, int cur, int length) {
        int temp = arr[cur];
        for (int i = cur * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                i++;
            }
            if (temp < arr[i]) {
                arr[cur] = arr[i];
                cur = i;
            }
            arr[cur] = temp;
        }
    }
    /*public static void bigHeap(int[] arr,int cur,int length){
        int temp = arr[cur];
        for(int i=cur*2+1;i<length;i = i*2+1){
            if(i+1<length&&arr[i]<arr[i+1]){
                i++;
            }
            if(temp<arr[i]){
                arr[cur] = arr[i];
                cur = i;
            }
        }
        arr[cur] = temp;
    }*/
}
