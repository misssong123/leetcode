package com.meng.origin;

/**
 * 80. 删除有序数组中的重复项 II
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 3 * 104
 *     -104 <= nums[i] <= 104
 *     nums 已按升序排列
 *
 * 通过次数115,332
 * 提交次数190,312
 */
public class RemoveDuplicates_80 {
    //使用快慢指针进行相关操作

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了82.43% 的用户
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return len;
        int slow = 2 , fast = 2;
        while (fast < len){
            //慢指针控制差距，和即将赋的值
            if (nums[slow-2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
    /**
     * 方法一：双指针
     *
     * 思路及解法
     *
     * 在阅读本题前，读者们可以先尝试解决「26. 删除有序数组中的重复项」。
     *
     * 因为给定数组是有序的，所以相同元素必然连续。我们可以使用双指针解决本题，遍历数组检查每一个元素是否应该被保留，如果应该被保留，就将其移动到指定位置。具体地，我们定义两个指针 slow\textit{slow}slow 和 fast\textit{fast}fast 分别为慢指针和快指针，其中慢指针表示处理出的数组的长度，快指针表示已经检查过的数组的长度，即 nums[fast]\textit{nums}[\textit{fast}]nums[fast] 表示待检查的第一个元素，nums[slow−1]\textit{nums}[\textit{slow} - 1]nums[slow−1] 为上一个应该被保留的元素所移动到的指定位置。
     *
     * 因为本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2]\textit{nums}[\textit{slow} - 2]nums[slow−2] 是否和当前待检查元素 nums[fast]\textit{nums}[\textit{fast}]nums[fast] 相同。当且仅当 nums[slow−2]=nums[fast]\textit{nums}[\textit{slow} - 2] = \textit{nums}[\textit{fast}]nums[slow−2]=nums[fast] 时，当前待检查元素 nums[fast]\textit{nums}[\textit{fast}]nums[fast] 不应该被保留（因为此时必然有 nums[slow−2]=nums[slow−1]=nums[fast]\textit{nums}[\textit{slow} - 2] = nums[\textit{slow} - 1] = \textit{nums}[\textit{fast}]nums[slow−2]=nums[slow−1]=nums[fast]）。最后，slow\textit{slow}slow 即为处理好的数组的长度。
     *
     * 特别地，数组的前两个数必然可以被保留，因此对于长度不超过 222 的数组，我们无需进行任何处理，对于长度超过 222 的数组，我们直接将双指针的初始值设为 222 即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-yec2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     */
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
