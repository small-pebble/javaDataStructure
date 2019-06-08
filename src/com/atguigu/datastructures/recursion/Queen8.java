package com.atguigu.datastructures.recursion;

/**
 * @Auther:smallPebble
 * @Date:2019/6/7
 * @Description:com.atguigu.datastructures.recursion
 * @version:1.0
 **/
public class Queen8 {
    //定义有几个皇后
    private static int num = 8;
    //定义放置皇后的数组
    private static int[] arr = new int[num];
    public static void main(String[] args) {
        check(0);
    }

    //放置num个皇后,n表示从第n个皇后开始放
    private static void check(int n){
        if(n==num){
            print();
            return;
        }
        for(int i=0;i<num;i++){
            arr[n] = i;
            if(judge(n)){
                check(n+1);
            }
        }
    }

    //放置第n个皇后时，检查其与前面的皇后是否冲突
    private static boolean judge(int n){
        for(int i=0;i<n;i++){
            if(arr[i]==arr[n]||Math.abs(arr[i]-arr[n])==Math.abs(i-n)){
                return false;
            }
        }
        return true;
    }

    //打印皇后的位置
    private static void print(){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
