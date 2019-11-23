package algorithm.solution.Acmicpc;
/*
소수 경로

문제
소수를 유난히도 좋아하는 창영이는 게임 아이디 비밀번호를 4자리 ‘소수’로 정해놓았다. 어느 날 창영이는 친한 친구와 대화를 나누었는데:

“이제 슬슬 비번 바꿀 때도 됐잖아”
“응 지금은 1033으로 해놨는데... 다음 소수를 무엇으로 할지 고민중이야"
“그럼 8179로 해”
“흠... 생각 좀 해볼게. 이 게임은 좀 이상해서 비밀번호를 한 번에 한 자리 밖에 못 바꾼단 말이야.
예를 들어 내가 첫 자리만 바꾸면 8033이 되니까 소수가 아니잖아. 여러 단계를 거쳐야 만들 수 있을 것 같은데...
예를 들면... 1033 1733 3733 3739 3779 8779 8179처럼 말이야.”
“흠...역시 소수에 미쳤군. 그럼 아예 프로그램을 짜지 그래.
네 자리 소수 두 개를 입력받아서 바꾸는데 몇 단계나 필요한지 계산하게 말야.”
“귀찮아”
그렇다. 그래서 여러분이 이 문제를 풀게 되었다.
입력은 항상 네 자리 소수만(1000 이상) 주어진다고 가정하자.
주어진 두 소수 A에서 B로 바꾸는 과정에서도 항상 네 자리 소수임을 유지해야 하고,
‘네 자리 수’라 하였기 때문에 0039 와 같은 1000 미만의 비밀번호는 허용되지 않는다.

입력
첫 줄에 test case의 수 T가 주어진다.
다음 T줄에 걸쳐 각 줄에 1쌍씩 네 자리 소수가 주어진다.

출력
각 test case에 대해 두 소수 사이의 변환에 필요한 최소 회수를 출력한다.
불가능한 경우 Impossible을 출력한다.

예제 입력 1
3
1033 8179
1373 8017
1033 1033
예제 출력 1
6
7
0
 */

import java.util.*;

public class b1963 {
    static List<Integer> orderNumber = new ArrayList<>();
    static Map<Integer, Integer> traveled = new HashMap<>();

    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        Integer testCase = scanner.nextInt();
        for ( int i = 0; i < testCase; i ++) {
            Integer now = scanner.nextInt();
            Integer target = scanner.nextInt();
            getEasy();
            get(now, target);
            traveled.clear();
        }
    }

    public static void get(Integer now, Integer target) {
        List<Integer> numbers = new ArrayList<>();
        traveled.put(now, 0);
        numbers.add(now);
        while (true) {
            Integer number = numbers.remove(0);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    Integer num = changeNum(number, i, j);
                    if (num != -1) {
                        numbers.add(num);
                        traveled.put(num, traveled.get(number) + 1);
                    }
                    if (traveled.containsKey(target)) {
                        System.out.println(traveled.get(target));
                        return;
                    }
                }
            }
            if (numbers.size() == 0) {
                System.out.println("Impossible");
                return;
            }
        }
    }

    public static Integer changeNum(Integer num, Integer dec, Integer target) {
        String number = String.valueOf(num);
        String str = number.substring(0, dec).concat(target.toString()).concat(number.substring(dec + 1, 4));
        Integer result = Integer.parseInt(str);
        if (traveled.containsKey(result) || !orderNumber.contains(result)) {
            return -1;
        }
        return result;
    }

    public static void getEasy() {
        for (int i = 1000; i < 9999; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    break;
                }
                if (j == i - 1) {
                    orderNumber.add(i);
                }
            }
        }
    }
}
