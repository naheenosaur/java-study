package algorithm.solution.programers;
/*
가로, 세로 길이가 n인 정사각형으로된 체스판이 있습니다. 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하고 싶습니다.

예를 들어서 n이 2인경우 다음과 같이 퀸을 배치하면 n개의 퀸은 서로를 한번에 공격 할 수 없습니다.


체스판의 가로 세로의 세로의 길이 n이 매개변수로 주어질 때, n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수를 return하는 solution함수를 완성해주세요.

제한사항
퀸(Queen)은 가로, 세로, 대각선으로 이동할 수 있습니다.
n은 12이하의 자연수 입니다.
입출력 예
n	result
4	2
입출력 예 설명
입출력 예 #1
문제의 예시와 같습니다.

// 행, 열, 열-행, 행-열 이 같지 않아야 함

 */

import java.util.BitSet;

public class P12952 {
    private static BitSet column = new BitSet(), row = new BitSet(), columnRow = new BitSet(), rowColumn = new BitSet();
    private static int answer;

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    private static int solution(int n) {
        get(n, 0);
        return answer;
    }

    public static void get(int n, int i) {
        if (i == n) {
            answer++;
            return;
        }
        for (int c = 0; c < n; c++) {
            if (!(column.get(i) || row.get(c) || columnRow.get(c - i + n) || rowColumn.get(i + c))) {
                column.set(i);
                row.set(c);
                columnRow.set(c - i + n);
                rowColumn.set(i + c);
                get(n, i + 1);
                column.clear(i);
                row.clear(c);
                columnRow.clear(c - i + n);
                rowColumn.clear(i + c);
            }
        }
    }
}
