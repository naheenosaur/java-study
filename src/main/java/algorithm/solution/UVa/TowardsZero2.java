package algorithm.solution.UVa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TowardsZero2 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer = null;
    static int INPUT_SIZE;
    static List<List<Integer>> INPUT_NUMBERS = new ArrayList<>();

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
            INPUT_NUMBERS.clear();
            INPUT_SIZE = Integer.parseInt(bufferedReader.readLine());
        } while (INPUT_SIZE != 0);
    }

    public static void result() {

    }
}

