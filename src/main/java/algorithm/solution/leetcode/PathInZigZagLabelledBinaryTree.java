package algorithm.solution.leetcode;
/*
In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.

Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]


Constraints:

1 <= label <= 10^6
 */

import java.util.ArrayList;
import java.util.List;

public class PathInZigZagLabelledBinaryTree {
    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<>();
        // 1 / 3,   2 / 4, 5,   6, 7 / 15, 14, 13, 12,   11, 10, 9, 8
        // 5 --> 2 / 2 --> 1 / 1 --> 0
        // 6 --> 3 / 3 --> 1 / 1 --> 0
        //  0, 1 / 2, 3 / 4, 5 / 6, 7
        if (label == 1) {
            result.add(1);
            return result;
        }

        int turn = 0;
        int num = 0;
        while (num < label) {
            num = (int) (Math.pow(2, ++turn) - 1);
            result.add(num);
        }

        int index;

        if (turn % 2 == 0) { // getting smaller
            index = result.get(turn - 1) - label;
        } else { // getting bigger
            index = label - result.get(turn - 2) - 1;
        }
        result.set(--turn, label);
        index = (index + 2) / 2 - 1;

        for (; turn > 1; turn--) {
            if (turn % 2 == 0) { // getting smaller
                label = (int) (Math.pow(2, turn) - index) - 1;
            } else { // getting bigger
                label = (int) (Math.pow(2, turn - 1) + index);
            }
            result.set(turn - 1, label);
            index = (index + 2) / 2 - 1;
        }

        return result;
    }

    public static void main(String[] args) {
        print(pathInZigZagTree(13));
        print(pathInZigZagTree(14));
        print(pathInZigZagTree(26));
    }

    public static void print(List<Integer> result) {
        System.out.print("[ ");
        for (Integer num : result) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
