package com.meng;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 *
 *     若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *     若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *
 * 示例 3：
 *
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *
 *
 *
 * 提示：
 *
 *     n == nums.length
 *     1 <= n <= 5000
 *     -5000 <= nums[i] <= 5000
 *     nums 中的所有整数 互不相同
 *     nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class FindMin {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了56.81% 的用户
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0 , right = nums.length-1;
        while (left < right){
            int mid = (left + right)/2;
            //若中间元素小于右侧元素，表示右侧为递增顺序，最小值一定位于该中间位置或者左侧
            if (nums[mid] < nums[right]){
                right = mid;
            }else {
                //表示该中间位于逆转后的元素之间,则最小值一定处于该位置的右侧
                left = mid + 1;
            }
        }
        return nums[left];
    }
    /**
     * 其中横轴表示数组元素的下标，纵轴表示数组元素的值。图中标出了最小值的位置，是我们需要查找的目标。
     *
     * 我们考虑数组中的最后一个元素 xxx：在最小值右侧的元素（不包括最后一个元素本身），它们的值一定都严格小于 xxx；而在最小值左侧的元素，它们的值一定都严格大于 xxx。因此，我们可以根据这一条性质，通过二分查找的方法找出最小值。
     *
     * 在二分查找的每一步中，左边界为 low\it lowlow，右边界为 high\it highhigh，区间的中点为 pivot\it pivotpivot，最小值就在该区间内。我们将中轴元素 nums[pivot]\textit{nums}[\textit{pivot}]nums[pivot] 与右边界元素 nums[high]\textit{nums}[\textit{high}]nums[high] 进行比较，可能会有以下的三种情况：
     *
     * 第一种情况是 nums[pivot]<nums[high]\textit{nums}[\textit{pivot}] < \textit{nums}[\textit{high}]nums[pivot]<nums[high]。如下图所示，这说明 nums[pivot]\textit{nums}[\textit{pivot}]nums[pivot] 是最小值右侧的元素，因此我们可以忽略二分查找区间的右半部分。
     *
     * fig2
     *
     * 第二种情况是 nums[pivot]>nums[high]\textit{nums}[\textit{pivot}] > \textit{nums}[\textit{high}]nums[pivot]>nums[high]。如下图所示，这说明 nums[pivot]\textit{nums}[\textit{pivot}]nums[pivot] 是最小值左侧的元素，因此我们可以忽略二分查找区间的左半部分。
     *
     * fig3
     *
     * 由于数组不包含重复元素，并且只要当前的区间长度不为 111，pivot\it pivotpivot 就不会与 high\it highhigh 重合；而如果当前的区间长度为 111，这说明我们已经可以结束二分查找了。因此不会存在 nums[pivot]=nums[high]\textit{nums}[\textit{pivot}] = \textit{nums}[\textit{high}]nums[pivot]=nums[high] 的情况。
     *
     * 当二分查找结束时，我们就得到了最小值所在的位置。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/xun-zhao-xuan-zhuan-pai-xu-shu-zu-zhong-5irwp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了85.54% 的用户
     */
    public int findMin1(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }
}

