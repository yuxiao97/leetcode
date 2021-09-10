package com.yuxiao.daily;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 17.14. 最小K个数
 * <p>
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-03
 */
public class SmallestK {


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        quickSort(arr, 0, arr.length-1);
        System.out.println("");
    }


    public static int[] smallestKV4(int[] arr, int k) {
        if(k == 0) {
            return new int[0];
        }
        quickSort(arr, 0, arr.length -1);
        return Arrays.copyOfRange(arr, 0, k);
    }



    private static void quickSort(int[] src, int begin, int end) {
        if (begin < end) {
            // 基准值（比较值）
            int pivot = src[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                // 从最后边和基准值进行比较，大于则不处理，继续向前移动
                while (i < j && src[j] > pivot) {
                    j--;
                }
                if (i < j) {
                    // 出现最右边的值大于基准值时，则将当前位置（小于基准值的位置）的值赋值给基准位置
                    src[i] = src[j];
                    i++;
                }
                // 从前向后寻找大于基准值的位置
                while (i < j && src[i] < pivot) {
                    i++;
                }
                if (i < j) {
                    // 将当前位置大于基准值的值赋值到
                    src[j] = src[i];
                    j--;
                }
            }
            src[i] = pivot;
            // 对小的再次进行快速排序
            quickSort(src, begin, i - 1);
            // 对大的再次进行快速排序
            quickSort(src, i + 1, end);
        }
    }



    public static int[] smallestKV3(int[] arr, int k) {
        Arrays.sort(arr);
        int[] result = new int[k];
        System.arraycopy(arr, 0, result, 0, k);
        return result;
    }

    public static int[] smallestKV2(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    public static int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.offer(arr[i]);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll();
        }
        return result;
    }


}
