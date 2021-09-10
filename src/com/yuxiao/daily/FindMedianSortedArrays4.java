package com.yuxiao.daily;

import java.util.PriorityQueue;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 * @author yuxiao
 * @date 2021-08-27 16:12
 */
public class FindMedianSortedArrays4 {


    public static void main(String[] args) {
        int[] nums1 = {1, 3}, nums2 = {2};
        FindMedianSortedArrays4 findMedianSortedArrays4 = new FindMedianSortedArrays4();
        System.out.println("cursor:"+findMedianSortedArrays4.sortCursor(nums1, nums2));
        System.out.println(findMedianSortedArrays4.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{0, 0};
        nums2 = new int[]{0, 0};
        System.out.println("cursor:"+findMedianSortedArrays4.sortCursor(nums1, nums2));
        System.out.println(findMedianSortedArrays4.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[0];
        nums2 = new int[]{2,3};
        System.out.println("cursor:"+findMedianSortedArrays4.sortCursor(nums1, nums2));
        System.out.println(findMedianSortedArrays4.findMedianSortedArrays(nums1, nums2));
    }


    private PriorityQueue<Integer> min;

    private PriorityQueue<Integer> max;


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        max = new PriorityQueue<>();
        min = new PriorityQueue<>((o1, o2) -> o2 - o1);
        sortByPriorityQueue(nums1);
        sortByPriorityQueue(nums2);
        int minSize = min.size(), maxSize = max.size();
        if (minSize > maxSize) {
            return min.peek() * 1.0;
        } else if (minSize == maxSize) {
            return (min.peek() + max.peek()) / 2.0;
        } else {
            return max.peek() * 1.0;
        }
    }


    /**
     * 交替移动法：给两个数组各自一个下标游标，初始位置为0，分别比较下标位置的值，值小的游标继续移动，
     * 如果到达两个数组总长度的一般，则根据长度奇偶计算中位数，如果游标已经到达末端还未到达中位数的位置，则停止移动，另一个继续移动到中位数位置即可
     * <p>
     * 中位数位置 = 两个游标之和
     */
    private double sortCursor(int[] nums1, int[] nums2) {
        int allLen = nums1.length + nums2.length;
        int mid = (allLen - 1) / 2;
        int nc1 = 0;
        int nc2 = 0;
        int n1L = nums1.length;
        int n2L = nums2.length;

        while (nc1 + nc2 <= mid) {
            if(n1L == 0) {
                nc2 = mid;
                break;
            } else if (n2L == 0) {
                nc1 = mid;
                break;
            } else {
                if (nc1 < n1L && nums1[nc1] <= nums2[nc2]) {
                    nc1++;
                } else
                if (nc2 < n2L && nums1[nc1] > nums2[nc2]) {
                    nc2++;
                }
            }
        }
        if (allLen % 2 == 0) {
            int l1 = nums1.length;
            int l2 = nums2.length;
            if(l1 == 0) {
                return (nums2[nc2] + nums2[nc2 + 1]) / 2.0;
            }
            if(l2 == 0) {
                return (nums1[nc1] + nums1[nc1 + 1]) / 2.0;
            }
            return (nums1[nc1] + nums2[nc2]) / 2.0;
        }
        return nc1 <= nc2 && nums1.length > 0 ? nums1[nc1] * 1.0 : nums2[nc2] * 1.0;
    }


    /**
     * sort by PriorityQueue，使用堆顶的思想，聚焦中位数
     *
     * @param num
     */
    private void sortByPriorityQueue(int[] num) {
        for (int i : num) {
            if (min.isEmpty()) {
                min.offer(i);
            } else {
                int refValue = min.peek();
                if (i >= refValue) {
                    max.offer(i);
                } else {
                    min.offer(i);
                }
                int minSize = min.size(), maxSize = max.size();
                if (minSize - maxSize > 1) {
                    max.offer(min.poll());
                }
                if (maxSize - minSize > 1) {
                    min.offer(max.poll());
                }
            }
        }
    }

}
