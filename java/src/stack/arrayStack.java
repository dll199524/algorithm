package stack;

import java.util.Scanner;

public class arrayStack {
    public static void main(String[] args) {
        // 测试一下ArrayStack 是否正确
        // 先创建一个ArrayStack对象->表示栈
        MyArrayStack stack = new MyArrayStack(4);
        String key = "";
        boolean loop = true; // 控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
    
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println();
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    
        System.out.println("程序退出~~~");
    }
}
class MyArrayStack {
    private int[] arr;
    private int maxSize;
    private int top = -1;
    public MyArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }
    public boolean isFull() {
        return top == maxSize - 1;
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public void push(int data) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        arr[++top] = data;
    }
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈空");
            return 0;
        }
        int value = arr[top];
        top--;
        return value;
    }
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return; 
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, arr[i]);
        }
    }

}