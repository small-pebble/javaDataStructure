package com.atguigu.datastructures.linkedList;

import java.util.Stack;

/**
 * @Auther:smallPebble
 * @Date:2019/6/2
 * @Description:com.atguigu.datastructures.linkedList
 * @version:1.0
 **/
public class SingleLinkedListDemo {

    public static HeroNode mergeLinkedList(HeroNode head1,HeroNode head2){
        if(head1.next==null){
            return head2;
        }
        if(head2.next==null){
            return head1;
        }

        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;
        HeroNode newHead = new HeroNode(0,"","");
        HeroNode cur = newHead;
        while(getLength(head1)!=0 && getLength(head2)!=0){
            if(temp1.no<temp2.no){
                cur.next = temp1;
                head1.next = temp1.next;
                temp1 = temp1.next;
                cur = cur.next;
            }else{
                cur.next = temp2;
                head2.next = temp2.next;
                temp2 = temp2.next;
                cur = cur.next;
            }
        }
        if(getLength(head1)!=0){
            cur.next = head1.next;
        }
        if(getLength(head2)!=0){
            cur.next = head2.next;
        }
        return newHead;
    }

    public static void reversePrint(SingleLinkedList sl){
        HeroNode head = sl.getHead();
        HeroNode temp = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while(temp!=null){
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //反转链表
    public static HeroNode reverseLinkedList(HeroNode head){
        if(head==null||head.next==null){
            return head;
        }
        HeroNode thisNode = head.next;
        HeroNode nextNode = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        while(thisNode!=null){
            nextNode = thisNode.next;
            thisNode.next = reverseHead.next;
            reverseHead.next = thisNode;
            thisNode = nextNode;
        }
        return reverseHead;
    }

    //返回倒数lastIndex个节点，从1开始数
    public static HeroNode getLastIndexNode(HeroNode head,int lastIndex){
        int length = getLength(head);
        int index = length-lastIndex+1;
        if(index<1 || index>length){
            throw new IllegalArgumentException("传入参数有误");
        }
        while(index>0){
            index--;
            head = head.next;
        }
        return head;
    }

    public static int getLength(HeroNode head){
        HeroNode temp = head;
        int length = 0;
        while(temp.next!=null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        HeroNode hero5 = new HeroNode(5,"dada","dede");

        SingleLinkedList sl = new SingleLinkedList();
        sl.add(hero1);
        sl.addByOrder(hero4);
        sl.list();

        SingleLinkedList s2 = new SingleLinkedList();
        s2.addByOrder(hero3);
        s2.addByOrder(hero2);
        s2.addByOrder(hero5);
        s2.list();
        System.out.println("============");

        HeroNode newHead = mergeLinkedList(sl.getHead(), s2.getHead());
        while(newHead.next!=null){
            System.out.println(newHead.next);
            newHead = newHead.next;
        }
        //System.out.println(getLength(sl.getHead()));
        //System.out.println(getLastIndexNode(sl.getHead(),2));
        /*HeroNode reverseHead = reverseLinkedList(sl.getHead());
        while(reverseHead.next!=null){
            System.out.println(reverseHead.next);
            reverseHead = reverseHead.next;
        }*/
        //reversePrint(sl);
    }
}

class SingleLinkedList{

    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead(){
        return head;
    }

    public int getLength(){
        HeroNode temp = head;
        int length = 0;
        while(temp.next!=null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while(temp.next!=null){
            if(temp.next.no>heroNode.no){
                break;
            }
            if(temp.next.no==heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("排名为%d的英雄已存在",heroNode.no);
            System.out.println();
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode){
        HeroNode temp = head;
        boolean flag = false;
        while(temp.next!=null){
            if(temp.next.no==newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next.name = newHeroNode.name;
            temp.next.nickName = newHeroNode.nickName;
        }else{
            System.out.printf("不存在排名为%d的英雄",newHeroNode.no);
            System.out.println();
        }
    }

    public void delete(int no){
        if(head.next==null){
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while(temp.next!=null){
            if(temp.next.no==no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("不存在排名为%d的英雄",no);
            System.out.println();
        }
    }

    public void list(){
        HeroNode temp = head.next;
        if(temp==null){
            System.out.println("链表为空");
            return;
        }
        while(temp!=null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}