package algorithm.solution.Codility;
//https://app.codility.com/programmers/task/theater_tickets/
// 70
/*
Kate was given a birthday gift of three theater tickets. Now she is browsing the theater program for the next N days.
On the program, performances are named by integers.
Every day, one performance is staged. Kate wants to choose three days (not necessarily consecutive) to go to the theater.

In how many ways can she use her tickets? Two ways are different if the sequences of watched performances are different.
Kate likes the theater, so she may watch one performance more than once.
For example, if N = 4 and theater program looks as following: [1, 2, 1, 1],
Kate has four possibilities to choose the dates: [1, 2, 1, 1], [1, 2, 1, 1], [1, 2, 1, 1], and [1, 2, 1, 1],
but they create only three different sequences: (1, 2, 1), (1, 1, 1) and (2, 1, 1).
The correct answer for this example is 3. Notice that the order of performances matters, so the first and the last sequences are considered different.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, denoting names of performances for the next N days, returns the number of possible ways to spend the tickets.
Since the answer can be very large, provide it modulo 109 + 7 (1,000,000,007).

For example, given A = [1, 2, 1, 1], the function should return 3 as exmplained above.

Given A = [1, 2, 3, 4], the function should return 4. There are four ways to spend tickets: (1, 2, 3), (1, 2, 4), (1, 3, 4) and (2, 3, 4).

Given A = [2, 2, 2, 2], the function should return 1. There is only one way to spend tickets: (2, 2, 2).

Given A = [2, 2, 1, 2, 2], the function should return 4. There are four ways to spend tickets: (1, 2, 2), (2, 1, 2), (2, 2, 1) and (2, 2, 2).

Given A = [1, 2], the function should return 0. Kate cannot use all three tickets in only two days.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [1..N].
 */

import java.util.*;

public class TheaterTickets_v2 {
    public static void main(String[] args) {
        //        int[] A = {1, 2, 1, 1}; // 3
        //        int[] A = {1, 2, 3, 2, 2, 1, 3, 4}; // 27
        //        int[] A = {1, 2, 3, 4, 1}; // 10
        //        int[] A = {1, 2, 3, 4, 1, 2}; // 20
        //        int[] A = {1, 2, 3, 4, 1, 2, 3}; // 32
        //        int[] A = {1, 2, 3, 4, 2, 1, 3}; // 31
        //        int[] A = {1, 2, 4, 2, 1, 3}; // 17
        //        int[] A = {1, 2, 4, 2, 1, 3}; // 17
        //        int[] A = {1, 2, 3, 1, 2, 3}; // 17
        /*
                    1, 2, 3
                    1, 2,    1
                    1, 2,       2
                    1,    3, 1
                    1,    3,   2
                    1,    3,       3
                    1,       1,  2
                    1,       1,    3
                       2, 3,  1
                       2, 3,    2
                       2, 3,       3
                       2,     1, 2
                       2,     1,   3
                       2,        2, 3
                          3, 1, 2
                          3, 1,    3
                          3,   2,  3
         */
        //        int[] A = {1, 2, 3, 2, 1, 3}; // 16
        /*
                    1, 2, 3
                    1, 2,    2
                    1, 2,      1
                    1,    3, 2
                    1,    3,   1
                    1,    3,      3
                    1,         1,  3
                      2, 3,  2
                      2, 3,    1
                      2, 3        3
                      2,    2, 1
                      2,    2,    3
                      2,        1, 3
                         3, 2, 1
                         3, 2,    3
                         3,    1, 3
         */
        //        int[] A = {1, 2, 3, 4}; // 4
        /*
                     1, 2, 3
                     1, 2,    4
                     1,    3, 4
                        2, 3, 4
         */
        //        int[] A = {2, 2, 2, 2}; // 1
        //        int[] A = {2, 2, 1, 2, 2}; // 4
        //        int[] A = {1, 2, 2, 1, 2, 3, 2}; // 12
        /*         1  2  2                  1 개
                   1  2     1
                   1  2           3
                   1        1  2
                   1        1     3
                   1              3  2

                      2  2  1
                      2  2     2
                      2  2        3
                      2     1  2
                      2     1     3
                      2           3 2
         */
        //        int[] A = {1, 2, 3, 4, 5, 1, 1, 5};//34
        //        int[] B = {1, 2, 3, 4, 5, 1, 1, 4};//37
        //        int[] C = {1, 2, 3, 4, 5, 1, 1, 3};//39
        //        int[] C2 = {1, 2, 3, 4, 5, 1, 1, 2};//40
        //        int[] C3 = {1, 2, 3, 4, 5, 1, 1, 1};//25
        //        int[] D = {1, 2, 3, 4, 5, 6, 1, 4};//53
        //        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 1, 1, 2, 2, 3, 3, 4, 4}; // 1186
        //        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 1, 15, 12, 11, 10, 2, 5, 7, 2, 4, 5, 5, 5, 3, 5, 2}; // 2016
        //        int[] A = {1, 2}; // 0
        //        int[] A = {1, 2, 3, 1, 1, 5};//13 -->
        //        // 연속으로 2개 나오면,
        //        // 앞에서 1개, 연속2개 / 연속 2개 , 뒤에서 1개 / 앞에서 1개, 연속1개, 뒤에서 1개
        //        // 연속으로 3개 이상 나오면,
        //        // 앞에서 1개, 연속2개 / 연속 2개 , 뒤에서 1개 / 앞에서 1개, 연속1개, 뒤에서 1개, 연속 3개
        //        int[] B = {1, 2, 3, 1, 1, 4};//13
        //        int[] C = {1, 2, 3, 1, 1, 3};//12
        //        int[] C2 = {1, 2, 3, 1, 1, 2};//13
        //        int[] C3 = {1, 2, 3, 1, 1, 1};//7
        //        int[] D = {1, 2, 3, 6, 1, 4};//20

        int[] A = {2, 3, 4, 1, 2};//10
        int[] B = {3, 2, 3, 4, 1, 2};//17
        int[] C = {2, 3, 2, 3, 4, 1, 2};//22
        int[] D = {1, 2, 3, 2, 3, 4, 1, 2};//33
        int[] E = {2, 1, 2, 3, 2, 3, 4, 1, 2};//36
        int[] F = {3, 2, 3, 4, 1, 2}; // 17
        int[] G = {3, 2, 3, 2, 4, 2, 1, 2}; // 22
        /*
            3 ---- 2 ----- 3 2 1 (2) -- 323 322 321
            3 2 ---- 3 -----  2 1 (2) -- 332 331 232 231
            3 2 (3) --- 2 ------ 1 2  -- 221 222
            3 2 (3 2) ----1 ---------2 -- 312 212
        */

        /*
             3 2 3 2 4 2 1 2

             3--2--3 2 4 x 1 x  : 323 322 324 321

             3 2--3--2 4 x 1 x  : 332 334 331 232 234 231

             3 2 x--2--4 2 1 x  : (324 322 321) 224 222 221 = ( 1 * 3 )

             3 2 x x--4--2 1 x  : 342 341 242 241

             3 2 x x 4--2--1 2  : (321 322) (221 222) 421 422= ( 1 * 2 )

             3 2 x x 4 x--1--2  : 312 212 412

         */

        System.out.println(solution(A));
        System.out.println(solution(B));
        System.out.println(solution(C));
        System.out.println(solution(D));
        System.out.println(solution(E));
        System.out.println(solution(F));
        System.out.println(solution(G));
    }

