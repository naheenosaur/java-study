package algorithm.solution.Acmicpc;
/*
숨바꼭질

문제
수빈이는 동생과 숨바꼭질을 하고 있다.
수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
수빈이는 걷거나 순간이동을 할 수 있다.
 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때,
수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

출력
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

예제 입력 1
5 17
예제 출력 1
4
 */

import java.util.*;

public class b1697 {
    static final int MAX = 100000;
    static final int MIN = 0;
    static int ME;
    static int SISTER;
    static Map<Integer, Integer> traveled = new HashMap<>();

    // key에 위치, value에 걸리는 시간
    public static void main(String[] args) {
        // 이 문제는 dfs로 플면 overflow 나옴
        // bfs로 풀어야 함
        Scanner scanner = new Scanner(System.in);
        ME = scanner.nextInt();
        SISTER = scanner.nextInt();
        if (SISTER <= ME) {
            System.out.println(ME - SISTER);
            return;
        }
        move(ME);
    }

    public static void move(int location) {
        List<Integer> bfs = new ArrayList<>();
        traveled.put(location, 0);
        bfs.add(location);
        while (true) {
            int now = bfs.remove(0);
            if (MIN <= now - 1) {
                if (traveled.containsKey(now - 1) == false) {
                    bfs.add(now - 1);
                    traveled.put(now - 1, traveled.get(now) + 1);
                }
            }
            if (now + 1 < MAX) {
                if (traveled.containsKey(now + 1) == false) {
                    bfs.add(now + 1);
                    traveled.put(now + 1, traveled.get(now) + 1);
                }
            }
            if (now * 2 < MAX) {
                if (traveled.containsKey(now * 2) == false) {
                    bfs.add(now * 2);
                    traveled.put(now * 2, traveled.get(now) + 1);
                }
            }
            if (traveled.containsKey(SISTER)) {
                break;
            }
        }
        System.out.println(traveled.get(SISTER));
    }
}

