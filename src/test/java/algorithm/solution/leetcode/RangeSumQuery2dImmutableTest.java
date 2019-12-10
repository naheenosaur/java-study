package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RangeSumQuery2dImmutableTest {
    @Test
    void test() {

        RangeSumQuery2dImmutable obj = new RangeSumQuery2dImmutable(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5},
        });
        assertThat(obj.sumRegion(2, 1, 4, 3)).isEqualTo(8);
        assertThat(obj.sumRegion(1, 1, 2, 2)).isEqualTo(11);
        assertThat(obj.sumRegion(1, 2, 2, 4)).isEqualTo(12);
        assertThat(obj.sumRegion(0, 0, 2, 4)).isEqualTo(36);
        assertThat(obj.sumRegion(0, 2, 2, 4)).isEqualTo(19);
        assertThat(obj.sumRegion(1, 0, 2, 4)).isEqualTo(26);

        assertThat(new RangeSumQuery2dImmutable(new int[][]{{}}));
        RangeSumQuery2dImmutable obj2 = new RangeSumQuery2dImmutable(new int[][]{
                {-4, -5}
        });

        assertThat(obj2.sumRegion(0, 0, 0, 0)).isEqualTo(-4);
        assertThat(obj2.sumRegion(0, 0, 0, 1)).isEqualTo(-9);
        assertThat(obj2.sumRegion(0, 1, 0, 1)).isEqualTo(-5);

    }
}