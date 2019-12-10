package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContainerWithMostWaterTest {
    ContainerWithMostWater solution = new ContainerWithMostWater();

    @Test
    void maxAreaTest() {
        assertThat(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})).isEqualTo(49);
    }
}