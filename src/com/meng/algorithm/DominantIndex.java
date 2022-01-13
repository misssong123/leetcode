package com.meng.algorithm;

/**
 * 747. 至少是其他数字两倍的最大数
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 *
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,1,0]
 * 输出：1
 * 解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：-1
 * 解释：4 没有超过 3 的两倍大，所以返回 -1 。
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 * 解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 100
 * nums 中的最大元素是唯一的
 */
public class DominantIndex {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.3 MB
     * , 在所有 Java 提交中击败了
     * 25.91%
     * 的用户
     * 通过测试用例：
     * 232 / 232
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        int first = -1;
        int second = -1;
        int firstIndex = -1;
        for(int i = 0 ; i < nums.length ;i++){
            int num = nums[i];
            if (num > second){
                if (num > first){
                    second = first;
                    first = num;
                    firstIndex = i;
                    continue;
                }
                second = num;
            }
        }
        System.out.println(first + ","+second);
        return first >= 2 * second ? firstIndex : -1;
    }

    /**
     * 方法一：遍历
     * 思路与算法
     *
     * 遍历数组分别找到数组的最大值 m_1m
     * 1
     * ​
     *   和次大值 m_2m
     * 2
     * ​
     *  。如果 m_1 \ge m_2 \times 2m
     * 1
     * ​
     *  ≥m
     * 2
     * ​
     *  ×2 成立，则最大值至少是数组其余数字的两倍，此时返回最大值的下标，否则返回 -1−1。
     *
     * 为了返回最大值的下标，我们需要在计算最大值的同时记录最大值的下标。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/solution/zhi-shao-shi-qi-ta-shu-zi-liang-bei-de-z-985m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.2 MB
     * , 在所有 Java 提交中击败了
     * 56.24%
     * 的用户
     * 通过测试用例：
     * 232 / 232
     */
    public int dominantIndex1(int[] nums) {
        int m1 = -1, m2 = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                index = i;
            } else if (nums[i] > m2) {
                m2 = nums[i];
            }
        }
        return m1 >= m2 * 2 ? index : -1;
    }

    public static void main(String[] args) {
        DominantIndex demo = new DominantIndex();
        System.out.println(demo.dominantIndex(new int[]{1,2,3,4}));
    }
}
