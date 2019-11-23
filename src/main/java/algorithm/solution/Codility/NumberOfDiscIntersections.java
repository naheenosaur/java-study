package algorithm.solution.Codility;
/*
We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0


There are eleven (unordered) pairs of discs that intersect, namely:

discs 1 and 4 intersect, and both intersect with all the other discs;
disc 2 also intersects with discs 0 and 3.
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).

 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NumberOfDiscIntersections {
    public static int solution(int[] A) {

        class Index {
            long idx;
            boolean isLeft;

            private Index(long idx, boolean isLeft) {
                this.idx = idx;
                this.isLeft = isLeft;
            }
        }

        List<Index> list = new ArrayList<>();

        int N = A.length;

        int fullCnt = 0;

        for (int i = 0; i < A.length; i++) {
            long min = Math.max((long) i - (long) A[i], 0);
            long max = Math.min((long) i + (long) A[i], N);
            if (min == 0 && max == N) {
                fullCnt++;
            } else {
                list.add(new Index(min, true));
                list.add(new Index(max, false));
            }
        }

        Comparator<Index> comparator = new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                if (o1.idx < o2.idx) {
                    return -1;
                } else if (o1.idx > o2.idx) {
                    return 1;
                } else {
                    if (o1.isLeft && !o2.isLeft) {
                        return -1;
                    } else if (!o1.isLeft && o2.isLeft) {
                        return 1;
                    }
                    return 0;
                }
            }
        };
        list.sort(comparator);

        int num = 0;
        num += (fullCnt * (fullCnt - 1)) / 2 + ((list.size() * fullCnt) / 2);
        if (num > 10000000) {
            return -1;
        }

        int inter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isLeft) {
                num += inter;
                if (num > 10000000) {
                    return -1;
                }
                inter++;
            }
            if (!list.get(i).isLeft) {

                inter--;
            }
        }
        if (inter >= 0) {
            num += inter;
        }
        return num;
    }

    public static void main(String[] args) {

        int[] arr = {1, 5, 2, 1, 4, 0};
        int[] arr2 = {1, 2147483647, 0};
        System.out.println(solution(arr));
    }
}
