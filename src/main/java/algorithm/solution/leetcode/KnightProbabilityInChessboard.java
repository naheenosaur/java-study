package algorithm.solution.leetcode;

/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below.
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random
(even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard.
Return the probability that the knight remains on the board after it has stopped moving.

Example:
Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.

Note:
N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
 */
// tip : N*N배열이고, 나이트의 방향이 좌우 대칭이기 때문에, n*n 배열을 대각선으로 나누어 반만 사용하면 시간복잡도를 줄일 수 있다.
public class KnightProbabilityInChessboard {
    // N*N, K번, (r,c)
    public double knightProbability(int N, int K, int r, int c) {
        if (N == 0) {
            return 0;
        }
        if (K == 0) {
            return 1;
        }
        double result = 1;
        double[] linearPin = new double[N * N];
        if (r < c) {
            linearPin[r * N + c] = 1;
        } else {
            linearPin[c * N + r] = 1;
        }
        for (int count = 1; count <= K; count++) {
            double move = 0;
            double in = 0;
            double[] nextLinearPin = new double[N * N];
            for (int rIndex = 0; rIndex < N; rIndex++) {
                for (int cIndex = rIndex; cIndex < N; cIndex++) {
                    if (linearPin[rIndex * N + cIndex] == 0) {
                        continue;
                    }
                    double pins = linearPin[rIndex * N + cIndex];
                    move += pins;
                    double tempIn = 0;
                    tempIn += setThisTurn(nextLinearPin, rIndex - 1, cIndex - 2, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex - 1, cIndex + 2, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex + 1, cIndex - 2, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex + 1, cIndex + 2, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex - 2, cIndex - 1, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex - 2, cIndex + 1, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex + 2, cIndex - 1, pins, N);
                    tempIn += setThisTurn(nextLinearPin, rIndex + 2, cIndex + 1, pins, N);
                    in += (tempIn * linearPin[rIndex * N + cIndex]);
                }
            }
            result *= (in / (move * 8));
            linearPin = nextLinearPin;
            if (result == 0) {
                return 0;
            }
        }
        return result;
    }

    private int setThisTurn(double[] linearPin, int r, int c, double count, int N) {
        if (r < 0 || N <= r || c < 0 || N <= c) {
            return 0;
        }
        if (r < c) {
            linearPin[r * N + c] += count;
        } else {
            linearPin[c * N + r] += count;
        }
        return 1;
    }
}
