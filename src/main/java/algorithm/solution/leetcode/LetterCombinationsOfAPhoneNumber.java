package algorithm.solution.leetcode;
/*
Given a string containing digits from 2-9 inclusive,
return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

// tip 쓸데없는 List를 만들지 말고 array를 이용하자.
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> strings = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return strings;
        }
        char[][] map = new char[8][];
        map[0] = "abc".toCharArray();
        map[1] = "def".toCharArray();
        map[2] = "ghi".toCharArray();
        map[3] = "jkl".toCharArray();
        map[4] = "mno".toCharArray();
        map[5] = "pqrs".toCharArray();
        map[6] = "tuv".toCharArray();
        map[7] = "wxyz".toCharArray();

        char[] chars = digits.toCharArray();
        strings.add("");
        for (char character : chars) {
            strings = append(map[character - '2'], strings);
        }
        return strings;
    }

    private List<String> append(char[] characters, List<String> results) {
        int size = results.size();
        for (int i = 0; i < size; i++) {
            String nowLetters = results.remove(0);
            for (char character : characters) {
                results.add(nowLetters + character);
            }
        }
        return results;
    }

    private enum Letter {
        a(2), b(2), c(2),
        d(3), e(3), f(3),
        g(4), h(4), i(4),
        j(5), k(5), l(5),
        m(6), n(6), o(6),
        p(7), q(7), r(7), s(7),
        t(8), u(8), v(8),
        w(9), x(9), y(9), z(9);
        int num;

        Letter(int num) {
            this.num = num;
        }

        static List<String> getLetters(char num) {
            return Arrays.stream(values())
                    .filter(letter -> Character.digit(num, 10) == letter.num)
                    .map(letter -> letter.name())
                    .collect(toList());
        }
    }
}