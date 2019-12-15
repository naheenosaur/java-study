package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoinChangeTest {
    @Test
    void coinChange() {
        CoinChange coinChange = new CoinChange();
        assertThat(coinChange.coinChange(new int[]{1, 2, 5}, 11)).isEqualTo(3);
        assertThat(coinChange.coinChange(new int[]{2, 5}, 21)).isEqualTo(6);
        assertThat(coinChange.coinChange(new int[]{1, 5}, 21)).isEqualTo(5);
        assertThat(coinChange.coinChange(new int[]{2}, 3)).isEqualTo(-1);
    }
}