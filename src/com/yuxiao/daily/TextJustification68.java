package com.yuxiao.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 * <p>
 * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 示例2:
 * <p>
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What   must  be",
 * "acknowledgment ",
 * "shall be       "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yuxiao
 * @date 2021-09-09
 */
public class TextJustification68 {


    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> strings = fullJustify(words, maxWidth);
        System.out.println(strings);

        words = new String[]{"What","must","be","acknowledgment","shall","be"};
        System.out.println(fullJustify(words, maxWidth));

        words = new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        maxWidth = 20;
        System.out.println(fullJustify(words, maxWidth));

    }


    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        // 当前子串的个数
        int wordCount = 0;
        // 当前子串的字符长度
        int subStrLen = 0;
        StringBuilder subStr  = new StringBuilder();
        for (int i= 0; i<words.length; i++) {
            String word = words[i];
            subStrLen += word.length();
            // 当前元素长度和元素间隔长度总和不能大于等于maxWidth，wordCount - 1 即间隔数（每个间隔至少一个空格）
            if(subStrLen + wordCount - 1 >= maxWidth) {
                // 如果叠加完当前字符串长度后超出最大长度限制，那么当前字符串只能延迟到下一个子串中，需要回退一位
                i--;
                // 剩余长度，用于填充空串
                int remainLen = maxWidth - (subStrLen - word.length() - subStr.length());
                int fillGap = wordCount - 1;
                // 计算间隙需要填充的最少空格个数
                int fillLen = wordCount == 1 ? remainLen : remainLen / fillGap;
                // 计算余数，如果不为0，则余数的填充字符均匀+1
                int remainder = fillGap == 0 ? 0 : remainLen % fillGap;
                for (int j = wordCount-1; j >=0; j--) {
                    String word1 = words[i - j];
                    if(j == 0 && wordCount > 1) {
                        fillLen = maxWidth - subStr.length() - word1.length();
                        fillBlank(subStr, fillLen);
                        subStr.append(word1);
                    } else {
                        subStr.append(word1);
                        int curFillLen = fillLen;
                        // 空格填充不均匀时，多个的空白字符从最左边开始填充
                        if(remainder != 0) {
                            curFillLen += 1;
                            remainder--;
                        }
                        fillBlank(subStr, curFillLen);
                    }
                }
                result.add(subStr.toString());
                // 数据复位
                subStr = new StringBuilder();
                wordCount = 0;
                subStrLen = 0;
            } else {
                // 最后一个子串时，保持左对齐
                if(i == words.length - 1) {
                    subStr = new StringBuilder();
                    for (int j = wordCount; j>=0; j--) {
                        subStr.append(words[i - j]).append(" ");
                    }
                    subStr = new StringBuilder(subStr.substring(0, subStr.length() - 1));
                    fillBlank(subStr, maxWidth - subStr.length());
                    result.add(subStr.toString());
                } else {
                    wordCount++;
                }
            }
        }
        return result;
    }


    private static void fillBlank(StringBuilder subStr, int fillLen) {
        for (int k = 0; k < fillLen; k++) {
            subStr.append(" ");
        }
    }


}
