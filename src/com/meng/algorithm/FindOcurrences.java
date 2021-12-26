package com.meng.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1078. Bigram 分词
 * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，其中 second 紧随 first 出现，third 紧随 second 出现。
 *
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
 * 输出：["girl","student"]
 * 示例 2：
 *
 * 输入：text = "we will we will rock you", first = "we", second = "will"
 * 输出：["we","rock"]
 *
 *
 * 提示：
 *
 * 1 <= text.length <= 1000
 * text 由小写英文字母和空格组成
 * text 中的所有单词之间都由 单个空格字符 分隔
 * 1 <= first.length, second.length <= 10
 * first 和 second 由小写英文字母组成
 */
public class FindOcurrences {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 25.00%
     * 的用户
     * 内存消耗：
     * 36.6 MB
     * , 在所有 Java 提交中击败了
     * 32.00%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     * @param text
     * @param first
     * @param second
     * @return
     */
    public String[] findOcurrences(String text, String first, String second) {
        if (text.length() < first.length() + second.length() +1){
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        String[] strs = text.split(" ");
        int len = strs.length;
        boolean flag = false;
        for(int i = 0 ; i < len ; i++){
            if (flag){
                if (strs[i].equals(second)&&i+1 < len){
                    list.add(strs[i+1]);
                }
                flag = false;
            }
            if (strs[i].equals(first)){
                    flag = true;
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0 ; i < list.size() ; i++){
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 方法一：遍历
     * 思路与算法
     *
     * 我们将文本 \textit{text}text 按空格分割成单词数组 \textit{words}words，然后遍历 \textit{words}words 数组，如果一个单词的前两个单词分别按顺序等于 \textit{first}first 和 \textit{second}second，则该单词符合第三个单词 \textit{third}third 的定义, 将其加入结果中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/occurrences-after-bigram/solution/bigram-fen-ci-by-leetcode-solution-7q3e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param text
     * @param first
     * @param second
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.6 MB
     * , 在所有 Java 提交中击败了
     * 36.00%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     */
    public String[] findOcurrences1(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> list = new ArrayList<String>();
        for (int i = 2; i < words.length; i++) {
            if (words[i - 2].equals(first) && words[i - 1].equals(second)) {
                list.add(words[i]);
            }
        }
        int size = list.size();
        String[] ret = new String[size];
        for (int i = 0; i < size; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }


    public static void main(String[] args) {
        String text ="jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa";
        String first = "kcyxdfnoa";
        String second = "jkypmsxd";
        FindOcurrences demo = new FindOcurrences();
        System.out.println(Arrays.toString(demo.findOcurrences(text,first,second)));
    }
}
