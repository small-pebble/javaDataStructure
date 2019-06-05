package com.atguigu.datastructures.linkedList;

/**
 * @Auther:smallPebble
 * @Date:2019/6/4
 * @Description:com.atguigu.datastructures.linkedList
 * @version:1.0
 **/
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList cs = new CircleSingleLinkedList();
        cs.addBoys(125);
        cs.showBoys();
        System.out.println("===============================");
        cs.countBoy(10,20);
    }
}

class CircleSingleLinkedList {
    private Boy first = null;

    public void addBoys(int nums) {
        if (nums < 1) {
            System.out.println("小孩数量不能低于1个");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                cur = first;
                first.setNext(first);
            } else {
                cur.setNext(boy);
                cur = boy;
                cur.setNext(first);
            }
        }
    }

    public void showBoys() {
        if (first == null) {
            System.out.println("目前没有小孩");
            return;
        }

        System.out.printf("这个小孩编号为%d\n", first.getNo());
        Boy cur = first.getNext();
        while (cur != first) {
            System.out.printf("这个小孩编号为%d\n", cur.getNo());
            cur = cur.getNext();
        }
    }
    //startNo   代表从第几个小孩开始数数
    //countNum  代表数到第几个小孩出圈
    //nums      代表一共有多少小孩
    public void countBoy(int startNo,int countNum){
        if(first==null||startNo<1||countNum<1){
            System.out.println("输入参数有误");
            return;
        }
        //定义一个辅助指针，将其指向最后一个
        Boy helper = first;
        while (helper.getNext()!=first){
            helper = helper.getNext();
        }
        //将first指针移动到startNo处
        for(int i=0;i<startNo-1;i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //小朋友开始出圈
        while (first.getNext()!=first){
            for(int i=0;i<countNum-1;i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("第%d个小朋友出圈了\n",first.getNo());
            helper.setNext(first.getNext());
            first = helper.getNext();
        }
        System.out.printf("第%d个小朋友最后一个出圈\n",first.getNo());

    }
}

class Boy {
    private int no;
    private Boy next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int no) {
        this.no = no;
    }
}
