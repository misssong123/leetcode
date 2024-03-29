package com.meng.origin;

/**
 * 26. 删除有序数组中的重复项
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
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
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *
 *
 * 提示：
 *
 *     0 <= nums.length <= 3 * 104
 *     -104 <= nums[i] <= 104
 *     nums 已按升序排列
 */
public class RemoveDuplicates_26 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了39.18% 的用户
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int len = nums.length;
        int index = 1;
        for(int i = 1 ; i < len ; i ++){
            if (nums[i] != nums[index-1]){
                nums[index++] = nums[i];
            }
        }
        return index;
    }
    /**
     * 方法一：双指针
     *
     * 这道题目的要求是：对给定的有序数组 nums\textit{nums}nums 删除重复元素，在删除重复元素之后，每个元素只出现一次，并返回新的长度，上述操作必须通过原地修改数组的方法，使用 O(1)O(1)O(1) 的空间复杂度完成。
     *
     * 由于给定的数组 nums\textit{nums}nums 是有序的，因此对于任意 i<ji<ji<j，如果 nums[i]=nums[j]\textit{nums}[i]=\textit{nums}[j]nums[i]=nums[j]，则对任意 i≤k≤ji \le k \le ji≤k≤j，必有 nums[i]=nums[k]=nums[j]\textit{nums}[i]=\textit{nums}[k]=\textit{nums}[j]nums[i]=nums[k]=nums[j]，即相等的元素在数组中的下标一定是连续的。利用数组有序的特点，可以通过双指针的方法删除重复元素。
     *
     * 如果数组 nums\textit{nums}nums 的长度为 000，则数组不包含任何元素，因此返回 000。
     *
     * 当数组 nums\textit{nums}nums 的长度大于 000 时，数组中至少包含一个元素，在删除重复元素之后也至少剩下一个元素，因此 nums[0]\textit{nums}[0]nums[0] 保持原状即可，从下标 111 开始删除重复元素。
     *
     * 定义两个指针 fast\textit{fast}fast 和 slow\textit{slow}slow 分别为快指针和慢指针，快指针表示遍历数组到达的下标位置，慢指针表示下一个不同元素要填入的下标位置，初始时两个指针都指向下标 111。
     *
     * 假设数组 nums\textit{nums}nums 的长度为 nnn。将快指针 fast\textit{fast}fast 依次遍历从 111 到 n−1n-1n−1 的每个位置，对于每个位置，如果 nums[fast]≠nums[fast−1]\textit{nums}[\textit{fast}] \ne \textit{nums}[\textit{fast}-1]nums[fast]​=nums[fast−1]，说明 nums[fast]\textit{nums}[\textit{fast}]nums[fast] 和之前的元素都不同，因此将 nums[fast]\textit{nums}[\textit{fast}]nums[fast] 的值复制到 nums[slow]\textit{nums}[\textit{slow}]nums[slow]，然后将 slow\textit{slow}slow 的值加 111，即指向下一个位置。
     *
     * 遍历结束之后，从 nums[0]\textit{nums}[0]nums[0] 到 nums[slow−1]\textit{nums}[\textit{slow}-1]nums[slow−1] 的每个元素都不相同且包含原数组中的每个不同的元素，因此新的长度即为 slow\textit{slow}slow，返回 slow\textit{slow}slow 即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-tudo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了81.45% 的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了14.35% 的用户
     */
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
