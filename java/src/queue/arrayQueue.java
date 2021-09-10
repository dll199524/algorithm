package queue;

public class arrayQueue {
    public static void main(String[] args) {
        
    }
}


class MyArrayQueue {

    private int maxSize;
    private int[] arr;
    private int front = -1;
    private int rear = -1;

    public  MyArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满,无法入队");
            return;
        }
        arr[++rear] = data;
    }

    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列已空,无法出队");
            return 0;
        }
        return arr[++front];
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据");
            return;
        }
        for(int i = front; i < rear; i++) {
            System.out.printf("%d\t", arr[i]);
        }
    }

    public int getHead() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据");
            return 0;
        }
        return arr[++front];
    }
}