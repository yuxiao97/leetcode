package com.yuxiao.daily;

/**
 * 165. 比较版本号
 * <p>
 * 给你两个版本号 version1 和 version2 ，请你比较它们。
 * <p>
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 * <p>
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 * <p>
 * 返回规则如下：
 * <p>
 * 如果version1>version2返回1，
 * 如果version1<version2 返回 -1，
 * 除此之外返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compare-version-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-01 09:40
 */
public class CompareVersion165 {

    public static void main(String[] args) {
        System.out.println(compareVersionV2("1.0.1", "1"));

    }


    /**
     * 通过自行解析点分版本号的方式，而非String.split的方式进行处理，提升效率
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersionV2(String version1, String version2) {
        int v1l = version1.length();
        int v2l = version2.length();
        // 点分数记录器，值记录当前，使用后重置，
        int[] v1 = new int[1];
        int[] v2 = new int[1];

        // 当前遍历到字符串的下标位置
        int v1Idx = 0;
        int v2Idx = 0;
        for (int i=0; i<v1l || i<v2l; i++) {
            v1Idx = parseDotArray(version1, v1l, v1, v1Idx);
            v2Idx = parseDotArray(version2, v2l, v2, v2Idx);
            if(v1[0] > v2[0]) {
                return 1;
            } else if(v1[0] < v2[0]) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 解析每个点分数组，比较然后比较当前分组的带下
     * @param version 版本号
     * @param vl 版本号总长度
     * @param arr 结果数据
     * @param idx 向后遍历字符串的游标
     * @return
     */
    private static int parseDotArray(String version, int vl, int[] arr, int idx) {
        arr[0] = 0;
        for (; idx < vl; idx++) {
            char c = version.charAt(idx);
            // 当前点分解析结束
            if (c == ".".charAt(0)) {
                idx++;
                break;
            }
            // 忽略前缀0
            if (arr[0] == 0 && c == "0".charAt(0)) {
                continue;
            }
            arr[0] += arr[0] + c;
        }
        return idx;
    }

    /**
     * 使用String的split方法转成数组后的操作，split方法比较耗时
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < (v1.length >= v2.length ? v1.length : v2.length); i++) {
            int d1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int d2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if(d1 > d2) {
                return 1;
            } else if(d1 < d2) {
                return -1;
            }
        }
        return 0;
    }


    public static int compareVersionByOfficial(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; ++i) {
            int x = 0, y = 0;
            if (i < v1.length) {
                x = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                y = Integer.parseInt(v2[i]);
            }
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;
    }

}
