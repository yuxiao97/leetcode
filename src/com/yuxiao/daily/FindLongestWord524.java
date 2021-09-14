package com.yuxiao.daily;

import java.util.Arrays;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * <p>
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * <p>
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-14
 */
public class FindLongestWord524 {


    public static void main(String[] args) {
        String s = "abpcplea";
        String[] dic = new String[]{"ale", "apple", "monkey", "plea"};
        System.out.println(findLongestWord(s, Arrays.asList(dic)));
    }


    public static String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1,o2) -> o2.length() - o1.length());
        String result = "";
        for (String dic : dictionary) {
            if(dic.length() > s.length() || dic.length() < result.length()) {
                continue;
            }
            int i = 0, j = 0;
            while (i < s.length() && j < dic.length()) {
                if (dic.charAt(j) == s.charAt(i)) {
                    // 字典中字符匹配到一个时，向后移动一位
                    j++;
                }
                // 不管是否匹配到，原始字符串都要向后移动一位
                i++;
            }
            // j == dic.length()时，说明s中存在匹配的dic子串
            if (j == dic.length()) {
                // 当前字典子串长度大于已经匹配到的子串 或者 长度和匹配到的相等，但字符排序小于已匹配到的字符时，则当前子串符合规则
                if (dic.length() > result.length() ||
                        (dic.length() == result.length() && dic.compareTo(result) < 0)) {
                    result = dic;
                }
            }
        }
        return result;
    }


}
