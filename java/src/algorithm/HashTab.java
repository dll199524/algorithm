package algorithm;

public class HashTab {
    public static void main(String[] args) {
        
    }
}

class EmpNode {
    public int id;
    public String name;
    public EmpNode next;
    public EmpNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkdeList {
    public EmpNode head;
    public void add(EmpNode node) {
        if (head == null) {
            head = node;
            return;
        }
        EmpNode cur = head;
        while (true) {
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        cur.next = node;
    }
    public void list(int no) {
        if (head == null) System.out.println("第" + (no + 1) + "链表为空");
        EmpNode cur = head;
        while (true) {
            System.out.printf(" => id = %d, name = %d", cur.id, cur.name);
            if (cur.next == null) break;
            cur = cur.next;
        }
        System.out.println();
    }
    public EmpNode findEmpByid(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        } 
        EmpNode cur = head;
        while (true) {
            if (cur.id == id) break;
            cur = cur.next;
            if (cur.next == null) break;
        }
        return cur;
    }
}

class HashEmp {
    private EmpLinkdeList[] empLinkdeLists;
    private int size;
    public HashEmp(int size) {
        this.size = size;
        empLinkdeLists = new EmpLinkdeList[size];
        for (int i = 0; i < size; i++) {
            empLinkdeLists[i] = new EmpLinkdeList();
        }
    }
    public void add(EmpNode node) {
        int no = hashFun(node.id);
        empLinkdeLists[no].add(node);
    }
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkdeLists[i].list(i);
        }
    }
    public void findEmpByid(int id) {
        int no = hashFun(id);
        EmpNode node = empLinkdeLists[no].findEmpByid(id);
        if (node != null) {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (no + 1), id);
        } else {
            System.out.println("哈希表中找不到该雇员");
        }
    }
    private int hashFun(int id) {
        return id % size;
    }
}
