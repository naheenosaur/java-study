package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LetterCombinationsOfAPhoneNumberTest {
    LetterCombinationsOfAPhoneNumber solution = new LetterCombinationsOfAPhoneNumber();

    @Test
    void letterCombinationsTest() {
        assertThat(solution.letterCombinations("23"))
                .containsOnly("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");

    }
}