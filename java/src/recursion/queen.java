package recursion;

public class queen {

    private int max = 8;
    int[] arr = new int[max];
    private static int count;
    private static int judgeCount = 0;

    public static void main(String[] args) {
        queen q = new queen();
        q.check(0);
        System.out.printf("共有多少种解发%d", count);
        System.out.println();
        System.out.printf("共比较了多少次%d", judgeCount);
    }

    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) check(n + 1);
        }

    }

    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) return false;
        }
        return true;
    }

    public void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }

    


}
