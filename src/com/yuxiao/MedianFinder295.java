package com.yuxiao;

import java.util.*;

/**
 * 寻找中位数
 * <p>
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4]的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * <p>
 * <p>
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-08-27 07:43
 */
public class MedianFinder295 {

    /**
     * 问题分析：需要一个可排序的容器，然后取中间1位或2位的值计算平均值，问题是如何快速排序后中间的一个或两个数值呢？
     * 从目前看一个独立的数据结构似乎没法同时完成任务的两个目标：排序和取中间值，要嘛可以排序，然后可以获取中间值，
     * List似乎可以完成这两个任务，但List的排序的性能跟不上；
     * 所以我们需要一个这样的数据结构：添加数据时需要比较加入的数据和已有数据根据排序规则选择合适的位置，另外在计算中位数的时候要快速获取中位数。
     * <p>
     * TreeSet可以平衡数据，但不支持重复数据，不合适；
     * <p>
     * PriorityQueue也支持排序，但无法快速获取中位数；
     * <p>
     * 至此，可以定义两个PriorityQueue，一个正排，从小到大，另一个倒排，从大到小，把较小数值的按倒排，这样大值排在堆顶，较大数值的正排，这样小值就排在堆顶，
     * 总体上来看，小堆的堆顶数据小于等于大堆的堆顶数据，这样可以通过各自的堆顶数据计算中位数，通过peek或poll获取堆顶数据的时间复杂度为O(1)。
     * <p>
     * 关键：聚焦中间两位数，如何快速获取排序后的中间位数值是关键，另外排序的分而治之也是关键的关键。
     *
     * @param args
     */

    public static void main(String[] args) {
        MedianFinder295 medianFinder295 = new MedianFinder295();

        medianFinder295.addNum(1);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(2);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(3);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(4);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(5);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(6);
        System.out.println(medianFinder295.findMedian());


        medianFinder295.addNum(7);
        System.out.println(medianFinder295.findMedian());


        medianFinder295.addNum(8);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(9);
        System.out.println(medianFinder295.findMedian());

        medianFinder295.addNum(10);
        System.out.println(medianFinder295.findMedian());
    }


    private PriorityQueue<Integer> minQueue;

    private PriorityQueue<Integer> maxQueue;


    /**
     * initialize your data structure here.
     */
    public MedianFinder295() {
        // 由于Queue只能peek头部的值，分为大小两个堆后，一个需要获取堆中最大的值，一个需要获取堆中最小的值，这样这两个值便是完整堆中中间的数值，所以一个需要正排，一个需要倒排
        // 正排
        minQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 倒排
        maxQueue = new PriorityQueue<>();
    }


    /**
     * 添加元素
     * <p>
     * 针对首次添加和后续添加的操作分开处理，首次添加默认添加到小堆中。
     * <p>
     * 后续的添加操作都以小堆中的堆顶数据作为参考值，新增数值大于等于参考值则加入到大堆中，否则添加到小堆中，添加完成后，需要平衡大小堆，避免失衡导致最终的中位数计算错误。
     *
     * @param num 本次添加的数值
     */
    public void addNum(int num) {
        // 首次添加元素判断，添加到大顶堆中，并将添加元素作为mid值
        if (minQueue.isEmpty()) {
            minQueue.offer(num);
        } else {
            int minTop = minQueue.peek();
            if (num >= minTop) {
                maxQueue.offer(num);
            } else {
                minQueue.offer(num);
            }
            // 维持大小堆顶的平衡
            int minSize = minQueue.size(), maxSize = maxQueue.size();
            if (minSize - maxSize > 1) {
                maxQueue.offer(minQueue.poll());
            }
            if (maxSize - minSize > 1) {
                minQueue.offer(maxQueue.poll());
            }
        }
    }


    /**
     * 获取中位数
     * <p>
     * 判断大小堆元素的个数，哪个个数多就获取那个堆中堆顶的数据作为中位数放，否则取大小堆堆顶的元素值的平均值
     *
     * @return 中位数
     */
    public double findMedian() {
        int minSize = minQueue.size(), maxSize = maxQueue.size();
        if (minSize > maxSize) {
            return minQueue.peek() * 1.0;
        } else if (minSize == maxSize) {
            return (minQueue.peek() + maxQueue.peek()) / 2.0;
        } else {
            return maxQueue.peek() * 1.0;
        }
    }

}
