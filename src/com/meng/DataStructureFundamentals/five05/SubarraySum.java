package com.meng.DataStructureFundamentals.five05;

import java.util.HashMap;

/**
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubarraySum {
    /**
     * 遍历
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 1433 ms
     * , 在所有 Java 提交中击败了
     * 12.72%
     * 的用户
     * 内存消耗：
     * 44.4 MB
     * , 在所有 Java 提交中击败了
     * 14.20%
     * 的用户
     * 通过测试用例：
     * 89 / 89
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法二：前缀和 + 哈希表优化
     * 思路和算法
     *
     * 我们可以基于方法一利用数据结构进行进一步的优化，我们知道方法一的瓶颈在于对每个 ii，我们需要枚举所有的 jj 来判断是否符合条件，这一步是否可以优化呢？答案是可以的。
     *
     * 我们定义 \textit{pre}[i]pre[i] 为 [0..i][0..i] 里所有数的和，则 \textit{pre}[i]pre[i] 可以由 \textit{pre}[i-1]pre[i−1] 递推而来，即：
     *
     * \textit{pre}[i]=\textit{pre}[i-1]+\textit{nums}[i]
     * pre[i]=pre[i−1]+nums[i]
     *
     * 那么「[j..i][j..i] 这个子数组和为 kk 」这个条件我们可以转化为
     *
     * \textit{pre}[i]-\textit{pre}[j-1]==k
     * pre[i]−pre[j−1]==k
     *
     * 简单移项可得符合条件的下标 jj 需要满足
     *
     * \textit{pre}[j-1] == \textit{pre}[i] - k
     * pre[j−1]==pre[i]−k
     *
     * 所以我们考虑以 ii 结尾的和为 kk 的连续子数组个数时只要统计有多少个前缀和为 \textit{pre}[i]-kpre[i]−k 的 \textit{pre}[j]pre[j] 即可。我们建立哈希表 \textit{mp}mp，以和为键，出现次数为对应的值，记录 \textit{pre}[i]pre[i] 出现的次数，从左往右边更新 \textit{mp}mp 边计算答案，那么以 ii 结尾的答案 \textit{mp}[\textit{pre}[i]-k]mp[pre[i]−k] 即可在 O(1)O(1) 时间内得到。最后的答案即为所有下标结尾的和为 kk 的子数组个数之和。
     *
     * 需要注意的是，从左往右边更新边计算的时候已经保证了\textit{mp}[\textit{pre}[i]-k]mp[pre[i]−k] 里记录的 \textit{pre}[j]pre[j] 的下标范围是 0\leq j\leq i0≤j≤i 。同时，由于\textit{pre}[i]pre[i] 的计算只与前一项的答案有关，因此我们可以不用建立 \textit{pre}pre 数组，直接用 \textit{pre}pre 变量来记录 pre[i-1]pre[i−1] 的答案即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 25 ms
     * , 在所有 Java 提交中击败了
     * 43.78%
     * 的用户
     * 内存消耗：
     * 44.1 MB
     * , 在所有 Java 提交中击败了
     * 35.23%
     * 的用户
     * 通过测试用例：
     * 89 / 89
     */
    public int subarraySum1(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap< Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


}
