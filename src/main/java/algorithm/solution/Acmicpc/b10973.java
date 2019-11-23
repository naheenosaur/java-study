package algorithm.solution.Acmicpc;
/*
이전 순열

문제
1부터 N까지의 수로 이루어진 순열이 있다.
이 때, 사전순으로 바로 이전에 오는 순열을 구하는 프로그램을 작성하시오.

사전 순으로 가장 앞서는 순열은 오름차순으로 이루어진 순열이고,
가장 마지막에 오는 순열은 내림차순으로 이루어진 순열이다.

N = 3인 경우에 사전순으로 순열을 나열하면 다음과 같다.

1, 2, 3
1, 3, 2
2, 1, 3
2, 3, 1
3, 1, 2
3, 2, 1
입력
첫째 줄에 N(1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄에 순열이 주어진다.

출력
첫째 줄에 입력으로 주어진 순열의 이전에 오는 순열을 출력한다.
만약, 사전순으로 가장 처음에 오는 순열인 경우에는 -1을 출력한다.

예제 입력 1
4
1 2 3 4

예제 출력 1
-1

예제 입력 2
5
5 4 3 2 1
예제 출력 2
5 4 3 1 2

7
5 3 4 6 2 7 1
5 3 4 6 2 1 7

7
1 2 4 5 7 3 6
1 2 4 5 6 7 3

7
1 2 4 7 3 5 6
1 2 4 6 7 5 3

7
1 2 4 7 6 3 5
1 2 4 7 5 6 3
 */

import java.util.Scanner;

public class b10973 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        getPre(arr, n);
    }

    public static int hasPre(int[] arr, int size) {
        int index = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                index = i;
            }
        }
        return index;
    }

    public static void getPre(int[] arr, int size) {
        int index = hasPre(arr, size);
        if (index == -1) {
            System.out.println(index);
        } else {
            for (int i = 0; i < index; i++) {
                System.out.print(arr[i] + " ");
            }
            int preIndex = index;
            for (int i = index + 1; i < size; i++) {
                if (arr[i] < arr[index]) {
                    preIndex = i;
                }
            }
            if (index != preIndex) {
                System.out.print(arr[preIndex] + " ");
            } else {
                System.out.print(arr[index] + " ");
            }
            for (int i = size - 1; i > preIndex; i--) {
                System.out.print(arr[i] + " ");
            }
            if (index != preIndex) {
                System.out.print(arr[index] + " ");
            }
            for (int i = preIndex - 1; i > index; i--) {
                System.out.print(arr[i] + " ");
            }
        }
    }
}
/*
9
1 2 7 3 4 5 6 8 9
1 2 6 9 8 7 5 4 3

9
1 2 6 3 4 5 7 8 9
1 2 5 9 8 7 6 4 3


 */