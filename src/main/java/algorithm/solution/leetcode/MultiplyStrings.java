package algorithm.solution.leetcode;

/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] arr1 = new int[num1.length()];
        int[] arr2 = new int[num2.length()];

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        for (int index = 0; index < arr1.length; index++) {
            arr1[index] = Character.getNumericValue(chars1[arr1.length - index - 1]);
        }
        for (int index = 0; index < arr2.length; index++) {
            arr2[index] = Character.getNumericValue(chars2[arr2.length - index - 1]);
        }

        int[] result = new int[num1.length() + num2.length()];

        int size = 0;
        for (int index1 = 0; index1 < arr1.length; index1++) {
            for (int index2 = 0; index2 < arr2.length; index2++) {
                int multiply = arr1[index1] * arr2[index2] + result[index1 + index2];
                result[index1 + index2] = multiply % 10;
                result[index1 + index2 + 1] = result[index1 + index2 + 1] + (multiply / 10);
            }
        }
        char[] resultChar;
        if (result[result.length - 1] == 0) {
            resultChar = new char[result.length - 1];

            for (int resultIndex = 0, index = result.length - 2; index >= 0; index--, resultIndex++) {
                resultChar[resultIndex] = Character.forDigit(result[index], 10);
            }
        } else {
            resultChar = new char[result.length];

            for (int resultIndex = 0, index = result.length - 1; index >= 0; index--, resultIndex++) {
                resultChar[resultIndex] = Character.forDigit(result[index], 10);
            }
        }
        return String.valueOf(resultChar);
    }

    public static void main(String[] args) {

        System.out.println(multiply("999", "0"));
    }
}
