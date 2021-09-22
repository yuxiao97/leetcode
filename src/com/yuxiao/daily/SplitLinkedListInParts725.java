package com.yuxiao.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 725. 分隔链表
 * <p>
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * <p>
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * <p>
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 * <p>
 * 返回一个由上述 k 部分组成的数组。
 * <p>
 * 输入：head = [1,2,3], k = 5
 * 输出：[[1],[2],[3],[],[]]
 * 解释：
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-22
 */
public class SplitLinkedListInParts725 {


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        ListNode[] listNodes = splitListToParts(head, 2);
    }


    public static ListNode[] splitListToParts(ListNode head, int k) {
        // 元素为空时，直接返回大小为k的ListNode数组
        if (head == null) {
            return new ListNode[k];
        }
        // 遍历ListNode所有数据
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        ListNode next = head.next;
        while (next != null) {
            list.add(next.val);
            next = next.next;
        }
        int size = list.size();
        int len = size / k;
        ListNode[] res = new ListNode[k];
        // 如果平均长度小于1，说明前面数组长度全为1，后面的为空即可
        if (len < 1) {
            ListNode tmp;
            for (int i = 0; i < k; i++) {
                if (i < list.size()) {
                    tmp = new ListNode(list.get(i));
                    res[i] = tmp;
                }
            }
            return res;
        }

        int preLen = size % k;
        int idx = 0;
        for (int i = 0; i < k; i++) {
            ListNode head1 = new ListNode(list.get(idx++));
            if (i < preLen) {
                int num = len + 1;
                res[i] = handleNode(head1, num, idx, list);
                idx += num - 1;
            } else {
                res[i] = handleNode(head1, len, idx, list);
                idx += len - 1;
            }
        }
        return res;
    }


    /**
     * 构建单个数组的元素
     *
     * @param head
     * @param len
     * @param idx
     * @param list
     * @return
     */
    private static ListNode handleNode(ListNode head, int len, int idx, List<Integer> list) {
        ListNode tmpHead = head;
        for (int i = 0; i < len - 1; i++) {
            ListNode cur = new ListNode(list.get(idx++));
            tmpHead.next = cur;
            tmpHead = cur;
        }
        return head;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
