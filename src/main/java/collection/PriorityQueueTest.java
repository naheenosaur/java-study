package collection;

import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {

        class Index implements Comparable<Index> {
            String a;
            Integer value;

            Index(String a, Integer value) {
                this.a = a;
                this.value = value;
            }

            @Override
            public int compareTo(Index o) {
                if (o.value > this.value) {
                    return -1;
                } else if (o.value.equals(this.value)) {
                    return 0;
                }
                return 1;
            }

            public String toString() {
                return "(" + this.a + "," + this.value + ")";
            }
        }

        PriorityQueue<Index> queue = new PriorityQueue<Index>();

        queue.add(new Index("a", 1));
        queue.add(new Index("v", 3));
        queue.add(new Index("c", 5));
        queue.add(new Index("c", 4));
        queue.add(new Index("c", 3));

        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }
}
