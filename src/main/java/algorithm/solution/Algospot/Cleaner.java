package algorithm.solution.Algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.StringTokenizer;

public class Cleaner {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(bufferedReader.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            BitSet map = new BitSet(N * M);
            List<Integer> mapList = new ArrayList<>();
            List<List<Integer>> MAP = new ArrayList<>();

            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(bufferedReader.readLine());
                List<Integer> row = new ArrayList<>();
                for (int m = 0; m < M; m++) {
                    int temp = Integer.parseInt(st.nextToken());
                    mapList.add(temp);
                    row.add(temp);
                    if (temp != -1) {
                        map.set((n * M) + m);
                    }
                }
                MAP.add(row);
            }
            solve(mapList, map, N, M);
        }
    }

    private static void solve(List<Integer> mapList, BitSet map, int N, int M) {
        // i = index / n
        // j = index % n

    }
}
