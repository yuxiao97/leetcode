package com.yuxiao;

import java.util.Arrays;

/**
 * LCP 28. 采购方案
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4xy4Wx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-08-30 16:11
 */
public class LCP28 {

    public static void main(String[] args) {
        int[] nums = {2,5,3,5};
        int target = 6;
        System.out.println(purchasePlans(nums, target));
    }



    public static int purchasePlans(int[] nums, int target) {
        return binarySearch(nums, target);
    }


    private static int doubleFor(int[] nums, int target) {
        int mod = 1000000007;
        int plans = 0;
        for(int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] <= target) {
                    plans++;
                }
            }
        }
        return plans % mod;
    }

    private static int binarySearch(int[] nums, int target) {
        int mod = 1000000007;
        int plans = 0;
        nums = Arrays.stream(nums).filter(i -> i < target).toArray();
        Arrays.sort(nums);
        int low = 0, high = nums.length - 1;
        while (low < high) {
            if(nums[low] + nums[high] <= target) {
                // 如果最大的和目前最小的可以满足预算目标，那么小于high的所有元素都可以满足目标，则满足的方案有 high - low
                plans %= mod;
                plans += high - low;
                low++;
            } else {
                high--;
            }
        }
        return plans % mod;
    }

}
