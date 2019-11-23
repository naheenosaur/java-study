package algorithm.solution.programers;

/*
처리해야 할 동일한 작업이 n 개가 있고, 이를 처리하기 위한 CPU가 있습니다.

이 CPU는 다음과 같은 특징이 있습니다.

CPU에는 여러 개의 코어가 있고, 코어별로 한 작업을 처리하는 시간이 다릅니다.
한 코어에서 작업이 끝나면 작업이 없는 코어가 바로 다음 작업을 수행합니다.
2개 이상의 코어가 남을 경우 앞의 코어부터 작업을 처리 합니다.
처리해야 될 작업의 개수 n과, 각 코어의 처리시간이 담긴 배열 cores 가 매개변수로 주어질 때,
마지막 작업을 처리하는 코어의 번호를 return 하는 solution 함수를 완성해주세요.

제한 사항
코어의 수는 10,000 이하 2이상 입니다.
코어당 작업을 처리하는 시간은 10,000이하 입니다.
처리해야 하는 일의 개수는 50,000개를 넘기지 않습니다.
 */

// other solution : timeout이 나고 있으니, 최댓값부터 binary search로 해당값 찾아줌
public class P30_12920 {
    public static void main(String[] args) {
        int a = 17;
        int[] arr = {2, 3, 1};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 3, 6};
        int[] arr4 = {7, 3};
        int[] arr5 = {1, 9, 3};
        int[] arr6 = {3, 1, 4, 5};
        int[] arr7 = {3, 1, 4, 5, 6, 6, 7, 7, 7, 8, 9, 51};
        System.out.println(solution(17, arr) == solution2(17, arr)); // 3
        System.out.println(solution(6, arr2) == solution2(6, arr2)); // 2
        System.out.println(solution(19, arr3) == solution2(19, arr3)); // 2
        System.out.println(solution(97, arr4) == solution2(97, arr4)); // 2
        System.out.println(solution(2, arr4) == solution2(2, arr4)); //
        System.out.println(solution(44, arr5) == solution2(44, arr5)); // 1
        System.out.println(solution(5, arr6) == solution2(5, arr6)); // 1
        System.out.println(solution(170000220, arr7) == solution2(170000220, arr7)); // 3
    }

    public static int solution(int n, int[] cores) {

        return 1;
    }

    ////     1. 정확도 100 %
    public static int solution2(int n, int[] cores) {
        int i = 1;
        int index = cores.length + 1;
        if (n < index) {
            return n;
        }
        while (true) {
            for (int core = 0; core < cores.length; core++) {
                if (i % cores[core] == 0) {
                    if (index == n) {
                        return core + 1;
                    }
                    index++;
                }
            }
            i++;
        }
    }

    //    public static int solution(int n, int[] cores) {
    //        int length = cores.length;
    //        int lcm_num = nlcm(cores);
    //
    //        int asum = 0;
    //        for ( int i = 0; i < length; i++ ) {
    //            asum += lcm_num / cores[i];
    //        }
    //
    //        int start = (n - length) % asum + length;
    //
    //        int i = 1;
    //        int index = length + 1;
    //        while (true) {
    //            for ( int core = 0 ; core < length; core++ ) {
    //                if ( i % cores[core] == 0 ) {
    //                    if ( index == start ) {
    //                        return core + 1;
    //                    }
    //                    index++;
    //                }
    //            }
    //            i++;
    //        }
    //    }
    //
    //    static int nlcm(int[] num) {
    //        int numLength = num.length;
    //        int answer = num[0];
    //        int number = 0;
    //        int high = 0;
    //        int low = 0;
    //
    //        for (int i = 1; i < numLength; i++) {
    //            number = num[i];
    //            high = Math.max(answer, number);
    //            low = Math.min(answer, number);
    //            answer = high * low / gcd(high, low);
    //        }
    //        return answer;
    //    }
    //
    //    static int gcd(final int high, final int low) {
    //        if (low == 0) {
    //            return high;
    //        } else {
    //            return gcd(low, high % low);
    //        }
    //    }

    //    public static int solution (int n, int[] cores) {
    //        int size = cores.length;
    //        if ( n <= size ) {
    //            return n;
    //        }
    //        List<Integer> order_list = new ArrayList<>();
    //        BitSet bitset = new BitSet(size);
    //
    //        int i = 0;
    //        int low = 1;
    //        while (bitset.cardinality() != size) {
    //            if ( i >=  size ) {
    //                i = 0;
    //                low++;
    //                bitset = new BitSet(size);
    //            }
    //            if ( low % cores[i] == 0 ) {
    //                bitset.set(i);
    //                order_list.add(i + 1);
    //            }
    //            i++;
    //        }
    //        int index = ((n - size - 1) % (order_list.size()));
    //        return order_list.get(index);
    //    }
}
