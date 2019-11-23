package algorithm.solution.Acmicpc;
/*
차이를 최대로

문제
N개의 정수로 이루어진 배열 A가 주어진다.
이 때, 배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최대값을 구하는 프로그램을 작성하시오.

|A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|

입력
첫째 줄에 N (3 ≤ N ≤ 8)이 주어진다. 둘째 줄에는 배열 A에 들어있는 정수가 주어진다.
배열에 들어있는 정수는 -100보다 크거나 같고, 100보다 작거나 같다.

출력
첫째 줄에 배열에 들어있는 수의 순서를 적절히 바꿔서 얻을 수 있는 식의 최댓값을 출력한다.

예제 입력 1
6
20 1 15 8 4 10

예제 출력 1
62
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b10819 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        List<Integer> numbers = new ArrayList();
        for (int i = 0; i < num; i++) {
            numbers.add(scanner.nextInt());
        }
        numbers.sort((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            }
            return 0;
        });
        int max = 0;
        do {
            int sum = 0;
            for (int i = 0; i < numbers.size() - 1; i++) {
                sum += Math.abs(numbers.get(i) - numbers.get(i + 1));
            }
            max = Math.max(sum, max);
        } while (nextPermutation(numbers));
        System.out.println(max);
    }

    public static boolean nextPermutation(List<Integer> numbers) {
        int temp = -1;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) < numbers.get(i + 1)) {
                temp = i;
            }
        }
        if (temp == -1) {
            return false;
        }
        int next = temp;
        for (int i = temp; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(temp)) {
                next = i;
            }
        }
        List<Integer> rootList = new ArrayList<>(numbers.subList(0, temp));
        rootList.add(numbers.get(next));
        List<Integer> tempList = new ArrayList<>(numbers.subList(temp, next));

        tempList.addAll(new ArrayList<>(numbers.subList(next + 1, numbers.size())));
        tempList.sort((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else {
                return -1;
            }
        });
        numbers.clear();
        numbers.addAll(rootList);
        numbers.addAll(tempList);
        return true;
    }
}
