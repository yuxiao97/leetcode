package com.yuxiao;

import java.util.Arrays;

/**
 * 救生艇问题
 * <p>
 * 题目描述：
 * 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
 * <h1>每艘船最多可同时载两人</h1>，但条件是这些人的重量之和最多为limit。
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * </p>
 * 来源：力扣（LeetCode）<br>
 * 链接：https://leetcode-cn.com/problems/boats-to-save-people <br>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<br>
 *
 * @author yangjunwei
 * @date 2021-08-26 10:40
 */
public class Lifeboat881 {

    public static void main(String[] args) {
        Lifeboat881 lifeboat881 = new Lifeboat881();
        int[] people = {3,2,2,1};
        int limit = 3;
        System.out.println("最多2人需要救生艇数量："+ lifeboat881.numRescueBoats(people, limit));
        System.out.println("人数无限制需要救生艇数量：" + lifeboat881.numRescueBoats2(people, limit));
    }


    /**
     * 计算需要的救生艇数量
     * <p>
     * 问题分析：这是一个分组问题，挑选出体重之和不超过limit的人群分组，但最多两个人，有多少个分组就需要多少救生艇
     * 另外，people中的值的最大值肯定不会超过limit，不然，这个人无论如何都无法被救
     *
     * @param people 需要救助的人，值为每个人的体重
     * @param limit  救生艇可以承受的最大重量
     * @return 需要的救生艇
     */
    private int numRescueBoats(int[] people, int limit) {
        // 需要对人群的体重进行排序，排序后通过首尾相组合的方式进行分组
        Arrays.sort(people);
        // 定义首尾游标，记录当前遍历的位置
        int headCursor = 0;
        int tailCursor = people.length - 1;
        int count = 0;
        while (headCursor <= tailCursor) {
            // 首尾重量不过超过limit，则该首尾占用一艘救生艇，否则尾部独占一艘
            if(people[tailCursor] + people[headCursor] <= limit) {
                ++headCursor;
            }
            --tailCursor;
            ++count;
        }
        return count;
    }


    /**
     * 延伸：每艘救生艇不限制人数，只要重量不超过limit的解法
     *
     * @param people 待拯救的人
     * @param limit  没搜救生艇的最大承载量
     * @return 需要的救生艇数量
     */
    private int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        // 定义首尾游标，记录当前遍历的位置
        int headCursor = 0;
        int tailCursor = people.length - 1;
        int count = 0;
        while (headCursor <= tailCursor) {
            if(headCursor == tailCursor) {
                count++;
                break;
            }
            if(people[tailCursor] + people[headCursor] > limit) {
                tailCursor--;
                count++;
            } else if(people[tailCursor] + people[headCursor] == limit) {
                tailCursor--;
                headCursor++;
                count++;
            } else {
                int tailWeight = people[tailCursor];
                int headWeight = people[headCursor];
                int totalWeight = tailWeight + headWeight;
                // 此处需要不断累加，直到达到救生艇最大限度，另外首尾游标不能交叉
                while ((totalWeight += people[headCursor+1]) <= limit && headCursor < tailCursor) {
                    headCursor++;
                }
                tailCursor--;
                headCursor++;
                count++;
            }
        }
        return count;
    }

}
