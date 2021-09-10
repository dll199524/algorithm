package algorithm;
import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int arr[] = {1, 4, 6, 2, 6, 8, 4};

        System.out.println("排序前的数组");
        System.out.println(Arrays.toString(arr));
        System.out.println();
        //[1, 2, 4, 4, 6, 6, 8]
        // bubbleSort(arr);       
        // selectSort(arr);
        // insertSort(arr);
        // shellBSort(arr);
        // shellISort(arr);
        // quickSort(arr, 0, arr.length - 1);
        // int[] temp = new int[arr.length];
        // mergeSort(arr, 0, arr.length - 1, temp);

        // radixSort(arr);

        heapSort(arr);
        
        
        System.out.println("排序后");
		System.out.println(Arrays.toString(arr));


    }   
    
    //冒泡排序
    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 1; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
            System.out.println("第" + (i + 1) + "趟排序的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag) break;
            else flag = false;
        }    
    }

    //选择排序
    public static void selectSort(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < size; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) + "趟排序的数组");
            System.out.println(Arrays.toString(arr));
        }
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int size = arr.length;
        int insertIndex = 0;
        int insertVal = 0;
        for (int i = 1; i < size; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
            System.out.println("第" + (i + 1) + "趟插入的数组");
            System.out.println(Arrays.toString(arr));   
        }
    }


    //希尔排序交换法
    public static void shellBSort(int[] arr) {
        int size = arr.length;
        int temp = 0;
        int count = 0;
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) 
                    {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + 1] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

    //希尔排序插入法
    public static void shellISort(int[] arr) {
        int size = arr.length;
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                int temp = arr[i];
                int j = i;
                if (arr[i] < arr[i - gap]) {
                    while (j - gap >= 0 && arr[j - gap] > temp) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex  - 1);
            quickSort(arr, partitionIndex + 1, right);
        }

    }

    public static int partition(int[] arr, int left, int right) {
        int provit = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= provit) right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= provit) left++;
            arr[right] = arr[left];
        }
        arr[left] = provit;
        return left;
    }



    //归并排序
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[t++] = arr[i++];
            else temp[t++] = arr[j++];
        }
        while (i <= mid) temp[t++] = arr[i++];
        while (j <= right) temp[t++] = arr[j++];
        int m = 0;
        int n = left;
        while (n <= right) arr[n++] = temp[m++];
    }



    //桶排序
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arr.length];
        int[] bucketCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digetOfElement = arr[j] / n % 10;
                bucket[digetOfElement][bucketCounts[digetOfElement]] = arr[j];
                bucketCounts[digetOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketCounts.length; k++) {
                for (int l = 0; l < bucketCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                bucketCounts[k] = 0;
            }
            System.out.println("第"+(i+1)+"轮，对个位的排序处理 arr =" + Arrays.toString(arr));
        }
    }


    //堆排序
    public static void heapSort(int[] arr) {
        int temp = 0;
        //构造大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //交换堆顶和末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = j * 2 + 1) {
            if (arr[j] < arr[j + 1] && j + 1 < length) j++;
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else break;
        }
        arr[i] = temp;
    }


}
