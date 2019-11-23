package algorithm.solution.Acmicpc;
/*
리모컨

문제
수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다.
+를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다.
채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다.
어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.

수빈이가 지금 보고 있는 채널은 100번이다.

입력
첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.
둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다.
고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 중복되서 주어지는 경우는 없다.

출력
첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.

예제 입력 1
5457
3
6 7 8
예제 출력 1
6

// 5357
// 5455 + +

9999
1
9

6

8888
1
8
7777 + 1111
9999 - 1111

예제 입력 2
100
5
0 1 2 3 4
예제 출력 2
0
예제 입력 3
500000
8
0 2 3 4 6 7 8 9
예제 출력 3
11117

500000
8
0 2 3 5 6 7 8 9
444444(6) + (55556) = 55562

4444444
9
1 2 3 4 5 6 7 8 9

4444344

4444444
9
2 3 4 5 6 7 8 9 0

1111111 + 3333333 = 3333340

4444444
9
1 2 4 5 6 7 8 9 0

3333333 + 1111111 = 1111118

50
9
1 2 3 4 5 6 7 8 9

49
9
1 2 3 4 5 6 7 8 9

59834
7
1 3 4 5 6 7 0

82222(5) + 22388 = 22393
29999(5) + 29835 = 29840


 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b1107 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        int broken = scanner.nextInt();
        List<Integer> brokenNum = new ArrayList<>();
        for (int i = 0; i < broken; i++) {
            brokenNum.add(scanner.nextInt());
        }
        int min = Math.abs(target - 100);
        if (broken == 10) {
            System.out.println(min);
            return;
        }
        if ((broken == 9 && !brokenNum.contains(0))) {
            System.out.println(Math.min(min, target + 1));
            return;
        }
        int goup = goUp(target, brokenNum);
        int godown = goDown(target, brokenNum);
        min = Math.min(Math.min(min, goup), godown);
        System.out.println(min);
    }

    private static int goUp(int target, List<Integer> brokenNum) {
        int clickButton = target;
        int click = 0;
        boolean match = true;
        Integer a = 123;
        while (match) {
            match = false;
            String clickString = String.valueOf(clickButton);
            List<Integer> targetNum = new ArrayList<>();
            for (int i = 0; i < clickString.length(); i++) {
                targetNum.add(Integer.parseInt(clickString.substring(i, i + 1)));
            }
            for (int i = targetNum.size() - 1; i >= 0; i--) {
                if (brokenNum.contains(targetNum.get(i))) {
                    match = true;
                    clickButton -= Math.pow(10, targetNum.size() - i - 1);
                    break;
                }
            }
            if (clickButton <= 0) {
                for (int i = 0; i < 10; i++) {
                    if (brokenNum.contains(i)) {
                        continue;
                    }
                    clickButton = i;
                    break;
                }
                break;
            }
        }
        click = Math.abs(target - clickButton) + String.valueOf(clickButton).length();
        return click;
    }

    private static int goDown(int target, List<Integer> brokenNum) {
        int clickButton = target;
        int click = 0;
        boolean match = true;
        Integer a = 123;
        while (match) {
            match = false;
            String clickString = String.valueOf(clickButton);
            List<Integer> targetNum = new ArrayList<>();
            for (int i = 0; i < clickString.length(); i++) {
                targetNum.add(Integer.parseInt(clickString.substring(i, i + 1)));
            }
            for (int i = targetNum.size() - 1; i >= 0; i--) {
                if (brokenNum.contains(targetNum.get(i))) {
                    match = true;
                    clickButton += Math.pow(10, targetNum.size() - i - 1);
                    break;
                }
            }
        }
        click = Math.abs(target - clickButton) + String.valueOf(clickButton).length();
        return click;
    }
}







