package algorithm.solution.leetcode;
// https://leetcode.com/problems/longest-substring-without-repeating-characters/
/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.LinkedList;

public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        LinkedList<Integer> subString = new LinkedList<>();
        int max = 0;
        for (char c : s.toCharArray()) {
            int num = c + 0;
            if (subString.contains(num)) {
                max = Math.max(max, subString.size());
                int bit = 0;
                while (bit != num) {
                    bit = subString.poll();
                }
                subString.add(num);
            } else {
                subString.add(num);
            }
        }
        max = Math.max(max, subString.size());
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(lengthOfLongestSubstring("qweasdqweasd")); // 6
        System.out.println(lengthOfLongestSubstring("qqqqqqqwwwweewewq")); // 3
        System.out.println(lengthOfLongestSubstring("qowimeomp")); // 6
        System.out.println(lengthOfLongestSubstring("qowime omp")); // 7
        System.out.println(lengthOfLongestSubstring(" ")); // 7
        //        for (char c : "qwertyuiopasdfghjklzxcvbnm".toCharArray()) {
        //            System.out.println(c - 'a' + 1);
        //        }

    }
}
