package com.atguigu.datastructures.tree;

/**
 * @Auther:smallPebble
 * @Date:2019/6/13
 * @Description:com.atguigu.datastructures.tree
 * @version:1.0
 **/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        ArrBinaryTree abt = new ArrBinaryTree(arr);
        abt.postOrder();
    }
}

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历
    public void preOrder(int index){
        if(index<arr.length){
            System.out.println(arr[index]);
        }
        if(2*index+1<arr.length){
            preOrder(2*index+1);
        }
        if(2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }

    public void preOrder(){
        preOrder(0);
    }

    //中序遍历
    public void infixOrder(int index){
        if(index*2+1<arr.length){
            infixOrder(index*2+1);
        }
        if(index<arr.length){
            System.out.println(arr[index]);
        }
        if(index*2+2<arr.length){
            infixOrder(index*2+2);
        }
    }
    public void infixOrder(){
        infixOrder(0);
    }
    //后序遍历
    public void postOrder(int index){
        if(index*2+1<arr.length){
            postOrder(index*2+1);
        }
        if(index*2+2<arr.length){
            postOrder(index*2+2);
        }
        if(index<arr.length){
            System.out.println(arr[index]);
        }
    }
    public void postOrder(){
        postOrder(0);
    }
}
