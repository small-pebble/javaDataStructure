package com.atguigu.datastructures.search;

import java.util.Arrays;

/**
 * @Auther:smallPebble
 * @Date:2019/6/9
 * @Description:com.atguigu.datastructures.search
 * @version:1.0
 **/
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1,3,5,6,8,12,23,234,567,890,1000};
        int index = fibonacciSearch(arr, 0);
        System.out.println(index);
    }

    public static int[] fib(){
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for(int i=2;i<f.length;i++){
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    public static int fibonacciSearch(int[] arr,int val){
        int low = 0;
        int high = arr.length-1;
        int mid ;
        int k = 0;
        int[] fib = fib();
        //先扩数组的容
        while(high>fib[k]){
            k++;
        }
        int[] temp = Arrays.copyOf(arr,fib[k]);
        for(int i=high+1;i<temp.length;i++){
            temp[i] = temp[high];
        }
        //循环找到对应下标
        while(low<=high){
            mid = low+fib[k-1]-1;
            if(val<temp[mid]){
                high = mid-1;
                k-=2;
            }else if (val>temp[mid]){
                low = mid+1;
                k--;
            }else{
                if(mid>=arr.length){
                    return high;
                }else{
                    return mid;
                }
            }
        }
        return -1;
    }
}
