package algorithm;

import java.util.Arrays;



public class Search {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89, 1000, 1234};
        
        System.out.println(binarySearch(arr, 1234));
        System.out.println(binarySearchRecursion(arr, 0, arr.length - 1, 1234));
        System.out.println(insertValSearch(arr, 0, arr.length - 1, 1234));
        System.out.println(fibSearch(arr, 1234));


    }


    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else if (target == arr[mid]) return mid;
        }
        return -1;
    }


    public static int binarySearchRecursion(int[] arr, int left, int right, int findVal) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (arr[mid] > findVal) return binarySearchRecursion(arr, left, mid - 1, findVal);
        else if (arr[mid] < findVal) return binarySearchRecursion(arr, mid + 1, right, findVal);
        else return mid;
    }

    public static int insertValSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[left] || findVal > arr[right]) return -1;
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] > findVal) return insertValSearch(arr, left, mid - 1, findVal);
        else if (arr[mid] < findVal) return insertValSearch(arr, left, mid - 1, findVal);
        else return mid;
    }


    public static int[] fib() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int[] f = fib();
        int k = 0;

        while (high > f[k] - 1) k++;
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low < high) {
            mid = low + f[k - 1] - 1;
            if (target < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (target > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) return mid;
                else return high;
            }
        }
        if (arr[low] == target) return low;
        else return -1;
    }

}
