package algorithm.solution.Codility;
/*
An integer M and a non-empty array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.

A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q].
A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.

For example, consider integer M = 6 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 5
    A[3] = 5
    A[4] = 2
There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).

The goal is to calculate the number of distinct slices.

Write a function:

class Solution { public int solution(int M, int[] A); }

that, given an integer M and a non-empty array A consisting of N integers, returns the number of distinct slices.

If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.

For example, given integer M = 6 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 5
    A[3] = 5
    A[4] = 2
the function should return 9, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
M is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..M].
 */

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class CountDistinctSlices {
    //If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.

    public static void main(String[] args) {

        //        int[] arr = {3, 4, 5, 5, 2}; // 9
        //        int[] arr = {1, 5, 2, 4, 7, 8}; // 21
        //        int[] arr = {1, 1, 5, 6, 12, 3, 5, 6, 5, 5, 2, 4, 7, 8}; // 41
        //        int[] arr = {1, 5, 2, 4, 13, 1, 2, 2, 5, 4, 5, 4, 7, 8, 7, 8, 7, 8, 7, 8}; // 53
        int[] arr = {1, 5, 2, 4, 7, 2, 8}; // 22
        //        int[] arr = {2, 4, 7, 8, 1, 2}; // 20
        //        int[] arr = {1, 1, 1, 2, 2, 2, 3, 4, 5, 5, 6, 6}; // 20
        System.out.println(solution(14, arr));
    }

    // WRONG ANSWER,  got 705082704 expected 1000000000
    public static int solution(int M, int[] A) {
        Map<Integer, Integer> arrMap = new HashMap<>();
        BitSet bitSet = new BitSet(M);
        int fromIndex = 0;
        long num = 0;

        // fromIndex ~ map에서 나오는 해당 index 까지 지워줌
        for (int i = 0; i < A.length; i++) {
            if (bitSet.get(A[i])) {
                num += (bitSet.cardinality() * (bitSet.cardinality() - 1) / 2);
                int tempNum = arrMap.get(A[i]);
                for (; fromIndex <= tempNum; fromIndex++) {
                    bitSet.clear(A[fromIndex]);
                }
                num -= ((i - fromIndex) * (i - fromIndex - 1) / 2);
            }
            bitSet.set(A[i]);
            arrMap.put(A[i], i);
            if (i == A.length - 1) {
                num += (bitSet.cardinality() * (bitSet.cardinality() - 1) / 2);
            }
            if (num >= 1000000000) {
                return 1000000000;
            }
        }
        num += A.length;
        if (num >= 1000000000) {
            return 1000000000;
        }
        return (int) num;

        //        for ( int i = 0; i < A.length; i++ ) {
        //            bitSet = new BitSet(M);
        //            bitSet.set(A[i]);
        //            for ( int j = i; j < A.length; j++ ) {
        //                if ( i != j && bitSet.get(A[j]) ) {
        //                    break;
        //                } else {
        //                    bitSet.set(A[j]);
        //                }
        //                if ( j == A.length - 1 ) {
        //                    num += ((bitSet.cardinality() * (bitSet.cardinality() - 1) / 2) + bitSet.cardinality());
        //                    if ( num >= 1000000000 ) {
        //                        return 1000000000;
        //                    }
        //                    return num;
        //                }
        //            }
        //            num += (bitSet.cardinality());
        //            if ( num >= 1000000000 ) {
        //                return 1000000000;
        //            }
        //        }
        //        return num;
    }
}
