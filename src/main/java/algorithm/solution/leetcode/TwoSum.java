package algorithm.solution.leetcode;
/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueAndIndexMap = new HashMap<>(); // (숫자, index)
        int[] result = new int[2];
        for (int index = 0; index < nums.length; index++) {
            Integer pairIndex = valueAndIndexMap.getOrDefault(target - nums[index], null);
            if (pairIndex != null && pairIndex != index) {
                result[0] = pairIndex;
                result[1] = index;
                break;
            }
            valueAndIndexMap.put(nums[index], index);
        }
        return result;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        for (int a : result) { // 0, 1
            System.out.println(a); //
        }
    }
}
