package algorithm.solution.Codility;
//https://app.codility.com/programmers/task/theater_tickets/
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

/*
    3개 이상 연속되어 있으면 3개째부터 지움
    맨 앞 1개 중에 3개 고르기 -> 0
    맨 앞 2개 중에 2개 고르기 -> 0
    맨 앞 3개 중에 3개 고르기 -> 1
    맨 앞 3개 중에 2개 고르기 (중복 제거) + 뒤에있는 1개 로 고르기
        뒤에 있는 1개가 한 번 도 나온 적 없는 경우 -> 1
        뒤에 있는 1개가 이미 나온 경우 ->
 */

import java.util.BitSet;

public class TheaterTickets {
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
        int[] D = {1, 2, 3, 2, 3, 4, 1, 2};//29
        int[] E = {2, 1, 2, 3, 2, 3, 4, 1, 2};//29
        System.out.println(solution2(A));
        System.out.println(solution2(B));
        System.out.println(solution2(C));
        System.out.println(solution2(D));
        System.out.println(solution2(E));
    }


    /*
        뒤에서 부터 탐색

        1, 2, 3, 1, 1, 2                         ( 13 )
                 1  1  2  ( 1개네 )


              3     1  2  ((1))
              3  1  1     ( 1개네 )
            //3  1     2 --> 있음 ( 이제 이 2개 짜리는 안봐도 댐 ) ( 1 + 1개 )


           2        1  2  ((1))
           2     1  1     ((1))
           2   3       2
           2   3    1     ( 2개네 )     ( 1 + 1 + 2개 )


     //1            1   2 --> 있음
       1         1  1     ((1))
       1       3        2 ((2))
       1       3    1
       1   2            2
       1   2        1
       1   2   3



     */
    //    public static int solution (int[] A) {
    //        Map<Integer, Set<Integer>> lastTwoMap = new HashMap<>();
    //        int sumTwo = 0;
    //        int size = A.length;
    //        // size - 2  --> sumTwo = 1
    //        // size - 3  --> sumTwo = 1 + 1 ( lastTwoMap.get(1).contains(2) )
    //
    //        for ( int i = size - 2; i >= 0; i-- ) {
    //            Set<Integer> last = lastTwoMap.getOrDefault(A[i], new HashSet<>());
    //            if ( ! last.isEmpty() ) {
    //                if ( last.contains())
    //            }
    //        }
    //
    //
    //        return 0;
    //    }

    public static int solution2(int[] A) {
        if (A.length < 3) {
            return 0;
        }

        int size = A.length;
        int sum = 0;
        BitSet first = new BitSet();
        for (int i = 0; i < size - 2; i++) {
            if (!first.get(A[i])) {
                first.set(A[i]);
                BitSet second = new BitSet();
                for (int j = i + 1; j < size - 1; j++) {
                    if (!second.get(A[j])) {
                        second.set(A[j]);
                        BitSet third = new BitSet();
                        for (int k = j + 1; k < size; k++) {
                            if (!third.get(A[k])) {
                                third.set(A[k]);
                                //                                System.out.println("" + A[i] + " " + A[j] + " " + A[k]);
                                sum++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("-----------------------");
        return sum;
    }
}
