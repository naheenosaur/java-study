package algorithm.solution.leetcode;

/*
There is an m by n grid with a ball.
Given the start coordinate (i,j) of the ball,
you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
However, you can at most move N times.
Find out the number of paths to move the ball out of grid boundary.
The answer may be very large, return it after mod 109 + 7.

Note:

1. Once you move the ball out of boundary, you cannot move it back.
2. The length and height of the grid is in range [1,50].
3. N is in range [0,50].
 */
// tip : 1000000007 은 페르마의 소정리 ( 모듈러공식 ) 에 의해서 어떤 소수로 나눈 값은 동일한 값으로 여기는 것과 관련이 있음
// 값들을 더한 값이 1000000007을 넘길 수 있으니, 덧셈 혹은 곱셈연산이 나올 때 마다 저 수로 나누어 주는 것이 좋음
// 그리고 이 값은 문제에 주어짐
// 이 값을 넘으면 int의 값에서 넘어갈 수 있기 때문임.
public class OutOfBoundaryPaths {
    public int findPaths(int m, int n, int N, int i, int j) {
        int max = 1000000000 + 7;
        if (N == 0) {
            return 0;
        }
        int sum = 0;

        int[][] dp = new int[m][n];
        dp[i][j] = 1;
        for (int count = 1; count <= N; count++) {
            int[][] temp = new int[m][n];
            for (int iIndex = 0; iIndex < m; iIndex++) {
                for (int jIndex = 0; jIndex < n; jIndex++) {
                    temp[iIndex][jIndex]
                            = ((get(dp, iIndex - 1, jIndex)
                            + get(dp, iIndex + 1, jIndex)) % max
                            + (get(dp, iIndex, jIndex - 1)
                            + get(dp, iIndex, jIndex + 1)) % max) % max;
                    if (iIndex == 0) {
                        sum = (sum + dp[iIndex][jIndex]) % max;
                    }
                    if (iIndex == m - 1) {
                        sum = (sum + dp[iIndex][jIndex]) % max;
                    }
                    if (jIndex == 0) {
                        sum = (sum + dp[iIndex][jIndex]) % max;
                    }
                    if (jIndex == n - 1) {
                        sum = (sum + dp[iIndex][jIndex]) % max;
                    }
                }
            }
            dp = temp;
        }
        return sum;
    }

    private int get(int[][] dp, int iIndex, int jIndex) {
        if (iIndex < 0 || dp.length <= iIndex
                || jIndex < 0 || dp[0].length <= jIndex) {
            return 0;
        }
        return dp[iIndex][jIndex];
    }
}
