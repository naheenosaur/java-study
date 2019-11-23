package algorithm.solution.Acmicpc;



/*
문제
땅 위에 달팽이가 있다. 이 달팽이는 높이가 V미터인 나무 막대를 올라갈 것이다.

달팽이는 낮에 A미터 올라갈 수 있다. 하지만, 밤에 잠을 자는 동안 B미터 미끄러진다. 또, 정상에 올라간 후에는 미끄러지지 않는다.

달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 세 정수 A, B, V가 공백으로 구분되어서 주어진다. (1 ≤ B < A ≤ V ≤ 1,000,000,000)
2 1 5
7 4 55
7 10 13 16 19 22 25 28 31 34 37 40 43 46 49 52 55 --> 17

4 3

2 1 3 -> 2
7 1 8 -> 2
7 2 9 -> 2
7 1 15

출력
첫째 줄에 달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 출력한다.
4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2869 {
    public static int solution(int A, int B, int V) {
        if (A >= V) {
            return 1;
        }
        int result = 0;
        result = (V - B) / (A - B);
        if ((V - B) % (A - B) != 0) {
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        StringTokenizer stringTokenizer = null;
        try {
            stringTokenizer = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        } catch (IOException ignored) {
        }
        int A, B, V;
        A = Integer.parseInt(stringTokenizer.nextToken());
        B = Integer.parseInt(stringTokenizer.nextToken());
        V = Integer.parseInt(stringTokenizer.nextToken());
        System.out.println(solution(A, B, V));
    }
}



