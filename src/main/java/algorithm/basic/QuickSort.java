package algorithm.basic;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {10, 40, 50, 20, 30};

        quickSort(arr, 0, arr.length - 1);

        print(arr);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = section(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private static int section(int[] arr, int leftIdx, int rightIdx) {

        int pivot = arr[(leftIdx + rightIdx) / 2];

        while (leftIdx < rightIdx) {
            while ((arr[leftIdx] < pivot) && leftIdx < rightIdx) {
                leftIdx++;
            }
            while (pivot < arr[rightIdx] && leftIdx < rightIdx) {
                rightIdx--;
            }
            if (leftIdx < rightIdx) {
                int temp = arr[leftIdx];
                arr[leftIdx] = arr[rightIdx];
                arr[rightIdx] = temp;
            }
        }
        return leftIdx;
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.println(i + " ");
        }
    }
}
