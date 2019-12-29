package algorithm.solution.leetcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindMedianFromDataStreamTest {
    @Test
    void negativeTest() {
        FindMedianFromDataStream obj = new FindMedianFromDataStream();
        obj.addNum(-1);
        assertThat(obj.findMedian()).isEqualTo(-1);
        obj.addNum(-2);
        assertThat(obj.findMedian()).isEqualTo(-1.5);
        obj.addNum(-3);
        assertThat(obj.findMedian()).isEqualTo(-2);
    }
    @Test
    void positiveTest1() {
        FindMedianFromDataStream obj = new FindMedianFromDataStream();
        obj.addNum(2);
        assertThat(obj.findMedian()).isEqualTo(2);
        obj.addNum(4);
        assertThat(obj.findMedian()).isEqualTo(3);
        obj.addNum(6);
        assertThat(obj.findMedian()).isEqualTo(4);
    }
    @Test
    void positiveTest2() {
        FindMedianFromDataStream obj = new FindMedianFromDataStream();
        obj.addNum(6);
        assertThat(obj.findMedian()).isEqualTo(6);
        obj.addNum(10);
        assertThat(obj.findMedian()).isEqualTo(8);
        obj.addNum(2);
        assertThat(obj.findMedian()).isEqualTo(6);
        obj.addNum(6);
        assertThat(obj.findMedian()).isEqualTo(6);
        obj.addNum(5);
        assertThat(obj.findMedian()).isEqualTo(6);
        obj.addNum(0);
        assertThat(obj.findMedian()).isEqualTo(5.5);
    }
}