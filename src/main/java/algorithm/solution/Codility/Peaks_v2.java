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

/*
https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/

https://app.codility.com/demo/results/trainingSAZ5A2-7B7/

https://app.codility.com/demo/results/training68HP9B-A4S/
bitset 사용, 큰값부터 탐색

 */

// peak이 없으면 0 리턴
// peak은 있는데 안 나눠지면 1 리턴

// 1. 배열 크기의 약수를 구함
// 2. peaks 를 구하는데, 가장 간격이 큰 것을 구함
// 3. 그 간격 보다 바로 큰 약수 >>>> 그 간격 / 2 보다 바로 큰 약수 까지 구해보고 없으면 return

import java.util.BitSet;

public class Peaks_v2 {
    public static int solution(int[] arr) {

        int maxGap = 0; // gap 은 edge 포힘
        int startMaxGap = 0;
        int endMaxGap = 0;
        BitSet divided = new BitSet(); // 약수들의 모임 -> 요 개수만큼 나눠짐
        BitSet peakSet = new BitSet(); // peak 들의 모임
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr.length % i == 0 && i != 1) {
                divided.set(i);
            }
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                if (peakSet.length() > 0) {
                    if (i + 1 - peakSet.length() > maxGap) {
                        startMaxGap = peakSet.length();
                        endMaxGap = i;
                        maxGap = endMaxGap - startMaxGap + 1;
                    }
                }
                peakSet.set(i);
            }
        }

        if (arr.length - peakSet.length() > maxGap) {
            startMaxGap = peakSet.length();
            endMaxGap = arr.length - 1;
            maxGap = endMaxGap - startMaxGap + 1;
        }

        if (peakSet.length() <= 0) {
            return 0;
        }

        if (divided.length() == 0) {
            return 1;
        }

        // peakSet.cardinality() -> peak의 개수, 이것보다 많이 나눌 수는 없아
        // --> arr.length / peakSet.cardinality() 갭보다는 gap이 작아야 한다. --> 이것보다 gap 이 커지면 return 1

        // maxgap

        return 1;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}; // 3
        int[] arr1 = {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 6, 2}; // 1 2 3 (4) 3 2 1 2 3 4 (6) 2
        // 1 2 3 4 3 2 / 1 2 3 4 6 2
        int[] arr2 = {1, 3, 2, 1};
        int[] arr3 = {1, 1000, 5};
        int[] arr4 = {1, 1000, 5, 6, 100, 5, 100, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        int[] arr5 = {1, 1000, 5, 6, 100, 5, 100, 8, 9, 10, 11, 112, 13, 14, 115, 16, 117, 16, 115, 14, 15, 17, 181, 19, 20, 21, 21, 21, 14, 13, 12, 12, 111, 1, 11};
        int[] arr6 = {1, 1000, 5, 6, 100, 1};
        int[] arr7 = {0, 1, 0, 0, 0};
        int[] arr8 = {0, 1, 2, 3, 4};
        //        System.out.println(solution(arr)); //3
        System.out.println(solution(arr1)); //2
        System.out.println(solution(arr2)); //1
        System.out.println(solution(arr3)); //1
        System.out.println(solution(arr4)); //1
        System.out.println(solution(arr5)); //5
        System.out.println(solution(arr6)); //2
        System.out.println(solution(arr7)); //1
        System.out.println(solution(arr8)); //0
    }
}
