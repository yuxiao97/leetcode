package com.yuxiao;

import java.util.Random;

/**
 * 470. 用 Rand7() 实现 Rand10()
 *
 * 已有方法rand7可生成 1 到 7 范围内的均匀随机整数，试写一个方法rand10生成 1 到 10 范围内的均匀随机整数。
 *
 * 不要使用系统的Math.random()方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-09-05 09:24
 */
public class ImplementRand10UsingRand7 {

    public static void main(String[] args) {
        System.out.println(rand10());
    }

    public static int rand10() {
        int r1,r2;
        while ((r1 = rand7()) + (r2 = rand7()) > 10) {

        }
        return r1+r2;
    }


    public static int rand7() {
        return new Random().nextInt(7)+1;
    }

}
