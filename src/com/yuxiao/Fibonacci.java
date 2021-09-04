package com.yuxiao;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * <p>
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-04 08:58
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fib(95));
    }


    public static int fib(int n) {
        if(n < 2) {
            return n;
        }
        int mod = 1000000007;
        // 使用大小为2的数组，记录n-1和n-2的大小
        int[] preTwo = new int[2];
        preTwo[0] = 0;
        preTwo[1] = 1;
        for(int i=2; i<n; i++) {
            preTwo[i%2] = (preTwo[0] + preTwo[1]) % mod;
        }
        return (preTwo[0] + preTwo[1]) % mod;
    }

}
