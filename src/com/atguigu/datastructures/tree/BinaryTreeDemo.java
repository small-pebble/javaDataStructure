package com.atguigu.datastructures.tree;

/**
 * @Auther:smallPebble
 * @Date:2019/6/12
 * @Description:com.atguigu.datastructures.tree
 * @version:1.0
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        HeroNode n1 = new HeroNode(1, "宋江");
        HeroNode n2 = new HeroNode(2, "吴用");
        HeroNode n3 = new HeroNode(3, "卢俊义");
        HeroNode n4 = new HeroNode(4, "林冲");
        bt.setRoot(n1);
        n1.setLeft(n2);
        n1.setRight(n3);
        n3.setRight(n4);

        bt.preOrder();
        System.out.println("==============");
        /*bt.infixOrder();
        System.out.println("==============");
        bt.postOrder();
        System.out.println("==============");*/

        /*HeroNode heroNode = bt.postSearch(1);
        System.out.println(heroNode);*/

        bt.delNode(5);
        bt.preOrder();

    }
}

class BinaryTree {
    private HeroNode root;

    public void delNode(int id) {
        if (root != null && root.getNo() == id) {
            root = null;
            return;
        }
        root.delNode(id);

    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.postOrder();
    }

    //前序查找
    public HeroNode preSearch(int id) {
        if (root == null) {
            return null;
        }
        return root.preSearch(id);
    }

    //中序查找
    public HeroNode infixSearch(int id) {
        if (root == null) {
            return null;
        }
        return root.infixSearch(id);
    }

    //后序查找
    public HeroNode postSearch(int id) {
        if (root == null) {
            return null;
        }
        return root.postSearch(id);
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void delNode(int id) {
        if (this.left != null && this.left.no == id) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == id) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(id);
        }
        if (this.right != null) {
            this.right.delNode(id);
        }
    }

    //前序查找
    public HeroNode preSearch(int id) {
        if (this.no == id) {
            return this;
        }
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.preSearch(id);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.preSearch(id);
        }
        return res;
    }

    //中序查找
    public HeroNode infixSearch(int id) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.infixSearch(id);
        }
        if (res != null) {
            return res;
        }
        if (this.no == id) {
            return this;
        }
        if (this.right != null) {
            res = this.right.infixSearch(id);
        }
        return res;
    }

    //后序查找
    public HeroNode postSearch(int id) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.postSearch(id);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.postSearch(id);
        }
        if (this.no == id) {
            return this;
        }
        return res;
    }


    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
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

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
