package com.meng.algorithm;

/**
 * 1220. 统计元音字母序列的数目
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 *
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 *
 * 输入：n = 5
 * 输出：68
 *
 *
 * 提示：
 *
 * 1 <= n <= 2 * 10^4
 */
public class CountVowelPermutation {
    public int countVowelPermutation(int n) {
        return 5;
    }

    /**
     * 方法一：动态规划
     * 思路
     *
     * 题目中给定的字符的下一个字符的规则如下：
     *
     * 字符串中的每个字符都应当是小写元音字母 （\texttt{`a'}, \texttt{`e'}, \texttt{`i'}, \texttt{`o'}, \texttt{`u'}）（‘a’,‘e’,‘i’,‘o’,‘u’）；
     * 每个元音 \texttt{`a'}‘a’ 后面都只能跟着 \texttt{`e'}‘e’；
     * 每个元音 \texttt{`e'}‘e’ 后面只能跟着 \texttt{`a'}‘a’ 或者是 \texttt{`a'}‘a’；
     * 每个元音 \texttt{`i'}‘i’ 后面不能再跟着另一个 \texttt{`i'}‘i’；
     * 每个元音 \texttt{`o'}‘o’ 后面只能跟着 \texttt{`i'}‘i’ 或者是 \texttt{`u'}‘u’；
     * 每个元音 \texttt{`u'}‘u’ 后面只能跟着 \texttt{`a'}‘a’；
     * 以上等价于每个字符的前一个字符的规则如下：
     *
     * 元音字母 \texttt{`a'}‘a’ 前面只能跟着 \texttt{`e'}, \texttt{`i'}, \texttt{`u'}‘e’,‘i’,‘u’；
     * 元音字母 \texttt{`e'}‘e’ 前面只能跟着 \texttt{`a'}, \texttt{`i'}‘a’,‘i’；
     * 每个元音 \texttt{`i'}‘i’ 前面只能跟着 \texttt{`e'}, \texttt{`o'}‘e’,‘o’；
     * 每个元音 \texttt{`o'}‘o’ 前面只能跟着 \texttt{`i'}‘i’；
     * 每个元音 \texttt{`u'}‘u’ 后面只能跟着 \texttt{`o'}, \texttt{`i'}‘o’,‘i’；
     * 我们设 \textit{dp}[i][j]dp[i][j] 代表当前长度为 ii 且以字符 jj 为结尾的字符串的数目，其中在此 j = {0,1,2,3,4}j=0,1,2,3,4 分别代表元音字母 {\texttt{`a'}, \texttt{`e'}, \texttt{`i'}, \texttt{`o'}, \texttt{`u'}}‘a’,‘e’,‘i’,‘o’,‘u’，通过以上的字符规则，我们可以得到动态规划递推公式如下：
     *
     * \left\{   \begin{array}{lr} \textit{dp}[i][0] = \textit{dp}[i-1][1] + \textit{dp}[i-1][2] + \textit{dp}[i-1][4] \\ \textit{dp}[i][1] = \textit{dp}[i-1][0] + \textit{dp}[i-1][2] \\ \textit{dp}[i][2] = \textit{dp}[i-1][1] + \textit{dp}[i-1][3] \\ \textit{dp}[i][3] = \textit{dp}[i-1][2] \\ \textit{dp}[i][4] = \textit{dp}[i-1][2] + \textit{dp}[i-1][3]   \end{array} \right.
     * ⎩
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎧
     * ​
     *   
     * dp[i][0]=dp[i−1][1]+dp[i−1][2]+dp[i−1][4]
     * dp[i][1]=dp[i−1][0]+dp[i−1][2]
     * dp[i][2]=dp[i−1][1]+dp[i−1][3]
     * dp[i][3]=dp[i−1][2]
     * dp[i][4]=dp[i−1][2]+dp[i−1][3] 
     * ​
     *
     *
     * 按照题目规则最终可以形成长度为 nn 的字符串的数目为：\sum_{i=0}^4\textit{dp}[n][i]∑
     * i=0
     * 4
     * ​
     *  dp[n][i]
     *
     * 实际计算过程中只需要保留前一个状态即可推导出后一个状态，计算长度为 ii 的状态只需要长度为 i-1i−1 的中间变量即可，i-1i−1 之前的状态全部都可以丢弃掉。计算过程中，答案需要取模 1\text{e}9+71e9+7。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-vowels-permutation/solution/tong-ji-yuan-yin-zi-mu-xu-lie-de-shu-mu-jxo09/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 65.09%
     * 的用户
     * 内存消耗：
     * 35 MB
     * , 在所有 Java 提交中击败了
     * 97.17%
     * 的用户
     * 通过测试用例：
     * 43 / 43
     */
    public int countVowelPermutation1(int n) {
        long mod = 1000000007;
        long[] dp = new long[5];
        long[] ndp = new long[5];
        for (int i = 0; i < 5; ++i) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            /* a前面可以为e,u,i */
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;
            /* e前面可以为a,i */
            ndp[1] = (dp[0] + dp[2]) % mod;
            /* i前面可以为e,o */
            ndp[2] = (dp[1] + dp[3]) % mod;
            /* o前面可以为i */
            ndp[3] = dp[2];
            /* u前面可以为i,o */
            ndp[4] = (dp[2] + dp[3]) % mod;
            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            ans = (ans + dp[i]) % mod;
        }
        return (int)ans;
    }

    /**
     * 方法二：矩阵快速幂
     * 思路
     *
     * 已经知道上述的递推公式，可以转将其转化为矩阵乘法，设 f(n)f(n) 表示长度为 nn 的字符串且以不同元音字母为结尾的组合数矩阵，构造矩阵的递推关系如下：
     *
     * f(n) = \begin{bmatrix} 0 & 1 & 1 & 0 & 1\\ 1 & 0 & 1 & 0 & 0\\ 0 & 1 & 0 & 1 & 0\\ 0 & 0 & 1 & 0 & 0\\ 0 & 0 & 1 & 1 & 0\\ \end{bmatrix} \times f(n-1)
     * f(n)=
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 0
     * 1
     * 0
     * 0
     * 0
     * ​
     *
     * 1
     * 0
     * 1
     * 0
     * 0
     * ​
     *
     * 1
     * 1
     * 0
     * 1
     * 1
     * ​
     *
     * 0
     * 0
     * 1
     * 0
     * 1
     * ​
     *
     * 1
     * 0
     * 0
     * 0
     * 0
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *  ×f(n−1)
     *
     * 因此我们可以推出:
     *
     * f(n) = \begin{bmatrix} 0 & 1 & 1 & 0 & 1\\ 1 & 0 & 1 & 0 & 0\\ 0 & 1 & 0 & 1 & 0\\ 0 & 0 & 1 & 0 & 0\\ 0 & 0 & 1 & 1 & 0\\ \end{bmatrix}^{n-1} \times f(1)
     * f(n)=
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 0
     * 1
     * 0
     * 0
     * 0
     * ​
     *
     * 1
     * 0
     * 1
     * 0
     * 0
     * ​
     *
     * 1
     * 1
     * 0
     * 1
     * 1
     * ​
     *
     * 0
     * 0
     * 1
     * 0
     * 1
     * ​
     *
     * 1
     * 0
     * 0
     * 0
     * 0
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     * n−1
     *  ×f(1)
     *
     * 令：
     *
     * f(1) = \begin{bmatrix} 1 & 1 & 1 & 1 & 1 \end{bmatrix}
     * f(1)=[
     * 1
     * ​
     *
     * 1
     * ​
     *
     * 1
     * ​
     *
     * 1
     * ​
     *
     * 1
     * ​
     *  ]
     *
     * M = \begin{bmatrix} 0 & 1 & 1 & 0 & 1\\ 1 & 0 & 1 & 0 & 0\\ 0 & 1 & 0 & 1 & 0\\ 0 & 0 & 1 & 0 & 0\\ 0 & 0 & 1 & 1 & 0\\ \end{bmatrix}
     * M=
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 0
     * 1
     * 0
     * 0
     * 0
     * ​
     *
     * 1
     * 0
     * 1
     * 0
     * 0
     * ​
     *
     * 1
     * 1
     * 0
     * 1
     * 1
     * ​
     *
     * 0
     * 0
     * 1
     * 0
     * 1
     * ​
     *
     * 1
     * 0
     * 0
     * 0
     * 0
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 因此只要我们能快速计算矩阵 MM 的 nn 次幂，就可以得到 f(n)f(n) 的值。如果直接求取 M^nM
     * n
     *   ，时间复杂度是 O(n)O(n)，可以定义矩阵乘法，然后用快速幂算法来加速 M^nM
     * n
     *   的求取。计算过程中，答案需要取模 1\text{e}9+71e9+7。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-vowels-permutation/solution/tong-ji-yuan-yin-zi-mu-xu-lie-de-shu-mu-jxo09/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.3 MB
     * , 在所有 Java 提交中击败了
     * 71.70%
     * 的用户
     * 通过测试用例：
     * 43 / 43
     */
    public int countVowelPermutation2(int n) {
        long mod = 1_000_000_007;
        long[][] factor =
                {
                        {0, 1, 0, 0, 0},
                        {1, 0, 1, 0, 0},
                        {1, 1, 0, 1, 1},
                        {0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0}
                };

        long[][] res = fastPow(factor, n - 1, mod);
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int)ans;
    }

    public long[][] fastPow(long[][] matrix, int n, long mod) {
        int m = matrix.length;
        long[][] res = new long[m][m];
        long[][] curr = matrix;

        for (int i = 0; i < m; ++i) {
            res[i][i] = 1;
        }
        for (int i = n; i != 0; i >>= 1) {
            if ((i % 2) == 1) {
                res = multiply(curr, res, mod);
            }
            curr = multiply(curr, curr, mod);
        }
        return res;
    }

    public long[][] multiply(long[][] matrixA, long[][] matrixB, long mod) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        long[][] res = new long[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = 0;
                for (int k = 0; k < matrixA[i].length; ++k) {
                    res[i][j] = (res[i][j] + matrixA[i][k] * matrixB[k][j]) % mod;
                }
            }
        }
        return res;
    }

}
