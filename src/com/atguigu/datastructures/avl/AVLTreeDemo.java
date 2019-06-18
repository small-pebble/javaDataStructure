package com.atguigu.datastructures.avl;


/**
 * @Auther:smallPebble
 * @Date:2019/6/17
 * @Description:com.atguigu.datastructures.avl
 * @version:1.0
 **/
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        //int[] arr = {1,2,3};
        AVLTree avl = new AVLTree();
        for (int i : arr) {
            Node node = new Node(i);
            avl.add(node);
        }
        avl.infixOrder();
        System.out.println(avl.root.height());
        System.out.println(avl.root.leftHeight());
        System.out.println(avl.root.rightHeight());
        System.out.println(avl.root.right.left);
    }
}

class AVLTree {
    Node root;

    public Node getRoot() {
        return root;
    }

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
    //左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return this.left.height();
    }
    //右子树的高度
    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return this.right.height();
    }

    public int height(){
        return Math.max(left==null?0:this.left.height(),right==null?0:this.right.height())+1;
    }

    private void leftRotate(){
        //需要保证根节点不变，按以下步骤
        //创建一个与该节点相同的新节点
        Node newNode = new Node(this.value);
        newNode.left = this.left;
        this.left = newNode;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
    }

    private void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.right = newNode;
        this.value = this.left.value;
        this.left = this.left.left;
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
        //进行左旋转
        if(rightHeight()-leftHeight()>1){
            //如果右子树的左子树高度大于右子树的右子树
            if(right.leftHeight()>right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        //进行右旋转
        if(leftHeight()-rightHeight()>1){
            //如果左子树的右子树高度大于左子树的左子树
            if(left.rightHeight()>left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
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
