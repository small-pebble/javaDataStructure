package com.atguigu.datastructures.hashtable;

import java.util.Scanner;

/**
 * @Auther:smallPebble
 * @Date:2019/6/12
 * @Description:com.atguigu.datastructures.hashtable
 * @version:1.0
 **/
public class HashTableDemo {
    public static void main(String[] args) {
        EmpHashTable empHashTable = new EmpHashTable(7);
        Scanner sc = new Scanner(System.in);
        int id;
        String name;
        while (true) {
            System.out.println("输入list，查看员工");
            System.out.println("输入add，添加员工");
            System.out.println("输入delete，删除员工");
            System.out.println("输入find，查找员工");
            System.out.println("输入exit，退出操作");
            String input = sc.next();
            switch (input) {
                case "list":
                    empHashTable.list();
                    break;
                case "add":
                    System.out.println("请输入用户id");
                    id = sc.nextInt();
                    System.out.println("请输入用户名称");
                    name = sc.next();
                    Emp emp = new Emp(id, name);
                    empHashTable.add(emp);
                    break;
                case "delete":
                    System.out.println("请输入要删除的员工编号");
                    id = sc.nextInt();
                    empHashTable.delete(id);
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = sc.nextInt();
                    empHashTable.find(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入参数不合法");
                    break;
            }
        }
    }
}

class EmpHashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public EmpHashTable(int size) {
        empLinkedLists = new EmpLinkedList[size];
        this.size = size;
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int hashNo = getHashNo(emp.id);
        empLinkedLists[hashNo].add(emp);

    }

    public void delete(int id){
        int hashNo = getHashNo(id);
        empLinkedLists[hashNo].delete(id);
    }

    public void find(int id){
        int hashNo = getHashNo(id);
        Emp res = empLinkedLists[hashNo].find(id);
        if(res==null){
            System.out.println("该哈希表中不存在该id");
        }else {
            System.out.printf("第%d条链表中存在id为%d的员工\n",hashNo,id);
        }
    }

    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
        System.out.println();
    }

    private int getHashNo(int id) {
        return id % size;
    }

}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp e) {
        if (head == null) {
            head = e;
            return;
        }
        Emp cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = e;
    }

    public void delete(int id){
        if(head==null){
            System.out.println("该链表不存在该id，删除失败");
            return;
        }
        if(head.id==id){
            head = head.next;
            return;
        }
        Emp pre = head;
        while(pre.next!=null){
            if(pre.next.id==id){
                pre.next = pre.next.next;
                return;
            }
            pre = pre.next;
        }
    }

    public Emp find(int id){
        Emp res = null;
        if(head==null){
            return res;
        }
        Emp cur = head;
        while(cur!=null){
            if(cur.id==id){
                res = cur;
                break;
            }
            cur = cur.next;
        }
        return res;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为空");
            return;
        }
        Emp cur = head;
        while (cur != null) {
            System.out.print(cur+" -> ");
            cur = cur.next;
        }
        System.out.println("null");
    }
}
