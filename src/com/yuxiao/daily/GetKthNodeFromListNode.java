package com.yuxiao.daily;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 *
 * @author yuxiao
 * @date 2021-09-02
 */
public class GetKthNodeFromListNode {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode tmp = null;
        for (int i = 2; i <= 5; i++) {
            if (head.next == null) {
                tmp = new ListNode(i);
                head.next = tmp;
            } else {
                tmp.next = new ListNode(i);
                tmp = tmp.next;
            }
        }
        ListNode kthFromEnd = getKthFromEnd(head, 2);
        System.out.println(kthFromEnd);

    }


    /**
     * 通过双节点定位法，节点步差为k个节点，这样当第一个节点的next为null时，说明第二个节点到达倒数k点
     *
     * @param head 头节点
     * @param k    倒数节点
     * @return 倒数节点后的数据
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        // 当快速的节点到达结尾时，head指向的节点为倒数k节点
        while (fast != null) {
            fast = fast.next;
            // 等于0时，头节点可以向后移动，此时fast节点和head节点的差距为k
            if (k == 0) {
                head = head.next;
            } else {
                k--;
            }
        }
        return head;
    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
