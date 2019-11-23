package algorithm.solution.Acmicpc;



/*
순열의 순서

문제
1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지가 있다.

임의의 순열은 정렬을 할 수 있다.
예를 들어  N=3인 경우 {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}의 순서로 생각할 수 있다.
첫 번째 수가 작은 것이 순서상에서 앞서며,
첫 번째 수가 같으면 두 번째 수가 작은 것이,
두 번째 수도 같으면 세 번째 수가 작은 것이….

N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다.
k가 주어지면 k번째 순열을 구하고,
임의의 순열이 주어지면 이 순열이 몇 번째 순열인지를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1≤N≤20)이 주어진다. 둘째 줄의 첫 번째 수는 소문제 번호이다. 1인 경우 k(1≤k≤N!)를 입력받고,
2인 경우 임의의 순열을 나타내는 N개의 수를 입력받는다. N개의 수에는 1부터 N까지의 정수가 한 번씩만 나타난다.

출력
k번째 수열을 나타내는 N개의 수를 출력하거나, 몇 번째 수열인지를 출력하면 된다.

예제 입력 1
4
1 3
예제 출력 1
1 3 2 4

예제 입력 2
4
2
1 3 2 4
예제 출력 2
3


1 2 3 4
1 2 4 3
1 3 2 4

5 1 15 --> 1 4 5 2 3

12 6
13 6
14235 14253 14325

7
2
5 2 4 7 6 1 3       3071


*/

import java.util.Scanner;

public class b1722 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        if (scanner.nextInt() == 1) {
            long order = scanner.nextLong();
            getPerm(size, order - 1);
        } else {
            int[] perm = new int[size];
            for (int i = 0; i < size; i++) {
                perm[i] = scanner.nextInt();
            }
            getOrder(size, perm);
        }
    }

    private static void getPerm(int size, long order) { // 0 부터 시작해서 찾아야 하는 것
        boolean[] values = new boolean[size];
        for (int i = size - 1; i >= 0; i--) {
            long factorial = getFactorial(i);
            if (factorial > order) {
                for (int index = 0; index < size; index++) {
                    if (values[index] == false) {
                        values[index] = true;
                        System.out.print((index + 1) + " ");
                        break;
                    }
                }
            } else {
                order -= getMaxInArray(values, factorial, order);
            }
        }
        System.out.println();
    }

    private static long getMaxInArray(boolean[] values, long factorial, long order) {
        // 48 받으면 47번 째 찾아줘야 함 (보다 작은경우임, 크거나같음)
        // 24           6           2           1           0
        // 12345        12345       12345       12345       12345

        // 01234        0x123       0x12x       0x1xx       0xxxx   //
        //  1               3 마지막    2 마지막    1 마지막   0 마지막
        // [1]          [1][4]      [1][4][3]  [1][4][3][2] [1][4][3][2][0]

        // 47-24 = 23   23-18 = 5   5-4 = 1     1 - 1 = 0   0 - 0 = 0
        // 2 5 4 3 1

        // 5 1 15
        // 24           6           2           1           0
        // 12345        12345       12345       12345       12345

        // 01234        x0123       x01x2       01234       01234
        // 0               2          1
        // [0]            [3]        [2]

        // 14           14-12=2     2-2 = 0

        int j = 0;
        int tempIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == true) {
                continue;
            }
            j = i;
            if (tempIndex * factorial > order) {
                j--;
                break;
            }
            tempIndex++;
        }
        while (j > 0 && values[j] == true) {
            j--;
        }
        values[j] = true;
        System.out.print((j + 1) + " "); // 1
        return (tempIndex - 1) * factorial; // 24
    }

    private static void getOrder(int size, int[] arr) {
        boolean[] values = new boolean[size];
        long order = 0;
        for (int i = 0; i < size; i++) {
            int numberValue = getNumberValue(values, arr[i] - 1);
            long factorial = getFactorial(size - i - 1);
            order += numberValue * factorial;
        }
        System.out.println(order + 1);
    }

    private static int getNumberValue(boolean[] values, int value) {
        int cnt = 0;
        for (int i = 0; i < value; i++) {
            if (values[i] == false) {
                cnt++;
            }
        }
        values[value] = true;
        return cnt;
    }

    private static long getFactorial(int i) {
        if (i <= 1) {
            return 1;
        }
        return i * getFactorial(i - 1);
    }
}