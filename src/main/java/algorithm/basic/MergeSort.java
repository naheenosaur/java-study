package algorithm.basic;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 11, 23, 51, 66};
        mergeSort(arr, 0, arr.length - 1);
        print(arr);
    }

    private static void mergeSort(int[] arr, int start, int end) {

        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {

        int[] L = new int[mid - start + 1];
        int[] R = new int[end - mid];

        for (int i = 0; i < mid - start + 1; i++) {
            L[i] = arr[start + i];
        }
        for (int j = 0; j < end - mid; j++) {
            R[j] = arr[mid + j + 1];
        }

        int l = 0, r = 0;
        int i = start;
        while (l < (mid - start + 1) && r < (end - mid)) {
            if (L[l] < R[r]) {
                arr[i] = L[l];
                l++;
            } else {
                arr[i] = R[r];
                r++;
            }
            i++;
        }

        while (l < mid - start + 1) {
            arr[i] = L[l];
            l++;
            i++;
        }

        while (r < end - mid) {
            arr[i] = R[r];
            r++;
            i++;
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.println(i + " ");
        }
    }
}
