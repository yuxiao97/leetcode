package com.yuxiao;

/**
 * 1588. 所有奇数长度子数组的和
 * <p>
 * 给你一个正整数数组arr，请你计算所有可能的奇数长度子数组的和。
 * <p>
 * 子数组 定义为原数组中的一个连续子序列。
 * <p>
 * 请你返回 arr中 所有奇数长度子数组的和 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,4,2,5,3]
 * 输出：58
 * 解释：所有奇数长度子数组和它们的和为：
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * 示例 2：
 * <p>
 * 输入：arr = [1,2]
 * 输出：3
 * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-08-29 09:45
 */
public class OddLengthSubarraysSum1588 {


    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 3};
        System.out.println(sumOddLengthSubarrays(arr));
        arr = new int[]{1, 2};
        System.out.println(sumOddLengthSubarrays(arr));
        arr = new int[]{10, 11, 12};
        System.out.println(sumOddLengthSubarrays(arr));
        arr = new int[]{1};
        System.out.println(sumOddLengthSubarrays(arr));
    }


    private static int sum(int[] arr) {
        int sum = 0;
        for (int len = 1; len <= arr.length; len += 2) {
            for (int i = 0; i <= arr.length - len; ++i) {
                for (int j = 0; j < len; ++j) {
                    sum += arr[i + j];
                }
            }
        }
        return sum;
    }


    public static int sumOddLengthSubarrays(int[] arr) {
        int length = arr.length;
        int sum = 0;
        for(int i=0; i<length; i++) {
            sum += arr[i];
            // 从第三位开始，每向后递进一位，都要从遍历过的元素中，找到元素个数小于当前位置的奇数个的子数组
            for(int j=2; j<=i; j+=2) {
                // 遍历当前j奇数个元素的子数组
                for(int k=0; k<=j; k++) {
                    sum += arr[i-k];
                }
            }
        }
        return sum;
    }


    private static int getOddLenSum(int[] arr) {
        int oddLenSum = 0;
        if (arr.length >= 3) {
            for (int i = 0; i + 2 < arr.length; i += 2) {

            }
        }
        return oddLenSum;
    }


    private static int getAllLenSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

}
