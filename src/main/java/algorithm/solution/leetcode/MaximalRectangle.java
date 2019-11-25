package algorithm.solution.leetcode;

/*
    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
    {
     {'1', '0', '1', '0', '0'},
     {'1', '0', '1', '1', '1'},
     {'1', '1', '1', '1', '1'},
     {'1', '0', '0', '1', '0'}
    }
 */
public class MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] count = new int[columns];
        int result = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int num = Character.getNumericValue(matrix[row][column]);
                count[column] = count[column] * num + num;
            }

            for (int column = 0; column < columns; column++) {
                int minCount = count[column];
                for (int index = column; index < columns && count[index] > 0; index++) {
                    minCount = Math.min(minCount, count[index]);
                    result = Math.max(result, minCount * (index - column + 1));
                }
            }
        }

        return result;
    }

    public static int maximalRectangle2(char[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] count = new int[rows][columns];
        int result = 0;
        for (int row = 0; row < rows; row++) {
            int sum = 0;
            for (int column = 0; column < columns; column++) {
                if (matrix[row][column] == '0') {
                    sum = 0;
                } else {
                    sum++;
                }
                count[row][column] = sum;

                if (sum >= 1) {
                    int minColumn = sum;
                    for (int rowCount = 0; rowCount < row + 1; rowCount++) {
                        if (count[row - rowCount][column] == 0) {
                            break;
                        }
                        minColumn = Math.min(minColumn, count[row - rowCount][column]);
                        result = Math.max(result, minColumn * (rowCount + 1));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //        char[][] matrix = {{'1', '0', '1', '1', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '0'}, {'1', '0', '1', '1', '0'}};
        //        char[][] matrix = {{'0','1','1','0','1'},{'1','1','0','1','0'},{'0','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'0','0','0','0','0'}};
        char[][] matrix = {{'1', '1', '1', '1'}, {'1', '1', '1', '1'}, {'1', '1', '1', '1'}};
        System.out.println(maximalRectangle(matrix));
        System.out.println(maximalRectangle2(matrix));
    }
}
