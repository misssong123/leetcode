package com.meng;

/**
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 100
 *     0 <= nums[i] <= 1000
 */
public class Rob_213 {
    /**
     * 根据题意，第一个和最后一个只能选择一个
     * 所以只需要在 0,n-2 和 1 到 n-1 中选取一个最大值即可
     * @param nums
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了55.39% 的用户
     */
    public int rob(int[] nums) {
        if (nums.length == 1 )
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0],nums[1]);
        int len = nums.length;
        System.out.println(rob(nums,0,len-2));
        System.out.println(rob(nums,1,len-1));
        return Math.max(rob(nums,0,len-2),rob(nums,1,len-1));
    }
    public int rob(int[] nums , int start,int end){
        int first = nums[start],second = Math.max(nums[start+1],first);
        for(int i = start + 2 ; i <= end ; i++){
            int temp = second;
            second = Math.max(first+nums[i],second);
            first = temp;
        }
        return Math.max(first,second);
    }

    public static void main(String[] args) {
        int [] nums = {200,3,140,20,10};
        Rob_213 demo = new Rob_213();
        System.out.println(demo.rob(nums));
    }
    /**
     * 方法一：动态规划
     *
     * 首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。
     *
     * 注意到当房屋数量不超过两间时，最多只能偷窃一间房屋，因此不需要考虑首尾相连的问题。如果房屋数量大于两间，就必须考虑首尾相连的问题，第一间房屋和最后一间房屋不能同时偷窃。
     *
     * 如何才能保证第一间房屋和最后一间房屋不同时偷窃呢？如果偷窃了第一间房屋，则不能偷窃最后一间房屋，因此偷窃房屋的范围是第一间房屋到最后第二间房屋；如果偷窃了最后一间房屋，则不能偷窃第一间房屋，因此偷窃房屋的范围是第二间房屋到最后一间房屋。
     *
     * 假设数组 nums\textit{nums}nums 的长度为 nnn。如果不偷窃最后一间房屋，则偷窃房屋的下标范围是 [0,n−2][0, n-2][0,n−2]；如果不偷窃第一间房屋，则偷窃房屋的下标范围是 [1,n−1][1, n-1][1,n−1]。在确定偷窃房屋的下标范围之后，即可用第 198 题的方法解决。对于两段下标范围分别计算可以偷窃到的最高总金额，其中的最大值即为在 nnn 间房屋中可以偷窃到的最高总金额。
     *
     * 假设偷窃房屋的下标范围是 [start,end][\textit{start},\textit{end}][start,end]，用 dp[i]\textit{dp}[i]dp[i] 表示在下标范围 [start,i][\textit{start},i][start,i] 内可以偷窃到的最高总金额，那么就有如下的状态转移方程：
     *
     * dp[i]=max⁡(dp[i−2]+nums[i],dp[i−1])\textit{dp}[i] = \max(\textit{dp}[i-2]+\textit{nums}[i], \textit{dp}[i-1]) dp[i]=max(dp[i−2]+nums[i],dp[i−1])
     *
     * 边界条件为：
     *
     * {dp[start]=nums[start]只有一间房屋，则偷窃该房屋dp[start+1]=max⁡(nums[start],nums[start+1])只有两间房屋，偷窃其中金额较高的房屋\begin{cases} \textit{dp}[\textit{start}] = \textit{nums}[\textit{start}] & 只有一间房屋，则偷窃该房屋 \\ \textit{dp}[\textit{start}+1] = \max(\textit{nums}[\textit{start}], \textit{nums}[\textit{start}+1]) & 只有两间房屋，偷窃其中金额较高的房屋 \end{cases} {dp[start]=nums[start]dp[start+1]=max(nums[start],nums[start+1])​只有一间房屋，则偷窃该房屋只有两间房屋，偷窃其中金额较高的房屋​
     *
     * 计算得到 dp[end]\textit{dp}[\textit{end}]dp[end] 即为下标范围 [start,end][\textit{start},\textit{end}][start,end] 内可以偷窃到的最高总金额。
     *
     * 分别取 (start,end)=(0,n−2)(\textit{start},\textit{end})=(0,n-2)(start,end)=(0,n−2) 和 (start,end)=(1,n−1)(\textit{start},\textit{end})=(1,n-1)(start,end)=(1,n−1) 进行计算，取两个 dp[end]\textit{dp}[\textit{end}]dp[end] 中的最大值，即可得到最终结果。
     *
     * 根据上述思路，可以得到时间复杂度 O(n)O(n)O(n) 和空间复杂度 O(n)O(n)O(n) 的实现。考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，在每个时刻只需要存储前两间房屋的最高总金额，将空间复杂度降到 O(1)O(1)O(1)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/house-robber-ii/solution/da-jia-jie-she-ii-by-leetcode-solution-bwja/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了16.69% 的用户
     */
    public int rob1(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
