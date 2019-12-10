package algorithm.solution.leetcode;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 */

/*
P   A   H   N
A P L S I I G
Y   I   R
--> num = 2
--> 0, 2, 4, 6, 8, 10, 12
--> 1, 3, 5, 7, 9, 11, 13

--> num = 3
--> 0, 4, 8, 12
--> 1, 3, 5, 7, 9, 11, 13
--> 2, 6, 10

--> num = 4
--> 0, 6, 12, 18
--> 1, 5, 7, 11, 13, 17, 19
--> 2, 4, 8, 10, 14, 16, 20
--> 3, 9, 15, 21

--> num = 5
--> 0, 8, 16
--> 1, 7, 9, 15, 17
--> 2, 6, 10, 14, 18
--> 3, 5, 11, 13, 19, 21
--> 4,12, 20

--> num = 6
--> 0, 10, 20,
--> 1, 9, 11, 19, 21
--> 2, 8, 12, 18, 22
--> 3, 7, 13, 17, 23
--> 4, 6, 14, 16, 24
--> 5, 15,


 */

import java.util.List;

public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (s.length() < numRows || numRows == 1) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        for (int line = 1; line <= numRows; line++) {
            result.append(getLine(s, numRows, line));
        }
        return result.toString();
    }

    private StringBuilder getLine(String inputs, int numRows, int line) {
        StringBuilder str = new StringBuilder();

        int rest1, rest2;
        int divideNum = numRows * 2 - 2;
        rest1 = line - 1;
        rest2 = divideNum - line + 1;
        for (int index = 0; index < inputs.length(); index++) {
            if (index % divideNum == rest1 || index % divideNum == rest2) {
                str.append(inputs.charAt(index));
            }
        }
        return str;
    }

    private StringBuilder getLine2(List<Character> inputs, int numRows, int line) {
        StringBuilder reversed = new StringBuilder();

        int divideNum = (line == 1) ? (numRows - line) * 2 : ((numRows - line) * 2 + 1);
        int rest = (line == 1) ? 0 : divideNum - 1;

        for (int index = inputs.size() - 1; index > 0; index--) {
            if (index % divideNum == 0 || index % divideNum == rest) {
                reversed.append(inputs.remove(index));
            }
        }
        reversed.append(inputs.remove(0));
        return reversed.reverse();
    }
}
