package com.meng.algorithmfundamentals.seventeenth;

/**
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 *
 * 每步 可以删除任意一个字符串中的一个字符。
 *
 *
 *
 * 示例 1：
 *
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * 示例  2:
 *
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 *
 *
 * 提示：
 *
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 */
public class MinDistance {
    public int minDistance(String word1, String word2) {
        return -1;
    }

    /**
     * 方法一：最长公共子序列
     * 给定两个字符串 \textit{word}_1word
     * 1
     * ​
     *   和 \textit{word}_2word
     * 2
     * ​
     *  ，分别删除若干字符之后使得两个字符串相同，则剩下的字符为两个字符串的公共子序列。为了使删除操作的次数最少，剩下的字符应尽可能多。当剩下的字符为两个字符串的最长公共子序列时，删除操作的次数最少。因此，可以计算两个字符串的最长公共子序列的长度，然后分别计算两个字符串的长度和最长公共子序列的长度之差，即为两个字符串分别需要删除的字符数，两个字符串各自需要删除的字符数之和即为最少的删除操作的总次数。
     *
     * 关于最长公共子序列，请读者参考「1143. 最长公共子序列」。计算最长公共子序列的长度的方法见「1143. 最长公共子序列的官方题解」，这里不再具体阐述。
     *
     * 假设字符串 \textit{word}_1word
     * 1
     * ​
     *   和 \textit{word}_2word
     * 2
     * ​
     *   的长度分别为 mm 和 nn，计算字符串 \textit{word}_1word
     * 1
     * ​
     *   和 \textit{word}_2word
     * 2
     * ​
     *   的最长公共子序列的长度，记为 \textit{lcs}lcs，则最少删除操作次数为 m - \textit{lcs} + n - \textit{lcs}m−lcs+n−lcs。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings/solution/liang-ge-zi-fu-chuan-de-shan-chu-cao-zuo-14uw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param word1
     * @param word2
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 87.13%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 30.68%
     * 的用户
     * 通过测试用例：
     * 1306 / 1306
     */
    public int minDistance1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[m][n];
        return m - lcs + n - lcs;
    }

    /**
     * 方法二：动态规划
     * 也可以直接使用动态规划计算最少删除操作次数，不需要计算最长公共子序列的长度。
     *
     * 假设字符串 \textit{word}_1word
     * 1
     * ​
     *   和 \textit{word}_2word
     * 2
     * ​
     *   的长度分别为 mm 和 nn，创建 m+1m+1 行 n+1n+1 列的二维数组 \textit{dp}dp，其中 \textit{dp}[i][j]dp[i][j] 表示使 \textit{word}_1[0:i]word
     * 1
     * ​
     *  [0:i] 和 \textit{word}_2[0:j]word
     * 2
     * ​
     *  [0:j] 相同的最少删除操作次数。
     *
     * 上述表示中，\textit{word}_1[0:i]word
     * 1
     * ​
     *  [0:i] 表示 \textit{word}_1word
     * 1
     * ​
     *   的长度为 ii 的前缀，\textit{word}_2[0:j]word
     * 2
     * ​
     *  [0:j] 表示 \textit{word}_2word
     * 2
     * ​
     *   的长度为 jj 的前缀。
     *
     * 动态规划的边界情况如下：
     *
     * 当 i=0i=0 时，\textit{word}_1[0:i]word
     * 1
     * ​
     *  [0:i] 为空，空字符串和任何字符串要变成相同，只有将另一个字符串的字符全部删除，因此对任意 0 \le j \le n0≤j≤n，有 \textit{dp}[0][j]=jdp[0][j]=j；
     *
     * 当 j=0j=0 时，\textit{word}_2[0:j]word
     * 2
     * ​
     *  [0:j] 为空，同理可得，对任意 0 \le i \le m0≤i≤m，有 \textit{dp}[i][0]=idp[i][0]=i。
     *
     * 当 i>0i>0 且 j>0j>0 时，考虑 \textit{dp}[i][j]dp[i][j] 的计算：
     *
     * 当 \textit{word}_1[i-1]=\textit{word}_2[j-1]word
     * 1
     * ​
     *  [i−1]=word
     * 2
     * ​
     *  [j−1] 时，将这两个相同的字符称为公共字符，考虑使 \textit{word}_1[0:i-1]word
     * 1
     * ​
     *  [0:i−1] 和 \textit{word}_2[0:j-1]word
     * 2
     * ​
     *  [0:j−1] 相同的最少删除操作次数，增加一个字符公共字符之后，最少删除操作次数不变，因此 \textit{dp}[i][j]=\textit{dp}[i-1][j-1]dp[i][j]=dp[i−1][j−1]。
     *
     * 当 \textit{word}_1[i-1] \ne \textit{word}_2[j-1]word
     * 1
     * ​
     *  [i−1]
     * 
     * ​
     *  =word
     * 2
     * ​
     *  [j−1] 时，考虑以下两项：
     *
     * 使 \textit{word}_1[0:i-1]word
     * 1
     * ​
     *  [0:i−1] 和 \textit{word}_2[0:j]word
     * 2
     * ​
     *  [0:j] 相同的最少删除操作次数，加上删除 \textit{word}_1[i-1]word
     * 1
     * ​
     *  [i−1] 的 11 次操作；
     *
     * 使 \textit{word}_1[0:i]word
     * 1
     * ​
     *  [0:i] 和 \textit{word}_2[0:j-1]word
     * 2
     * ​
     *  [0:j−1] 相同的最少删除操作次数，加上删除 \textit{word}_2[j-1]word
     * 2
     * ​
     *  [j−1] 的 11 次操作。
     *
     * 要得到使 \textit{word}_1[0:i]word
     * 1
     * ​
     *  [0:i] 和 \textit{word}_2[0:j]word
     * 2
     * ​
     *  [0:j] 相同的最少删除操作次数，应取两项中较小的一项，因此 \textit{dp}[i][j]=\min(\textit{dp}[i-1][j]+1,\textit{dp}[i][j-1]+1)=\min(\textit{dp}[i-1][j],\textit{dp}[i][j-1])+1dp[i][j]=min(dp[i−1][j]+1,dp[i][j−1]+1)=min(dp[i−1][j],dp[i][j−1])+1。
     *
     * 由此可以得到如下状态转移方程：
     *
     * \textit{dp}[i][j] = \begin{cases} \textit{dp}[i-1][j-1], & \textit{word}_1[i-1]=\textit{word}_2[j-1] \\ \min(\textit{dp}[i-1][j],\textit{dp}[i][j-1])+1, & \textit{word}_1[i-1] \ne \textit{word}_2[j-1] \end{cases}
     * dp[i][j]={
     * dp[i−1][j−1],
     * min(dp[i−1][j],dp[i][j−1])+1,
     * ​
     *
     * word
     * 1
     * ​
     *  [i−1]=word
     * 2
     * ​
     *  [j−1]
     * word
     * 1
     * ​
     *  [i−1]
     * 
     * ​
     *  =word
     * 2
     * ​
     *  [j−1]
     * ​
     *
     *
     * 最终计算得到 \textit{dp}[m][n]dp[m][n] 即为使 \textit{word}_1word
     * 1
     * ​
     *   和 \textit{word}_2word
     * 2
     * ​
     *   相同的最少删除操作次数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings/solution/liang-ge-zi-fu-chuan-de-shan-chu-cao-zuo-14uw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }


}
