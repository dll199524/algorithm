package tree;



public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
        BinarySearchTree bTree = new BinarySearchTree();
        for (int i = 0; i < arr.length; i++) {
            bTree.add(new Node(arr[i]));
        }
         // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        bTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

 
        bTree.delNode(5);
        bTree.delNode(10);

        System.out.println("删除结点后");
        bTree.infixOrder();
    }

}


class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }
    //中序遍历
    public void infixOrder() {
        if (this.left != null) this.left.infixOrder();
        System.out.println(this);
        if (this.right !=  null) this.right.infixOrder();
    }
    public void add(Node node) {
        if (node == null) return;
        if (node.value < this.value) {
            if (this.left == null) this.left = node;
            else this.left.add(node);
        }else{
            if (this.right == null) this.right = node;
            else this.right.add(node);
        }
    }
    //查找要删除的节点
    public Node search(int value) {
        if (this.value == value) return this;
        else if (this.value < value) {
            if (this.right == null) return null;
            else return this.right.search(value);
        } else {
            if (this.left == null) return null;
            else return this.left.search(value);
        }
    }
    //查找要删除的父节点
    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) return this;
        else {
            if (value < this.value && this.left != null) return this.left.searchParent(value);
            else if (value > this.value && this.right != null) return this.right.searchParent(value);
            else return null;
        } 
    }
}

class BinarySearchTree {
    Node root;
    public Node getRoot() {return root;}
    public void add(Node node) {
        if (root == null) root = node;
        else root.add(node);
    }
    public void infixOrder() {
        if (root != null) root.infixOrder();
        else System.out.println("二叉排序树为空不能遍历");
    }
    public Node search(int value) {
        if (root == null) return null;
        else return root.search(value);
    }
    public Node searchParent(int value) {
        if (root == null) return null;
        return root.searchParent(value);
    }
    public void delNode(int value) {
        if (root == null) return;
        else {
            Node target = search(value);
            if (target == null) return;
            if (root.left  == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            if (target.left == null && target.right == null) {
                if (parent.left != null && parent.left.value == value) parent.left = null;
                else if (parent.right != null && parent.right.value == value) parent.right = null;
            } else if (target.left != null && target.right != null) {
                int rightMin = delRightTreeMin(target.right);
                target.value = rightMin;
            } else {
                if (target.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = target.left;
                        } else {
                            parent.right = target.left;
                        }
                    } else {
                        root = target.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = target.right;
                        } else {
                            parent.right = target.right;
                        }
                    } else {
                        root = target.right;
                    }
                }
            }
        }
    }
    private int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) target = target.left;
        delNode(target.value);
        return target.value;
    }

}
