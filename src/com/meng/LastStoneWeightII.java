package com.meng;

import java.util.Arrays;

/**
 * 1049. 最后一块石头的重量 II
 *
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 *     如果 x == y，那么两块石头都会被完全粉碎；
 *     如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 *
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 示例 2：
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 *
 * 示例 3：
 *
 * 输入：stones = [1,2]
 * 输出：1
 *
 *
 *
 * 提示：
 *
 *     1 <= stones.length <= 30
 *     1 <= stones[i] <= 100
 */

public class LastStoneWeightII {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了47.30% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了35.01% 的用户
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int len = stones.length;
        int sum = 0;
        for(int i = 0 ; i < len ; i++){
            sum += stones[i];
        }
        int target = sum / 2 ;
        int [][] dp = new int[len + 1][target + 1];
        for(int i = 1 ; i <= len ; i++){
            for(int j = 1 ; j <= target ; j++){
                if (stones[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],stones[i-1] + dp[i-1][j-stones[i-1]]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 1 ; i <= len ; i++){
            res = Math.min(res,sum- 2*dp[i][target]);
        }
        return res;
    }

    public static void main(String[] args) {
        LastStoneWeightII demo = new LastStoneWeightII();
        int[] stones = {1,2,3,4,5};
        System.out.println(demo.lastStoneWeightII(stones));
    }


    /**
     * 前言：合法性证明
     *
     * 为了便于讨论，若最终没有石头剩下，则视作最终剩下了一块重量为 000 的石头。
     *
     * 用归纳法可以证明，无论按照何种顺序粉碎石头，最后一块石头的重量总是可以表示成
     *
     * ∑i=0n−1ki×stonesi,  ki∈{−1,1}\sum_{i=0}^{n-1} k_i \times \textit{stones}_i,\ \ k_i\in\{-1,1\} i=0∑n−1​ki​×stonesi​,  ki​∈{−1,1}
     *
     * 但不是所有 kik_iki​ 的取值都是合法的。例如有四块石头，其重量分别为 aaa，bbb，ccc，ddd，且满足 a≤b≤c≤da\le b\le c\le da≤b≤c≤d。由于石头的重量不可能增加，无论怎么操作，我们是不可能得到大小为 d+c+b−ad+c+b-ad+c+b−a 的石头的，该重量已经超过了 ccc 以及 ddd。
     *
     * 那么，上述和式的最小非负值所对应的这组 {ki}\{k_i\}{ki​} 是合法的吗？
     *
     * 我们将这组 {ki}\{k_i\}{ki​} 对应的石头划分成两堆，ki=1k_i=1ki​=1 的石头分至一堆，ki=−1k_i=-1ki​=−1 的石头分至另一堆。由于这是最小非负值所对应的 {ki}\{k_i\}{ki​}，这两堆石头重量之差的绝对值也是所有划分当中最小的。
     *
     * 记这两堆石头重量之差的绝对值为 diff\textit{diff}diff。若能找到一种粉碎方案，使得最后一块石头的重量也为 diff\textit{diff}diff，那就能说明这组 {ki}\{k_i\}{ki​} 是合法的。
     *
     * 我们不断地粉碎石头。每次粉碎时，记重量最大的石头所处的堆为 AAA（若两堆最大重量相同则任选一堆），另一堆为 BBB。从 AAA 中取出重量最大的石头，BBB 中任取一石头，若没有完全粉碎，则将新石头重新放入 AAA。这一操作从每堆石头中减去了同样的重量，从而保证重量之差的绝对值在粉碎前后是不变的。
     *
     * 若出现一堆没有石头，而另一堆不止一块石头的情况，记有石头的那一堆为 AAA，另一堆为 BBB。要继续粉碎，则需要从 AAA 中取出一块石头移入 BBB，然后按规则粉碎。但移入操作让重量之差的绝对值变得更小，与事实（上文加粗文字）矛盾，所以不会出现这种情况。
     *
     * 因此，按照上述流程操作，最后一块石头的重量为 diff\textit{diff}diff，所以这组 {ki}\{k_i\}{ki​} 对应着一个合法的粉碎结果。
     * 方法一：动态规划
     *
     * 记石头的总重量为 sum\textit{sum}sum，ki=−1k_i=-1ki​=−1 的石头的重量之和为 neg\textit{neg}neg，则其余 ki=1k_i=1ki​=1 的石头的重量之和为 sum−neg\textit{sum}-\textit{neg}sum−neg。则有
     *
     * ∑i=0n−1ki⋅stonesi=(sum−neg)−neg=sum−2⋅neg\sum_{i=0}^{n-1} k_i\cdot\textit{stones}_i = (\textit{sum}-\textit{neg})-\textit{neg} = \textit{sum}-2\cdot\textit{neg} i=0∑n−1​ki​⋅stonesi​=(sum−neg)−neg=sum−2⋅neg
     *
     * 要使最后一块石头的重量尽可能地小，neg\textit{neg}neg 需要在不超过 ⌊sum/2⌋\lfloor \textit{sum}/2 \rfloor⌊sum/2⌋ 的前提下尽可能地大。因此本问题可以看作是背包容量为 ⌊sum/2⌋\lfloor \textit{sum}/2 \rfloor⌊sum/2⌋，物品重量和价值均为 stonesi\textit{stones}_istonesi​ 的 0-1 背包问题。
     *
     * 对于该问题，定义二维布尔数组 dp\textit{dp}dp，其中 dp[i+1][j]\textit{dp}[i+1][j]dp[i+1][j] 表示前 iii 个石头能否凑出重量 jjj。特别地，dp[0][]\textit{dp}[0][]dp[0][] 为不选任何石头的状态，因此除了 dp[0][0]\textit{dp}[0][0]dp[0][0] 为真，其余 dp[0][j]\textit{dp}[0][j]dp[0][j] 全为假。
     *
     * 对于第 iii 个石头，考虑凑出重量 jjj：
     *
     *     若 j<stones[i]j<\textit{stones}[i]j<stones[i]，则不能选第 iii 个石头，此时有 dp[i+1][j]=dp[i][j]\textit{dp}[i+1][j]=\textit{dp}[i][j]dp[i+1][j]=dp[i][j]；
     *     若 j≥stones[i]j\ge \textit{stones}[i]j≥stones[i]，存在选或不选两种决策，不选时有 dp[i+1][j]=dp[i][j]\textit{dp}[i+1][j]=\textit{dp}[i][j]dp[i+1][j]=dp[i][j]，选时需要考虑能否凑出重量 j−stones[i]j-\textit{stones}[i]j−stones[i]，即 dp[i+1][j]=dp[i][j−stones[i]]\textit{dp}[i+1][j]=\textit{dp}[i][j-\textit{stones}[i]]dp[i+1][j]=dp[i][j−stones[i]]。若二者均为假则 dp[i+1][j]\textit{dp}[i+1][j]dp[i+1][j] 为假，否则 dp[i+1][j]\textit{dp}[i+1][j]dp[i+1][j] 为真。
     *
     * 因此状态转移方程如下：
     *
     * dp[i+1][j]={dp[i][j],j<stones[i]dp[i][j]∨dp[i][j−stones[i]],j≥stones[i]\textit{dp}[i+1][j]= \begin{cases} \textit{dp}[i][j],& j<\textit{stones}[i] \\ \textit{dp}[i][j] \lor \textit{dp}[i][j-\textit{stones}[i]], & j\ge \textit{stones}[i] \end{cases} dp[i+1][j]={dp[i][j],dp[i][j]∨dp[i][j−stones[i]],​j<stones[i]j≥stones[i]​
     *
     * 其中 ∨\lor∨ 表示逻辑或运算。求出 dp[n][]\textit{dp}[n][]dp[n][] 后，所有为真的 dp[n][j]\textit{dp}[n][j]dp[n][j] 中，最大的 jjj 即为 neg\textit{neg}neg 能取到的最大值。代入 sum−2⋅neg\textit{sum}-2\cdot\textit{neg}sum−2⋅neg 中即得到最后一块石头的最小重量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii/solution/zui-hou-yi-kuai-shi-tou-de-zhong-liang-i-95p9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了88.86% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了82.24% 的用户
     * @param stones
     * @return
     */
    public int lastStoneWeightII1(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        int n = stones.length, m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m;; --j) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
    }

