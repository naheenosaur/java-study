package algorithm.solution.Algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int TEST_CASE;
    static List<Map<String, Integer>> RESULT = new ArrayList<>();
    // CASE.get(a).get(b) --> a를 만들 때 케이스 b의 경우의 수

    public static void main(String[] args) throws IOException {

        init();
        TEST_CASE = Integer.parseInt(bufferedReader.readLine());

        for (int test = 1; test <= TEST_CASE; test++) {
            int number = Integer.parseInt(bufferedReader.readLine());
            solve(number);
            System.out.println(test + " " + RESULT.get(number - 1).get("TOTAL"));
        }
    }

    public static void solve(int index) {
        if (RESULT.size() - 1 >= index) {
            return;
        }
        // 내가 만드는 건 i+1 부터 index - 1 까지.
        for (int i = RESULT.size() - 1; i < index; i++) {
            Map<String, Integer> temp = new HashMap<>();
            temp.put("ABCD", RESULT.get(i).get("TOTAL"));
            temp.put("AB", RESULT.get(i).get("ABCD") + RESULT.get(i).get("CD"));
            temp.put("CD", RESULT.get(i).get("ABCD") + RESULT.get(i).get("AB"));
            temp.put("BC", RESULT.get(i).get("ABCD") + RESULT.get(i).get("AD"));
            temp.put("AD", RESULT.get(i).get("BC"));
            temp.put("EMPTY", RESULT.get(i).get("ABCD"));
            Integer total = temp.get("ABCD") + temp.get("AB") + temp.get("CD") + temp.get("BC") + temp.get("EMPTY");
            temp.put("TOTAL", total);
            RESULT.add(temp);
        }
    }

    public static void init() {
        Map<String, Integer> temp = new HashMap<>();
        temp.put("ABCD", 1);
        temp.put("AB", 0);
        temp.put("CD", 0);
        temp.put("BC", 0);
        temp.put("AD", 0);
        temp.put("EMPTY", 0);
        temp.put("TOTAL", 1);
        RESULT.add(temp);
    }
}
