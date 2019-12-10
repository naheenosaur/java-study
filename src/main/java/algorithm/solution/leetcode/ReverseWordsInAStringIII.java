package algorithm.solution.leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWordsInAStringIII {
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

//        return Arrays.stream(s.split(" "))
//                .map(word -> new StringBuilder(word).reverse())
//                .reduce((a, b) -> a.append(" ").append(b))
//                .orElse(new StringBuilder()).toString();

        return Arrays.stream(s.split(" "))
                .map(word -> new StringBuilder(word).reverse())
                .collect(Collectors.joining(" "));
    }

    // tip 속도는 stream보다 for문이 빠르다. ( 정확도도.. 복잡할뿐 )
    public String reverseWords2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String[] strings = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            StringBuilder sb = new StringBuilder(string).reverse();
            result.append(sb).append(" ");
        }
        return result.toString().trim();
    }
}
