package com.yuxiao;

import java.util.*;

/**
 * LCP 30. 魔塔游戏
 * <p>
 * 小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 * <p>
 * 小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]
 * <p>
 * 输出：1
 * <p>
 * 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-200,-300,400,0]
 * <p>
 * 输出：-1
 * <p>
 * 解释：调整访问顺序也无法完成全部房间的访问。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/p0NxJO
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-09-01 15:21
 */
public class MagicTowerLCP30 {


    public static void main(String[] args) {
        int[] blood = {100,100,100,-250,-60,-140,-50,-50,100,150};
        blood = new int[]{-200,-300,400,0};
        blood = new int[]{5,-4,1,-2,-2,-2,4};
        System.out.println(magicTower(blood));
    }


    public static int magicTower(int[] nums) {
        long blood = 1L;
        // 如果血量总和小于等于0，则无论如何都失败，直接返回-1即可
        for (int num : nums) {
            blood += num;
        }
        if(blood <= 0) {
            return -1;
        }

        blood = 1L;
        int skip = 0;
        // 使用小顶堆，存储怪的伤害血量
        PriorityQueue<Integer> q = new PriorityQueue<>(nums.length);
        for (int i : nums) {
            blood += i;
            if (i < 0) {
                q.offer(i);
            }
            if (blood <= 0) {
                skip++;
                // 当血液小于等于0时，可以回滚受过最高的伤害
                blood -= q.poll();
            }
        }
        return skip;
    }

}
