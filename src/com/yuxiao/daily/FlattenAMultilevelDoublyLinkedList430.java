package com.yuxiao.daily;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 430. 扁平化多级双向链表
 * <p>
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * <p>
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-24
 */
public class FlattenAMultilevelDoublyLinkedList430 {


    public static void main(String[] args) {
        Node head = new Node();
        head.val = 1;
        Node node2 = linkNode(head, 2, false);
        Node node3 = linkNode(node2, 3, false);
        Node node4 = linkNode(node3, 4, false);
        Node node5 = linkNode(node4, 5, false);
        Node node6 = linkNode(node5, 6, false);

        Node node7 = linkNode(node3, 7, true);
        Node node8 = linkNode(node7, 8, false);
        Node node9 = linkNode(node8, 9, false);
        Node node10 = linkNode(node9, 10, false);

        Node node11 = linkNode(node8, 11, true);
        Node node12 = linkNode(node11, 12, false);

        //Node flatten = flatten(head);
        //System.out.println(flatten);
        Node node = flattenDfs(head);
        System.out.println(node);

    }

    static Node linkNode(Node pre, int val, boolean child) {
        Node node = new Node();
        node.val = val;

        if (child) {
            pre.child = node;
        } else {
            node.prev = pre;
            pre.next = node;
        }
        return node;
    }


    public static Node flattenDfs(Node head) {
        dfs(head);
        return head;
    }


    /**
     * 深度优先搜索
     *
     * @param node 子节点头节点
     * @return
     */
    private static Node dfs(Node node) {
        Node cur = node;
        // 记录链表的最后一个节点
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            // 优先处理子节点
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                // 设置当前节点 子节点 为 后继节点
                cur.next = cur.child;
                cur.child.prev = cur;
                // 把子节点插入当前节点和后继节点中间
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }
                // 将节点的child设置为null
                cur.child = null;
                // 子节点的最后一个节点设置为当前的最后一个节点
                last = childLast;
            } else {
                // 当前节点即为遍历过程的最后一个节点
                last = cur;
            }
            cur = next;
        }
        return last;
    }


    /**
     * 子节点优先
     *
     * @param head
     * @return
     */
    public static Node flatten(Node head) {
        LinkedBlockingQueue<Node> nodeStack = new LinkedBlockingQueue<>();
        // 先将数据扁平化到队列
        nodeSearch(nodeStack, head);
        if (nodeStack.isEmpty()) {
            return new Node();
        }

        Node newHead = new Node();
        Node first = nodeStack.poll();
        newHead.val = first.val;
        newHead.next = first.next;

        Node pre = newHead;
        Node next;
        while ((next = nodeStack.poll()) != null) {
            pre.child = null;
            pre.next = next;
            next.prev = pre;
            pre = next;
        }
        return newHead;
    }


    private static void nodeSearch(BlockingQueue<Node> nodeStack, Node node) {
        if (node == null) {
            return;
        }
        nodeStack.offer(node);
        if (node.child != null) {
            nodeSearch(nodeStack, node.child);
            // help useless ref
            node.child = null;
        }
        if (node.next != null) {
            nodeSearch(nodeStack, node.next);
            // help useless ref
            node.next = null;
            node.prev = null;
        }
    }


    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
