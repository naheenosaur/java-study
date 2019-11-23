package algorithm.solution.Algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DiamondPath {
    static int TESTCASE;
    static int DIAMOND_SIZE;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static List<List<Integer>> DIAMOND = new ArrayList<>();
    static List<List<Integer>> RESULT_SUM = new ArrayList<>();
    static List<List<StringBuilder>> RESULT_PATH = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 첫 줄 : test case
        TESTCASE = Integer.parseInt(bufferedReader.readLine());
        // 둘째 줄 : 다이아몬드 크기
        for (int i = 0; i < TESTCASE; i++) {
            DIAMOND_SIZE = Integer.parseInt(bufferedReader.readLine()) * 2 - 1;
            for (int j = 0; j < DIAMOND_SIZE; j++) {
                st = new StringTokenizer(bufferedReader.readLine());
                List<Integer> row = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    row.add(Integer.parseInt(st.nextToken()));
                }
                DIAMOND.add(row);
            }
            solve();

            /* 결과값 출력용
            for ( List<Integer> row : RESULT_SUM) {
                for ( Integer num : row ) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }*/
            System.out.print(RESULT_SUM.get(RESULT_PATH.size() - 1).get(0) + " ");


            /* 경로 출력용
            for ( List<StringBuilder> row : RESULT_PATH) {
                for ( StringBuilder sb : row ) {
                    System.out.print(sb.toString() + "\t");
                }
                System.out.println();
            } */

            System.out.println(RESULT_PATH.get(RESULT_PATH.size() - 1).get(0));

            RESULT_SUM.clear();
            RESULT_PATH.clear();
            DIAMOND.clear();
        }
    }

    public static void solve() {
        for (int i = 0; i < DIAMOND.size(); i++) {
            RESULT_SUM.add(new ArrayList<>());
            RESULT_PATH.add(new ArrayList<>());
            if (i == 0) {
                RESULT_SUM.get(0).add(DIAMOND.get(0).get(0));
                RESULT_PATH.get(0).add(new StringBuilder());
                continue;
            }
            for (int j = 0; j < DIAMOND.get(i).size(); j++) {
                int me = DIAMOND.get(i).get(j);
                if (DIAMOND.get(i - 1).size() < DIAMOND.get(i).size()) {
                    // 커지고 있는 다이아몬드
                    // left = index - 1
                    // right = index
                    if (j == 0) { // 더 커지고 있을 때 맨 앞이면 index 같은 경우
                        int right = RESULT_SUM.get(i - 1).get(j);
                        StringBuilder rightStringBuilder = RESULT_PATH.get(i - 1).get(j);
                        RESULT_SUM.get(i).add(right + me);
                        RESULT_PATH.get(i).add(new StringBuilder(rightStringBuilder).append("0"));
                    } else if (j == DIAMOND.get(i).size() - 1) { // 더 커지고 있을 때 맨 뒤면 index - 1인 경우
                        int left = RESULT_SUM.get(i - 1).get(j - 1);
                        StringBuilder leftStringBuilder = RESULT_PATH.get(i - 1).get(j - 1);
                        RESULT_SUM.get(i).add(left + me);
                        RESULT_PATH.get(i).add(new StringBuilder(leftStringBuilder).append("1"));
                    } else {  // 이 외의 경우는 index 같은 경우, index -1 인 경우중 큰 것
                        int left = RESULT_SUM.get(i - 1).get(j - 1);
                        int right = RESULT_SUM.get(i - 1).get(j);
                        StringBuilder leftStringBuilder = RESULT_PATH.get(i - 1).get(j - 1);
                        StringBuilder rightStringBuilder = RESULT_PATH.get(i - 1).get(j);
                        if (left > right) {
                            RESULT_SUM.get(i).add(left + me);
                            RESULT_PATH.get(i).add(new StringBuilder(leftStringBuilder).append("1"));
                        } else {
                            RESULT_SUM.get(i).add(right + me);
                            RESULT_PATH.get(i).add(new StringBuilder(rightStringBuilder).append("0"));
                        }
                    }
                } else {
                    // 작아지고 있는 다이아몬드
                    // left = index
                    // right = index + 1
                    int left = RESULT_SUM.get(i - 1).get(j);
                    int right = RESULT_SUM.get(i - 1).get(j + 1);
                    StringBuilder leftStringBuilder = RESULT_PATH.get(i - 1).get(j);
                    StringBuilder rightStringBuilder = RESULT_PATH.get(i - 1).get(j + 1);
                    if (left > right) {
                        RESULT_SUM.get(i).add(left + me);
                        if (i != DIAMOND.size()) {
                            RESULT_PATH.get(i).add(new StringBuilder(leftStringBuilder).append("1"));
                        }
                    } else {
                        RESULT_SUM.get(i).add(right + me);
                        if (i != DIAMOND.size()) {
                            RESULT_PATH.get(i).add(new StringBuilder(rightStringBuilder).append("0"));
                        }
                    }
                }
            }
        }
    }
}

