package algorithm.solution.Acmicpc;
/*
테트로미노

문제
폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.

정사각형은 서로 겹치면 안된다.
도형은 모두 연결되어 있어야 한다.
정사각형의 꼭지점끼리 연결되어 있어야 한다. 즉, 변과 꼭지점이 맞닿아있으면 안된다.
정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.

아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 써 있다.

테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.

테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.

입력
첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)

둘째 줄부터 N개의 줄에 종이에 써 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 써 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.

출력
첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.

예제 입력 1
5 5
1 2 3 4 5
5 4 3 2 1
2 3 4 5 6
6 5 4 3 2
1 2 1 2 1
예제 출력 1
19
예제 입력 2
4 5
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5
1 2 3 4 5
예제 출력 2
20
예제 입력 3
4 10
1 2 1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1 2 1
1 2 1 2 1 2 1 2 1 2
2 1 2 1 2 1 2 1 2 1
예제 출력 3
7

7 6
0 1 2 3 4 5
3 4 5 6 7 8
2 5 2 6 2 6
3 6 6 6 6 6
4 5 6 7 3 2
1 1 1 1 1 1
2 5 6 7 8 9

4 4
1 1 1 1
1 2 3 4
1 4 5 6
1 3 9 9

4 10
9 7 5 4 2 4 5 6 1 1
1 2 3 4 7 8 9 0 7 5
2 4 5 6 7 8 9 0 3 6
2 4 2 6 7 8 3 1 5 7

4 1
9
9
9
9

7 5
1000  999   1   999 4
2     4     5   4   999
2     3     3   3   575
1     12    4   6   2
13    4     52  5   5
1000  1     194 3   3
23    4     555 1   4

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b14500 {
    static int N, M;
    static Scanner scanner = new Scanner(System.in);
    static List<List<Integer>> map = new ArrayList<>();
    static int max = 0;

    public static void main(String args[]) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        for ( int h = 0; h < N ; h ++) {
            List<Integer> temp = new ArrayList<>();
            for ( int w = 0; w < M ; w++) {
                temp.add(scanner.nextInt());
            }
            map.add(temp);
        }

        for ( int h = 0; h < N ; h ++) {
            for ( int w = 0; w < M ; w++) {
                get(w, h);
            }
        }
        System.out.println(max);
    }

    static void get(int x, int y) {
        if (M > x + 2 && N > y + 1) {
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y).get(x + 2) + map.get(y + 1).get(x), max);
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y).get(x + 2) + map.get(y + 1).get(x + 2), max);
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 1).get(x + 1) + map.get(y + 1).get(x + 2), max);
        }

        if (M > x + 1 && N > y + 2) {
            /*
                    0
                    *
                    * *

                    0 *
                    *
                    *

                    0 *
                      *
                      *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 2).get(x) + map.get(y + 2).get(x + 1), max);
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y + 1).get(x) + map.get(y + 2).get(x), max);
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y + 1).get(x + 1) + map.get(y + 2).get(x + 1), max);
        }

        if (x > 0 && N > y + 2) {
            /*
                      0
                      *
                    * *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 2).get(x) + map.get(y + 2).get(x - 1), max);
        }

        if (x > 1 && N > y + 1) {
            /*
                        0
                    * * *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 1).get(x - 1) + map.get(y + 1).get(x - 2), max);
        }

        if (M > x + 3) {
            /*
                 0 * * *
            */
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y).get(x + 2) + map.get(y).get(x + 3), max);
        }

        if (N > y + 3) {
            /*
                    0
                    *
                    *
                    *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 2).get(x) + map.get(y + 3).get(x), max);
        }

        if (M > x + 1 && N > y + 1) {
            /*
                    0 *
                    * *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y).get(x + 1) + map.get(y + 1).get(x + 1), max);
        }

        if (x > 0 && M > x + 1 && N > y + 1) {
            /*
                      0
                    * * *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x - 1) + map.get(y + 1).get(x) + map.get(y + 1).get(x + 1), max);
        }

        if (M > x + 2 && N > y + 1) {
            /*
                     0 * *
                       *
             */
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y + 1).get(x + 1) + map.get(y).get(x + 2), max);
        }

        if (M > x + 1 && x > 0 && N > y + 1) {
            /*
                     0 *
                   * *
             */
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y + 1).get(x - 1) + map.get(y + 1).get(x), max);
        }

        if (M > x + 2 && N > y + 1) {
            /*
                    0 *
                      * *
             */
            max = Math.max(map.get(y).get(x) + map.get(y).get(x + 1) + map.get(y + 1).get(x + 1) + map.get(y + 1).get(x + 2), max);
        }

        if (M > x + 1 && N > y + 2) {
            /*
                    0
                    * *
                    *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 1).get(x + 1) + map.get(y + 2).get(x + 1), max);
        }

        if (x > 0 && N > y + 2) {
            /*
                      0
                    * *
                      *
             */
            max = Math.max(map.get(y).get(x) + map.get(y + 1).get(x) + map.get(y + 1).get(x - 1) + map.get(y + 2).get(x - 1), max);
        }
    }
}
