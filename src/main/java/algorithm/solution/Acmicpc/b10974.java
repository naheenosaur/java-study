package algorithm.solution.Acmicpc;
/*
모든 순열
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	4420	2427	1820	56.294%
문제
N이 주어졌을 때, 1부터 N까지의 수로 이루어진 순열을 사전순으로 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다.

출력
첫째 줄부터 N!개의 줄에 걸쳐서 모든 순열을 사전순으로 출력한다.

예제 입력 1
3
예제 출력 1
1 2 3
1 3 2
2 1 3
2 3 1
3 1 2
3 2 1
 */

import java.util.Scanner;

public class b10974 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int i = 0;
        while (true) {
            int index = hasNext(arr, n);
            if (index == -1) {
                break;
            }
            int[] arr2 = getNext(arr, n, index);
            arr = arr2;
            System.out.println();
        }
    }

    public static int[] getNext(int[] arr, int size, int index) {
        int[] arr2 = new int[size];
        int j = 0;
        for (int i = 0; i < index; i++) {
            System.out.print(arr[i] + " ");
            arr2[j] = arr[i];
            j++;
        }
        int nextIndex = index;
        for (int i = index + 1; i < size; i++) {
            if (arr[i] > arr[index]) {
                nextIndex = i;
            }
        }
        System.out.print(arr[nextIndex] + " ");
        arr2[j] = arr[nextIndex];
        j++;

        for (int i = size - 1; i > nextIndex; i--) {
            System.out.print(arr[i] + " ");
            arr2[j] = arr[i];
            j++;
        }
        System.out.print(arr[index] + " ");
        arr2[j] = arr[index];
        j++;
        for (int i = nextIndex - 1; i > index; i--) {
            System.out.print(arr[i] + " ");
            arr2[j] = arr[i];
            j++;
        }
        return arr2;
    }

    public static int hasNext(int[] arr, int size) {
        int index = -1;
        for (int i = 0; i < size - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                index = i;
            }
        }
        return index;
    }
}
