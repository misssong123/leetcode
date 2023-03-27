package com.meng.algorithmfundamentals.twenty;

import java.util.Random;

/**
 * 384. 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
 *
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 *
 *
 * 示例 1：
 *
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 */
public class Solution {

    int[] nums;
    int[] original;

    /**
     * 方法二：Fisher-Yates 洗牌算法
     * 思路和算法
     *
     * 考虑通过调整 \textit{waiting}waiting 的实现方式以优化方法一。
     *
     * 我们可以在移除 \textit{waiting}waiting 的第 kk 个元素时，将第 kk 个元素与数组的最后 11 个元素交换，然后移除交换后数组的最后 11 个元素，这样我们只需要 O(1)O(1) 的时间复杂度即可完成移除第 kk 个元素的操作。此时，被移除的交换后数组的最后 11 个元素即为我们根据随机下标获取的元素。
     *
     * 在此基础上，我们也可以不移除最后 11 个元素，而直接将其作为乱序后的结果，并更新待乱序数组的长度，从而实现数组的原地乱序。因为我们不再需要从数组中移除元素，所以也可以将第 kk 个元素与第 11 个元素交换。
     *
     * 具体地，实现算法如下：
     *
     * 设待原地乱序的数组 \textit{nums}nums。
     * 循环 nn 次，在第 ii 次循环中（0 \le i < n0≤i<n）：
     * 在 [i,n)[i,n) 中随机抽取一个下标 jj；
     * 将第 ii 个元素与第 jj 个元素交换。
     * 其中数组中的 \textit{nums}[i \ .. \ n-1]nums[i .. n−1] 的部分为待乱序的数组，其长度为 n-in−i；\textit{nums}[0 \ .. \ i-1]nums[0 .. i−1] 的部分为乱序后的数组，其长度为 ii。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shuffle-an-array/solution/da-luan-shu-zu-by-leetcode-solution-og5u/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * 执行用时：
     * 81 ms
     * , 在所有 Java 提交中击败了
     * 49.07%
     * 的用户
     * 内存消耗：
     * 49.7 MB
     * , 在所有 Java 提交中击败了
     * 5.14%
     * 的用户
     * 通过测试用例：
     * 10 / 10
     */
    public Solution(int[] nums) {
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            int j = i + random.nextInt(nums.length - i);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

}
