package algorithm.solution.leetcode;

public class RangeSumQuery2dImmutable {
    private static int[][] cachedData;

    public RangeSumQuery2dImmutable(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] rowSum;

        cachedData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            rowSum = new int[columns];
            for (int j = 0; j < columns; j++) {
                rowSum[j] = (j == 0 ? 0 : rowSum[j - 1]) + matrix[i][j];
                cachedData[i][j] = (i == 0 ? 0 : cachedData[i - 1][j]) + rowSum[j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0) {
            return cachedData[row2][col2];
        } else if (row1 == 0) {
            return cachedData[row2][col2] - cachedData[row2][col1 - 1];
        } else if (col1 == 0) {
            return cachedData[row2][col2] - cachedData[row1 - 1][col2];
        }
        return cachedData[row2][col2] + cachedData[row1 - 1][col1 - 1]
                - cachedData[row2][col1 - 1] - cachedData[row1 - 1][col2];
    }
}
