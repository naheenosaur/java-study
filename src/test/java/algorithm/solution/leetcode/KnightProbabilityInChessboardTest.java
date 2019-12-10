package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightProbabilityInChessboardTest {
    KnightProbabilityInChessboard solution = new KnightProbabilityInChessboard();
    @Test
    void knightProbabilityTest() {
        assertThat(solution.knightProbability(3, 2, 0, 0)).isEqualTo(0.0625);
        assertThat(solution.knightProbability(3, 3, 0, 0)).isEqualTo(0.015625);
        assertThat(solution.knightProbability(3, 1, 1, 1)).isEqualTo(0);
        assertThat(solution.knightProbability(3, 2, 1, 1)).isEqualTo(0);
        assertThat(solution.knightProbability(10, 3, 5, 3)).isEqualTo(0.787109375);
        assertThat(solution.knightProbability(10, 6, 5, 3)).isEqualTo(0.45009613037109375);
        assertThat(solution.knightProbability(10, 13, 5, 3)).isEqualTo(0.11733772067236714);

    }
}