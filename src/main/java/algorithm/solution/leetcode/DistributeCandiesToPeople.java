package algorithm.solution.leetcode;

/*
We distribute some number of candies, to a row of n = num_people people in the following way:

We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.

Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.

This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).

Return an array (of length num_people and sum candies) that represents the final distribution of candies.



Example 1:

Input: candies = 7, num_people = 4
Output: [1,2,3,1]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0,0].
On the third turn, ans[2] += 3, and the array is [1,2,3,0].
On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
Example 2:

Input: candies = 10, num_people = 3
Output: [5,2,3]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0].
On the third turn, ans[2] += 3, and the array is [1,2,3].
On the fourth turn, ans[0] += 4, and the final array is [5,2,3].


Constraints:

1 <= candies <= 10^9
1 <= num_people <= 1000
 */
public class DistributeCandiesToPeople {
    public static int[] distributeCandies(int candies, int num_people) {
        // turn check
        int turn = 0;
        int[] result = new int[num_people];

        while (candies > ((turn + 1) * num_people) * ((turn + 1) * num_people + 1) / 2) {
            turn++;
        }

        if (turn > 0) {
            for (int index = num_people - 1; index >= 0; index--) {
                result[index] = (turn * (turn + 1) / 2) * num_people - (turn * (num_people - index - 1));
            }
        }

        int left = candies - (turn * num_people) * (turn * num_people + 1) / 2;

        for (int index = 0; index < num_people && left > 0; index++) {
            int num = Math.min(turn * num_people + index + 1, left);
            result[index] += num;
            left -= num;
        }

        return result;
    }

    public static int[] slow(int candies, int num_people) {
        int[] result = new int[num_people];
        for (int count = 1; candies > 0; count++) {
            int candy = Math.min(candies, count);
            candies -= candy;
            result[(count - 1) % num_people] += candy;
        }
        return result;
    }

    public static void main(String[] args) {
        //        print(slow(7, 4));
        //        print(distributeCandies(7, 4));
        //        print(slow(10, 3));
        //        print(distributeCandies(10, 3));
        print(slow(60, 4));
        print(distributeCandies(60, 4));
    }

    public static void print(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
