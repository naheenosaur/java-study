package algorithm.solution.leetcode;
/*
Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value.
So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.


Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2


Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */

import java.util.PriorityQueue;
import java.util.Stack;

public class FindMedianFromDataStream {
    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    int mid;

    public FindMedianFromDataStream() {
        left = new PriorityQueue<>((i1, i2) -> i2.compareTo(i1));
        right = new PriorityQueue<>((i1, i2) -> i1.compareTo(i2));
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    public void addNum(int num) {
        if (left.size() == 0) {
            left.add(num);
            mid = num;
            return;
        }
        if (mid < num) {
            right.add(num);
        } else {
            left.add(num);
        }
    }

    public double findMedian() {
        // right 앞쪽에 있는 애들을 left 앞쪽으로 하나씩 옮겨줌
        // left는 항상 right보다 크거나 같다.
        int sizeDiff = left.size() - right.size();

        if (sizeDiff < 0) {
            while (sizeDiff < 0) {
                left.add(right.poll());
                sizeDiff = sizeDiff +2;
            }
        } else {
            while (sizeDiff > 1) {
                right.add(left.poll());
                sizeDiff = sizeDiff -2;
            }
        }
        mid = left.peek();
        if (sizeDiff == 0) {
            return ((double) mid + (double) right.peek()) / 2;
        } else if (sizeDiff == 1) {
            return mid;
        }
        return 0;
    }
}
