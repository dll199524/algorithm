package singleLinkedList;

import java.util.Stack;

public class singleMain {
    public static void main(String[] args) {
		// 进行测试
		// 先创建节点
		Node hero1 = new Node(1, "宋江");
		Node hero2 = new Node(2, "卢俊义");
		Node hero3 = new Node(3, "吴用");
		Node hero4 = new Node(4, "林冲");
		Node hero5 = new Node(4, "hah");


		// 创建要给链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();

		// 加入
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.update(hero5);
//		singleLinkedList.delete(2);
			
		singleLinkedList.list();
//		System.out.println(getLength(singleLinkedList.getHead()));
//		Node res = findLastIndexNode(singleLinkedList.getHead(), 1);
//		System.out.println(res);
//		System.out.println();
//		reverseNode(singleLinkedList.getHead());
		reserverPrint(singleLinkedList.getHead());
//		singleLinkedList.list();
	}
    public static int getLength(Node head)
	{
		if(head.next == null) return 0;
		Node temp = head;
		int length = 0;
		while (true)
		{
			if (temp.next == null) break;
			else {
				temp = temp.next;
				length++;
			}
		}
		return length;
	}
	
	//查找单链表中的倒数第k个结点
	public static Node findLastIndexNode(Node head, int k) 
	{
		if(head.next == null) return null;
		int length = getLength(head);
		Node temp = head.next;
		if (k < 0 || k > length) return null;
		for (int i = 0; i < length - k; i++) {
			temp = temp.next;
		}
		return temp;
	}
	
	//单链表的反转
	public static void reverseNode(Node head)
	{
		if (head.next == null || head.next.next == null) return;
		Node cur = head.next;
		Node next = null;
		Node reverseHead = new Node(0, "");
		while (cur != null)
		{
			next = cur.next;
			cur.next = reverseHead.next;
			reverseHead.next = cur;
			cur = next;
		}
		head.next = reverseHead.next;
	}
	
	public static void reserverPrint(Node head)
	{
		if (head == null || head.next == null) return;
		Node cur = head.next;
		Stack<Node> stack = new Stack<>();
		while (cur != null)
		{
			stack.push(cur);
			cur = cur.next;
		}
		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}


}
class Node {
	public int no;
	public String name;
	public Node next;
	public Node(int no, String name) {
		this.no = no;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Node = [no: "  + no + ", name:"  + name + "]";
	}
}

class SingleLinkedList {
	
	private Node head = new Node(0, "");
	public Node getHead() {return head;}
	
	public void list() {
		if(head.next == null) return;
		Node temp = head.next;
		while (true) {
			if (temp == null) break;
			System.out.println(temp);
			temp = temp.next;
		}
	}
	public void addBack(Node node) {
		Node temp = head;
		while (true) {
			if (temp.next == null) break;
			temp = temp.next;
		}
		temp.next = node;
	}
	public void addByOrder(Node node)
	{
		Node temp = head;
		boolean flag = false;
		while (true) 
		{
			if (temp.next == null) break;
			if (node.no == temp.next.no) {
				System.out.printf("节点已存在无法插入");
			}
			else if (node.no > temp.no) 
			{
				temp = temp.next;
			} else {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.printf("找不到插入节点");
			return;
		} else {
			node.next = temp.next;
			temp.next = node;
		}
	}
	
	public void update(Node node)
	{
		if (head.next == null) 
		{
			System.out.println("链表为空");
			return;
		}
		Node temp = head.next;
		boolean flag = true;
		while (true) 
		{
			if (temp == null) break;
			if (temp.no == node.no) 
			{
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			System.out.println("找到节点");
			temp.name = node.name;
		} else {
			System.out.println("找不到该节点");
			return;
		}
	}
	
	public void delete(int no) 
	{
		Node temp = head;
		boolean flag = false;
		while (true)
		{
			if (temp.next == null) break;
			if (temp.next.no == no)
			{
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag)
		{
			System.out.println("找到删除节点");
			temp.next = temp.next.next;
		}else {
			System.out.println("不存在该节点无法删除");
		}
	}
	
	
}