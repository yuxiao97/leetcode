package com.yuxiao.daily;

/**
 * 162. 寻找峰值
 * <p>
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-15
 */
public class FindPeakElement162 {


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        System.out.println(findPeakElement(nums));
        nums = new int[]{1,2,1,3,5,6,4};
        System.out.println(findPeakElement(nums));
    }

    public static int findPeakElement(int[] nums) {
        if(nums.length == 1) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            int next = 0;
            if(i < nums.length - 1) {
                next = nums[i+1];
            } else {
                next = nums[i] - 1;
            }
            int pre = nums[i-1];
            if(nums[i] >  pre && nums[i] > next) {
                return i;
            }
        }
        return 0;
    }

}
