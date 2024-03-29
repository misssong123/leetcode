package com.meng.algorithmfundamentals.eighteenth;

/**
 * 343. 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 *
 * 返回 你可以获得的最大乘积 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 *
 * 提示:
 *
 * 2 <= n <= 58
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        return -1;
    }

    /**
     * 方法一：动态规划
     * 对于的正整数 nn，当 n \ge 2n≥2 时，可以拆分成至少两个正整数的和。令 kk 是拆分出的第一个正整数，则剩下的部分是 n-kn−k，n-kn−k 可以不继续拆分，或者继续拆分成至少两个正整数的和。由于每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积，因此可以使用动态规划求解。
     *
     * 创建数组 \textit{dp}dp，其中 \textit{dp}[i]dp[i] 表示将正整数 ii 拆分成至少两个正整数的和之后，这些正整数的最大乘积。特别地，00 不是正整数，11 是最小的正整数，00 和 11 都不能拆分，因此 \textit{dp}[0]=\textit{dp}[1]=0dp[0]=dp[1]=0。
     *
     * 当 i \ge 2i≥2 时，假设对正整数 ii 拆分出的第一个正整数是 jj（1 \le j < i1≤j<i），则有以下两种方案：
     *
     * 将 ii 拆分成 jj 和 i-ji−j 的和，且 i-ji−j 不再拆分成多个正整数，此时的乘积是 j \times (i-j)j×(i−j)；
     *
     * 将 ii 拆分成 jj 和 i-ji−j 的和，且 i-ji−j 继续拆分成多个正整数，此时的乘积是 j \times \textit{dp}[i-j]j×dp[i−j]。
     *
     * 因此，当 jj 固定时，有 \textit{dp}[i]=\max(j \times (i-j), j \times \textit{dp}[i-j])dp[i]=max(j×(i−j),j×dp[i−j])。由于 jj 的取值范围是 11 到 i-1i−1，需要遍历所有的 jj 得到 \textit{dp}[i]dp[i] 的最大值，因此可以得到状态转移方程如下：
     *
     * \textit{dp}[i]=\mathop{\max}\limits_{1 \le j < i}\{\max(j \times (i-j), j \times \textit{dp}[i-j])\}
     * dp[i]=
     * 1≤j<i
     * max
     * ​
     *  {max(j×(i−j),j×dp[i−j])}
     *
     * 最终得到 \textit{dp}[n]dp[n] 的值即为将正整数 nn 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int integerBreak1(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    /**
     * 方法二：优化的动态规划
     * 方法一中定义的状态转移方程如下：
     *
     * \textit{dp}[i]=\mathop{\max}\limits_{1 \le j < i}\{\max(j \times (i-j), j \times \textit{dp}[i-j])\}
     * dp[i]=
     * 1≤j<i
     * max
     * ​
     *  {max(j×(i−j),j×dp[i−j])}
     *
     * 使用上述状态转移方程，计算 \textit{dp}[i]dp[i] 时，jj 的值遍历了从 11 到 i-1i−1 的所有值，因此总时间复杂度是 O(n^2)O(n
     * 2
     *  )。是否可以降低时间复杂度？
     *
     * 上述状态转移方程包含两项，当 jj 固定时，\textit{dp}[i]dp[i] 的值由 j \times (i-j)j×(i−j) 和 j \times \textit{dp}[i-j]j×dp[i−j] 中的较大值决定，因此需要对两项分别考虑。
     *
     * 首先考虑 j \times \textit{dp}[i-j]j×dp[i−j] 这一项。
     *
     * 注意到 \textit{dp}dp 的定义，\textit{dp}[i]dp[i] 表示将正整数 ii 拆分成至少两个正整数的和之后，这些正整数的最大乘积，因此对于任意 1 \le j < i1≤j<i，有 \textit{dp}[i] \ge j \times \textit{dp}[i-j]dp[i]≥j×dp[i−j]。
     *
     * 当 jj 是奇数时，有 j=\frac{j-1}{2}+\frac{j+1}{2}j=
     * 2
     * j−1
     * ​
     *  +
     * 2
     * j+1
     * ​
     *  ，因此 \textit{dp}[i] \geq \frac{j-1}{2} \times \textit{dp}[i - \frac{j-1}{2}] \ge \frac{j-1}{2} \times \frac{j+1}{2} \times \textit{dp}[i-j]dp[i]≥
     * 2
     * j−1
     * ​
     *  ×dp[i−
     * 2
     * j−1
     * ​
     *  ]≥
     * 2
     * j−1
     * ​
     *  ×
     * 2
     * j+1
     * ​
     *  ×dp[i−j]。
     *
     * 当 jj 是偶数时，有 j=\frac{j}{2}+\frac{j}{2}j=
     * 2
     * j
     * ​
     *  +
     * 2
     * j
     * ​
     *  ，因此 \textit{dp}[i] \ge \frac{j}{2} \times \textit{dp}[i - \frac{j}{2}] \ge \frac{j}{2} \times \frac{j}{2} \times \textit{dp}[i-j]dp[i]≥
     * 2
     * j
     * ​
     *  ×dp[i−
     * 2
     * j
     * ​
     *  ]≥
     * 2
     * j
     * ​
     *  ×
     * 2
     * j
     * ​
     *  ×dp[i−j]。
     *
     * 如果 j \ge 4j≥4 且 jj 是奇数，则 \frac{j-1}{2} \times \frac{j+1}{2}>j
     * 2
     * j−1
     * ​
     *  ×
     * 2
     * j+1
     * ​
     *  >j 恒成立。如果 j \ge 4j≥4 且 jj 是偶数，则 \frac{j}{2} \times \frac{j}{2} \ge j
     * 2
     * j
     * ​
     *  ×
     * 2
     * j
     * ​
     *  ≥j 恒成立，当且仅当 j=4j=4 时等号成立。
     *
     * 由此可知，如果 j \ge 4j≥4，则 \textit{dp}[j] \ge jdp[j]≥j，当且仅当 j=4j=4 时等号成立，即当 j \ge 4j≥4 时一定能将 jj 拆成至少两个正整数的和，这些正整数的乘积大于或等于 jj。
     *
     * 同时也可以得到，如果 j \ge 4j≥4，则 \textit{dp}[i] \ge j \times \textit{dp}[i-j]dp[i]≥j×dp[i−j]，只有当 j=4j=4 时等号可能成立。又由于
     *
     * \textit{dp}[i] \ge 2 \times \textit{dp}[i-2] \ge 2 \times (2 \times \textit{dp}[i-4]) = 4 \times \textit{dp}[i-4]
     * dp[i]≥2×dp[i−2]≥2×(2×dp[i−4])=4×dp[i−4]
     *
     * 因此取 j=2j=2 计算得到的 \textit{dp}[i]dp[i] 一定不会小于取 j=4j=4 计算得到的 \textit{dp}[i]dp[i]。根据上述分析，j \ge 4j≥4 的情况都是不需要考虑的。
     *
     * 那么 j=1j=1 是否需要考虑？答案是不需要。如果取 j=1j=1，则有 \textit{dp}[i] \ge 1 \times \textit{dp}[i-1]=\textit{dp}[i-1]dp[i]≥1×dp[i−1]=dp[i−1]。当 i \ge 3i≥3 时，\textit{dp}[i-1]dp[i−1] 是将正整数 i-1i−1 拆分成至少两个正整数的和之后，这些正整数的最大乘积，在拆分成的正整数中，任选一个数字加 11，则拆分成的正整数的和变成 ii，且乘积一定大于 \textit{dp}[i-1]dp[i−1]，因此必有 \textit{dp}[i]>\textit{dp}[i-1]dp[i]>dp[i−1]，即当 j=1j=1 时不可能得到最大的 \textit{dp}[i]dp[i] 的值。
     *
     * 根据上述分析可知，计算 \textit{dp}[i]dp[i] 的值只需要考虑 j=2j=2 和 j=3j=3 的情况，不需要遍历从 11 到 i-1i−1 的所有值。
     *
     * 其次考虑 j \times (i-j)j×(i−j) 这一项。
     *
     * 根据上述推导可知，如果 j \ge 4j≥4，则 \textit{dp}[j] \ge jdp[j]≥j，当且仅当 j=4j=4 时等号成立。因此在 i-j \ge 4i−j≥4 的情况下，有 \textit{dp}[i-j] \ge i-jdp[i−j]≥i−j，\textit{dp}[i] \ge j \times \textit{dp}[i-j] \ge j \times (i-j)dp[i]≥j×dp[i−j]≥j×(i−j)，此时计算 \textit{dp}[i]dp[i] 的值不需要考虑 j \times (i-j)j×(i−j) 的值。
     *
     * 如果 i-j<4i−j<4，在计算 \textit{dp}[i]dp[i] 的值的时候就需要考虑 j \times (i-j)j×(i−j) 的值。在考虑 j \times \textit{dp}[i-j]j×dp[i−j] 时，根据上述分析，只需要考虑 j=2j=2 和 j=3j=3 的情况。在考虑 j \times (i-j)j×(i−j) 时，需要考虑 jj 的哪些值？
     *
     * 如果 j=1j=1，则 1 \times (i-1)=i-11×(i−1)=i−1，当 i=2i=2 或 i=3i=3 时有 \textit{dp}[i]=i-1dp[i]=i−1，当 i \ge 4i≥4 时有 \textit{dp}[i] \ge i>i-1dp[i]≥i>i−1，显然当 i \ge 4i≥4 时取 j=1j=1 不可能得到最大乘积，因此 j=1j=1 时是不需要考虑的。
     *
     * 如果 j \ge 4j≥4，\textit{dp}[i]dp[i] 是否可能等于 j \times (i-j)j×(i−j)？当 ii 固定时，要使得 j \times (i-j)j×(i−j) 的值最大，jj 的值应该取 j=i/2j=i/2，这里的 // 表示整数除法。当 j \ge 4j≥4 时，若要满足 j=i/2j=i/2，则 i \ge 8i≥8，此时 i-j \ge 4i−j≥4，利用上述结论，\textit{dp}[i-j] \ge i-jdp[i−j]≥i−j，因此 j \times \textit{dp}[i-j] \ge j \times (i-j)j×dp[i−j]≥j×(i−j)。由此可见，当 j \ge 4j≥4 时，计算 \textit{dp}[i]dp[i] 只需要考虑 j \times \textit{dp}[i-j]j×dp[i−j]，不需要考虑 j \times (i-j)j×(i−j)。
     *
     * 又由于在使用 j \times \textit{dp}[i-j]j×dp[i−j] 计算 \textit{dp}[i]dp[i] 时，j=2j=2 和 j=3j=3 的情况一定优于 j \ge 4j≥4 的情况，因此无论是考虑 j \times \textit{dp}[i-j]j×dp[i−j] 还是考虑 j \times (i-j)j×(i−j)，都只需要考虑 j=2j=2 和 j=3j=3 的情况。
     *
     * 由此可以对方法一的动态规划进行优化。
     *
     * 边界情况是 n=2n=2，此时唯一的拆分方案是 2=1+12=1+1，最大乘积是 1 \times 1=11×1=1。
     *
     * 当 i \ge 3i≥3 时，状态转移方程如下：
     *
     * \textit{dp}[i]=\max(2 \times (i-2), 2 \times \textit{dp}[i-2], 3 \times (i-3), 3 \times \textit{dp}[i-3])
     * dp[i]=max(2×(i−2),2×dp[i−2],3×(i−3),3×dp[i−3])
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38 MB
     * , 在所有 Java 提交中击败了
     * 46.38%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     */
    public int integerBreak2(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }

    /**
     * 方法三：数学
     * 方法二中利用了数学知识降低时间复杂度。正整数 44 可以拆分成 2+22+2，乘积不变（4=2 \times 24=2×2）。对于大于 44 的正整数，总是存在一种拆分的方案，使得拆分成的两个正整数的乘积大于拆分前的正整数（例如，5=2+35=2+3，2 \times 3=6>52×3=6>5）。那么，能否利用数学知识在方法二的基础上进一步降低时间复杂度，找到最优的拆分方案呢？
     *
     * 下面给出两种直接得出最优拆分方案的证明方法。
     *
     * 函数极值证明法
     *
     * 显然，如果将给定的正整数拆分成尽可能多的某个特定的正整数，则这些正整数的乘积最大。
     *
     * 定义函数 f(x)f(x) 表示将给定的正整数 nn 拆分成尽可能多的正数 xx 的情况下的最大乘积，则可以将 nn 分成 \frac{n}{x}
     * x
     * n
     * ​
     *   项，此时 f(x)=x^{\frac{n}{x}}f(x)=x
     * x
     * n
     * ​
     *
     *  ，目标是求 f(x)f(x) 的最大值，即
     *
     * \mathop{\max}\limits_{x}(f(x))
     * x
     * max
     * ​
     *  (f(x))
     *
     * 可以将 f(x)f(x) 写成如下形式：
     *
     * f(x)=x^{\frac{n}{x}}=e^{\frac{n \ln x}{x}}
     * f(x)=x
     * x
     * n
     * ​
     *
     *  =e
     * x
     * nlnx
     * ​
     *
     *
     *
     * 令 g(t)=e^tg(t)=e
     * t
     *  ，h(x)=\frac{\ln x}{x}h(x)=
     * x
     * lnx
     * ​
     *  ，则有 f(x)=g(n \cdot h(x))f(x)=g(n⋅h(x))。由于 g(t)g(t) 是单调递增的，n>0n>0，因此 h(x)h(x) 与 f(x)f(x) 的单调性相同。
     *
     * 计算 h(x)h(x) 的驻点，即 h'(x)=\frac{1- \ln x}{x^2}=0h
     * ′
     *  (x)=
     * x
     * 2
     *
     * 1−lnx
     * ​
     *  =0 的点，得到驻点为 x=ex=e。
     *
     * 由于当 0<x<e0<x<e 时 h'(x)>0h
     * ′
     *  (x)>0，当 x>ex>e 时 h'(x)<0h
     * ′
     *  (x)<0，因此 x=ex=e 是 h(x)h(x) 的极大值点，也是 f(x)f(x) 的极大值点。由于函数 f(x)f(x) 的定义域连续，因此极大值点唯一，也是最大值点。
     *
     * 因此，当 x=ex=e 时，f(x)f(x) 取到最大值，\max f(x)=f(e)=e^{\frac{n}{e}}maxf(x)=f(e)=e
     * e
     * n
     * ​
     *
     *  。
     *
     * 由于 ee 不是整数，因此使用与 ee 最接近的整数作为 xx 的值，xx 可以是 22 或 33，此时需要比较 f(2)f(2) 与 f(3)f(3) 的大小，可以通过计算 \frac{f(3)}{f(2)}
     * f(2)
     * f(3)
     * ​
     *   进行比较。
     *
     * \frac{f(3)}{f(2)}=\frac{e^{n \cdot h(3)}}{e^{n \cdot h(2)}}=e^{n \cdot h(3)-n \cdot h(2)}=e^{n \cdot (\frac{\ln 3}{3} - \frac{\ln 2}{2})}=e^{\frac{n}{6} \cdot (2 \ln 3 - 3 \ln 2)}=e^{\frac{n}{6} \cdot (\ln 9 - \ln 8)}
     * f(2)
     * f(3)
     * ​
     *  =
     * e
     * n⋅h(2)
     *
     * e
     * n⋅h(3)
     *
     * ​
     *  =e
     * n⋅h(3)−n⋅h(2)
     *  =e
     * n⋅(
     * 3
     * ln3
     * ​
     *  −
     * 2
     * ln2
     * ​
     *  )
     *  =e
     * 6
     * n
     * ​
     *  ⋅(2ln3−3ln2)
     *  =e
     * 6
     * n
     * ​
     *  ⋅(ln9−ln8)
     *
     *
     * 由于 \ln 9 > \ln 8ln9>ln8，因此 \frac{f(3)}{f(2)}>1
     * f(2)
     * f(3)
     * ​
     *  >1，即 f(3)>f(2)f(3)>f(2)。当 x=3x=3 时，可以得到最大乘积。因此，应该将给定的正整数拆分成尽可能多的 33。
     *
     * 根据 nn 除以 33 的余数进行分类讨论：
     *
     * 如果余数为 00，即 n=3m(m \ge 2)n=3m(m≥2)，则将 nn 拆分成 mm 个 33；
     *
     * 如果余数为 11，即 n=3m+1(m \ge 1)n=3m+1(m≥1)，由于 2 \times 2 > 3 \times 12×2>3×1，因此将 nn 拆分成 m-1m−1 个 33 和 22 个 22；
     *
     * 如果余数为 22，即 n=3m+2(m \ge 1)n=3m+2(m≥1)，则将 nn 拆分成 mm 个 33 和 11 个 22。
     *
     * 上述拆分的适用条件是 n \ge 4n≥4。如果 n \le 3n≤3，则上述拆分不适用，需要单独处理。
     *
     * 如果 n=2n=2，则唯一的拆分方案是 2=1+12=1+1，最大乘积是 1 \times 1=11×1=1；
     *
     * 如果 n=3n=3，则拆分方案有 3=1+2=1+1+13=1+2=1+1+1，最大乘积对应方案 3=1+23=1+2，最大乘积是 1 \times 2=21×2=2。
     *
     * 这两种情形可以合并为：当 n \le 3n≤3 时，最大乘积是 n-1n−1。
     *
     * 归纳证明法
     *
     * 第一步：证明最优的拆分方案中不会出现大于 44 的整数。
     *
     * 假设出现了大于 44 的整数 xx，由于 2(x-2) > x2(x−2)>x 在 x > 4x>4 时恒成立，将 xx 拆分成 22 和 x-2x−2 可以增大乘积。因此最优的拆分方案中不会出现大于 44 的整数。
     *
     * 第二步：证明最优的拆分方案中可以不出现整数 44。
     *
     * 如果出现了整数 44，我们可以用 2 \times 22×2 代替之，乘积不变。
     *
     * 此时，我们可以知道，最优的拆分方案中只会出现 11，22 和 33。
     *
     * 第三步：证明当 n \geq 5n≥5 时，最优的拆分方案中不会出现整数 11。
     *
     * 当 n \geq 5n≥5 时，如果出现了整数 11，那么拆分中剩余的数的和为 n-1 \geq 4n−1≥4，对应这至少两个整数。我们将其中任意一个整数 xx 加上 11，乘积就会增大。因此最优的拆分方案中不会出现整数 11。
     *
     * 此时，我们可以知道，当 n \geq 5n≥5 时，最优的拆分方案中只会出现 22 和 33。
     *
     * 第四步：证明当 n \geq 5n≥5 时，最优的拆分方案中 22 的个数不会超过 33 个。
     *
     * 如果出现了超过 33 个 22，那么将它们转换成 22 个 33，可以增大乘积，即3 \times 3 > 2 \times 2 \times 23×3>2×2×2。
     *
     * 此时，n \geq 5n≥5 的最优拆分方案就唯一了。这是因为当最优的拆分方案中 22 的个数分别为 00，11，22 个时，就对应着 nn 除以 33 的余数分别为 00，22，11 的情况。因此我们可以得到和「函数极值证明法」相同的分类讨论结果。
     *
     * 当 n = 4n=4 时，4 = 2 \times 24=2×2 的最优拆分方案也可以放入分类讨论结果；当 2 \leq n \leq 32≤n≤3 时，只有唯一的拆分方案 1 \times (n - 1)1×(n−1)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int integerBreak3(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }


}
