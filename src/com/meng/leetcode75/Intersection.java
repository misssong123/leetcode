package com.meng.leetcode75;

import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集(简单)
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 *
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class Intersection {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.11%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 31.73%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] flags = new boolean[1001];
        for(int num : nums1){
            flags[num] = true;
        }
        Set<Integer> set = new HashSet<>();
        for(int num : nums2){
            if (flags[num]){
                set.add(num);
            }
        }
        int[] res = new int[set.size()];
        int index = 0 ;
        for(int num : set){
            res[index++] = num;
        }
        return res;
    }
}
