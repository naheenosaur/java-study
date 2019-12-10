package algorithm.solution.leetcode;

import java.util.Arrays;

/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int mostWater = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            boolean isLeftSmaller = (height[left] < height[right]);
            mostWater = Math.max((right - left) * (isLeftSmaller ? height[left] : height[right]), mostWater);
            if (isLeftSmaller) {
                left++;
            } else {
                right--;
            }
        }
        return mostWater;
    }

    public int maxArea2(int[] height) {
        int maxHeight = Arrays.stream(height).max()
                .orElseThrow(IllegalArgumentException::new);

        int max = 0;
        int[] mostLeftIndex = new int[maxHeight];
        for (int i = 0; i < height.length && max < maxHeight; i++) {
            int tempMax = Math.max(height[i], max);
            while (max < tempMax) {
                mostLeftIndex[max] = i + 1;
                max++;
            }
        }

        max = 0;
        int[] mostRightIndex = new int[maxHeight];
        for (int i = height.length - 1; i >= 0 && max < maxHeight; i--) {
            int tempMax = Math.max(height[i], max);
            while (max < tempMax) {
                mostRightIndex[max] = i + 1;
                max++;
            }
        }

        int mostWater = 0;
        for (int i = 0; i < maxHeight; i++) {
            mostWater = Math.max((i + 1) * (mostRightIndex[i] - mostLeftIndex[i]), mostWater);
        }
        return mostWater;
    }
}
