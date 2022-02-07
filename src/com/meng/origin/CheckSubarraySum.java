package com.meng.origin;

import java.util.*;

/**
 * 523. 连续的子数组和
 *
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 *
 *     子数组大小 至少为 2 ，且
 *     子数组元素总和为 k 的倍数。
 *
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 *
 * 示例 2：
 *
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 *
 * 示例 3：
 *
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 105
 *     0 <= nums[i] <= 109
 *     0 <= sum(nums[i]) <= 231 - 1
 *     1 <= k <= 231 - 1
 * @author lenovo
 */

public class CheckSubarraySum {
    /**
     * 超出时间限制
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len < 2){
            return false;
        }
        int[] temp = new int[len + 1 ];
        for(int i = 0 ; i < len ; i++){
            temp[i+1] = temp[i] + nums[i];
        }
        for(int i = 1 ; i < len ; i++){
            for(int j = i ; j < len ; j++){
                if ((temp[j + 1] - temp[i-1])%k == 0 ){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 执行用时：67 ms, 在所有 Java 提交中击败了5.02% 的用户
     * 内存消耗：53.1 MB, 在所有 Java 提交中击败了51.82% 的用户
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumProv(int[] nums, int k) {
        int len = nums.length;
        if (len < 2){
            return false;
        }
        int[] temp = new int[len + 1 ];
        for(int i = 0 ; i < len ; i++){
            temp[i+1] = temp[i] + nums[i];
        }
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i = 0 ; i <= len ; i++){
            int key = temp[i] % k ;
            if (cache.get(key) != null){
                if (i - cache.get(key) > 1){
                    return true;
                }
            }else {
                cache.put(key,i);
            }
        }
        System.out.println(cache);
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23,2,4,6,6};
        int k = 7;
        CheckSubarraySum demo = new CheckSubarraySum();
        System.out.println(demo.checkSubarraySumProv(nums,k));
        System.out.println("--------------------------------");
        System.out.println(demo.checkSubarraySum2(nums,k));
    }
    /**
     * 方法一：前缀和 + 哈希表
     *
     * 朴素的思路是遍历数组 nums\textit{nums}nums 的每个大小至少为 222 的子数组并计算每个子数组的元素和，判断是否存在一个子数组的元素和为 kkk 的倍数。当数组 nums\textit{nums}nums 的长度为 mmm 时，上述思路需要用 O(m2)O(m^2)O(m2) 的时间遍历全部子数组，对于每个子数组需要 O(m)O(m)O(m) 的时间计算元素和，因此时间复杂度是 O(m3)O(m^3)O(m3)，会超出时间限制，因此必须优化。
     *
     * 如果事先计算出数组 nums\textit{nums}nums 的前缀和数组，则对于任意一个子数组，都可以在 O(1)O(1)O(1) 的时间内得到其元素和。用 prefixSums[i]\textit{prefixSums}[i]prefixSums[i] 表示数组 nums\textit{nums}nums 从下标 000 到下标 iii 的前缀和，则 nums\textit{nums}nums 从下标 p+1p+1p+1 到下标 qqq（其中 p<qp<qp<q）的子数组的长度为 q−pq-pq−p，该子数组的元素和为 prefixSums[q]−prefixSums[p]\textit{prefixSums}[q]-\textit{prefixSums}[p]prefixSums[q]−prefixSums[p]。
     *
     * 如果 prefixSums[q]−prefixSums[p]\textit{prefixSums}[q]-\textit{prefixSums}[p]prefixSums[q]−prefixSums[p] 为 kkk 的倍数，且 q−p≥2q-p \ge 2q−p≥2，则上述子数组即满足大小至少为 222 且元素和为 kkk 的倍数。
     *
     * 当 prefixSums[q]−prefixSums[p]\textit{prefixSums}[q]-\textit{prefixSums}[p]prefixSums[q]−prefixSums[p] 为 kkk 的倍数时，prefixSums[p]\textit{prefixSums}[p]prefixSums[p] 和 prefixSums[q]\textit{prefixSums}[q]prefixSums[q] 除以 kkk 的余数相同。因此只需要计算每个下标对应的前缀和除以 kkk 的余数即可，使用哈希表存储每个余数第一次出现的下标。
     *
     * 规定空的前缀的结束下标为 −1-1−1，由于空的前缀的元素和为 000，因此在哈希表中存入键值对 (0,−1)(0,-1)(0,−1)。对于 0≤i<m0 \le i<m0≤i<m，从小到大依次遍历每个 iii，计算每个下标对应的前缀和除以 kkk 的余数，并维护哈希表：
     *
     *     如果当前余数在哈希表中已经存在，则取出该余数在哈希表中对应的下标 prevIndex\textit{prevIndex}prevIndex，nums\textit{nums}nums 从下标 prevIndex+1\textit{prevIndex}+1prevIndex+1 到下标 iii 的子数组的长度为 i−prevIndexi-\textit{prevIndex}i−prevIndex，该子数组的元素和为 kkk 的倍数，如果 i−prevIndex≥2i-\textit{prevIndex} \ge 2i−prevIndex≥2，则找到了一个大小至少为 222 且元素和为 kkk 的倍数的子数组，返回 true\text{true}true；
     *
     *     如果当前余数在哈希表中不存在，则将当前余数和当前下标 iii 的键值对存入哈希表中。
     *
     * 由于哈希表存储的是每个余数第一次出现的下标，因此当遇到重复的余数时，根据当前下标和哈希表中存储的下标计算得到的子数组长度是以当前下标结尾的子数组中满足元素和为 kkk 的倍数的子数组长度中的最大值。只要最大长度至少为 222，即存在符合要求的子数组。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/lian-xu-de-zi-shu-zu-he-by-leetcode-solu-rdzi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：20 ms, 在所有 Java 提交中击败了54.79% 的用户
     * 内存消耗：54.8 MB, 在所有 Java 提交中击败了10.32% 的用户
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    /**
     * 前缀和 + HashSet
     *
     * 具体的，使用 HashSet 来保存出现过的值。
     *
     * 让循环从 222 开始枚举右端点（根据题目要求，子数组长度至少为 222），每次将符合长度要求的位置的取余结果存入 HashSet。
     *
     * 如果枚举某个右端点 jjj 时发现存在某个左端点 iii 符合要求，则返回 True。
     *
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/gong-shui-san-xie-tuo-zhan-wei-qiu-fang-1juse/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 其他解法
     * @param nums
     * @param k
     * @return
     * 执行用时：19 ms, 在所有 Java 提交中击败了69.23% 的用户
     * 内存消耗：52.7 MB, 在所有 Java 提交中击败了54.28% 的用户
     *
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }


}
