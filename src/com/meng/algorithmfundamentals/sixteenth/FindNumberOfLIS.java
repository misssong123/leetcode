package com.meng.algorithmfundamentals.sixteenth;

import java.util.ArrayList;
import java.util.List;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 *
 * 注意 这个数列必须是 严格 递增的。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 2000
 * -106 <= nums[i] <= 106
 */
public class FindNumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        return -1;
    }

    /**
     *方法一：动态规划
     * 思路与算法
     *
     * 定义 \textit{dp}[i]dp[i] 表示以 \textit{nums}[i]nums[i] 结尾的最长上升子序列的长度，\textit{cnt}[i]cnt[i] 表示以 \textit{nums}[i]nums[i] 结尾的最长上升子序列的个数。设 \textit{nums}nums 的最长上升子序列的长度为 \textit{maxLen}maxLen，那么答案为所有满足 \textit{dp}[i]=\textit{maxLen}dp[i]=maxLen 的 ii 所对应的 \textit{cnt}[i]cnt[i] 之和。
     *
     * 我们从小到大计算 \textit{dp}dp 数组的值，在计算 \textit{dp}[i]dp[i] 之前，我们已经计算出 \textit{dp}[0 \ldots i-1]dp[0…i−1] 的值，则状态转移方程为：
     *
     * \textit{dp}[i] = \max(\textit{dp}[j]) + 1, \text{其中} \, 0 \leq j < i \, \text{且} \, \textit{num}[j]<\textit{num}[i]
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     *
     * 即考虑往 \textit{dp}[0 \ldots i-1]dp[0…i−1] 中最长的上升子序列后面再加一个 \textit{nums}[i]nums[i]。由于 \textit{dp}[j]dp[j] 代表 \textit{nums}[0 \ldots j]nums[0…j] 中以 \textit{nums}[j]nums[j] 结尾的最长上升子序列，所以如果能从 \textit{dp}[j]dp[j] 这个状态转移过来，那么 \textit{nums}[i]nums[i] 必然要大于 \textit{nums}[j]nums[j]，才能将 \textit{nums}[i]nums[i] 放在 \textit{nums}[j]nums[j] 后面以形成更长的上升子序列。
     *
     * 对于 \textit{cnt}[i]cnt[i]，其等于所有满足 \textit{dp}[j]+1=\textit{dp}[i]dp[j]+1=dp[i] 的 \textit{cnt}[j]cnt[j] 之和。在代码实现时，我们可以在计算 \textit{dp}[i]dp[i] 的同时统计 \textit{cnt}[i]cnt[i] 的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/zui-chang-di-zeng-zi-xu-lie-de-ge-shu-by-w12f/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 77.68%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 29.33%
     * 的用户
     * 通过测试用例：
     * 223 / 223
     */
    public int findNumberOfLIS1(int[] nums) {
        int n = nums.length, maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; // 重置计数
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i]; // 重置计数
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }

    /**
     * 方法二：贪心 + 前缀和 + 二分查找
     * 下文在「300. 最长递增子序列的官方题解」的方法二上进行扩展，请读者在了解该方法后继续阅读。
     *
     * 我们将数组 dd 扩展成一个二维数组，其中 d[i]d[i] 数组表示所有能成为长度为 ii 的最长上升子序列的末尾元素的值。具体地，我们将更新 d[i]=\textit{nums}[j]d[i]=nums[j] 这一操作替换成将 \textit{nums}[j]nums[j] 置于 d[i]d[i] 数组末尾。这样 d[i]d[i] 中就保留了历史信息，且 d[i]d[i] 中的元素是有序的（单调非增）。
     *
     * 类似地，我们也可以定义一个二维数组 \textit{cnt}cnt，其中 \textit{cnt}[i][j]cnt[i][j] 记录了以 d[i][j]d[i][j] 为结尾的最长上升子序列的个数。为了计算 \textit{cnt}[i][j]cnt[i][j]，我们可以考察 d[i-1]d[i−1] 和 \textit{cnt}[i-1]cnt[i−1]，将所有满足 d[i-1][k]<d[i][j]d[i−1][k]<d[i][j] 的 \textit{cnt}[i-1][k]cnt[i−1][k] 累加到 \textit{cnt}[i][j]cnt[i][j]，这样最终答案就是 \textit{cnt}[\textit{maxLen}]cnt[maxLen] 的所有元素之和。
     *
     * 在代码实现时，由于 d[i]d[i] 中的元素是有序的，我们可以二分得到最小的满足 d[i-1][k]<d[i][j]d[i−1][k]<d[i][j] 的下标 kk。另一处优化是将 \textit{cnt}cnt 改为其前缀和，并在开头填上 00，此时 d[i][j]d[i][j] 对应的最长上升子序列的个数就是 \textit{cnt}[i-1][-1]-\textit{cnt}[i-1][k]cnt[i−1][−1]−cnt[i−1][k]，这里 [-1][−1] 表示数组的最后一个元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/solution/zui-chang-di-zeng-zi-xu-lie-de-ge-shu-by-w12f/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int findNumberOfLIS2(int[] nums) {
        List<List<Integer>> d = new ArrayList<List<Integer>>();
        List<List<Integer>> cnt = new ArrayList<List<Integer>>();
        for (int v : nums) {
            int i = binarySearch1(d.size(), d, v);
            int c = 1;
            if (i > 0) {
                int k = binarySearch2(d.get(i - 1).size(), d.get(i - 1), v);
                c = cnt.get(i - 1).get(cnt.get(i - 1).size() - 1) - cnt.get(i - 1).get(k);
            }
            if (i == d.size()) {
                List<Integer> dList = new ArrayList<Integer>();
                dList.add(v);
                d.add(dList);
                List<Integer> cntList = new ArrayList<Integer>();
                cntList.add(0);
                cntList.add(c);
                cnt.add(cntList);
            } else {
                d.get(i).add(v);
                int cntSize = cnt.get(i).size();
                cnt.get(i).add(cnt.get(i).get(cntSize - 1) + c);
            }
        }

        int size1 = cnt.size(), size2 = cnt.get(size1 - 1).size();
        return cnt.get(size1 - 1).get(size2 - 1);
    }

    public int binarySearch1(int n, List<List<Integer>> d, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            List<Integer> list = d.get(mid);
            if (list.get(list.size() - 1) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int binarySearch2(int n, List<Integer> list, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) < target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


}
