package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ZigzagConversionTest {
    private ZigzagConversion zigzagConversion = new ZigzagConversion();

    @Test
    void convertTest() {
        String input = "PAYPALISHIRING";

        assertThat(zigzagConversion.convert(input, 1)).isEqualTo("PAYPALISHIRING");
        assertThat(zigzagConversion.convert(input, 3)).isEqualTo("PAHNAPLSIIGYIR");
        assertThat(zigzagConversion.convert(input, 4)).isEqualTo("PINALSIGYAHRPI");
    }
}