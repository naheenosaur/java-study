package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutOfBoundaryPathsTest {
    OutOfBoundaryPaths solution = new OutOfBoundaryPaths();

    @Test
    void findPathsTest() {
        assertThat(solution.findPaths(2, 2, 1, 0, 0)).isEqualTo(2);
        assertThat(solution.findPaths(2, 2, 2, 0, 0)).isEqualTo(6);
        assertThat(solution.findPaths(2, 2, 3, 0, 0)).isEqualTo(14);

        assertThat(solution.findPaths(1, 3, 1, 0, 1)).isEqualTo(2);
        assertThat(solution.findPaths(1, 3, 2, 0, 1)).isEqualTo(8);
        assertThat(solution.findPaths(1, 3, 3, 0, 1)).isEqualTo(12);
        assertThat(solution.findPaths(1, 3, 4, 0, 1)).isEqualTo(24);

        assertThat(solution.findPaths(8, 50, 23, 5, 26)).isEqualTo(914783380);
    }
}