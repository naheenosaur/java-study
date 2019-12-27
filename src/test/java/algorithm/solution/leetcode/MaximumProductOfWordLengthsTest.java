package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaximumProductOfWordLengthsTest {
    @Test
    void maxProduct() {
        MaximumProductOfWordLengths solution = new MaximumProductOfWordLengths();
        assertThat(solution.maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"} )).isEqualTo(16);
        assertThat(solution.maxProduct(new String[]{"eae","ea","aaf","bda","fcf","dc","ac","ce","cefde","dabae"})).isEqualTo(15);
        assertThat(solution.maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"} )).isEqualTo(4);
        assertThat(solution.maxProduct(new String[]{"a","aa","aaa","aaaa"} )).isEqualTo(0);
        assertThat(solution.maxProduct(new String[]{} )).isEqualTo(0);
    }
}