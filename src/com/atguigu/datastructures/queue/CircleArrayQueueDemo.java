package com.atguigu.datastructures.queue;

import java.util.Scanner;

/**
 * @Auther:smallPebble
 * @Date:2019/6/2
 * @Description:com.atguigu.datastructures.queue
 * @version:1.0
 **/
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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

class CircleArrayQueue {
    private int[] data;
    private int maxSize;
    //指向第一个有数据的位置，默认值为0
    private int front;
    //指向最后一个有数据位置的后一个，默认值为0
    private int rear;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void addQueue(int e) {
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        data[rear] = e;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空，不能取数据");
        }
        int res = data[front];
        front = (front + 1) % maxSize;
        return res;
    }

    public int headQueue(){
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空，不能取数据");
        }
        return data[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，不能展示数据");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, data[i % maxSize]);
        }
    }

    public int size() {
        return (rear - front + maxSize) % maxSize;
    }
}
