package algorithm.solution.Codility;

/*
A non-empty array A consisting of N integers is given.
A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

For example, given array A such that:

A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:

(3, 4) is a slice of A that has sum 4,
(2, 2) is a slice of A that has sum −6,
(0, 1) is a slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).
Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [−1,000,000..1,000,000];
the result will be an integer within the range [−2,147,483,648..2,147,483,647].
 */
/*
    부분 합의 최댓값
 */
/*
 0, 3, -7, 8, -7, 5, 6, 1, -8, 10

    3, -4  4  -3  2  8  9   1   9
       -7  1  -6 -1  5  6  -2   8
           8   1  6 12 13   5  15
              -7 -2  4  5  -3   7
                  5 11 12   4  14
                     6  8   0  10
                        1  -7   3
                           -8   2
                               10
    최댓값에서 다음숫자 더함.. - 나오면 처음부터

 */
/*
    https://app.codility.com/demo/results/trainingDQT49W-JT9/
 */
public class MaxSliceSum {
    public static void main(String[] args) {
        //        int[] A = {3, 2, -6, 4, 0};
        int[] A = {0, 3, -7, 8, -7, 5, 6, 1, -8, 10}; // 12
        System.out.println(solution(A)); // 5
    }

    public static int solution(int[] A) {
        int max = A[0];
        int temp_sum = A[0];

        for (int i = 1; i < A.length; i++) {
            if (temp_sum < 0) {
                temp_sum = A[i];
            } else {
                temp_sum += A[i];
            }
            max = (max > temp_sum ? max : temp_sum);
        }
        return max;
    }
}
