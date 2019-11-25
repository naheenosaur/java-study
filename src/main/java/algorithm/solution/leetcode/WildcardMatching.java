package algorithm.solution.leetcode;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:


Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

 */
public class WildcardMatching {
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return (s.length() == 0);
        }

        int lastIndexOfStar = p.lastIndexOf('*');

        if (lastIndexOfStar == -1) {
            if (p.length() != s.length()) {
                return false;
            }
            for (int index = 0; index < s.length(); index++) {
                if (s.charAt(index) != p.charAt(index) && p.charAt(index) != '?') {
                    return false;
                }
            }
        } else {
            int checkedArrayIndex = 0;
            boolean isStar = false;
            int patternIndex = 0;
            int lastStarIndex = -1;
            for (int index = 0; patternIndex <= lastIndexOfStar; index++) {

                while (p.charAt(patternIndex) == '*') {
                    lastStarIndex = patternIndex;
                    if (!isStar) {
                        checkedArrayIndex = index;
                    }
                    isStar = true;
                    patternIndex++;
                    if (patternIndex == p.length()) {
                        return true;
                    }
                }
                if (patternIndex >= lastIndexOfStar) {
                    break;
                }
                if (index >= s.length()) {
                    return false;
                }
                if (s.charAt(index) == p.charAt(patternIndex)) {
                    isStar = false;
                    patternIndex++;
                } else if (p.charAt(patternIndex) == '?') {
                    isStar = false;
                    patternIndex++;
                } else if (isStar) {
                    continue;
                } else if (lastStarIndex >= 0) {
                    index = checkedArrayIndex++;
                    patternIndex = lastStarIndex + 1;
                } else {
                    return false;
                }
            }
            for (int index = 0; index < p.length() - lastIndexOfStar - 1; index++) {
                if (checkedArrayIndex + index >= s.length()) {
                    return false;
                }
                if (s.charAt(s.length() - 1 - index) != p.charAt(p.length() - 1 - index) && p.charAt(p.length() - 1 - index) != '?') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("", "***")); // true
        System.out.println(isMatch("qwerqweqweqwe", "????**")); // true
        System.out.println(isMatch("qwerrrr", "***????")); // true
        System.out.println(isMatch("qweqweqwer", "???***????")); // true
        System.out.println(!isMatch("", "m*issip*")); // false
        System.out.println(isMatch("mississippi", "m*issip*")); // true
        System.out.println(!isMatch("abcdef", "a?de*")); // false
        System.out.println(isMatch("aac", "*ac")); // false
        System.out.println(!isMatch("ab", "*a")); // false
        System.out.println(isMatch("hi", "*?")); // true
        System.out.println(!isMatch("mississippi", "m??*ss*?i*pi")); // false
        System.out.println(!isMatch("a", "aa")); // false
        System.out.println(isMatch("aaaa", "***a")); // true
        System.out.println(isMatch("waja", "*a")); // true
        System.out.println(isMatch("", "")); // true
        System.out.println(!isMatch("aa", "a")); // false
        System.out.println(isMatch("aa", "*")); // true
        System.out.println(!isMatch("cb", "?a")); // false
        System.out.println(isMatch("adceb", "*a*b")); // true
        System.out.println(!isMatch("acdcb", "a*c?b")); // false
        System.out.println(!isMatch("aacacccacaxb", "a*c?b")); // false
        System.out.println(isMatch("aacacccacab", "a*c?b")); // true
        System.out.println(!isMatch("", "a*c?b")); // false
    }
}
