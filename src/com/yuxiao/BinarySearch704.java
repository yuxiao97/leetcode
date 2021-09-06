package com.yuxiao;

/**
 * 704. 二分查找
 * <p>
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-06
 */
public class BinarySearch704 {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println(search(arr, target));

        arr = new int[]{-1,0,3,5,9,12};
        target = 2;
        System.out.println(search(arr, target));

        arr = new int[]{1};
        target = 1;
        System.out.println(search(arr, target));
    }

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

}
