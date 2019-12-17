package algorithm.solution.leetcode;

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        boolean[][] palindromic = new boolean[length][length];

        int max = 0;
        String result = "";
        for (int end = 0; end < length; end++) {
            for (int start = end; start >= 0; start--) {
                if (chars[start] == chars[end]) {
                    if (start == end || start == end - 1 || palindromic[start + 1][end - 1]) {
                        palindromic[start][end] = true;
                        if (end - start + 1 > max) {
                            max = end - start + 1;
                            result = s.substring(start, end + 1);
                        }
                    }
                }
            }
        }
        return result;
    }
}