package singleLinkedList;

public class doubleMain {
    public static void main(String[] args) {
        NodeDouble node1 = new NodeDouble(1, "宋江");
        NodeDouble node2 = new NodeDouble(2, "鲁智深");
        NodeDouble node3 = new NodeDouble(3, "吴用");
        NodeDouble node4 = new NodeDouble(4, "林冲");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();
    }
}

class NodeDouble {
    int no;
    String name;
    NodeDouble next;
    NodeDouble pre;
    public NodeDouble(int no, String name) {
        this.no = no;
        this.name = name;
    }
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }
}

class DoubleLinkedList {

    private NodeDouble head = new NodeDouble(0, "");
    public NodeDouble getHead() {return head;}
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        NodeDouble temp = head.next;
        while (true) {
            if (temp == null) break;
            System.out.println(temp);
            temp = temp.next;
        }
    }
    //添加到最后
    public void add(NodeDouble node) {
        NodeDouble temp = head;
        while (true) {
            if (temp.next == null) break;
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }
    //按顺序插入
    public void addByOrder(NodeDouble node) {
        NodeDouble temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) break;
            if (temp.next.no > node.no) break;
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", node.no);
        } else {
            node.next = temp.next;
            if (temp.next != null) temp.next.pre = node;
            temp.next = node;
            node.pre = temp;
        }
    }

    public void updateNode(NodeDouble node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        NodeDouble temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) break;
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
        } else {
            System.out.println("不存在该节点无法更新");
        }
    }

    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空无法删除");
        }
        NodeDouble temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) break;
            if (temp.no == no) {
                flag = true;
                break;
            } 
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("不存在该节点无法删除");
        }
    }

    


}
