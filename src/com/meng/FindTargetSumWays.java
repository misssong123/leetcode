package com.meng;

import java.util.Arrays;

/**
 * 494. 目标和
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 *     例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 *
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 20
 *     0 <= nums[i] <= 1000
 *     0 <= sum(nums[i]) <= 1000
 *     -1000 <= target <= 100
 * @author lenovo
 */
public class FindTargetSumWays {
    /**
     * 执行用时：495 ms, 在所有 Java 提交中击败了35.84% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了67.07% 的用户
     */
    int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (target > sum || target < -sum || (sum % 2) != (target % 2)){
            return res;
        }
        dfs(nums,0,target,0);
        return res;
    }

    private void dfs(int[] nums, int sum,int target ,int index) {
        if (index == nums.length){
            if (sum == target){
                res++;
            }
            return;
        }
        dfs(nums,sum + nums[index],target,index+1);
        dfs(nums,sum - nums[index],target,index+1);
    }

    /**
     * 方法一：回溯
     *
     * 数组 nums\textit{nums}nums 的每个元素都可以添加符号 +\texttt{+}+ 或 -\texttt{-}-，因此每个元素有 222 种添加符号的方法，nnn 个数共有 2n2^n2n 种添加符号的方法，对应 2n2^n2n 种不同的表达式。当 nnn 个元素都添加符号之后，即得到一种表达式，如果表达式的结果等于目标数 target\textit{target}target，则该表达式即为符合要求的表达式。
     *
     * 可以使用回溯的方法遍历所有的表达式，回溯过程中维护一个计数器 count\textit{count}count，当遇到一种表达式的结果等于目标数 target\textit{target}target 时，将 count\textit{count}count 的值加 111。遍历完所有的表达式之后，即可得到结果等于目标数 target\textit{target}target 的表达式的数目。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：597 ms, 在所有 Java 提交中击败了32.34% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了74.98% 的用户
     */
    int count = 0;

    public int findTargetSumWays1(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    /**
     * 方法二：动态规划
     *
     * 记数组的元素和为 sum\textit{sum}sum，添加 -\texttt{-}- 号的元素之和为 neg\textit{neg}neg，则其余添加 +\texttt{+}+ 的元素之和为 sum−neg\textit{sum}-\textit{neg}sum−neg，得到的表达式的结果为
     *
     * (sum−neg)−neg=sum−2⋅neg=target(\textit{sum}-\textit{neg})-\textit{neg}=\textit{sum}-2\cdot\textit{neg}=\textit{target} (sum−neg)−neg=sum−2⋅neg=target
     *
     * 即
     *
     * neg=sum−target2\textit{neg}=\dfrac{\textit{sum}-\textit{target}}{2} neg=2sum−target​
     *
     * 由于数组 nums\textit{nums}nums 中的元素都是非负整数，neg\textit{neg}neg 也必须是非负整数，所以上式成立的前提是 sum−target\textit{sum}-\textit{target}sum−target 是非负偶数。若不符合该条件可直接返回 000。
     *
     * 若上式成立，问题转化成在数组 nums\textit{nums}nums 中选取若干元素，使得这些元素之和等于 neg\textit{neg}neg，计算选取元素的方案数。我们可以使用动态规划的方法求解。
     *
     * 定义二维数组 dp\textit{dp}dp，其中 dp[i][j]\textit{dp}[i][j]dp[i][j] 表示在数组 nums\textit{nums}nums 的前 iii 个数中选取元素，使得这些元素之和等于 jjj 的方案数。假设数组 nums\textit{nums}nums 的长度为 nnn，则最终答案为 dp[n][neg]\textit{dp}[n][\textit{neg}]dp[n][neg]。
     *
     * 当没有任何元素可以选取时，元素和只能是 000，对应的方案数是 111，因此动态规划的边界条件是：
     *
     * dp[0][j]={1,j=00,j≥1\textit{dp}[0][j]=\begin{cases} 1, & j=0 \\ 0, & j \ge 1 \end{cases} dp[0][j]={1,0,​j=0j≥1​
     *
     * 当 1≤i≤n1 \le i \le n1≤i≤n 时，对于数组 nums\textit{nums}nums 中的第 iii 个元素 num\textit{num}num（iii 的计数从 111 开始），遍历 0≤j≤neg0 \le j \le \textit{neg}0≤j≤neg，计算 dp[i][j]\textit{dp}[i][j]dp[i][j] 的值：
     *
     *     如果 j<numj < \textit{num}j<num，则不能选 num\textit{num}num，此时有 dp[i][j]=dp[i−1][j]\textit{dp}[i][j] = \textit{dp}[i - 1][j]dp[i][j]=dp[i−1][j]；
     *
     *     如果 j≥numj \ge \textit{num}j≥num，则如果不选 num\textit{num}num，方案数是 dp[i−1][j]\textit{dp}[i - 1][j]dp[i−1][j]，如果选 num\textit{num}num，方案数是 dp[i−1][j−num]\textit{dp}[i - 1][j - \textit{num}]dp[i−1][j−num]，此时有 dp[i][j]=dp[i−1][j]+dp[i−1][j−num]\textit{dp}[i][j] = \textit{dp}[i - 1][j] + \textit{dp}[i - 1][j - \textit{num}]dp[i][j]=dp[i−1][j]+dp[i−1][j−num]。
     *
     * 因此状态转移方程如下：
     *
     * dp[i][j]={dp[i−1][j],j<nums[i]dp[i−1][j]+dp[i−1][j−nums[i]],j≥nums[i]\textit{dp}[i][j]=\begin{cases} \textit{dp}[i - 1][j], & j<\textit{nums}[i] \\ \textit{dp}[i - 1][j] + \textit{dp}[i - 1][j - \textit{nums}[i]], & j \ge \textit{nums}[i] \end{cases} dp[i][j]={dp[i−1][j],dp[i−1][j]+dp[i−1][j−nums[i]],​j<nums[i]j≥nums[i]​
     *
     * 最终得到 dp[n][neg]\textit{dp}[n][\textit{neg}]dp[n][neg] 的值即为答案。
     *
     * 由此可以得到空间复杂度为 O(n×neg)O(n \times \textit{neg})O(n×neg) 的实现。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     * 执行用时：5 ms, 在所有 Java 提交中击败了75.76% 的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了38.90% 的用户
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    /**
     * 由于 dp\textit{dp}dp 的每一行的计算只和上一行有关，因此可以使用滚动数组的方式，去掉 dp\textit{dp}dp 的第一个维度，将空间复杂度优化到 O(neg)O(\textit{neg})O(neg)。
     *
     * 实现时，内层循环需采用倒序遍历的方式，这种方式保证转移来的是 dp[i−1][]\textit{dp}[i-1][]dp[i−1][] 中的元素值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode-solution-o0cp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了58.92% 的用户
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }

    public static void main(String[] args) {
        FindTargetSumWays demo = new FindTargetSumWays();
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(demo.findTargetSumWays2(nums,target));
    }
}
