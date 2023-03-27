package com.meng.dynamicprogramming.day19;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        return false;
    }

    /**
     * 方法二：动态规划
     * 思路及算法
     *
     * 考虑前面的双指针的做法，我们注意到我们有大量的时间用于在 tt 中找到下一个匹配字符。
     *
     * 这样我们可以预处理出对于 tt 的每一个位置，从该位置开始往后每一个字符第一次出现的位置。
     *
     * 我们可以使用动态规划的方法实现预处理，令 f[i][j]f[i][j] 表示字符串 tt 中从位置 ii 开始往后字符 jj 第一次出现的位置。在进行状态转移时，如果 tt 中位置 ii 的字符就是 jj，那么 f[i][j]=if[i][j]=i，否则 jj 出现在位置 i+1i+1 开始往后，即 f[i][j]=f[i+1][j]f[i][j]=f[i+1][j]，因此我们要倒过来进行动态规划，从后往前枚举 ii。
     *
     * 这样我们可以写出状态转移方程：
     *
     * f[i][j]=\begin{cases} i, & t[i]=j\\ f[i+1][j], & t[i] \neq j \end{cases}
     * f[i][j]={
     * i,
     * f[i+1][j],
     * ​
     *
     * t[i]=j
     * t[i]
     * 
     * ​
     *  =j
     * ​
     *
     *
     * 假定下标从 00 开始，那么 f[i][j]f[i][j] 中有 0 \leq i \leq m-10≤i≤m−1 ，对于边界状态 f[m-1][..]f[m−1][..]，我们置 f[m][..]f[m][..] 为 mm，让 f[m-1][..]f[m−1][..] 正常进行转移。这样如果 f[i][j]=mf[i][j]=m，则表示从位置 ii 开始往后不存在字符 jj。
     *
     * 这样，我们可以利用 ff 数组，每次 O(1)O(1) 地跳转到下一个位置，直到位置变为 mm 或 ss 中的每一个字符都匹配成功。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/is-subsequence/solution/pan-duan-zi-xu-lie-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 32.51%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 5.04%
     * 的用户
     * 通过测试用例：
     * 17 / 17
     */
    public boolean isSubsequence1(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

}
