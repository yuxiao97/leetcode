package com.yuxiao.daily;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

/**
 * 528. 按权重随机选择
 * <p>
 * 给定一个正整数数组w ，其中w[i]代表下标 i的权重（下标从 0 开始），请写一个函数pickIndex，它可以随机地获取下标 i，选取下标 i的概率与w[i]成正比。
 * <p>
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
 * <p>
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-08-30 07:32
 */
public class RandomPickWithWeight528 {

    private RandomPickWithWeight randomPickWithWeight;

    public static void main(String[] args) {
        int[] arr = {1};
        arr = new int[]{1,3};
        RandomPickWithWeight528 randomPickWithWeight528 = new RandomPickWithWeight528(arr);
        System.out.println(randomPickWithWeight528.pickIndex());
    }

    public RandomPickWithWeight528(int[] w) {
        //randomPickWithWeight = new RandomPickWithWeightByTreeMap(w);
        randomPickWithWeight = new RandomPickWithWeightByBinarySearch(w);
    }

    public int pickIndex() {
        return randomPickWithWeight.pickIndex();
    }


    /**
     * 按权重随机算法实现 - 二分查找
     */
    static class RandomPickWithWeightByBinarySearch implements RandomPickWithWeight {

        /**
         * 记录每个位置的前序之和
         */
        int[] pre;

        /**
         * 权重之和
         */
        int sum;

        RandomPickWithWeightByBinarySearch(int[] arr) {
            pre = new int[arr.length];
            for (int i = 1; i < arr.length; i++) {
                pre[i] = pre[i-1] + arr[i];
            }
            sum = Arrays.stream(arr).sum();
        }

        @Override
        public int pickIndex() {
            int x = (int) (Math.random() * sum) + 1;
            int low = 0, high = pre.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (pre[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }


    /**
     * 算法实现类 - 通过TreeMap获取大于随机数的第一个key
     */
    static class RandomPickWithWeightByTreeMap implements RandomPickWithWeight{

        /**
         * 权重和下标容器
         * key：前序元素之和
         * value：元素下标
         */
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        Random random = new Random();

        /**
         * 总权重
         */
        int sum = 0;

        public RandomPickWithWeightByTreeMap(int[] arr) {
            for (int i=0; i<arr.length; i++) {
                sum += arr[i];
                // 将前序元素之和作为当前节点的key
                treeMap.put(sum, i);
            }
        }

        @Override
        public int pickIndex() {
            // treeMap.get(treeMap.tailMap(random.nextInt(sum),false).firstKey());
            return treeMap.higherEntry(random.nextInt(sum)).getValue();
        }
    }

    /**
     * 定义算法接口
     */
    interface RandomPickWithWeight {
        int pickIndex();
    }

}



/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
