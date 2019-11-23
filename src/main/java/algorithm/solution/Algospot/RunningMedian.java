package algorithm.solution.Algospot;
/*
문제
한 수열의 중간값(median)은 이 수열을 정렬했을 때 가운데 오는 값입니다.
예를 들어 {3,1,5,4,2}를 정렬했을 때 가운데 오는 값은 3이지요.
수열의 길이가 짝수일 때는 가운데 있는 두 값 중 보다 작은 것을 수열의 중간값이라고 정의하도록 합시다.

한 수열의 중간값은 수열에 새로운 수가 추가될 때마다 바뀔 수 있습니다.
텅 빈 수열에서 시작해서 각 수가 추가될 때마다 중간값을 계산하는 프로그램을 작성하세요.
예를 들어 3, 1, 5, 4, 2 순서대로 숫자가 추가될 경우 수열의 중간값은 3, 1, 3, 3, 3 순서로 변화합니다.

입력 생성
입력의 크기가 큰 관계로, 이 문제에서는 수열을 입력받는 대신 다음과 같은 식을 통해 프로그램 내에서 직접 생성합니다.

A[0] = 1983
A[i] = (A[i-1] * a + b) % 20090711
a와 b는 입력에 주어지는 상수입니다. 이 문제의 해법은 입력을 생성하는 방식과는 아무 상관이 없습니다.

입력
입력 파일의 첫 줄에는 테스트 케이스의 수 C (1 <= C <= 20)가 주어지고,
그 후 C줄에 각 3개의 정수로 수열의 길이 N (1 <= N <= 200,000),
그리고 수열을 생성하는 데 필요한 두 정수 a , b (0 <= a,b <= 10000) 가 주어집니다.

출력
각 테스트 케이스마다 한 줄에 N개의 중간값의 합을 20090711로 나눈 나머지를 출력합니다.

예제 입력
3
10 1 0
10 1 1
10000 1273 4936

예제 출력
19830
19850
2448920

10 1 1
1983


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RunningMedian {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(bufferedReader.readLine());
            List<Long> list = new ArrayList<>();
            long n = Long.parseLong(st.nextToken()), a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
            long temp = 1983, total = 0;
            for (int i = 0; i < n; i++) {
                list.add(temp);
                list.sort(((o1, o2) -> {
                    if (o1 > o2) {
                        return 1;
                    } else if (o1 < o2) {
                        return -1;
                    }
                    return 0;
                }));
                total += list.get((list.size() - 1) / 2);
                temp = (temp * a + b) % 20090711;
            }
            System.out.println(total % 20090711);
        }
    }
}
