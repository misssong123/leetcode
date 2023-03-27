package com.meng.collect.dynamic;

/**
 * 剑指 Offer II 095. 最长公共子序列
 * 难度 中等
 *
 * 117
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class T0002LongestCommonSubsequence {
    /**
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 55.79%
     * 的用户
     * 内存消耗：
     * 45 MB
     * , 在所有 Java 提交中击败了
     * 78.25%
     * 的用户
     * 通过测试用例：
     * 44 / 44
     * @param text1
     * @param text2
     * @return
     * 思路
     * 1.拆分成字串进行逐步解决
     * 2.当i,j处的字符串相等时,f(i,j) = f(i-1,j-1) +1
     * 3.当i,j处的字符串不相等时,f(i,j) = Max(f(i-1,j),f(i,j-1))
     * 逐步拆分成子问题进行解决
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length() , len2 = text2.length();
        int[][] cache = new int[len1+1][len2+1];
        for(int  i = 1 ; i <= len1 ; i++){
            for (int j = 1 ; j <= len2 ; j++){
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    cache[i][j] = cache[i-1][j-1] + 1;
                }else{
                    cache[i][j] = Math.max(cache[i-1][j],cache[i][j-1]);
                }
            }
        }
        return cache[len1][len2];
    }

}
