package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrappingRainWaterTest {
    TrappingRainWater solution = new TrappingRainWater();

    @Test
    void trap() {
        assertThat(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}))
                .isEqualTo(6);
        assertThat(solution.trapWithStack(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}))
                .isEqualTo(6);
    }
}