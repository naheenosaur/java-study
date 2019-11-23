package algorithm.solution.Acmicpc;
/*
연구소

문제
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다.
다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며,
직사각형은 1×1 크기의 정사각형으로 나누어져 있다.
연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.

일부 칸은 바이러스가 존재하며, 이 바이러스는 인접한 빈 칸으로 모두 퍼져나갈 수 있다.
새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.

2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
이 때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.

2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.

2 1 0 0 1 1 0
1 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 1 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
바이러스가 퍼진 뒤의 모습은 아래와 같아진다.

2 1 0 0 1 1 2
1 0 1 0 1 2 2
0 1 1 0 1 2 2
0 1 0 0 0 1 2
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.

연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최대값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다.
2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.

빈 칸의 개수는 3개 이상이다.

출력
첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

예제 입력 1
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
예제 출력 1
27
예제 입력 2
4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2
예제 출력 2
9
예제 입력 3
8 8
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
예제 출력 3
3
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class b14502 {
    public static final Logger logger = Logger.getLogger(b14502.class.getName());
    public static final Scanner scanner = new Scanner(System.in);
    public static List<List<Integer>> map = new ArrayList<>();
    public static List<List<Integer>> input = new ArrayList<>();
    static int H;
    static int W;
    static int min = 0;
    static int change;
    static int blank = 0;

    public static void main(String args[]) {
        H = scanner.nextInt();
        W = scanner.nextInt();

        for ( int h = 0; h < H ; h ++) {
            List<Integer> low = new ArrayList<>();
            for ( int w = 0; w < W ; w ++) {
                low.add(scanner.nextInt());
            }
            input.add(low);
        }
        for ( int h = 0; h < H ; h ++) {
            for ( int w = 0; w < W ; w ++) {
                if (input.get(h).get(w) == 0) {
                    blank++;
                }
            }
        }
        min = H * W;
        map = new ArrayList<>(input);
        wall(0, 0);

        System.out.println(blank - 3 - min);
    }

    public static void wall(int h1, int w1) {
        if (change == 3) {
            getMin();
            return;
        }
        for (int w = w1; w < W; w++) {
            if (map.get(h1).get(w) == 0) {
                map.get(h1).set(w, 1);
                change++;
                wall(h1, w);
                change--;
                map.get(h1).set(w, 0);
            }
        }
        for (int h = h1 + 1; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (map.get(h).get(w) == 0) {
                    map.get(h).set(w, 1);
                    change++;
                    wall(h, w);
                    change--;
                    map.get(h).set(w, 0);
                }
            }
        }
    }

    public static void getMin() {

        List<List<Integer>> temp = new ArrayList<>();
        for (int h = 0; h < H; h++) {
            List<Integer> low = new ArrayList<>();
            for (int w = 0; w < W; w++) {
                low.add(map.get(h).get(w));
            }
            temp.add(low);
        }
        // 벽 세개 세운다
        int n = 0;
        int a = 0;
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (temp.get(h).get(w) == 2) {
                    n += go(h, w, temp);
                    a++;
                }
            }
        }
        min = Math.min(min, n);
    }

    public static int go(int y, int x, List<List<Integer>> temp) {
        int t = 0;
        // 왼쪽으로 감염
        if (x > 0) {
            if (temp.get(y).get(x - 1) == 0) {
                temp.get(y).set(x - 1, 2);
                t += go(y, x - 1, temp) + 1;
            }
        }

        // 오른쪽으로 감염
        if (x < W - 1) {
            if (temp.get(y).get(x + 1) == 0) {
                temp.get(y).set(x + 1, 2);
                t += go(y, x + 1, temp) + 1;
            }
        }

        // 위으로 감염
        if (y > 0) {
            if (temp.get(y - 1).get(x) == 0) {
                temp.get(y - 1).set(x, 2);
                t += go(y - 1, x, temp) + 1;
            }
        }

        // 아래쪽으로 감염
        if (y < H - 1) {
            if (temp.get(y + 1).get(x) == 0) {
                temp.get(y + 1).set(x, 2);
                t += go(y + 1, x, temp) + 1;
            }
        }
        return t;
    }
}
