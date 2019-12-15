package algorithm.solution.leetcode;
/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0 || coins == null) {
            return -1;
        }

        int max = amount + 1;
        int[] dp = new int[max];

        Arrays.fill(dp, max);
        int minIndex = max;

        for (int coin : coins) {
            minIndex = Math.min(minIndex, coin);
            if (coin <= amount) {
                dp[coin] = 1;
            }
            if (coin == amount) {
                return 1;
            }
        }

        for (int index = minIndex; index <= amount; index++) {
            for (int i = 1; i <= ( index +1 ) / 2; i++) {
                dp[index] = Math.min(dp[index], dp[index - i] + dp[i]);
            }
        }

        return dp[amount] == max ? -1 : dp[amount];
    }
}
