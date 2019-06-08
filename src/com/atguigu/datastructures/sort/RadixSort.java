package com.atguigu.datastructures.sort;

/**
 * @Auther:smallPebble
 * @Date:2019/6/8
 * @Description:com.atguigu.datastructures.sort
 * @version:1.0
 **/
public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {2, 32, 667, 98, 6, 686};
        //int[] arr = {2, 32, 4, 667, 5, 98, 6, 0, 2, 56, 78, 6, 7, 8, 1};
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2000000);
        }
        long startTime = System.currentTimeMillis();
        radixSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        //System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //找到最大值的位数
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        //int maxLen = (max + "").length();
        //存放每次排列的桶
        int[][] bucket = new int[10][arr.length];
        //存放每个桶存放数字的数量
        int[] elementSum = new int[10];
        //一共要进行maxLen次循环
        for (int radix = 1; radix <= max; radix *= 10) {
            //把每个数放进对应的桶
            for (int num : arr) {
                int bucketNo = num / radix % 10;
                bucket[bucketNo][elementSum[bucketNo]] = num;
                elementSum[bucketNo]++;
            }
            //将每个桶中的数据拿出来
            int index = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < elementSum[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            //注意！！！
            //清空桶的所有的计数
            for(int i=0;i<elementSum.length;i++){
                elementSum[i] = 0;
            }
        }
    }
}
