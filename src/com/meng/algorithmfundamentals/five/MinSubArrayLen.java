package com.meng.algorithmfundamentals.five;

import java.util.Arrays;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLen {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 36.65%
     * 的用户
     * 通过测试用例：
     * 19 / 19
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = -1 ,right = 0;
        int sum = 0;
        int size = Integer.MAX_VALUE;
        while (left < right && right < len){
            while (right < len && sum <target){
                sum+=nums[right++];
            }
            while (sum>=target){
                if (sum >=target){
                    size = Math.min(size,right-left-1);
                    if (size == 1){
                        return size;
                    }
                    sum-=nums[++left];
                }
            }
        }
        return size==Integer.MAX_VALUE?0:size;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,7};
        MinSubArrayLen demo = new MinSubArrayLen();
        System.out.println(demo.minSubArrayLen(11,nums));
    }
    /**
     * 方法一：暴力法
     * 暴力法是最直观的方法。初始化子数组的最小长度为无穷大，枚举数组 \text{nums}nums 中的每个下标作为子数组的开始下标，对于每个开始下标 ii，需要找到大于或等于 ii 的最小下标 jj，使得从 \text{nums}[i]nums[i] 到 \text{nums}[j]nums[j] 的元素和大于或等于 ss，并更新子数组的最小长度（此时子数组的长度是 j-i+1j−i+1）。
     *
     * 注意：使用 Python 语言实现方法一会超出时间限制。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param nums
     * @return
     * 执行用时：
     * 123 ms
     * , 在所有 Java 提交中击败了
     * 15.12%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 10.25%
     * 的用户
     * 通过测试用例：
     * 19 / 19
     */
    public int minSubArrayLen1(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法二：前缀和 + 二分查找
     * 方法一的时间复杂度是 O(n^2)O(n
     * 2
     *  )，因为在确定每个子数组的开始下标后，找到长度最小的子数组需要 O(n)O(n) 的时间。如果使用二分查找，则可以将时间优化到 O(\log n)O(logn)。
     *
     * 为了使用二分查找，需要额外创建一个数组 \text{sums}sums 用于存储数组 \text{nums}nums 的前缀和，其中 \text{sums}[i]sums[i] 表示从 \text{nums}[0]nums[0] 到 \text{nums}[i-1]nums[i−1] 的元素和。得到前缀和之后，对于每个开始下标 ii，可通过二分查找得到大于或等于 ii 的最小下标 \textit{bound}bound，使得 \text{sums}[\textit{bound}]-\text{sums}[i-1] \ge ssums[bound]−sums[i−1]≥s，并更新子数组的最小长度（此时子数组的长度是 \textit{bound}-(i-1)bound−(i−1)）。
     *
     * 因为这道题保证了数组中每个元素都为正，所以前缀和一定是递增的，这一点保证了二分的正确性。如果题目没有说明数组中每个元素都为正，这里就不能使用二分来查找这个位置了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param nums
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 23.16%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 7.67%
     * 的用户
     * 通过测试用例：
     * 19 / 19
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法三：滑动窗口
     * 在方法一和方法二中，都是每次确定子数组的开始下标，然后得到长度最小的子数组，因此时间复杂度较高。为了降低时间复杂度，可以使用滑动窗口的方法。
     *
     * 定义两个指针 \textit{start}start 和 \textit{end}end 分别表示子数组（滑动窗口窗口）的开始位置和结束位置，维护变量 \textit{sum}sum 存储子数组中的元素和（即从 \text{nums}[\textit{start}]nums[start] 到 \text{nums}[\textit{end}]nums[end] 的元素和）。
     *
     * 初始状态下，\textit{start}start 和 \textit{end}end 都指向下标 00，\textit{sum}sum 的值为 00。
     *
     * 每一轮迭代，将 \text{nums}[end]nums[end] 加到 \textit{sum}sum，如果 \textit{sum} \ge ssum≥s，则更新子数组的最小长度（此时子数组的长度是 \textit{end}-\textit{start}+1end−start+1），然后将 \text{nums}[start]nums[start] 从 \textit{sum}sum 中减去并将 \textit{start}start 右移，直到 \textit{sum} < ssum<s，在此过程中同样更新子数组的最小长度。在每一轮迭代的最后，将 \textit{end}end 右移。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param nums
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 32.69%
     * 的用户
     * 通过测试用例：
     * 19 / 19
     */
    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}

