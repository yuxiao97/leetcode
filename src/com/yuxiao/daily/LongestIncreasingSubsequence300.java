package com.yuxiao.daily;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-20 20:32
 */
public class LongestIncreasingSubsequence300 {

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }


    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int[] preMax = new int[nums.length];
        Arrays.fill(preMax, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    preMax[i] = Math.max(preMax[i], preMax[j] + 1);
                }
            }
            res = Math.max(res, preMax[i]);
        }
        return res;
    }
}
