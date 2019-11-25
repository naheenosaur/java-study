package algorithm.solution.leetcode;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
public class AddTowNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num = 0;

        ListNode l3 = new ListNode(0);
        ListNode temp = l3;
        while (true) {
            int value1 = 0, value2 = 0;
            if (l1 == null && l2 == null) {
                break;
            }
            if (l1 != null) {
                value1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value2 = l2.val;
                l2 = l2.next;
            }
            num += (value1 + value2);
            if (num > 10) {
                temp.next = new ListNode(num - 10);
                num = 1;
            } else {
                temp.next = new ListNode(num);
                num = 0;
            }

            temp = temp.next;
        }

        return l3.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(3);
        //        l1.setNext(new ListNode(4)).setNext(new ListNode(3));

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        //        ListNode temp = l2.next;
        //        temp.next = new ListNode(4);

        l2.next.next = new ListNode(4);
        //        l2.setNext(new ListNode(6)).setNext(new ListNode(4));

        //        l1.print();
        //        l2.print();

        ListNode l3 = addTwoNumbers(l1, l2);
        l3.print();
    }
    //
    //    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //        if (l1 == null && l2 == null) {
    //            return null;
    //        }
    //        int value1 = 0, value2 = 0;
    //        if (l1 != null) {
    //            value1 = l1.val;
    //            l1 = l1.next;
    //        }
    //        if (l2 != null) {
    //            value2 = l2.val;
    //            l2 = l2.next;
    //        }
    //        int num = value1 + value2;
    //        if (num > 9) {
    //            ListNode l3 = new ListNode(num - 10);
    //            l3.next = addTwoNumbers(addTwoNumbers(l1, l2), new ListNode(1));
    //            return l3;
    //        } else {
    //            ListNode l3 = new ListNode(num);
    //            l3.next = addTwoNumbers(l1, l2);
    //            return l3;
    //        }
    //    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        void print() {
            System.out.println(val);
            if (next != null) {
                next.print();
            }
        }
    }
}


