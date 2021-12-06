package com.meng.algorithm;

/**
 * 1816. 截断句子
 * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 *
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
 * 给你一个句子 s​​​​​​ 和一个整数 k​​​​​​ ，请你将 s​​ 截断 ​，​​​使截断后的句子仅含 前 k​​​​​​ 个单词。返回 截断 s​​​​​​ 后得到的句子。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "Hello how are you Contestant", k = 4
 * 输出："Hello how are you"
 * 解释：
 * s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
 * 前 4 个单词为 ["Hello", "how", "are", "you"]
 * 因此，应当返回 "Hello how are you"
 * 示例 2：
 *
 * 输入：s = "What is the solution to this problem", k = 4
 * 输出："What is the solution"
 * 解释：
 * s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
 * 前 4 个单词为 ["What", "is", "the", "solution"]
 * 因此，应当返回 "What is the solution"
 * 示例 3：
 *
 * 输入：s = "chopper is not a tanuki", k = 5
 * 输出："chopper is not a tanuki"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * k 的取值范围是 [1,  s 中单词的数目]
 * s 仅由大小写英文字母和空格组成
 * s 中的单词之间由单个空格隔开
 * 不存在前导或尾随空格
 */
public class TruncateSentence {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.7 MB
     * , 在所有 Java 提交中击败了
     * 54.77%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     * @param s
     * @param k
     * @return
     */
    public String truncateSentence(String s, int k) {
        int index = 0;
        while (k>0){
            index = s.indexOf(" ",index+1);
            k--;
        }
        if (index == -1){
            return s;
        }
        return s.substring(0,index);
    }

    /**
     * 方法一：遍历
     * 思路与算法
     *
     * 由题意可知，除了最后一个单词，每个单词后面都跟随一个空格。因此我们可以通过统计空格与句子结尾的数目来统计单词数 \textit{count}count。当 \textit{count}=\textit{k}count=k 时，将当前的下标记录到 \textit{end}end，返回句子 \textit{s}s 在 \textit{end}end 处截断的句子。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/truncate-sentence/solution/jie-duan-ju-zi-by-leetcode-solution-d4ts/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param k
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.6 MB
     * , 在所有 Java 提交中击败了
     * 58.68%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     */
    public String truncateSentence1(String s, int k) {
        int n = s.length();
        int end = 0, count = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) == ' ') {
                count++;
                if (count == k) {
                    end = i;
                    break;
                }
            }
        }
        return s.substring(0, end);
    }

}
