package com.yuxiao;

/**
 * 1480. 一维数组的动态和
 * <p>
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 * <p>
 * 请返回 nums 的动态和。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-08-28 10:34
 */
public class ArraySum1480 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(new ArraySum1480().runningSum(nums));
    }


    /**
     * 动态求和：相加后每个i位置值就是前一个位置的值和自己，即 nums[i-1] + nums[i]，需要注意的是下标是从1开始，因为0的位置就是它自己
     *
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

}
