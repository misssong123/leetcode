package com.meng.origin;

/**
 * 746. 使用最小花费爬楼梯
 *
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 *
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 *
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *
 * 示例 1:
 *
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *
 *  示例 2:
 *
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 *
 * 注意：
 *
 *     cost 的长度将会在 [2, 1000]。
 *     每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 */
public class MinCostClimbingStairs {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.68% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了80.84% 的用户
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int [] res = new int[len+1];
        for(int i = 2 ; i <= len ; i++){
            //从那阶台阶过来，加上本身台阶的步数
            res[i] = Math.min(res[i-1]+cost[i-1],cost[i-2]+res[i-2]);
        }
        return res[len];
    }
    /**
     * 方法一：动态规划
     *
     * 假设数组 cost\textit{cost}cost 的长度为 nnn，则 nnn 个阶梯分别对应下标 000 到 n−1n-1n−1，楼层顶部对应下标 nnn，问题等价于计算达到下标 nnn 的最小花费。可以通过动态规划求解。
     *
     * 创建长度为 n+1n+1n+1 的数组 dp\textit{dp}dp，其中 dp[i]\textit{dp}[i]dp[i] 表示达到下标 iii 的最小花费。
     *
     * 由于可以选择下标 000 或 111 作为初始阶梯，因此有 dp[0]=dp[1]=0\textit{dp}[0]=\textit{dp}[1]=0dp[0]=dp[1]=0。
     *
     * 当 2≤i≤n2 \le i \le n2≤i≤n 时，可以从下标 i−1i-1i−1 使用 cost[i−1]\textit{cost}[i-1]cost[i−1] 的花费达到下标 iii，或者从下标 i−2i-2i−2 使用 cost[i−2]\textit{cost}[i-2]cost[i−2] 的花费达到下标 iii。为了使总花费最小，dp[i]\textit{dp}[i]dp[i] 应取上述两项的最小值，因此状态转移方程如下：
     *
     * dp[i]=min⁡(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])\textit{dp}[i]=\min(\textit{dp}[i-1]+\textit{cost}[i-1],\textit{dp}[i-2]+\textit{cost}[i-2]) dp[i]=min(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
     *
     * 依次计算 dp\textit{dp}dp 中的每一项的值，最终得到的 dp[n]\textit{dp}[n]dp[n] 即为达到楼层顶部的最小花费。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/shi-yong-zui-xiao-hua-fei-pa-lou-ti-by-l-ncf8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.68% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了84.19% 的用户
     */
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
    /**
     * 上述代码的时间复杂度和空间复杂度都是 O(n)O(n)O(n)。注意到当 i≥2i \ge 2i≥2 时，dp[i]\textit{dp}[i]dp[i] 只和 dp[i−1]\textit{dp}[i-1]dp[i−1] 与 dp[i−2]\textit{dp}[i-2]dp[i−2] 有关，因此可以使用滚动数组的思想，将空间复杂度优化到 O(1)O(1)O(1)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/shi-yong-zui-xiao-hua-fei-pa-lou-ti-by-l-ncf8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.68% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了81.02% 的用户
     */
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }

}
