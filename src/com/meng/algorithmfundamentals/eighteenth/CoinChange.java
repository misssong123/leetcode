package com.meng.algorithmfundamentals.eighteenth;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return -1;
    }

    /**
     * 方法一：记忆化搜索
     * 我们能改进上面的指数时间复杂度的解吗？当然可以，利用动态规划，我们可以在多项式的时间范围内求解。首先，我们定义：
     *
     * F(S)F(S)：组成金额 SS 所需的最少硬币数量
     *
     * [c_{0}\ldots c_{n-1}][c
     * 0
     * ​
     *  …c
     * n−1
     * ​
     *  ] ：可选的 nn 枚硬币面额值
     *
     * 我们注意到这个问题有一个最优的子结构性质，这是解决动态规划问题的关键。最优解可以从其子问题的最优解构造出来。如何将问题分解成子问题？假设我们知道 F(S)F(S)，即组成金额 SS 最少的硬币数，最后一枚硬币的面值是 CC。那么由于问题的最优子结构，转移方程应为：
     *
     * F(S) = F(S - C) + 1
     * F(S)=F(S−C)+1
     *
     * 但我们不知道最后一枚硬币的面值是多少，所以我们需要枚举每个硬币面额值 c_0, c_1, c_2 \ldots c_{n -1}c
     * 0
     * ​
     *  ,c
     * 1
     * ​
     *  ,c
     * 2
     * ​
     *  …c
     * n−1
     * ​
     *   并选择其中的最小值。下列递推关系成立：
     *
     * F(S) = \min_{i=0 ... n-1}{ F(S - c_i) } + 1 \ \text{subject to} \ \ S-c_i \geq 0
     * F(S)=
     * i=0...n−1
     * min
     * ​
     *  F(S−c
     * i
     * ​
     *  )+1 subject to  S−c
     * i
     * ​
     *  ≥0
     *
     * F(S) = 0 \ , \text{when} \ S = 0
     * F(S)=0 ,when S=0
     *
     * F(S) = -1 \ , \text{when} \ n = 0
     * F(S)=−1 ,when n=0
     *
     *
     *
     * 在上面的递归树中，我们可以看到许多子问题被多次计算。例如，F(1)F(1) 被计算了 1313 次。为了避免重复的计算，我们将每个子问题的答案存在一个数组中进行记忆化，如果下次还要计算这个问题的值直接从数组中取出返回即可，这样能保证每个子问题最多只被计算一次。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     * 方法二：动态规划
     * 算法
     *
     * 我们采用自下而上的方式进行思考。仍定义 F(i)F(i) 为组成金额 ii 所需最少的硬币数量，假设在计算 F(i)F(i) 之前，我们已经计算出 F(0)-F(i-1)F(0)−F(i−1) 的答案。 则 F(i)F(i) 对应的转移方程应为
     *
     * F(i)=\min_{j=0 \ldots n-1}{F(i -c_j)} + 1
     * F(i)=
     * j=0…n−1
     * min
     * ​
     *  F(i−c
     * j
     * ​
     *  )+1
     *
     * 其中 c_jc
     * j
     * ​
     *   代表的是第 jj 枚硬币的面值，即我们枚举最后一枚硬币面额是 c_jc
     * j
     * ​
     *  ，那么需要从 i-c_ji−c
     * j
     * ​
     *   这个金额的状态 F(i-c_j)F(i−c
     * j
     * ​
     *  ) 转移过来，再算上枚举的这枚硬币数量 11 的贡献，由于要硬币数量最少，所以 F(i)F(i) 为前面能转移过来的状态的最小值加上枚举的硬币数量 11 。
     *
     * 例子1：假设
     *
     *
     * coins = [1, 2, 5], amount = 11
     * 则，当 i==0i==0 时无法用硬币组成，为 0 。当 i<0i<0 时，忽略 F(i)F(i)
     *
     * F(i)	最小硬币数量
     * F(0)	0 //金额为0不能由硬币组成
     * F(1)	1 //F(1)=min(F(1-1),F(1-2),F(1-5))+1=1F(1)=min(F(1−1),F(1−2),F(1−5))+1=1
     * F(2)	1 //F(2)=min(F(2-1),F(2-2),F(2-5))+1=1F(2)=min(F(2−1),F(2−2),F(2−5))+1=1
     * F(3)	2 //F(3)=min(F(3-1),F(3-2),F(3-5))+1=2F(3)=min(F(3−1),F(3−2),F(3−5))+1=2
     * F(4)	2 //F(4)=min(F(4-1),F(4-2),F(4-5))+1=2F(4)=min(F(4−1),F(4−2),F(4−5))+1=2
     * ...	...
     * F(11)	3 //F(11)=min(F(11-1),F(11-2),F(11-5))+1=3F(11)=min(F(11−1),F(11−2),F(11−5))+1=3
     * 我们可以看到问题的答案是通过子问题的最优解得到的。
     *
     * 例子2：假设
     *
     *
     * coins = [1, 2, 3], amount = 6
     *
     *
     * 在上图中，可以看到：
     *
     * \begin{aligned} F(3) &= \min({F(3- c_1), F(3-c_2), F(3-c_3)}) + 1 \\ &= \min({F(3- 1), F(3-2), F(3-3)}) + 1 \\ &= \min({F(2), F(1), F(0)}) + 1 \\ &= \min({1, 1, 0}) + 1 \\ &= 1 \end{aligned}
     * F(3)
     * ​
     *
     * =min(F(3−c
     * 1
     * ​
     *  ),F(3−c
     * 2
     * ​
     *  ),F(3−c
     * 3
     * ​
     *  ))+1
     * =min(F(3−1),F(3−2),F(3−3))+1
     * =min(F(2),F(1),F(0))+1
     * =min(1,1,0)+1
     * =1
     * ​
     *
     *
     * C++JavaPython3
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param coins
     * @param amount
     * @return
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 79.45%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 7.52%
     * 的用户
     * 通过测试用例：
     * 188 / 188
     */
    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


}
