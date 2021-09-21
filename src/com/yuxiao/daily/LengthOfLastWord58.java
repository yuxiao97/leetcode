package com.yuxiao.daily;

/**
 * 58. 最后一个单词的长度
 * <p>
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * <p>
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *
 * @author yuxiao
 * @date 2021-09-21 09:01
 */
public class LengthOfLastWord58 {


    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
        s = "luffy is still joyboy";
        System.out.println(lengthOfLastWord(s));
        s = "Hello World";
        System.out.println(lengthOfLastWord(s));
        s = "a";
        System.out.println(lengthOfLastWord(s));
    }


    public static int lengthOfLastWord(String s) {
        int res = 0;
        int len = s.length();
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if(res == 0) {
                    continue;
                }
                return res;
            }
            res++;
        }
        return res;
    }

}
