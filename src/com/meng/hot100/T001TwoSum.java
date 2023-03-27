package com.meng.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和(简单)
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */
public class T001TwoSum {
    /**
     * 1.使用map存储已经出现的数字和对应下标
     * 2.map查询当前数据与目标数据的差值是否已经存在
     * 3.存在则返回
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.64%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 29.69%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if (cache.get(target - nums[i]) != null){
                return new int[]{cache.get(target-nums[i]),i};
            }
            cache.put(nums[i],i);
        }
        return null;
    }

    /**
     *方法一：暴力枚举
     * 思路及算法
     *
     * 最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
     *
     * 当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，因此不需要再进行匹配。而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     * 执行用时：
     * 49 ms
     * , 在所有 Java 提交中击败了
     * 37.84%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 64.25%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

}
