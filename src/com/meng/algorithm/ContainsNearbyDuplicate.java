package com.meng.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 *
 *
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 */
public class ContainsNearbyDuplicate {
    /**
     * 执行用时：
     * 31 ms
     * , 在所有 Java 提交中击败了
     * 23.73%
     * 的用户
     * 内存消耗：
     * 52.1 MB
     * , 在所有 Java 提交中击败了
     * 38.01%
     * 的用户
     * 通过测试用例：
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        if (k==0 || len==1){
            return false;
        }
        if (k>=len-1){
            Set<Integer> set = new HashSet<>();
            for(int i = 0 ; i < len ; i++){
                if (!set.add(nums[i])){
                    return true;
                }
            }
            return false;
        }
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i <= k ; i++){
            int num = nums[i];
            cache.put(num,cache.getOrDefault(num,0)+1);
            if (cache.get(num) > 1){
                return true;
            }
        }
        for(int i=k+1 ; i < len ; i++){
            int num = nums[i];
            if (cache.getOrDefault(num,0) > 0){
                return true;
            }
            cache.put(num,1);
            num = nums[i-k-1];
            cache.put(num,cache.get(num)-1);
        }
        return false;
    }

    /**
     * 方法一：哈希表
     * 从左到右遍历数组 \textit{nums}nums，当遍历到下标 ii 时，如果存在下标 j < ij<i 使得 \textit{nums}[i] = \textit{nums}[j]nums[i]=nums[j]，则当 i - j \le ki−j≤k 时即找到了两个符合要求的下标 jj 和 ii。
     *
     * 如果在下标 ii 之前存在多个元素都和 \textit{nums}[i]nums[i] 相等，为了判断是否存在满足 \textit{nums}[i] = \textit{nums}[j]nums[i]=nums[j] 且 i - j \le ki−j≤k 的下标 jj，应该在这些元素中寻找下标最大的元素，将最大下标记为 jj，判断 i - j \le ki−j≤k 是否成立。
     *
     * 如果 i - j \le ki−j≤k，则找到了两个符合要求的下标 jj 和 ii；如果 i - j > ki−j>k，则在下标 ii 之前不存在任何元素满足与 \textit{nums}[i]nums[i] 相等且下标差的绝对值不超过 kk，理由如下。
     *
     * 假设存在下标 j'j
     * ′
     *   满足 j' < j < ij
     * ′
     *  <j<i 且 \textit{nums}[j'] = \textit{nums}[j] = \textit{nums}[i]nums[j
     * ′
     *  ]=nums[j]=nums[i]，则 i - j' > i - ji−j
     * ′
     *  >i−j，由于 i - j > ki−j>k，因此必有 i - j' > ki−j
     * ′
     *  >k。
     *
     * 因此，当遍历到下标 ii 时，如果在下标 ii 之前存在与 \textit{nums}[i]nums[i] 相等的元素，应该在这些元素中寻找最大的下标 jj，判断 i - j \le ki−j≤k 是否成立。
     *
     * 可以使用哈希表记录每个元素的最大下标。从左到右遍历数组 \textit{nums}nums，当遍历到下标 ii 时，进行如下操作：
     *
     * 如果哈希表中已经存在和 \textit{nums}[i]nums[i] 相等的元素且该元素在哈希表中记录的下标 jj 满足 i - j \le ki−j≤k，返回 \text{true}true；
     *
     * 将 \textit{nums}[i]nums[i] 和下标 ii 存入哈希表，此时 ii 是 \textit{nums}[i]nums[i] 的最大下标。
     *
     * 上述两步操作的顺序不能改变，因为当遍历到下标 ii 时，只能在下标 ii 之前的元素中寻找与当前元素相等的元素及该元素的最大下标。
     *
     * 当遍历结束时，如果没有遇到两个相等元素的下标差的绝对值不超过 kk，返回 \text{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii/solution/cun-zai-zhong-fu-yuan-su-ii-by-leetcode-kluvk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 90.50%
     * 的用户
     * 内存消耗：
     * 47.4 MB
     * , 在所有 Java 提交中击败了
     * 72.28%
     * 的用户
     * 通过测试用例：
     * 51 / 51
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }

    /**
     * 方法二：滑动窗口
     * 考虑数组 \textit{nums}nums 中的每个长度不超过 k + 1k+1 的滑动窗口，同一个滑动窗口中的任意两个下标差的绝对值不超过 kk。如果存在一个滑动窗口，其中有重复元素，则存在两个不同的下标 ii 和 jj 满足 \textit{nums}[i] = \textit{nums}[j]nums[i]=nums[j] 且 |i - j| \le k∣i−j∣≤k。如果所有滑动窗口中都没有重复元素，则不存在符合要求的下标。因此，只要遍历每个滑动窗口，判断滑动窗口中是否有重复元素即可。
     *
     * 如果一个滑动窗口的结束下标是 ii，则该滑动窗口的开始下标是 \max(0, i - k)max(0,i−k)。可以使用哈希集合存储滑动窗口中的元素。从左到右遍历数组 \textit{nums}nums，当遍历到下标 ii 时，具体操作如下：
     *
     * 如果 i > ki>k，则下标 i - k - 1i−k−1 处的元素被移出滑动窗口，因此将 \textit{nums}[i - k - 1]nums[i−k−1] 从哈希集合中删除；
     *
     * 判断 \textit{nums}[i]nums[i] 是否在哈希集合中，如果在哈希集合中则在同一个滑动窗口中有重复元素，返回 \text{true}true，如果不在哈希集合中则将其加入哈希集合。
     *
     * 当遍历结束时，如果所有滑动窗口中都没有重复元素，返回 \text{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii/solution/cun-zai-zhong-fu-yuan-su-ii-by-leetcode-kluvk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 99.05%
     * 的用户
     * 内存消耗：
     * 46.5 MB
     * , 在所有 Java 提交中击败了
     * 94.92%
     * 的用户
     * 通过测试用例：
     * 51 / 51
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }



}
