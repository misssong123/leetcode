package com.meng.enterprise.day01;

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
public class TwoSum {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.58%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 22.62%
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
            if (cache.get(target-nums[i]) == null){
                cache.put(nums[i],i);
                continue;
            }
            return new int[]{cache.get(target-nums[i]),i};
        }
        return null;
    }

    /**
     * 方法二：哈希表
     * 思路及算法
     *
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)O(1)。
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.58%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 89.84%
     * 的用户
     * 通过测试用例：
     * 57 / 57
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
