package com.meng.algorithm;

/**
 * 520. 检测大写字母
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word = "USA"
 * 输出：true
 * 示例 2：
 *
 * 输入：word = "FlaG"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 */
public class DetectCapitalUse {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 13.68%
     * 的用户
     * 内存消耗：
     * 36.7 MB
     * , 在所有 Java 提交中击败了
     * 66.39%
     * 的用户
     * 通过测试用例：
     * 550 / 550
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) || word.equals(word.toLowerCase());
    }

    /**
     * 方法一：根据题目要求实现
     * 思路和算法
     *
     * 根据题目要求，若单词的大写用法正确，则需要满足：
     *
     * 若第 11 个字母为大写，则其他字母必须均为大写或均为小写，即其他字母必须与第 22 个字母的大小写相同；
     *
     * 若第 11 个字母为小写，则其他字母必须均为小写。
     *
     * 根据以上规则，可以整理得到以下更简单的判断规则：
     *
     * 无论第 11 个字母是否大写，其他字母必须与第 22 个字母的大小写相同；
     *
     * 若第 11 个字母为小写，则需额外判断第 22 个字母是否为小写。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/detect-capital/solution/jian-ce-da-xie-zi-mu-by-leetcode-solutio-449z/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param word
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 98.19%
     * 的用户
     * 内存消耗：
     * 36.5 MB
     * , 在所有 Java 提交中击败了
     * 91.21%
     * 的用户
     * 通过测试用例：
     * 550 / 550
     */
    public boolean detectCapitalUse1(String word) {
        // 若第 1 个字母为小写，则需额外判断第 2 个字母是否为小写
        if (word.length() >= 2 && Character.isLowerCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            return false;
        }

        // 无论第 1 个字母是否大写，其他字母必须与第 2 个字母的大小写相同
        for (int i = 2; i < word.length(); ++i) {
            if (Character.isLowerCase(word.charAt(i)) ^ Character.isLowerCase(word.charAt(1))) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        DetectCapitalUse demo = new DetectCapitalUse();
        System.out.println(demo.detectCapitalUse("FlaG"));
        String word = "Flag";
        System.out.println();
        System.out.println(word.equals(word.substring(0,1).toUpperCase()+word.substring(1)));
    }
}
