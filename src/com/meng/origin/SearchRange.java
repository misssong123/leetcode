package com.meng.origin;

public class SearchRange {
    /**
     * 二分查找
     * 注意比较找到位置的前后是否存在相同项
     * @param nums
     * @param target
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了99.16% 的用户
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int [] ans = {-1,-1};
        if(len<1||nums[0]>target||nums[len-1]<target)
            return ans;
        int left = 0 , right = len-1;
        while (right>=left){
            int mid = (left+right)/2;
            if (nums[mid]==target){
                ans[0]=mid;
                ans[1]=mid;
                break;
            }else if(nums[mid]>target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        if (ans[0]==-1)
            return ans;
        while (ans[0]-1>=0&&nums[ans[0]-1]==target)
            ans[0]--;
        while (ans[1]+1<len&&nums[ans[1]+1]==target)
            ans[1]++;
        return ans;
    }
    /**
     * 官方解法
     * 方法一：二分查找
     *
     * 直观的思路肯定是从前往后遍历一遍。用两个变量记录第一次和最后一次遇见 target\textit{target}target 的下标，但这个方法的时间复杂度为 O(n)O(n)O(n)，没有利用到数组升序排列的条件。
     *
     * 由于数组已经排序，因此整个数组是单调递增的，我们可以利用二分法来加速查找的过程。
     *
     * 考虑 target\textit{target}target 开始和结束位置，其实我们要找的就是数组中「第一个等于 target\textit{target}target 的位置」（记为 leftIdx\textit{leftIdx}leftIdx）和「第一个大于 target\textit{target}target 的位置减一」（记为 rightIdx\textit{rightIdx}rightIdx）。
     *
     * 二分查找中，寻找 leftIdx\textit{leftIdx}leftIdx 即为在数组中寻找第一个大于等于 target\textit{target}target 的下标，寻找 rightIdx\textit{rightIdx}rightIdx 即为在数组中寻找第一个大于 target\textit{target}target 的下标，然后将下标减一。两者的判断条件不同，为了代码的复用，我们定义 binarySearch(nums, target, lower) 表示在 nums\textit{nums}nums 数组中二分查找 target\textit{target}target 的位置，如果 lower\textit{lower}lower 为 true\rm truetrue，则查找第一个大于等于 target\textit{target}target 的下标，否则查找第一个大于 target\textit{target}target 的下标。
     *
     * 最后，因为 target\textit{target}target 可能不存在数组中，因此我们需要重新校验我们得到的两个下标 leftIdx\textit{leftIdx}leftIdx 和 rightIdx\textit{rightIdx}rightIdx，看是否符合条件，如果符合条件就返回 [leftIdx,rightIdx][\textit{leftIdx},\textit{rightIdx}][leftIdx,rightIdx]，不符合就返回 [−1,−1][-1,-1][−1,−1]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了82.30% 的用户
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
