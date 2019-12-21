package algorithm.solution.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together.
Twelve is written as, XII, which is simply X + II.
The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right.
However, the numeral for four is not IIII.
Instead, the number four is written as IV.
Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.
Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"
Example 2:

Input: 4
Output: "IV"
Example 3:

Input: 9
Output: "IX"
Example 4:

Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
Example 5:

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        if (num <= 0 || num >= 4000) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        int digit = 1;
        while (num > 0) {
            result.insert(0, Roman.get(num % 10, digit++));
            num /= 10;
        }
        return result.toString();
    }

    public enum Roman {
        I(1, 1), V(1, 5),
        X(2, 1), L(2, 5),
        C(3, 1), D(3, 5),
        M(4, 1),
        IV(1, 4, true), IX(1, 9, true),
        XL(2, 4, true), XC(2, 9, true),
        CD(3, 4, true), CM(3, 9, true);

        int digit;
        int value;
        boolean fix;

        Roman(int digit, int value) {
            this.digit = digit;
            this.value = value;
            this.fix = false;
        }

        Roman(int digit, int value, boolean fixed) {
            this.digit = digit;
            this.value = value;
            this.fix = fixed;
        }

        public static String get(int num, int digit) {
            if (num == 0) {
                return "";
            }
            List<Roman> romans = Arrays.stream(values()).filter(roman -> roman.digit == digit).collect(toList());
            if (romans.size() == 0) {
                throw new IllegalArgumentException();
            }
            Roman result = null;
            for (Roman roman : romans) {
                if (roman.value == num) {
                    return roman.name();
                }
                if (roman.value < num && ! roman.fix) {
                    result = roman;
                }
            }
            if (result == null) {
                throw new IllegalArgumentException();
            }
            return result.name() +
                    String.join("", Collections.nCopies(num - result.value, romans.get(0).name()));
        }
    }
}
