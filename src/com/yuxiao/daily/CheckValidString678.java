package com.yuxiao.daily;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 678. 有效的括号字符串
 * <p>
 * 给定一个只包含三种字符的字符串：（，）和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * <p>
 * 任何左括号 (必须有相应的右括号 )。
 * 任何右括号 )必须有相应的左括号 (。
 * 左括号 ( 必须在对应的右括号之前 )。
 * *可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 * <p>
 * 输入: "(*))"
 * 输出: True
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-12 09:08
 */
public class CheckValidString678 {


    public static void main(String[] args) {
        String s = "(*)";
        System.out.println(checkValidString(s));
        s = "(*))";
        System.out.println(checkValidString(s));
        s = "()";
        System.out.println(checkValidString(s));
        s = "((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()";
        System.out.println(checkValidString(s));
    }


    public static boolean checkValidString(String s) {
        char l = '(', st = '*';
        char[] chars = s.toCharArray();
        if(chars.length == 1 && st != chars[0]) {
            return false;
        }
        if(chars[0] == l || chars[0] == st) {
            Deque<Integer> left = new ArrayDeque<>();
            Deque<Integer> star = new ArrayDeque<>();
            for (int i = 0; i < chars.length; i++) {
                char c =chars[i];
                if(c == l) {
                    left.push(i);
                } else if(c == st) {
                    star.push(i);
                } else {
                    if(!left.isEmpty()) {
                        left.pop();
                    } else if(!star.isEmpty()) {
                        star.pop();
                    } else {
                        return false;
                    }
                }
            }
            while(!left.isEmpty()){
                if(!star.isEmpty()) {
                    if(left.pop() > star.pop()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
