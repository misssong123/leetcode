package com.meng;

/**
 * 485. 最大连续1的个数
 *
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 *
 * 注意：
 *
 *     输入的数组只包含 0 和1。
 *     输入数组的长度是正整数，且不超过 10,000。
 */
public class FindMaxConsecutiveOnes {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了90.66% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了79.51% 的用户
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int len = nums.length , index = 0 , result  = 0 , count = 0 ;
        while(index < len) {
            if (nums[index] == 1){
                count++;
            }else {
                result = Math.max(count,result);
                count = 0 ;
            }
            index++;
        }
        result = Math.max(count,result);
        return result;
    }
    /**
     * 方法一：一次遍历
     *
     * 为了得到数组中最大连续 111 的个数，需要遍历数组，并记录最大的连续 111 的个数和当前的连续 111 的个数。如果当前元素是 111，则将当前的连续 111 的个数加 111，否则，使用之前的连续 111 的个数更新最大的连续 111 的个数，并将当前的连续 111 的个数清零。
     *
     * 遍历数组结束之后，需要再次使用当前的连续 111 的个数更新最大的连续 111 的个数，因为数组的最后一个元素可能是 111，且最长连续 111 的子数组可能出现在数组的末尾，如果遍历数组结束之后不更新最大的连续 111 的个数，则会导致结果错误。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/max-consecutive-ones/solution/zui-da-lian-xu-1de-ge-shu-by-leetcode-so-252a/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     *执行用时：2 ms, 在所有 Java 提交中击败了90.66% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了61.73% 的用户
     */
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}
