package com.yuxiao.daily;

/**
 * 650. 只有两个键的键盘
 * <p>
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * <p>
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字n ，你需要使用最少的操作次数，在记事本上输出 恰好n个 'A' 。返回能够打印出n个 'A' 的最少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/2-keys-keyboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-19
 */
public class MinSteps650 {


    public static void main(String[] args) {
        System.out.println(minSteps(3));
        System.out.println(minSteps(1));
    }

    public static int minSteps(int n) {
        int ans = 0, i = 2;
        for(; i<=n; ++i){
            if(n%i == 0){
                ans = minSteps(n/i)+i;
                break;
            }
        }
        return ans;
    }

}
