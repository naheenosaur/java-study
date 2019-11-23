package algorithm.solution.UVa;
/*
Have you ever heard of this game? The player jumps in a special game board under certain rules, so
that the numbers he jumps on, after being linked up by plus or minus signs, get closest to zero.
The game board looks like the one shown in Figure 1.1. Its size is determined by the number of
squares in the middle row N. (Figure 1.1 is an example where N = 4.) The player starts at the
bottom-most square, then jumps in any of the directions shown in Figure 1.2. The game ends when the
player reaches the topmost square. During the game, the player cannot jump out of the game board.
Finally we write down the 2N − 1 numbers in order, then insert plus or minus signs between each pair
of adjoining numbers such that the result is closest to zero.
Let us look at the game board in Figure 1.1 as a example. We should get: 7+8+(-5)+(-2)-5-1-2=0,
7+10+(-7)-6+(-3)-3+2=0, 7+10+(-5)-10-5+1+2=0, or 7+10+(-5)+(-2)-5-3-2=0.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TowardsZero {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer = null;
    static int INPUT_SIZE;
    static List<List<Integer>> INPUT_NUMBERS = new ArrayList<>();
    static List<List<Set<Integer>>> RESULT_LIST = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        INPUT_SIZE = Integer.parseInt(bufferedReader.readLine());
        do {
            for (int i = 0; i < 2 * INPUT_SIZE - 1; i++) {
                List<Integer> temp = new ArrayList<>();
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                while (stringTokenizer.hasMoreTokens()) {
                    temp.add(Integer.parseInt(stringTokenizer.nextToken()));
                }
                INPUT_NUMBERS.add(temp);
            }
            result();
            System.out.println(getClosest());
            INPUT_NUMBERS.clear();
            RESULT_LIST.clear();
            INPUT_SIZE = Integer.parseInt(bufferedReader.readLine());
        } while (INPUT_SIZE != 0);
    }

    public static void result() {
        List<Set<Integer>> first = new ArrayList<>();
        Set<Integer> firstSet = new HashSet<>();
        firstSet.add(INPUT_NUMBERS.get(0).get(0));
        first.add(firstSet);
        RESULT_LIST.add(first);
        for (int i = 1; i < INPUT_SIZE * 2 - 1; i++) {
            RESULT_LIST.add(new ArrayList<>());
            for (int j = 0; j < INPUT_NUMBERS.get(i).size(); j++) {
                Map<Integer, Integer> temp = new HashMap<>();
                if (i < INPUT_SIZE) {
                    // 커지고 있는 중
                    if (j == 0) {
                        for (int num : RESULT_LIST.get(i - 1).get(j)) {
                            temp.put(num + INPUT_NUMBERS.get(i).get(j), 1);
                            temp.put(num - INPUT_NUMBERS.get(i).get(j), 1);
                        }
                    } else if (j == INPUT_NUMBERS.get(i).size() - 1) {
                        for (int num : RESULT_LIST.get(i - 1).get(j - 1)) {
                            temp.put(num + INPUT_NUMBERS.get(i).get(j), 1);
                            temp.put(num - INPUT_NUMBERS.get(i).get(j), 1);
                        }
                    } else {
                        for (int num : RESULT_LIST.get(i - 1).get(j)) {
                            temp.put(num + INPUT_NUMBERS.get(i).get(j), 1);
                            temp.put(num - INPUT_NUMBERS.get(i).get(j), 1);
                        }
                        for (int num : RESULT_LIST.get(i - 1).get(j - 1)) {
                            temp.put(num + INPUT_NUMBERS.get(i).get(j), 1);
                            temp.put(num - INPUT_NUMBERS.get(i).get(j), 1);
                        }
                    }
                } else {
                    // 작아지고 있는 중
                    for (int num : RESULT_LIST.get(i - 1).get(j)) {
                        temp.put(num + INPUT_NUMBERS.get(i).get(j), 1);
                        temp.put(num - INPUT_NUMBERS.get(i).get(j), 1);
                    }
                    for (int num : RESULT_LIST.get(i - 1).get(j + 1)) {
                        temp.put(num + INPUT_NUMBERS.get(i).get(j), 1);
                        temp.put(num - INPUT_NUMBERS.get(i).get(j), 1);
                    }
                }
                RESULT_LIST.get(i).add(temp.keySet());
            }
        }
    }

    public static int getClosest() {
        int closest = 987654321;
        List<Integer> resultList = new ArrayList<>(RESULT_LIST.get(INPUT_SIZE * 2 - 2).get(0));

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i) == 0) {
                return 0;
            } else if (resultList.get(i) > 0) {
                if (closest > 0) {
                    if (closest > resultList.get(i)) {
                        closest = resultList.get(i);
                    }
                } else {
                    if (closest * (-1) > resultList.get(i)) {
                        closest = resultList.get(i);
                    }
                }
            } else {
                if (closest > 0) {
                    if (closest > resultList.get(i) * (-1)) {
                        closest = resultList.get(i);
                    }
                } else {
                    if (closest < resultList.get(i)) {
                        closest = resultList.get(i);
                    }
                }
            }
        }
        if (closest > 0) {
            return closest;
        } else {
            return closest * (-1);
        }
    }
}
