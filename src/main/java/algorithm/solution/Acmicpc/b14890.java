package algorithm.solution.Acmicpc;
/*
크기가 N×N인 지도가 있다. 지도의 각 칸에는 그 곳의 높이가 적혀져 있다.

길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다. 또는, 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다.
경사로는 높이가 항상 1이며, 길이는 L이다.
또, 개수는 매우 많아 부족할 일이 없다. 경사로는 낮은 칸과 높은 칸을 연결하며, 아래와 같은 조건을 만족해야한다.

경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
아래와 같은 경우에는 경사로를 놓을 수 없다.

경사로를 놓은 곳에 또 경사로를 놓는 경우
낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
경사로를 놓다가 범위를 벗어나는 경우
L = 2인 경우에 경사로를 놓을 수 있는 경우를 그림으로 나타내면 아래와 같다.

경사로를 놓을 수 없는 경우는 아래와 같다.

위의 그림의 가장 왼쪽부터 1번, 2번, 3번, 4번 예제라고 했을 때,
1번은 높이 차이가 1이 아니라서, 2번은 경사로를 바닥과 접하게 놓지 않아서, 3번은 겹쳐서 놓아서, 4번은 기울이게 놓아서 불가능한 경우이다.

가장 위에 주어진 그림 예의 경우에 지나갈 수 있는 길은 초록색으로, 지나갈 수 없는 길은 빨간색으로 표시되어 있으며, 아래와 같다.
경사로의 길이 L = 2이다.

지도가 주어졌을 때, 지나갈 수 있는 길의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (2 ≤ N ≤ 100)과 L (1 ≤ L ≤ N)이 주어진다. 둘째 줄부터 N개의 줄에 지도가 주어진다. 각 칸의 높이는 10보다 작거나 같은 자연수이다.

출력
첫째 줄에 지나갈 수 있는 길의 개수를 출력한다.

예제 입력 1
6 2
3 3 3 3 3 3
2 3 3 3 3 3
2 2 2 3 2 3
1 1 1 2 2 2
1 1 1 3 3 1
1 1 2 3 3 2
예제 출력 1
2 1
3
예제 입력 2
6 2
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2
예제 출력 2
3 4
7
예제 입력 3
6 3
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2
예제 출력 3
1 2
3
예제 입력 4
6 1
3 2 1 1 2 3
3 2 2 1 2 3
3 2 2 2 3 3
3 3 3 3 3 3
3 3 3 3 2 2
3 3 3 3 2 2
예제 출력 4
5 6
11

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b14890 {
    static final Scanner scanner = new Scanner(System.in);
    static int N, L, AVAILABLE = 0;
    static List<List<Integer>> map = new ArrayList<>();

    public static void main(String[] args) {
        N = scanner.nextInt();
        L = scanner.nextInt();
        for (int h = 0; h < N; h++) {
            List<Integer> temp = new ArrayList<>();
            for (int w = 0; w < N; w++) {
                temp.add(scanner.nextInt());
            }
            map.add(temp);
        }
        getX();
        getY();
        System.out.println(AVAILABLE);
    }

    public static void getX() {
        for (int h = 0; h < N; h++) {
            int num = 1;

            boolean changed = false; // 이거는 줄었을 때.. L 이상이어야 함
            for (int w = 1; w < N; w++) {
                if (map.get(h).get(w - 1) == map.get(h).get(w)) {
                    if (changed && L <= num) {
                        changed = false;
                        num = 1;
                    } else {
                        num++;
                    }
                } else {
                    if (changed && L <= num) {
                        changed = false;
                        num -= L;
                    }

                    if ((map.get(h).get(w - 1) + 1 == map.get(h).get(w))) {
                        // 커지는 경우
                        // 현재까지 w-1의 개수가 L보다 작으면 break;
                        if (num == L) {
                            num = 1;
                        } else if (num > L) {
                            num = 0;
                        } else {
                            break;
                        }
                        changed = false;
                    } else if (map.get(h).get(w - 1) == map.get(h).get(w) + 1) {
                        // 작아지는 경우
                        // w과 같은게 L보다 적게 나오면 break;
                        // 만약에 지금 L이랑 같다... 하면 num을 0으로 아니면 1로
                        if (num < L) {
                            break;
                        }
                        num = 1;
                        changed = true;
                    } else {
                        break;
                    }
                }
                if (w >= N - 1) {
                    if (changed && (num < L)) {
                        break;
                    }
                    AVAILABLE++;
                }
            }
        }
    }

    public static void getY() {
        for (int w = 0; w < N; w++) {
            int num = 1;

            boolean changed = false; // 이거는 줄었을 때.. L 이상이어야 함
            for (int h = 1; h < N; h++) {
                if (map.get(h - 1).get(w) == map.get(h).get(w)) {
                    if (changed && L <= num) {
                        changed = false;
                        num = 1;
                    } else {
                        num++;
                    }
                } else {
                    if (changed && L <= num) {
                        changed = false;
                        num = num - L + 1;
                    }

                    if ((map.get(h - 1).get(w) + 1 == map.get(h).get(w))) {
                        // 커지는 경우
                        // 현재까지 w-1의 개수가 L보다 작으면 break;
                        if (num >= L) {
                            num = 1;
                        } else {
                            break;
                        }
                        changed = false;
                    } else if (map.get(h - 1).get(w) == map.get(h).get(w) + 1) {
                        // 작아지는 경우
                        // w과 같은게 L보다 적게 나오면 break;
                        // 만약에 지금 L이랑 같다... 하면 num을 0으로 아니면 1로
                        if (num == 0) {
                            break;
                        }
                        num = 1;
                        changed = true;
                    } else {
                        break;
                    }
                }
                if (h >= N - 1) {
                    if (changed && (num < L)) {
                        break;
                    }
                    AVAILABLE++;
                }
            }
        }
    }
}
