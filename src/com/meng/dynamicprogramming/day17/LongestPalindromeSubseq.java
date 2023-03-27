package com.meng.dynamicprogramming.day17;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        return 0;
    }

    /**
     * 方法一：动态规划
     * 对于一个子序列而言，如果它是回文子序列，并且长度大于 22，那么将它首尾的两个字符去除之后，它仍然是个回文子序列。因此可以用动态规划的方法计算给定字符串的最长回文子序列。
     *
     * 用 \textit{dp}[i][j]dp[i][j] 表示字符串 ss 的下标范围 [i, j][i,j] 内的最长回文子序列的长度。假设字符串 ss 的长度为 nn，则只有当 0 \le i \le j < n0≤i≤j<n 时，才会有 \textit{dp}[i][j] > 0dp[i][j]>0，否则 \textit{dp}[i][j] = 0dp[i][j]=0。
     *
     * 由于任何长度为 11 的子序列都是回文子序列，因此动态规划的边界情况是，对任意 0 \le i < n0≤i<n，都有 \textit{dp}[i][i] = 1dp[i][i]=1。
     *
     * 当 i < ji<j 时，计算 \textit{dp}[i][j]dp[i][j] 需要分别考虑 s[i]s[i] 和 s[j]s[j] 相等和不相等的情况：
     *
     * 如果 s[i] = s[j]s[i]=s[j]，则首先得到 ss 的下标范围 [i+1, j-1][i+1,j−1] 内的最长回文子序列，然后在该子序列的首尾分别添加 s[i]s[i] 和 s[j]s[j]，即可得到 ss 的下标范围 [i, j][i,j] 内的最长回文子序列，因此 \textit{dp}[i][j] = \textit{dp}[i+1][j-1] + 2dp[i][j]=dp[i+1][j−1]+2；
     *
     * 如果 s[i] \ne s[j]s[i]
     * 
     * ​
     *  =s[j]，则 s[i]s[i] 和 s[j]s[j] 不可能同时作为同一个回文子序列的首尾，因此 \textit{dp}[i][j] = \max(\textit{dp}[i+1][j], \textit{dp}[i][j-1])dp[i][j]=max(dp[i+1][j],dp[i][j−1])。
     *
     * 由于状态转移方程都是从长度较短的子序列向长度较长的子序列转移，因此需要注意动态规划的循环顺序。
     *
     * 最终得到 \textit{dp}[0][n-1]dp[0][n−1] 即为字符串 ss 的最长回文子序列的长度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/zui-chang-hui-wen-zi-xu-lie-by-leetcode-hcjqp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 25 ms
     * , 在所有 Java 提交中击败了
     * 89.21%
     * 的用户
     * 内存消耗：
     * 50.3 MB
     * , 在所有 Java 提交中击败了
     * 68.87%
     * 的用户
     * 通过测试用例：
     * 86 / 86
     */
    public int longestPalindromeSubseq1(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

}
