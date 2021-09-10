package tree;

public class Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        BinaryTree binaryTree = new BinaryTree(root);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
    }
}

class TreeNode {
    private int no;
    private TreeNode left;
    private TreeNode right;
    public TreeNode(int no) {
        this.no = no;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public TreeNode getLeft() {
        return left;
    }
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    public TreeNode getRight() {
        return right;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return "TreeNode [no=" + no + "]";
    }
    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    } 

    //中序遍历
    public void infixOrder() {
        if (this.left != null) this.left.infixOrder();
        System.out.println(this);
        if (this.right != null) this.right.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) this.left.postOrder();
        if (this.right != null) this.right.postOrder();
        System.out.println(this);
    }

    public TreeNode preOrderSearch(int no) {
        if (this.no == no) return this;
        TreeNode res = null;
        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }
        if (res != null) return res;
        if (this.right != null) {
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    public TreeNode infixOrderSearch(int no) {
        TreeNode res = null;
        if (this.left != null) {
            res = this.left.infixOrderSearch(no);
        }
        if (res != null) return res;
        if (this.no == no) return this;
        if (this.right != null) {
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    public TreeNode postOrderSearch(int no) {
        TreeNode res = null;
        if (this.left != null) res = this.left.postOrderSearch(no);
        if (res != null) return res;
        if (this.right != null) res = this.right.postOrderSearch(no);
        if (res != null) return res;
        if (this.no == no) return this;
        return res;
    }

    public void del(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) this.left.del(no);
        if (this.right != null) this.right.del(no);
    }

}

class BinaryTree {
    private TreeNode root;
    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
}
