package algorithm.solution.leetcode;

/*
Given n non-negative integers representing an elevation map
where the width of each bar is 1,
compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.
Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

import java.util.Stack;

public class TrappingRainWater {
    public int trap(int[] height) {
        int size = height.length;
        int[] fromLeft = new int[size];
        int[] fromRight = new int[size];

        int max = 0;
        for (int i = 0; i < size; i++) {
            max = Math.max(max, height[i]);
            fromLeft[i] = max;
        }
        max = 0;
        for (int i = size - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            fromRight[i] = max;
        }

        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += (Math.min(fromLeft[i], fromRight[i]) - height[i]);
        }
        return sum;
    }

    public int trapWithStack(int[] height) {
        int sum = 0, currentIndex = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[currentIndex] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.empty()) {
                    break;
                }
                int distance = currentIndex - stack.peek() - 1;
                int boundHeight = Math.min(height[currentIndex], height[stack.peek()]) - height[top];
                sum += distance * boundHeight;
            }
            stack.push(currentIndex++);
        }
        return sum;
    }
}
