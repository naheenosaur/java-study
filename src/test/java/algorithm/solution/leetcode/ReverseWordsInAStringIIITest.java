package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReverseWordsInAStringIIITest {
    ReverseWordsInAStringIII solution = new ReverseWordsInAStringIII();

    @Test
    void reverseWordsTest() {
        assertThat(solution.reverseWords("Let's take LeetCode contest"))
                .isEqualTo("s'teL ekat edoCteeL tsetnoc");
    }
}