    /**
     * 由于 dp[i+1][]\textit{dp}[i+1][]dp[i+1][] 的每个元素值的计算只和 dp[i][]\textit{dp}[i][]dp[i][] 的元素值有关，因此可以使用滚动数组的方式，去掉 dp\textit{dp}dp 的第一个维度。
     *
     * 对于转移方程
     *
     * dp[i+1][j]=dp[i][j]∨dp[i][j−stones[i]]\textit{dp}[i+1][j]=\textit{dp}[i][j] \lor \textit{dp}[i][j-\textit{stones}[i]] dp[i+1][j]=dp[i][j]∨dp[i][j−stones[i]]
     *
     * 在去掉第一个维度后，若仍采用正序遍历，在计算 dp[j]\textit{dp}[j]dp[j] 时，dp[j−stones[i]]\textit{dp}[j-\textit{stones}[i]]dp[j−stones[i]] 的值已经被覆盖，这意味着 dp[j−stones[i]]\textit{dp}[j-\textit{stones}[i]]dp[j−stones[i]] 实际对应的是 dp[i+1][j−stones[i]]\textit{dp}[i+1][j-\textit{stones}[i]]dp[i+1][j−stones[i]]，即我们计算的是一个错误的转移方程
     *
     * dp[i+1][j]=dp[i][j]∨dp[i+1][j−stones[i]]\textit{dp}[i+1][j]=\textit{dp}[i][j] \lor \textit{dp}[i+1][j-\textit{stones}[i]] dp[i+1][j]=dp[i][j]∨dp[i+1][j−stones[i]]
     *
     * 若采用倒序遍历，则可消除该错误，这种方式保证计算 dp[j]\textit{dp}[j]dp[j] 时，dp[j−stones[i]]\textit{dp}[j-\textit{stones}[i]]dp[j−stones[i]] 的值实际对应的是 dp[i][j−stones[i]]\textit{dp}[i][j-\textit{stones}[i]]dp[i][j−stones[i]]，从而保证转移方程与去掉维度前一致。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii/solution/zui-hou-yi-kuai-shi-tou-de-zhong-liang-i-95p9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param stones
     * @return
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.07% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了99.86% 的用户
     */
    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        int m = sum / 2;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int weight : stones) {
            for (int j = m; j >= weight; --j) {
                dp[j] = dp[j] || dp[j - weight];
            }
        }
        for (int j = m;; --j) {
            if (dp[j]) {
                return sum - 2 * j;
            }
        }
    }

}
