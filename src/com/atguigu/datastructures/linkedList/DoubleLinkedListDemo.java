package com.atguigu.datastructures.linkedList;

/**
 * @Auther:smallPebble
 * @Date:2019/6/2
 * @Description:com.atguigu.datastructures.linkedList
 * @version:1.0
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");
        //HeroNode2 hero5 = new HeroNode2(5,"dada","dede");
        DoubelLinkedList dl = new DoubelLinkedList();
        dl.add(hero1);
        dl.add(hero2);
        dl.add(hero3);
        dl.add(hero4);
        dl.list();
        System.out.println("======================");
        HeroNode2 hero5 = new HeroNode2(3,"公孙胜","入云龙");
        dl.update(hero5);
        dl.list();

    }
}


class DoubelLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");


    public HeroNode2 getHead() {
        return head;
    }

    public void list() {
        HeroNode2 temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode2 temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            if (temp.next != null) {
                temp.next = temp.next.next;
                temp.next.pre = temp;
            }
        } else {
            System.out.printf("不存在排名为%d的英雄", no);
            System.out.println();
        }
    }

    public void update(HeroNode2 newHeroNode){
        HeroNode2 temp = head;
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
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}