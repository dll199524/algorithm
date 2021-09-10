package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    public static void main(String[] args) {
        
    }
}

class LNode {
    public int key;
    public int val;
    LNode pre, next;
    public LNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LDoubleLinkedList {

    private LNode head, tail;
    private int size;

    public LDoubleLinkedList() {
        head = new LNode(0, 0);
        tail = new LNode(0, 0);
        head.next = tail;
        tail.pre  = head;
        size = 0;
    }

    public void addLast(LNode node) {
        node.pre = tail.pre;
        node.next = tail;
        tail.pre.next = node;
        tail.pre = node;
        size++;
    }

    public void addFirst(LNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        size++;  
    }

    public void remove(LNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public LNode removeFirst() {
        if (head.next == null) return null;
        LNode first = head.next;
        remove(first);
        size--;
        return first;
    }

    public LNode removeLast() {
        if (tail.pre == null) return null;
        LNode last = tail.pre;
        remove(last);
        size--;
        return last;
    }

    public int size() {
        return size;
    }
}

class LRUCache {
    private Map<Integer, LNode> map;
    private LDoubleLinkedList cache;
    private int cap;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        cache = new LDoubleLinkedList();
        cap = capacity;
    }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        int val = map.get(key).val;
        //把节点提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        LNode node = new LNode(key, val);
        if (map.containsKey(key)) {
            cache.remove(node);
            cache.addFirst(node);
            map.put(key, node);
        } else {
            if (map.size() == cap) {
                LNode last = cache.removeLast();
                map.remove(last.key);
            } 
            cache.addFirst(node);
            map.put(key, node);
        }
    }

}


