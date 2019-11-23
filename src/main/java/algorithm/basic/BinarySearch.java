package algorithm.basic;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {4, 5, 67, 2, 6, 7, 6, 1, 7, 48, 62, 36, 4, 42, 33};
        Arrays.sort(arr);

        bs(arr, 6);
    }

    private static int bs(int[] arr, int i) {
        int start = 0;
        int end = arr.length - 1;
        int pivot;

        while (true) {
            pivot = (start + end) / 2;
            if (i < arr[pivot]) {
                end = pivot;
            } else if (arr[pivot] < i) {
                start = pivot;
            } else {
                return pivot;
            }
        }
    }
}
