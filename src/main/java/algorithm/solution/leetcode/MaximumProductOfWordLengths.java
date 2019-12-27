package algorithm.solution.leetcode;
/*
Given a string array words,
find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 */

import java.util.BitSet;

public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        int size = words.length;
        BitSet[] alphabets = new BitSet[size];

        for (int index = 0; index < size; index++) {
            alphabets[index] = getBitSet(words[index]);
        }

        int max = 0;
        for (int a = 0; a < size-1; a++) {
            for (int b = a + 1; b < size; b++) {
                if (!alphabets[a].intersects(alphabets[b])) {
                    max = Math.max(max, words[a].length() * words[b].length());
                }
            }
        }
        return max;
    }

    private BitSet getBitSet(String str) {
        char[] chars = str.toCharArray();

        BitSet result = new BitSet(26);
        for (char c : chars) {
            result.set(c-48);
        }
        return result;
    }

}
