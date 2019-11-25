package algorithm.solution.leetcode;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] possible = new boolean[s.length() + 1];
        possible[0] = true;
        for (int end = 1; end <= length; end++) {
            for (int start = 0; start < end; start++) {
                if (possible[start] && wordDict.contains(s.substring(start, end))) {
                    possible[end] = true;
                    break;
                }
            }
        }
        return possible[length];
    }
//
//    public static boolean wordBreak(String s, List<String> wordDict) {
//        // 첫 단어로 시작하는 단어 map을 만들어 준다.
//        Map<Character, List<String>> wordDictMap = new HashMap<>();
//        wordDict.forEach(word->{
//            List<String> words = wordDictMap.get(word.charAt(0));
//            if (words == null) {
//                words = new ArrayList<>();
//            }
//            words.add(word);
//            wordDictMap.put(word.charAt(0), words);
//        });
//
//        // 문자열을  DFS로 탐색한다.
//
//        boolean[] impossible = new boolean[s.length()];
//        return contains(0, s, wordDictMap, impossible);
//    }

    public static boolean contains(int index, String string, Map<Character, List<String>> wordDictMap, boolean[] impossible) {
        List<String> startList = wordDictMap.get(string.charAt(index));
        if (startList == null || startList.size() == 0) {
            impossible[index] = true;
            return false;
        }

        boolean anyMatch = startList.stream().anyMatch(word -> {
            if (string.length() - index < word.length()) {
                return false;
            }
            if (!impossible[index] && string.startsWith(word, index)) {
                if (string.length() - index == word.length()) {
                    return true;
                }
                return contains(index + word.length(), string, wordDictMap, impossible);
            }
            return false;
        });
        if (!anyMatch) {
            impossible[index] = true;
        }
        return anyMatch;
    }

    public static void main(String[] args) {
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("leet");
//        wordDict.add("code");
//        System.out.println(wordBreak("leetcode", wordDict));
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("apple");
//        wordDict.add("pen");
//        System.out.println(wordBreak("applepenapple", wordDict));
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("cats");
//        wordDict.add("dog");
//        wordDict.add("sand");
//        wordDict.add("and");
//        wordDict.add("cat");
//        System.out.println(wordBreak("catsandog", wordDict));

//        String[] strs = {"cbc", "bcda", "adb", "ddca", "bad", "bbb", "dad", "dac", "ba", "aa", "bd", "abab", "bb", "dbda", "cb", "caccc", "d", "dd", "aadb", "cc", "b", "bcc", "bcd", "cd", "cbca", "bbd", "ddd", "dabb", "ab", "acd", "a", "bbcc", "cdcbd", "cada", "dbca", "ac", "abacd", "cba", "cdb", "dbac", "aada", "cdcda", "cdc", "dbc", "dbcb", "bdb", "ddbdd", "cadaa", "ddbc", "babb"};
//        List<String> wordDict = new ArrayList<>();
//        Collections.addAll(wordDict, strs);
//        System.out.println(wordBreak("bccdbacdbdacddabbaaaadababadad", wordDict));

        List<String> wordDict = new ArrayList<>();
        wordDict.add("car");
        wordDict.add("ca");
        wordDict.add("rs");
        System.out.println(wordBreak("cars", wordDict));
    }
}
