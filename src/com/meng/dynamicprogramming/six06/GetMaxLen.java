package com.meng.dynamicprogramming.six06;

/**
 * 1567. 乘积为正数的最长子数组长度
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 *
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 *
 * 请你返回乘积为正数的最长子数组长度。
 *
 *
 *
 * 示例  1：
 *
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * 示例 2：
 *
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 * 示例 3：
 *
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class GetMaxLen {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 50.92%
     * 的用户
     * 内存消耗：
     * 53.4 MB
     * , 在所有 Java 提交中击败了
     * 81.65%
     * 的用户
     * 通过测试用例：
     * 112 / 112
     * @param nums
     * @return
     */
    public int getMaxLen(int[] nums) {
        int len = nums.length;
        int[] add = new int[len];
        int[] min = new int[len];
        int max = 0;
        int index = 0;
        for(int num : nums){
            if (num == 0){
                add[index] = 0;
                add[index] = 0;
            }else  if (num > 0){
                if (index==0){
                    add[index] = 1;
                }else {
                    add[index] = add[index-1]+1;
                    min[index] = min[index-1] > 0 ? min[index-1]+1:0;
                }
            }else {
                if (index==0){
                    min[index] = 1;
                }else {
                    add[index] = min[index-1]>0?min[index-1]+1:0;
                    min[index] = add[index-1]+1;
                }
            }
            max = Math.max(max,add[index]);
            index ++;
        }
        return max;
    }

    /**
     * 注意到 \textit{positive}[i]positive[i] 和 \textit{negative}[i]negative[i] 的值只与 \textit{positive}[i-1]positive[i−1] 和 \textit{negative}[i-1]negative[i−1] 有关，因此可以使用滚动数组优化空间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product/solution/cheng-ji-wei-zheng-shu-de-zui-chang-zi-shu-zu-ch-3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 99.82%
     * 的用户
     * 内存消耗：
     * 57.5 MB
     * , 在所有 Java 提交中击败了
     * 13.65%
     * 的用户
     * 通过测试用例：
     * 112 / 112
     */
    public int getMaxLen2(int[] nums) {
        int length = nums.length;
        int positive = nums[0] > 0 ? 1 : 0;
        int negative = nums[0] < 0 ? 1 : 0;
        int maxLength = positive;
        for (int i = 1; i < length; i++) {
            if (nums[i] > 0) {
                positive++;
                negative = negative > 0 ? negative + 1 : 0;
            } else if (nums[i] < 0) {
                int newPositive = negative > 0 ? negative + 1 : 0;
                int newNegative = positive + 1;
                positive = newPositive;
                negative = newNegative;
            } else {
                positive = 0;
                negative = 0;
            }
            maxLength = Math.max(maxLength, positive);
        }
        return maxLength;
    }
}
