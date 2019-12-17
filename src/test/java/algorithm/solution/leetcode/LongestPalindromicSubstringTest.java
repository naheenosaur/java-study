package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestPalindromicSubstringTest {
    private LongestPalindromicSubstring solution = new LongestPalindromicSubstring();

    @Test
    void testLongestPalindrome() {
        assertThat(solution.longestPalindrome("a")).isIn("a");
        assertThat(solution.longestPalindrome("babad")).isIn("aba", "bab");
        assertThat(solution.longestPalindrome("cbbd")).isIn("bb");
        assertThat(solution.longestPalindrome("abbbabaaabbbaaa")).isIn("aaabbbaaa");
    }
}
