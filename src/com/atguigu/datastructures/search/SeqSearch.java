package com.atguigu.datastructures.search;

/**
 * @Auther:smallPebble
 * @Date:2019/6/8
 * @Description:com.atguigu.datastructures.search
 * @version:1.0
 **/
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {2,32,4,667,5,98,6,0,2,56,78,6,7,8,1};
        System.out.println(seqSearch(arr,100));
    }

    //查找对应数值的下标，如果没有，返回-1
    public static int seqSearch(int[] arr,int val){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==val){
                return i;
            }
        }
        return -1;
    }
}
