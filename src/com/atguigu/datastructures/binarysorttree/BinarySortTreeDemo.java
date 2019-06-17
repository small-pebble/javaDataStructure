package com.atguigu.datastructures.binarysorttree;

/**
 * @Auther:smallPebble
 * @Date:2019/6/17
 * @Description:com.atguigu.datastructures.binarysorttree
 * @version:1.0
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree bst = new BinarySortTree();
        for (int i : arr) {
            Node node = new Node(i);
            bst.add(node);
        }

        /*bst.delete(1);
        bst.delete(5);
        bst.delete(9);
        bst.delete(12);
        bst.delete(3);
        bst.delete(10);
        bst.delete(7);*/
        /*bst.delete(12);
        bst.delete(10);*/
        bst.delete(7);
       /* bst.delete(1);
        bst.delete(3);
        bst.delete(5);
        bst.delete(7);
        bst.delete(12);*/

        bst.infixOrder();
    }
}

class BinarySortTree {
    Node root;

    //删除以node为根节点的最小值，并返回这个值
    public int delRightTreeMin(Node node){
        Node target = node;
        if(target.left!=null){
            target = target.left;
        }
        delete(target.value);
        return target.value;
    }

    public void delete(int value) {
        if (root == null) {
            return;
        }
        Node targetNode = root.searchNode(value);
        //二叉树中没有该节点，直接返回
        if (targetNode == null) {
            return;
        }
        //二叉树中有该节点，如果该节点为根节点，并且只有根节点，直接将根节点至为空
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        Node parent = root.searchParent(value);
        //如果要删除的节点为叶子节点
        if (parent != null && targetNode.left == null && targetNode.right == null) {
            //要删除的几点为父节点的左节点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) { //删除有左右节点的节点
            int min = delRightTreeMin(targetNode.right);
            targetNode.value = min;
        } else {  //删除只有一个孩子节点的节点
            //单独处理删除根节点的情况
            if(parent==null){
                if (targetNode.left!=null){
                    root = targetNode.left;
                    return;
                }else {
                    root = targetNode.right;
                    return;
                }
            }
            //如果要删除的节点有左子节点
            if(targetNode.left!=null){
                //要删除的节点为父节点的左子节点
                if(parent!=null&&parent.left!=null&&parent.left.value==value){
                    parent.left = targetNode.left;
                //要删除的节点为父节点的右子节点
                }else if(parent!=null && parent.right!=null&&parent.right.value==value){
                    parent.right = targetNode.left;
                }
            }else{
                //要删除的节点为父节点的左子节点
                if(parent!=null&&parent.left!=null&&parent.left.value==value){
                    parent.left = targetNode.right;
                //要删除的节点为父节点的右子节点
                }else if(parent!=null && parent.right!=null&&parent.right.value==value){
                    parent.right = targetNode.right;
                }
            }
        }
    }

    public Node searchNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchNode(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("根节点为空，不能遍历");
            return;
        }
        root.infixOrder();
    }


}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点
    public Node searchNode(int value) {
        if (this.value == value) {
            return this;
        }
        if (value < this.value) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.searchNode(value);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.searchNode(value);
            }
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        //根节点为要删除的节点在树中单独考虑
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    public void add(Node node) {
        if (node == null) {
            System.out.println("插入节点不能为空");
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}