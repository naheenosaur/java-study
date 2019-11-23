package algorithm.solution.Acmicpc;
/*
로또

문제
독일 로또는 {1, 2, ..., 49}에서 숫자 6개를 고른다.

로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 숫자 중 k(k>6)개의 숫자를 골라
집합 S를 만든 다음 그 숫자만 가지고 번호를 선택하는 것이다.

예를 들어, k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 숫자를 고를 수 있는 경우의 수는 총 28가지이다.
([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])

집합 S와 k가 주어졌을 때, 숫자를 고르는 모든 방법을 구하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있다.
첫 번째 숫자는 k (6 < k < 13)이고, 다음 k개 숫자는 집합 S에 포함되는 수이다. S의 원소는 오름차순으로 주어진다.

입력의 마지막 줄에는 0이 하나 주어진다.

출력
각 테스트 케이스 마다 숫자를 고르는 모든 방법을 출력한다. 이 때, 사전 순으로 출력한다.

각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.

8 1 2 3 4 5 6 7 8
예제 입력 1
7 1 2 3 4 5 6 7
8 1 2 3 5 8 13 21 34
0
예제 출력 1
 // 입력한 것이 7일 경우 , 7개
 // 8일 경우 28개
 // 9일 경우 84개 나옴


1 2 3 4 5 6
1 2 3 4 5 7
1 2 3 4 6 7
1 2 3 5 6 7
1 2 4 5 6 7
1 3 4 5 6 7
2 3 4 5 6 7

1 2 3 5 8 13
1 2 3 5 8 21
1 2 3 5 8 34
1 2 3 5 13 21
1 2 3 5 13 34
1 2 3 5 21 34
1 2 3 8 13 21
1 2 3 8 13 34
1 2 3 8 21 34
1 2 3 13 21 34
1 2 5 8 13 21
1 2 5 8 13 34
1 2 5 8 21 34
1 2 5 13 21 34
1 2 8 13 21 34
1 3 5 8 13 21
1 3 5 8 13 34
1 3 5 8 21 34
1 3 5 13 21 34
1 3 8 13 21 34
1 5 8 13 21 34
2 3 5 8 13 21
2 3 5 8 13 34
2 3 5 8 21 34
2 3 5 13 21 34
2 3 8 13 21 34
2 5 8 13 21 34
3 5 8 13 21 34
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b6603 {
    static final int ARRAY_SIZE = 6;
    static List<Integer> picked = new ArrayList<>();
    static List<Integer> inputList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int num = scanner.nextInt();
            if (num == 0) {
                return;
            }
            for (int i = 0; i < num; i++) {
                inputList.add(scanner.nextInt());
            }

            pick();
            System.out.println();
            inputList.clear();
            picked.clear();
        }
    }

    static void pick() {
        if (picked.size() == ARRAY_SIZE) {
            printArray();
            return;
        }
        for (int nextIndex = picked.size(); nextIndex < inputList.size(); nextIndex++) {
            if (!picked.contains(inputList.get(nextIndex))) {
                if (picked.size() > 0 && (picked.get(picked.size() - 1) > inputList.get(nextIndex))) {
                    continue;
                }
                picked.add(inputList.get(nextIndex));
                pick();
                picked.remove(picked.size() - 1);
            }
        }
    }

    public static void printArray() {
        for (int a = 0; a < picked.size(); a++) {
            System.out.print(picked.get(a) + " ");
        }
        System.out.println();
    }
}
