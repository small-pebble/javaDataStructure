package com.atguigu.datastructures.queue;

import java.util.Scanner;

/**
 * @Auther:smallPebble
 * @Date:2019/5/30
 * @Description:com.atguigu.datastructures.queue
 * @version:1.0
 **/
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop = true;
        Scanner s = new Scanner(System.in);
        char key;
        while (loop) {
            System.out.println("输入a(add):添加一个数");
            System.out.println("输入g(get):取出一个数");
            System.out.println("输入s(show):显示所有数");
            System.out.println("输入h(add):显示队首元素");
            System.out.println("输入e(exit):退出操作");
            key = s.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("请输入您要添加的数");
                    int e = s.nextInt();
                    queue.addQueue(e);
                    break;
                case 'g':
                    System.out.println(queue.getQueue());
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'h':
                    System.out.println(queue.headQueue());
                    break;
                case 'e':
                    loop = false;
                    s.close();
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出");
    }
}

class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] data;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        front = -1; //指向队首不含元素的位置
        rear = -1;  //指向队尾有元素的位置

    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int e) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        data[++rear] = e;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        return data[++front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < data.length; i++) {
            System.out.printf("data[%d]=%d\n", i, data[i]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return data[front + 1];
    }
}