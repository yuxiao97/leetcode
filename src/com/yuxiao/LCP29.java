package com.yuxiao;

/**
 * LCP 29. 乐团站位
 * <p>
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 * <img src="imgs/img.png">
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/SNJvJP
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-08-30 17:14
 */
public class LCP29 {

    public static void main(String[] args) {
        System.out.println(orchestraLayout(2511,
                1504,
                2235));
    }


    /**
     * 问题分析：
     *
     * @param num  矩阵大小
     * @param xPos x坐标
     * @param yPos y坐标
     * @return
     */
    public static  int orchestraLayout(int num, int xPos, int yPos) {
        int start = 1;
        long deep = (long)Math.min(Math.min(xPos, yPos), Math.min(num - xPos - 1, num - yPos - 1));
        long count = ((long)num * deep - deep * deep) * 4;
        start = (int)((count + start - 1) % 9 + 1);
        num -= deep * 2;
        count = ((long)num - 1) * 4;
        long dist = (long)xPos + (long)yPos - 2 * (long)deep;
        if (xPos == num + deep - 1 || (yPos == deep && xPos != deep)) {
            dist = count - dist;
        }
        return (int)((dist + start - 1) % 9 + 1);
    }

}
