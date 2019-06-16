package com.atguigu.datastructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Auther:smallPebble
 * @Date:2019/6/16
 * @Description:com.atguigu.datastructures.huffmantree
 * @version:1.0
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 3, 6, 0, 4, 9};
        ArrayList<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }

        Node root = huffmanTree(list);

        preOrder(root);
    }

    public static void preOrder(Node node){
        if(node==null){
            System.out.println("此哈夫曼树为空");
            return ;
        }else {
            System.out.println(node);
            if(node.left!=null){
                preOrder(node.left);
            }
            if (node.right!=null){
                preOrder(node.right);
            }
        }
    }

    public static Node huffmanTree(ArrayList<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(leftNode.value+rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

}


class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
