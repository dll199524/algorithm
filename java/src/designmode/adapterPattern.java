package designmode;

// import java.io.*;

// import javax.swing.text.Document;
// import javax.xml.parsers.*;



public class adapterPattern {
    public static void main(String[] args) {
        
    }
    
}
interface ScoreOperation {
    public int[] sort(int[] arr);
    public int search(int[] arr, int val);
}

class QuickSort {
    public int[] quickSort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组为空");
            return null;
        }
        sort(arr, 0, arr.length - 1);
        return arr;
    }
    public void sort(int[] arr, int left, int right) {
        int mid = 0;
        while (left < right) {
            mid = partition(arr, left, right);
            sort(arr, left, mid - 1);
            sort(arr, mid + 1, right);
        }
    }
    public int partition(int arr[], int left, int right) {
        int provit = arr[0];
        while (left < right) {
            while (left < right && arr[right] >= provit) right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= provit) left++;
            arr[right] = arr[left];
        }
        arr[left] = provit;
        return left;
    }

}

//必须为有序
class BinarySearch {
    public int binarySearch(int[] arr, int val) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组为空");
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] == val) return mid;
            else if (arr[mid] > val) right = mid--;
            else left = mid++;
        }
        return -1;
    }
}

class OperationAdapter implements ScoreOperation {

    private QuickSort sortObj;
    private BinarySearch searchObj;

    public OperationAdapter() {
        sortObj = new QuickSort();
        searchObj = new BinarySearch();
    }

    @Override
    public int[] sort(int[] arr) {
        return sortObj.quickSort(arr);
    }

    @Override
    public int search(int[] arr, int val) {
        return searchObj.binarySearch(arr, val);
    }

}

// class XMLUtil {
//     public static Object getBean() {
//         try {
//             DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
//             DocumentBuilder builder = dFactory.newDocumentBuilder();
//             Document doc;
//             doc = (Document) builder.parse(new File("config.xml"));
            
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//         return null;
//     }
   
// }