    public static int solution(int[] A) {
        int size = A.length;
        if (size < 3) {
            return 0;
        }

        // 마지막 숫자 정하기
        List<Integer> ListPost = new ArrayList<>();
        BitSet setPost = new BitSet();
        for (int index = A.length - 1; index > 1; index--) {
            setPost.set(A[index]);
            ListPost.add(setPost.cardinality());
        }

        int result = 0;

        // 첫번째 숫자 정하기
        List<Integer> ListPre = new ArrayList<>();
        BitSet setPre = new BitSet();
        setPre.set(A[0]);
        ListPre.add(setPre.cardinality());

        Map<Integer, Integer> traveled = new HashMap<>();
        // 가운데 숫자를 기준으로 뒤에서, 앞에서 구하기
        for (int index = 1; index < A.length - 1; index++) {
            setPre.set(A[index]);
            ListPre.add(setPre.cardinality());

            if (traveled.containsKey(A[index])) {
                result += (ListPre.get(index - 1) - ListPre.get(traveled.get(A[index]))) * ListPost.get(A.length - index - 2);
            } else {
                result += ListPre.get(index - 1) * ListPost.get(A.length - index - 2);
            }
            traveled.put(A[index], index - 1);
        }
        return result;
    }

    public static int slow(int[] A) {
        int size = A.length;
        if (size < 3) {
            return 0;
        }

        Map<Integer, Set<Integer>> watchMap = new HashMap<>();

        int sum = 0;
        for (int firstIdx = 0; firstIdx < A.length - 2; firstIdx++) {
            if (watchMap.containsKey(A[firstIdx])) {
                continue;
            }
            Set<Integer> seconds = new HashSet<>();
            for (int secondIdx = firstIdx + 1; secondIdx < A.length - 1; secondIdx++) {
                if (seconds.contains(A[secondIdx])) {
                    continue;
                }
                Set<Integer> thirds = new HashSet<>();
                for (int thirdIdx = secondIdx + 1; thirdIdx < A.length; thirdIdx++) {
                    thirds.add(A[thirdIdx]);
                }
                sum += thirds.size();
                seconds.add(A[secondIdx]);
            }
            watchMap.put(A[firstIdx], seconds);
        }
        return sum;
    }
}
