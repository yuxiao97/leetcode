package com.yuxiao.daily;

/**
 * 1894. 找到需要补充粉笔的学生编号
 * <p>
 * 一个班级里有n个学生，编号为 0到 n - 1。每个学生会依次回答问题，编号为 0的学生先回答，然后是编号为 1的学生，以此类推，直到编号为 n - 1的学生，然后老师会重复这个过程，重新从编号为 0的学生开始回答问题。
 * <p>
 * 给你一个长度为 n且下标从 0开始的整数数组chalk和一个整数k。一开始粉笔盒里总共有k支粉笔。当编号为i的学生回答问题时，他会消耗 chalk[i]支粉笔。如果剩余粉笔数量 严格小于chalk[i]，那么学生 i需要 补充粉笔。
 * <p>
 * 请你返回需要补充粉笔的学生编号。
 * <p>
 * <p>
 * 输入：chalk = [5,1,5], k = 22
 * 输出：0
 * 解释：学生消耗粉笔情况如下：
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
 * - 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
 * - 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
 * - 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
 * 编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-10
 */
public class ChalkReplacer1894 {


    public static void main(String[] args) {
        int[] chalk = {5,1,5};
        int k = 22;
        System.out.println(chalkReplacer(chalk, k));

        chalk = new int[]{3,4,1,2};
        k = 25;
        System.out.println(chalkReplacer(chalk, k));
    }


    public static int chalkReplacer(int[] chalk, int k) {
        int total = 0;
        // 第一遍处理中如果可以用完，那么直接即可，另外如果没有用完，则算出所有需要的粉笔总数
        for (int i = 0; i < chalk.length; i++) {
            total += chalk[i];
            if((k -= chalk[i]) < 0) {
                return i;
            }
        }
        // 如果没有找到，则求剩余粉笔数和单次需要的总粉笔数的余数，那么余数个数的粉笔则肯定可以在下一次循环中被消耗完毕
        k %= total;
        return chalkReplacer(chalk, k);
    }
}
