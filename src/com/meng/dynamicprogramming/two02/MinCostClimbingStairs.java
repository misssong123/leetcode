package com.meng.dynamicprogramming.two02;

/**
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * 示例 2：
 *
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 *
 *
 * 提示：
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.7 MB
     * , 在所有 Java 提交中击败了
     * 76.56%
     * 的用户
     * 通过测试用例：
     * 283 / 283
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 2){
            return Math.min(cost[0],cost[1]);
        }
        int[] nums = new int[len+1];
        nums[0] = 0 ;nums[1] = 0;
        for(int i = 2 ; i <= len ; i++){
            nums[i] = Math.min(cost[i-1] + nums[i-1],cost[i-2]+nums[i-2]);
        }
        return nums[len];
    }
    /**
     * 方法一：动态规划
     * 假设数组 \textit{cost}cost 的长度为 nn，则 nn 个阶梯分别对应下标 00 到 n-1n−1，楼层顶部对应下标 nn，问题等价于计算达到下标 nn 的最小花费。可以通过动态规划求解。
     *
     * 创建长度为 n+1n+1 的数组 \textit{dp}dp，其中 \textit{dp}[i]dp[i] 表示达到下标 ii 的最小花费。
     *
     * 由于可以选择下标 00 或 11 作为初始阶梯，因此有 \textit{dp}[0]=\textit{dp}[1]=0dp[0]=dp[1]=0。
     *
     * 当 2 \le i \le n2≤i≤n 时，可以从下标 i-1i−1 使用 \textit{cost}[i-1]cost[i−1] 的花费达到下标 ii，或者从下标 i-2i−2 使用 \textit{cost}[i-2]cost[i−2] 的花费达到下标 ii。为了使总花费最小，\textit{dp}[i]dp[i] 应取上述两项的最小值，因此状态转移方程如下：
     *
     * \textit{dp}[i]=\min(\textit{dp}[i-1]+\textit{cost}[i-1],\textit{dp}[i-2]+\textit{cost}[i-2])
     * dp[i]=min(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
     *
     * 依次计算 \textit{dp}dp 中的每一项的值，最终得到的 \textit{dp}[n]dp[n] 即为达到楼层顶部的最小花费。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/shi-yong-zui-xiao-hua-fei-pa-lou-ti-by-l-ncf8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param cost
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 34.63%
     * 的用户
     * 通过测试用例：
     * 283 / 283
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
     * 上述代码的时间复杂度和空间复杂度都是 O(n)O(n)。注意到当 i \ge 2i≥2 时，\textit{dp}[i]dp[i] 只和 \textit{dp}[i-1]dp[i−1] 与 \textit{dp}[i-2]dp[i−2] 有关，因此可以使用滚动数组的思想，将空间复杂度优化到 O(1)O(1)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs/solution/shi-yong-zui-xiao-hua-fei-pa-lou-ti-by-l-ncf8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param cost
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 44.18%
     * 的用户
     * 通过测试用例：
     * 283 / 283
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
