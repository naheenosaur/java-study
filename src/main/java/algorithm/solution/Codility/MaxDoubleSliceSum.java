package algorithm.solution.Codility;/*
A non-empty array A consisting of N integers is given.

A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.

The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

For example, array A such that:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
contains the following example double slices:

double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the maximal sum of any double slice.

For example, given:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
the function should return 17, because no double slice of array A has a sum of greater than 17.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−10,000..10,000].
 */

public class MaxDoubleSliceSum {
    public static void main(String[] args) {
        //        int[] A = {3, 2, 6, - 1, 4, 5, - 1, 2}; // 17
        int[] A = {-3, 2, -6, -1, 4, 5, -1, 2}; // 10
        //        int[] A = {3, 2, 6, - 1, 4, 5, - 1, 2};
        System.out.println(solution(A));
    }


/*
    3   2   6   -1  4   5   -1  2
    가운데 서 뺄 것을 기준으로 돌리기
    --> start : 가운데 전 필수
    --> end : 가운데 뒤 필수
    맨 앞, 맨 뒤 index 는 추가될 수 없음
 */

    public static int solution(int[] A) {
        int max = 0, size = A.length;

        int[] start = new int[size];
        int[] end = new int[size];

        for (int i = 1; i < size - 2; i++) { // i 까지
            start[i + 1] = Math.max(0, Math.max(start[i] + A[i], A[i]));
        }

        for (int i = size - 2; i > 1; i--) { // i부터
            end[i - 1] = Math.max(0, Math.max(end[i] + A[i], A[i]));
        }

        for (int i = 1; i < size - 1; i++) {
            int tempMax = start[i] + end[i];
            max = Math.max(max, tempMax);
        }
        return max;
    }
}
