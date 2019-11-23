package algorithm.solution.Acmicpc;
/*
퍼즐

문제
3*3 표에 다음과 같이 수가 채워져 있다. 오른쪽 아래 가장 끝 칸은 비어 있는 칸이다.

1	2	3
4	5	6
7	8
어떤 수와 인접해 있는 네 개의 칸 중에 하나가 비어 있으면, 수를 그 칸으로 이동시킬 수가 있다.
물론 표 바깥으로 나가는 경우는 불가능하다.
우리의 목표는 초기 상태가 주어졌을 때, 최소의 이동으로 위와 같은 정리된 상태를 만드는 것이다. 다음의 예를 보자.

1	 	3
4	2	5
7	8	6

1	2	3
4	 	5
7	8	6

1	2	3
4	5
7	8	6

1	2	3
4	5	6
7	8
가장 윗 상태에서 세 번의 이동을 통해 정리된 상태를 만들 수 있다. 이와 같이 최소 이동 횟수를 구하는 프로그램을 작성하시오.

입력
세 줄에 걸쳐서 표에 채워져 있는 아홉 개의 수가 주어진다. 한 줄에 세 개의 수가 주어지며, 빈 칸은 0으로 나타낸다.

출력
첫째 줄에 최소의 이동 횟수를 출력한다. 이동이 불가능한 경우 -1을 출력한다.

예제 입력 1
1 0 3
4 2 5
7 8 6
예제 출력 1
3
 */

import java.util.*;

public class b1525 {
    static Scanner scanner = new Scanner(System.in);
    static Map<Integer, Integer> map = new HashMap<>();
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> puzzle = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            puzzle.add(scanner.nextInt());
        }
        puzzle(puzzle);
    }

    public static void puzzle(List<Integer> puzzle) {
        StringBuilder str = getString(puzzle);
        int num = Integer.parseInt(str.toString());
        map.put(num, 0);
        list.add(num);
        while (true) {
            StringBuilder temp = new StringBuilder(list.remove(0).toString());
            Integer index = temp.indexOf("9");
            Integer tempVale = map.get(Integer.parseInt(temp.toString()));
            if (index > 2) { // 윗줄로 옮기기
                StringBuilder t = new StringBuilder(temp).replace(index, index + 1, temp.substring(index - 3, index - 2)).replace(index - 3, index - 2, "9");
                Integer me = Integer.parseInt(t.toString());
                if (!map.containsKey(me)) {
                    map.put(me, tempVale + 1);
                    list.add(me);
                }
            }
            if (index % 3 != 0) { // 왼쪽으로 옮기기
                StringBuilder t = new StringBuilder(temp).replace(index, index + 1, temp.substring(index - 1, index)).replace(index - 1, index, "9");
                Integer me = Integer.parseInt(t.toString());
                if (!map.containsKey(me)) {
                    map.put(me, tempVale + 1);
                    list.add(me);
                }
            }
            if (index % 3 != 2) { // 오른쪽으로 옮기기
                StringBuilder t = new StringBuilder(temp).replace(index, index + 1, temp.substring(index + 1, index + 2)).replace(index + 1, index + 2, "9");
                Integer me = Integer.parseInt(t.toString());
                if (!map.containsKey(me)) {
                    map.put(me, tempVale + 1);
                    list.add(me);
                }
            }
            if (index < 6) { // 아랫줄로 옮기기
                StringBuilder t = new StringBuilder(temp).replace(index, index + 1, temp.substring(index + 3, index + 4)).replace(index + 3, index + 4, "9");
                Integer me = Integer.parseInt(t.toString());
                if (!map.containsKey(me)) {
                    map.put(me, tempVale + 1);
                    list.add(me);
                }
            }

            if (list.size() == 0) {
                System.out.println(-1);
                return;
            }

            if (map.containsKey(123456789)) {
                System.out.println(map.get(123456789));
                return;
            }
        }
    }

    public static StringBuilder getString(List<Integer> puzzle) {
        StringBuilder sb = new StringBuilder();
        puzzle.forEach(num -> {
            sb.append(num == 0 ? 9 : num);
        });
        return sb;
    }
}
