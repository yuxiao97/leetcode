package com.yuxiao.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * <p>
 * 给定平面上n对互不相同的点points ，其中 points[i] = [xi, yi] 。回旋镖 是由点(i, j, k) 表示的元组 ，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）。
 * <p>
 * 返回平面上所有回旋镖的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-13
 */
public class NumberOfBoomerangs447 {


    public static void main(String[] args) {
        int[][]  points = new int[][]{{0,0},{1,0},{2,0}};
        System.out.println(numberOfBoomerangs1(points));
        points = new int[][]{{1,1},{2,2},{3,3}};
        System.out.println(numberOfBoomerangs1(points));
        points = new int[][]{{1,1}};
        System.out.println(numberOfBoomerangs1(points));
    }


    public static int numberOfBoomerangs1(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        int result = 0;
        // 相同距离的下标，具有传递性
        List<Integer> sameDistIdx = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            // 记录{"距离":"个数"}
            Map<Integer, Integer> distance = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                int dis = x*x + y*y;
                distance.compute(dis, (k,v) -> v == null ? 1 : v + 1);
            }
            for (Map.Entry<Integer, Integer> integerIntegerEntry : distance.entrySet()) {
                int dis = integerIntegerEntry.getValue();
                result += 2 * dis * dis;
            }
        }
        return result;
    }


    public static int numberOfBoomerangs(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            // 记录{"距离":"个数"}
            Map<Integer, Integer> distance = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if(j == i) {
                    continue;
                }
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                int dis = x*x + y*y;
                distance.compute(dis, (k,v) -> v == null ? 1 : v + 1);
            }
            for (Map.Entry<Integer, Integer> integerIntegerEntry : distance.entrySet()) {
                int dis = integerIntegerEntry.getValue();
                result += dis * (dis - 1);
            }
        }
        return result;
    }
}
