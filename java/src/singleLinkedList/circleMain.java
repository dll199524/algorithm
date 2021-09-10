package singleLinkedList;

public class circleMain {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.add(5);
        circleLinkedList.showBoy();
        circleLinkedList.countBoy(1, 2, 5);
    }
}
class Boy {
    private int no;
    private Boy next;
    public Boy (int no) {this.no = no;}
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Boy getNext() {
        return next;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}

class CircleLinkedList {

    private Boy first = null;

    public void add(int nums) {
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) break;
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 
     * @param stratNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初有几个小孩
     */
    public void countBoy(int stratNo, int countNum, int nums) {
        if (first == null || stratNo < 1 || stratNo > nums) {
            System.out.println("参数输入有误请重新输入");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) break;
            helper = helper.getNext();
        }
        for (int i = 0; i < stratNo - 1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        while (true) {
            if (helper == first) break;
            for (int i = 0; i < countNum - 1; i++) {
                helper = helper.getNext();
                first = first.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d", first.getNo());
    }

}
