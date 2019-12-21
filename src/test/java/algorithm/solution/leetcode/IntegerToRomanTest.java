package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerToRomanTest {
    IntegerToRoman solution = new IntegerToRoman();

    @Test
    void intToRoman() {
        assertThrows(IllegalArgumentException.class, () -> solution.intToRoman(0));
        assertThrows(IllegalArgumentException.class, () -> solution.intToRoman(4000));
        assertThat(solution.intToRoman(3)).isEqualTo("III");
        assertThat(solution.intToRoman(10)).isEqualTo("X");
        assertThat(solution.intToRoman(4)).isEqualTo("IV");
        assertThat(solution.intToRoman(9)).isEqualTo("IX");
        assertThat(solution.intToRoman(58)).isEqualTo("LVIII");
        assertThat(solution.intToRoman(1994)).isEqualTo("MCMXCIV");
        assertThat(solution.intToRoman(1000)).isEqualTo("M");
        assertThat(solution.intToRoman(3999)).isEqualTo("MMMCMXCIX");
    }
}