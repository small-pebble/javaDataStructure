package com.atguigu.datastructures.tree.threaded;

/**
 * @Auther:smallPebble
 * @Date:2019/6/13
 * @Description:com.atguigu.datastructures.tree.threaded
 * @version:1.0
 **/
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node3 = new HeroNode(3, "jack");
        HeroNode node6 = new HeroNode(6, "smith");
        HeroNode node8 = new HeroNode(8, "mary");
        HeroNode node10 = new HeroNode(10, "king");
        HeroNode node14 = new HeroNode(14, "dim");
        bt.setRoot(root);

        root.setLeft(node3);
        root.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);

        bt.infixOrder();
        System.out.println("=====================");
        //bt.infixThreaded();
        //bt.inThreadedList();
        bt.preThreaded();
        bt.preThreadedList();
    }
}

class BinaryTree {
    private HeroNode root;

    HeroNode pre = null;

    //前序线索化二叉树
    public void preThreaded(HeroNode node) {
        if (node != null && node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node != null && node.getLeftType() ==0) {
            preThreaded(node.getLeft());
        }
        if (node != null && node.getRightType() ==0) {
            preThreaded(node.getRight());
        }
    }

    public void preThreaded() {
        if (root == null) {
            System.out.println("二叉树为空，不能线索化");
            return;
        }
        preThreaded(root);
    }
    //遍历前序线索化二叉树
    public void preThreadedList(HeroNode node ){
        while(node != null) {

            while(node.getLeftType()==0) {
                System.out.print(node);
                node = node.getLeft();
            }

            System.out.print(node);
            node = node.getRight();
        }

    }
    public void preThreadedList(){
        preThreadedList(root);
    }

    //中序化线索二叉树
    //node代表当前要线索化的节点
    public void infixThreaded(HeroNode node) {

        if (node != null && node.getLeft() != null) {
            infixThreaded(node.getLeft());
        }

        if (node != null && node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        if (node != null && node.getRight() != null) {
            infixThreaded(node.getRight());
        }

    }

    //中序化线索二叉树
    public void infixThreaded() {
        if (root == null) {
            System.out.println("二叉树为空，不能线索化");
            return;
        }
        infixThreaded(root);
    }

    //线索化遍历二叉树
    public void inThreadedList() {
        HeroNode curNode = root;
        while (curNode != null) {
            while (curNode.getLeftType() == 0) {
                curNode = curNode.getLeft();
            }
            System.out.println(curNode);
            while (curNode.getRightType() == 1) {
                System.out.println(curNode.getRight());
                curNode = curNode.getRight();
            }
            curNode = curNode.getRight();
        }
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


    //删除节点
    public void delNode(int id) {
        if (root != null && root.getNo() == id) {
            root = null;
            return;
        }
        root.delNode(id);

    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //标志左右指针指向的类型，0代表左子树和右子树，1代表前驱和后继节点
    private int leftType;
    private int rightType;


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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

