package com.meng.hot100;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class T018SearchRange {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 44.4 MB
     * , 在所有 Java 提交中击败了
     * 83.84%
     * 的用户
     * 通过测试用例：
     * 88 / 88
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int left = 0 , right = len-1;
        while (left <= right){
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num == target){
                int l = mid ,r = mid;
                while (l >=0 && nums[l] == target){
                    l--;
                }
                while (r < len && nums[r] == target){
                    r++;
                }
                return new int[]{++l,--r};
            }else if (num > target){
                right = mid - 1;
            }else {
                left = mid + 1 ;
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 方法一：二分查找
     * 直观的思路肯定是从前往后遍历一遍。用两个变量记录第一次和最后一次遇见 \textit{target}target 的下标，但这个方法的时间复杂度为 O(n)O(n)，没有利用到数组升序排列的条件。
     *
     * 由于数组已经排序，因此整个数组是单调递增的，我们可以利用二分法来加速查找的过程。
     *
     * 考虑 \textit{target}target 开始和结束位置，其实我们要找的就是数组中「第一个等于 \textit{target}target 的位置」（记为 \textit{leftIdx}leftIdx）和「第一个大于 \textit{target}target 的位置减一」（记为 \textit{rightIdx}rightIdx）。
     *
     * 二分查找中，寻找 \textit{leftIdx}leftIdx 即为在数组中寻找第一个大于等于 \textit{target}target 的下标，寻找 \textit{rightIdx}rightIdx 即为在数组中寻找第一个大于 \textit{target}target 的下标，然后将下标减一。两者的判断条件不同，为了代码的复用，我们定义 binarySearch(nums, target, lower) 表示在 \textit{nums}nums 数组中二分查找 \textit{target}target 的位置，如果 \textit{lower}lower 为 \rm truetrue，则查找第一个大于等于 \textit{target}target 的下标，否则查找第一个大于 \textit{target}target 的下标。
     *
     * 最后，因为 \textit{target}target 可能不存在数组中，因此我们需要重新校验我们得到的两个下标 \textit{leftIdx}leftIdx 和 \textit{rightIdx}rightIdx，看是否符合条件，如果符合条件就返回 [\textit{leftIdx},\textit{rightIdx}][leftIdx,rightIdx]，不符合就返回 [-1,-1][−1,−1]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 44.6 MB
     * , 在所有 Java 提交中击败了
     * 54.55%
     * 的用户
     * 通过测试用例：
     * 88 / 88
     */
    public int[] searchRange1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
