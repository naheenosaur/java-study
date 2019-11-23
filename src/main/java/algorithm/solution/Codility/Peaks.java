package algorithm.solution.Codility;
/*
A non-empty array A consisting of N integers is given.

A peak is an array element which is larger than its neighbors. More precisely, it is an index P such that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].

For example, the following array A:

    A[0] = 1
    A[1] = 2
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2
has exactly three peaks: 3, 5, 10.

We want to divide this array into blocks containing the same number of elements. More precisely, we want to choose a number K that will yield the following blocks:

A[0], A[1], ..., A[K − 1],
A[K], A[K + 1], ..., A[2K − 1],
...
A[N − K], A[N − K + 1], ..., A[N − 1].
What's more, every block should contain at least one peak. Notice that extreme elements of the blocks (for example A[K − 1] or A[K]) can also be peaks, but only if they have both neighbors (including one in an adjacent blocks).

The goal is to find the maximum number of blocks into which the array A can be divided.

Array A can be divided into blocks as follows:

one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has a peak.
three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak. Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3], because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.
However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2), because the (1, 2, 3) blocks do not contain a peak. Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].

The maximum number of blocks that array A can be divided into is three.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the maximum number of blocks into which A can be divided.

If A cannot be divided into some number of blocks, the function should return 0.

For example, given:

    A[0] = 1
    A[1] = 2
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2
the function should return 3, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [0..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N*log(log(N)));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
 */

import java.util.ArrayList;
import java.util.List;

public class Peaks {
    public static int solution(int[] arr) {
        List<Integer> pickIdx = new ArrayList<>();
        List<Integer> gcb = new ArrayList<>();
        int temp = 1;
        int max = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr.length % i == 0) {
                gcb.add(i);
            }
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                pickIdx.add(i);
                if (max < temp) {
                    max = temp;
                }
                temp = 1;
            } else {
                temp++;
            }
        }
        gcb.add(arr.length);

        if (max < temp) {
            max = temp;
        }

        if (pickIdx.size() <= 1) {
            return pickIdx.size();
        }

        int idx = 0;
        while (gcb.get(idx) < (max + 1) / 2) {
            idx++;
        }
        int tempGap = gcb.get(idx);
        int div = arr.length / tempGap;

        while (true) {
            int find = 0;
            for (int i = 0; i < div; i++) {
                if (div - i > pickIdx.size() - find) {
                    break;
                }
                boolean is = false;
                for (int j = 0; j < tempGap; j++) {
                    if (pickIdx.contains(tempGap * i + j)) {
                        is = true;
                        find++;
                        break;
                    }
                }
                if (!is) {
                    break;
                }
                if (i == div - 1) {
                    return div;
                }
            }
            tempGap = gcb.get(++idx);
            div = arr.length / tempGap;
            if (div <= 1) {
                return div;
            }
        }
    }

    //    public static int solution(int[] arr) {
    //        // 1. 픽을 찾는다
    //        List<Integer> pickIdx = new ArrayList<>();
    ////        List<Integer> gap = new ArrayList<>();
    //        int temp = 1;
    //        int max = 0;
    //        for (int i = 1; i < arr.length - 1; i++) {
    //            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
    //                pickIdx.add(i);
    ////                if (temp != 0) {
    ////                    gap.add(temp);
    ////                }
    //                if (max < temp) {
    //                    max = temp;
    //                }
    //                temp = 1;
    //            } else {
    //                temp++;
    //            }
    //        }
    //        if (max < temp) {
    //            max = temp;
    //        }
    //
    ////        gap.add(temp + 1);
    //
    //        if (pickIdx.size() == 0) {
    //            return 0;
    //        }
    //
    //        // 2. 픽 거리중에 제일 짧은 길이 * 2
    ////        gap.sort((a, b) -> {
    ////            if (a < b) {
    ////                return -1;
    ////            } else if ( a > b){
    ////                return 1;
    ////            }
    ////            return 0;
    ////        });
    ////
    ////        if (gap.size() == 0) {
    ////            return 0;
    ////        }
    ////
    ////        int startGap = gap.get(0);
    //
    //        int div = arr.length / ( (max+1) / 2 );
    //        int startGap = arr.length / div;
    //
    //
    ////        while (arr.length % startGap != 0) {
    ////            startGap--;
    ////        }
    ////        int div = arr.length / startGap;
    //
    //        while (true) {
    //            int tempGap = startGap;
    //            int find = 0;
    //            for (int i = 0; i < div; i++) {
    //                if (div - i > pickIdx.size() - find) {
    //                    break;
    //                }
    //                boolean is = false;
    //                tempGap = arr.length / div;
    //                for (int j = 0; j < tempGap; j++) {
    //                    if (pickIdx.contains(tempGap * i + j)) {
    //                        is = true;
    //                        break;
    //                    }
    //                }
    //                if (!is) {
    //                    break;
    //                }
    //                if (i == div - 1) {
    //                    return div;
    //                }
    //            }
    //            div = arr.length / (tempGap + 1);
    //            if (div <= 1) {
    //                return div;
    //            }
    //        }
    //    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2};
        int[] arr1 = {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 6, 2};
        int[] arr2 = {1, 3, 2, 1};
        int[] arr3 = {1, 1000, 5};
        int[] arr4 = {1, 1000, 5, 6, 100, 5, 100, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        int[] arr5 = {1, 1000, 5, 6, 100, 5, 100, 8, 9, 10, 11, 112, 13, 14, 115, 16, 117, 16, 115, 14, 15, 17, 181, 19, 20, 21, 21, 21, 14, 13, 12, 12, 111, 1, 11};
        int[] arr6 = {1, 1000, 5, 6, 100, 1};
        System.out.println(solution(arr5));
    }
}
