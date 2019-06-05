package com.atguigu.datastructures.stack;

import java.util.Scanner;

/**
 * @Auther:smallPebble
 * @Date:2019/6/4
 * @Description:com.atguigu.datastructures.stack
 * @version:1.0
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack as = new ArrayStack(4);
        String key = "";
        boolean flag = true;
        Scanner sc = new Scanner(System.in);

        while (flag){
            System.out.println("输入a添加数据");
            System.out.println("输入d删除数据");
            System.out.println("输入s显示数据");
            System.out.println("输入e退出");
            System.out.println("=========================");
            key = sc.next();
            switch (key){
                case "a":
                    System.out.println("请输入要添加的数据");
                    int value = sc.nextInt();
                    as.push(value);
                    break;
                case "d":
                    as.pop();
                    break;
                case "s":
                    as.list();
                    break;
                case "e":
                    flag = false;
                    System.out.println("已退出系统");
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack {
    private int maxSize;
    private int top = -1;
    private int[] data;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int e) {
        if (isFull()) {
            System.out.println("栈满，不能添加数据");
            return;
        }
        data[++top] = e;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int res = data[top];
        top--;
        return res;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,data[i]);
        }
    }
}
