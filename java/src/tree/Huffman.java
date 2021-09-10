package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Huffman {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 9, 4, 8, 11};
        HuffmanNode root = createHuffmanTree(arr);
        root.preOrder();
    }

    public static void preOrder(HuffmanNode root) {
        if (root != null) root.preOrder();
        else System.out.println("是空树，不能遍历");
    }

    public static HuffmanNode createHuffmanTree(int[] arr) {
        List<HuffmanNode> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new HuffmanNode(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parent = new HuffmanNode(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    
}

class HuffmanNode implements Comparable<HuffmanNode> {

    int value;
    HuffmanNode left;
    HuffmanNode right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }

    public HuffmanNode(int value) {this.value = value;}
    
    @Override
    public String toString() {
        return "HuffmanNode [value=" + value + "]";
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }

}
