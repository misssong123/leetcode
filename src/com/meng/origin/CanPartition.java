package com.meng.origin;

import java.util.Arrays;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 *     每个数组中的元素不会超过 100
 *     数组的大小不会超过 200
 *
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 *
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanPartition {
    boolean flag = false;
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        if (length < 2)
            return false;
        int sum = 0;
        for (int i = 0 ; i < length ; i++)
            sum += nums[i];
        if (sum % 2 != 0 )
            return false;
        if (sum == 0)
            return true;
        Arrays.sort(nums);
        dfs(nums,sum/2,0,length);
        return flag;
    }

    /**
     * 效率太低
     * 递归回溯
     * @param nums
     * @param target
     * @param index
     * @param length
     */
    private void dfs(int[] nums, int target, int index,int length) {
        if (index == length)
            return;
        if (target == 0){
            flag = true;
            return;
        }
        //放入该下标的值
        if (target>=nums[index] && !flag){
            dfs(nums,target-nums[index],index+1,length);
        }
        //不放入该下标的值
        if (!flag && index+1 < length && target>=nums[index] ){
            dfs(nums,target,index+1,length);
        }
    }

    /**
     * 动态规划（0,1背包问题）
     * 1.求出目标数target，及最小数minNum
     * 2.建立二维数组boolean[nums.length][target-minNum+1]
     * 3.初始第一行的第一个数为true，及第一行nums[0]为true
     * 4.其他行的结果
     *      若当前列的下标小于nums[i],则结果为上一行该列的结果
     *      若当前列的下标大于等于nums[i],则结果为上一行该列的结果|上一行该列下标减去num的结果
     * 5.最后返回[nums.length][target-minNum]的值
     * @param nums
     * @return
     */
    public boolean myCanPartition(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return false;
        int sum = 0,maxNum =0 ,minNum =0;
        for (int i = 0 ; i < n ; i++){
            sum += nums[i];
            maxNum = Math.max(nums[i],maxNum);
            minNum = Math.min(nums[i],minNum);
        }
        int target = sum/2;
        if (sum % 2 != 0 || maxNum>target )
            return false;
        if (maxNum == 0)
            return true;
        //存储该数组能完成从minNum到target的相关情况
        boolean [][]ans = new boolean[n][target-minNum+1];
        //初始化
        ans[0][0] = true;
        ans[0][nums[0]-minNum] = true;
        for (int i = 1 ; i < n ;i++){
            int num = nums[i];
            for(int j = 0 ; j <= target ; j++){
                if (j<num)
                    ans[i][j] = ans[i-1][j];
                else
                    ans[i][j] = ans[i-1][j] | ans[i-1][j-num];
            }
        }
        return ans[n][target-minNum];
    }
    /**
     * 官方解法1
     * 方法一：动态规划
     *
     * 思路与算法
     *
     * 这道题可以换一种表述：给定一个只包含正整数的非空数组 nums[0]\textit{nums}[0]nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。因此这个问题可以转换成「0−10-10−1 背包问题」。这道题与传统的「0−10-10−1 背包问题」的区别在于，传统的「0−10-10−1 背包问题」要求选取的物品的重量之和不能超过背包的总容量，这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。类似于传统的「0−10-10−1 背包问题」，可以使用动态规划求解。
     *
     * 在使用动态规划求解之前，首先需要进行以下判断。
     *
     *     根据数组的长度 nnn 判断数组是否可以被划分。如果 n<2n<2n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false\text{false}false。
     *
     *     计算整个数组的元素和 sum\textit{sum}sum 以及最大元素 maxNum\textit{maxNum}maxNum。如果 sum\textit{sum}sum 是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false\text{false}false。如果 sum\textit{sum}sum 是偶数，则令 target=sum2\textit{target}=\frac{\textit{sum}}{2}target=2sum​，需要判断是否可以从数组中选出一些数字，使得这些数字的和等于 target\textit{target}target。如果 maxNum>target\textit{maxNum}>\textit{target}maxNum>target，则除了 maxNum\textit{maxNum}maxNum 以外的所有元素之和一定小于 target\textit{target}target，因此不可能将数组分割成元素和相等的两个子集，直接返回 false\text{false}false。
     *
     * 创建二维数组 dp\textit{dp}dp，包含 nnn 行 target+1\textit{target}+1target+1 列，其中 dp[i][j]\textit{dp}[i][j]dp[i][j] 表示从数组的 [0,i][0,i][0,i] 下标范围内选取若干个正整数（可以是 000 个），是否存在一种选取方案使得被选取的正整数的和等于 jjj。初始时，dp\textit{dp}dp 中的全部元素都是 false\text{false}false。
     *
     * 在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况。
     *
     *     如果不选取任何正整数，则被选取的正整数等于 000。因此对于所有 0≤i<n0 \le i < n0≤i<n，都有 dp[i][0]=true\textit{dp}[i][0]=\text{true}dp[i][0]=true。
     *
     *     当 i==0i==0i==0 时，只有一个正整数 nums[0]\textit{nums}[0]nums[0] 可以被选取，因此 dp[0][nums[0]]=true\textit{dp}[0][\textit{nums}[0]]=\text{true}dp[0][nums[0]]=true。
     *
     * 对于 i>0i>0i>0 且 j>0j>0j>0 的情况，如何确定 dp[i][j]\textit{dp}[i][j]dp[i][j] 的值？需要分别考虑以下两种情况。
     *
     *     如果 j≥nums[i]j \ge \textit{nums}[i]j≥nums[i]，则对于当前的数字 nums[i]\textit{nums}[i]nums[i]，可以选取也可以不选取，两种情况只要有一个为 true\text{true}true，就有 dp[i][j]=true\textit{dp}[i][j]=\text{true}dp[i][j]=true。
     *         如果不选取 nums[i]\textit{nums}[i]nums[i]，则 dp[i][j]=dp[i−1][j]\textit{dp}[i][j]=\textit{dp}[i-1][j]dp[i][j]=dp[i−1][j]；
     *         如果选取 nums[i]\textit{nums}[i]nums[i]，则 dp[i][j]=dp[i−1][j−nums[i]]\textit{dp}[i][j]=\textit{dp}[i-1][j-\textit{nums}[i]]dp[i][j]=dp[i−1][j−nums[i]]。
     *
     *     如果 j<nums[i]j < \textit{nums}[i]j<nums[i]，则在选取的数字的和等于 jjj 的情况下无法选取当前的数字 nums[i]\textit{nums}[i]nums[i]，因此有 dp[i][j]=dp[i−1][j]\textit{dp}[i][j]=\textit{dp}[i-1][j]dp[i][j]=dp[i−1][j]。
     *
     * 状态转移方程如下：
     *
     * dp[i][j]={dp[i−1][j] ∣ dp[i−1][j−nums[i]],j≥nums[i]dp[i−1][j],j<nums[i]\textit{dp}[i][j]=\begin{cases} \textit{dp}[i-1][j]~|~\textit{dp}[i-1][j-\textit{nums}[i]], & j \ge \textit{nums}[i] \\ \textit{dp}[i-1][j], & j < \textit{nums}[i] \end{cases} dp[i][j]={dp[i−1][j] ∣ dp[i−1][j−nums[i]],dp[i−1][j],​j≥nums[i]j<nums[i]​
     *
     * 最终得到 dp[n−1][target]\textit{dp}[n-1][\textit{target}]dp[n−1][target] 即为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
    /**
     * 官方解法二
     * 上述代码的空间复杂度是 O(n×target)O(n \times \textit{target})O(n×target)。但是可以发现在计算 dp\textit{dp}dp 的过程中，每一行的 dpdpdp 值都只与上一行的 dpdpdp 值有关，因此只需要一个一维数组即可将空间复杂度降到 O(target)O(\textit{target})O(target)。此时的转移方程为：
     *
     * dp[j]=dp[j] ∣ dp[j−nums[i]]\textit{dp}[j]=\textit{dp}[j]\ |\ dp[j-\textit{nums}[i]] dp[j]=dp[j] ∣ dp[j−nums[i]]
     *
     * 且需要注意的是第二层的循环我们需要从大到小计算，因为如果我们从小到大更新 dp\textit{dp}dp 值，那么在计算 dp[j]\textit{dp}[j]dp[j] 值的时候，dp[j−nums[i]]\textit{dp}[j-\textit{nums}[i]]dp[j−nums[i]] 已经是被更新过的状态，不再是上一行的 dp\textit{dp}dp 值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}
