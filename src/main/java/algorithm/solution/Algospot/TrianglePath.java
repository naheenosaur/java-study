package algorithm.solution.Algospot;
/*
    문제
        6
        1  2
        3  7  4
        9  4  1  7
        2  7  5  9  4
        위 형태와 같이 삼각형 모양으로 배치된 자연수들이 있습니다.
        맨 위의 숫자에서 시작해, 한 번에 한 칸씩 아래로 내려가 맨 아래 줄로 내려가는 경로를 만들려고 합니다.
        경로는 아래 줄로 내려갈 때마다 바로 아래 숫자, 혹은 오른쪽 아래 숫자로 내려갈 수 있습니다.
        이 때 모든 경로 중 포함된 숫자의 최대 합을 찾는 프로그램을 작성하세요.

    입력
        입력의 첫 줄에는 테스트 케이스의 수 C(C <= 50)가 주어집니다.
        각 테스트 케이스의 첫 줄에는 삼각형의 크기 n(2 <= n <= 100)이 주어지고,
        그 후 n줄에는 각 1개~n개의 숫자로 삼각형 각 가로줄에 있는 숫자가 왼쪽부터 주어집니다.
        각 숫자는 1 이상 100000 이하의 자연수입니다.

    출력
        각 테스트 케이스마다 한 줄에 최대 경로의 숫자 합을 출력합니다.

    예제 입력
        2
        5
        6
        1  2
        3  7  4
        9  4  1  7
        2  7  5  9  4
        5
        1
        2 4
        8 16 8
        32 64 32 64
        128 256 128 256 128
    예제 출력
        28
        341
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrianglePath {
    private static Scanner scanner = new Scanner(System.in);
    private static List<List<Integer>> triangle;
    private static List<List<Integer>> maxList;

    public static void main(String[] args) {
        int testCase = scanner.nextInt();
        for (int t = 0; t < testCase; t++) {
            int triangleSize = scanner.nextInt();
            triangle = new ArrayList<>(triangleSize);
            maxList = new ArrayList<>(triangleSize);
            for (int i = 0; i < triangleSize; i++) {
                List<Integer> line = new ArrayList<>();
                maxList.add(new ArrayList<>());
                for (int j = 0; j <= i; j++) {
                    line.add(scanner.nextInt());
                }
                triangle.add(line);
            }
            maxList.get(0).add(triangle.get(0).get(0));
            int max = getMax(triangleSize - 1, 0);
            for (int i = 1; i < triangleSize; i++) {
                max = Math.max(max, getMax(triangleSize - 1, i));
            }
            System.out.println(max);
        }
    }

    private static int getMax(int line, int index) {
        if (maxList.get(line).isEmpty()) {
            for (int i = 0; i <= line; i++) {
                maxList.get(line).add(0);
            }
        }
        int tempMax;
        if (maxList.get(line).get(index) != 0) {
            return maxList.get(line).get(index);
        }

        if (index == line) {
            tempMax = getMax(line - 1, index - 1);
        } else if (index == 0) {
            tempMax = getMax(line - 1, index);
        } else {
            tempMax = Math.max(getMax(line - 1, index - 1), getMax(line - 1, index));
        }
        tempMax += triangle.get(line).get(index);
        maxList.get(line).set(index, tempMax);
        return tempMax;
    }
}
