package algorithm.solution.programers;
/*
124 나라가 있습니다. 124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.

124 나라에는 자연수만 존재합니다.
124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
예를 들어서 124 나라에서 사용하는 숫자는 다음과 같이 변환됩니다.

10진법	124 나라	10진법	124 나라
1	    1	        6	    14          11      42          16      121
2	    2	        7	    21          12      44          17      122
3	    4	        8	    22          13      111         18      124
4	    11	        9   	24          14      112         19      141
5	    12  	    10	    41          15      114         20      142


자연수 n이 매개변수로 주어질 때, n을 124 나라에서 사용하는 숫자로 바꾼 값을 return 하도록 solution 함수를 완성해 주세요.

제한사항
n은 500,000,000이하의 자연수 입니다.
입출력 예
n	result
1	1
2	2
3	4
4	11
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P12899 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            Integer div = Integer.parseInt(bufferedReader.readLine());
            Solution solution = new Solution();
            System.out.println(solution.solution(div));
        }
    }

    static class Solution {
        StringBuilder RESULT;

        public String solution(int n) {
            String answer = "";
            RESULT = new StringBuilder();
            while (true) {
                int remain = n % 3;
                n = (n - 1) / 3;
                RESULT.append(getSB(remain));
                if (n == 0) {
                    break;
                }
            }
            return RESULT.reverse().toString();
        }

        private Integer getSB(Integer num) {
            switch (num) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    return 4;
            }
        }
    }
}

