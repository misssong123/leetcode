package com.meng;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 *
 * 统计一个数字在排序数组中出现的次数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 */
public class Search53 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了45.03% 的用户
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        if (target<nums[0] || target > nums[len-1]){
            return 0;
        }
        int index = -1;
        int left = 0 ;
        int right = len - 1;
        while (left <= right){
            int mid = (right - left) /2 + left;
            if (nums[mid] == target ){
                index = mid;
                break;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if (index == -1){
            return 0;
        }
        left = index - 1;
        right = index + 1;
        int res = 1;
        while (left >=0 && nums[left] == target){
            left--;
            res++;
        }
        while (right < len && nums[right] == target){
            right++;
            res++;
        }
        return res;
    }

    /**
     * 方法一：二分查找
     *
     * 直观的思路肯定是从前往后遍历一遍。用两个变量记录第一次和最后一次遇见 target\textit{target}target 的下标，但这个方法的时间复杂度为 O(n)O(n)O(n)，没有利用到数组升序排列的条件。
     *
     * 由于数组已经排序，因此整个数组是单调递增的，我们可以利用二分法来加速查找的过程。
     *
     * 考虑 target\textit{target}target 在数组中出现的次数，其实我们要找的就是数组中「第一个等于 target\textit{target}target 的位置」（记为 leftIdx\textit{leftIdx}leftIdx）和「第一个大于 target\textit{target}target 的位置减一」（记为 rightIdx\textit{rightIdx}rightIdx）。当 target\textit{target}target 在数组中存在时，target\textit{target}target 在数组中出现的次数为 rightIdx−leftIdx+1\textit{rightIdx}-\textit{leftIdx}+1rightIdx−leftIdx+1。
     *
     * 二分查找中，寻找 leftIdx\textit{leftIdx}leftIdx 即为在数组中寻找第一个大于等于 target\textit{target}target 的下标，寻找 rightIdx\textit{rightIdx}rightIdx 即为在数组中寻找第一个大于 target\textit{target}target 的下标，然后将下标减一。两者的判断条件不同，为了代码的复用，我们定义 binarySearch(nums, target, lower) 表示在 nums\textit{nums}nums 数组中二分查找 target\textit{target}target 的位置，如果 lower\textit{lower}lower 为 true\rm truetrue，则查找第一个大于等于 target\textit{target}target 的下标，否则查找第一个大于 target\textit{target}target 的下标。
     *
     * 最后，因为 target\textit{target}target 可能不存在数组中，因此我们需要重新校验我们得到的两个下标 leftIdx\textit{leftIdx}leftIdx 和 rightIdx\textit{rightIdx}rightIdx，看是否符合条件，如果符合条件就返回 rightIdx−leftIdx+1\textit{rightIdx}-\textit{leftIdx}+1rightIdx−leftIdx+1，不符合就返回 000。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-wl6kr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param nums
     * @param target
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了62.22% 的用户
     */
    public int search1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
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

