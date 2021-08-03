package com.meng.algorithm;

import java.util.Arrays;

/**
 * 581. 最短无序连续子数组
 *
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 *
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 104
 *     -105 <= nums[i] <= 105
 *
 *
 *
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 */
public class FindUnsortedSubarray {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了69.39% 的用户
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int start = -1 , end = -1,min = Integer.MAX_VALUE;
        int index = 0 , dis = 0;
        while (index < len-1){
                while (index < len-1 && nums[index] <= nums[index+1]){
                    index++;
                }
                if (index < len-1){
                    if (start == -1){
                        start = index;
                    }
                    dis = nums[index];
                    index++;
                    while (index < len && dis > nums[index]){
                        min = Math.min(min,nums[index]);
                        index++;
                    }
                    end = index - 1;
                    while (start >0 && nums[start-1] > min){
                        start--;
                    }
                }
        }
        if (start == end){
            return 0;
        }
        return end - start + 1;
    }

    /**
     * 方法二：一次遍历
     *
     * 思路与算法
     *
     * 假设 numsB\textit{nums}_BnumsB​ 在 nums\textit{nums}nums 中对应区间为 [left,right][\textit{left},\textit{right}][left,right]。
     *
     * 注意到 numsB\textit{nums}_BnumsB​ 和 numsC\textit{nums}_CnumsC​ 中任意一个数都大于等于 numsA\textit{nums}_AnumsA​ 中任意一个数。因此有 numsA\textit{nums}_AnumsA​ 中每一个数 numsi\textit{nums}_inumsi​ 都满足：
     *
     * numsi≤min⁡j=i+1n−1numsj\textit{nums}_i \leq \min_{j=i+1}^{n-1} \textit{nums}_j numsi​≤j=i+1minn−1​numsj​
     *
     * 我们可以从大到小枚举 iii，用一个变量 minn\textit{minn}minn 记录 min⁡j=i+1n−1numsj\min_{j=i+1}^{n-1} \textit{nums}_jminj=i+1n−1​numsj​。每次移动 iii，都可以 O(1)O(1)O(1) 地更新 minn\textit{minn}minn。这样最后一个使得不等式不成立的 iii 即为 left\textit{left}left。left\textit{left}left 左侧即为 numsA\textit{nums}_AnumsA​ 能取得的最大范围。
     *
     * 同理，我们可以用类似的方法确定 right\textit{right}right。在实际代码中，我们可以在一次循环中同时完成左右边界的计算。
     *
     * 特别地，我们需要特判 nums\textit{nums}nums 有序的情况，此时 numsB\textit{nums}_BnumsB​ 的长度为 000。当我们计算完成左右边界，即可返回 numsB\textit{nums}_BnumsB​ 的长度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leet-yhlf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了31.76% 的用户
     */
    public int findUnsortedSubarray1(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    /**
     * 方法一：排序
     *
     * 思路与算法
     *
     * 我们将给定的数组 nums\textit{nums}nums 表示为三段子数组拼接的形式，分别记作 numsA\textit{nums}_AnumsA​，numsB\textit{nums}_BnumsB​，numsC\textit{nums}_CnumsC​。当我们对 numsB\textit{nums}_BnumsB​ 进行排序，整个数组将变为有序。换而言之，当我们对整个序列进行排序，numsA\textit{nums}_AnumsA​ 和 numsC\textit{nums}_CnumsC​ 都不会改变。
     *
     * 本题要求我们找到最短的 numsB\textit{nums}_BnumsB​，即找到最大的 numsA\textit{nums}_AnumsA​ 和 numsC\textit{nums}_CnumsC​ 的长度之和。因此我们将原数组 nums\textit{nums}nums 排序与原数组进行比较，取最长的相同的前缀为 numsA\textit{nums}_AnumsA​，取最长的相同的后缀为 numsC\textit{nums}_CnumsC​，这样我们就可以取到最短的 numsB\textit{nums}_BnumsB​。
     *
     * 具体地，我们创建数组 nums\textit{nums}nums 的拷贝，记作数组 numsSorted\textit{numsSorted}numsSorted，并对该数组进行排序，然后我们从左向右找到第一个两数组不同的位置，即为 numsB\textit{nums}_BnumsB​ 的左边界。同理也可以找到 numsB\textit{nums}_BnumsB​ 右边界。最后我们输出 numsB\textit{nums}_BnumsB​ 的长度即可。
     *
     * 特别地，当原数组有序时，numsB\textit{nums}_BnumsB​ 的长度为 000，我们可以直接返回结果。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leet-yhlf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：7 ms, 在所有 Java 提交中击败了55.31% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了29.47% 的用户
     */
    public int findUnsortedSubarray2(int[] nums) {
        if (isSorted(nums)) {
            return 0;
        }
        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);
        int left = 0;
        while (nums[left] == numsSorted[left]) {
            left++;
        }
        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]) {
            right--;
        }
        return right - left + 1;
    }

    public boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        FindUnsortedSubarray demo = new FindUnsortedSubarray();
        int[] nums = {1,2,3,3,3};
        System.out.println(demo.findUnsortedSubarray1(nums));
    }
}
