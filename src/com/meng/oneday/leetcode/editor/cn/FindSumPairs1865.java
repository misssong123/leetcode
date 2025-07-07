package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;


/**
 * 解答成功:
 * 	执行耗时:166 ms,击败了17.12% 的Java用户
 * 	内存消耗:79.2 MB,击败了6.17% 的Java用户
 */
class FindSumPairs1865 {
    Map<Integer,Integer> nums1Cache;
    Map<Integer,Integer> nums2Cache;
    int[] nums2;
    public FindSumPairs1865(int[] nums1, int[] nums2) {
        nums1Cache = new HashMap<>();
        nums2Cache = new HashMap<>();
        for (int j : nums1) {
            nums1Cache.put(j, nums1Cache.getOrDefault(j, 0) + 1);
        }
        for (int j : nums2) {
            nums2Cache.put(j, nums2Cache.getOrDefault(j, 0) + 1);
        }
        this.nums2 = nums2;
    }
    
    public void add(int index, int val) {
        //移除
        nums2Cache.put(nums2[index],nums2Cache.get(nums2[index])-1);
        //添加
        nums2[index] += val;
        nums2Cache.put(nums2[index],nums2Cache.getOrDefault(nums2[index],0)+1);
    }
    
    public int count(int tot) {
        int count = 0;
        if (nums1Cache.size() <= nums2Cache.size()){
            for (Map.Entry<Integer, Integer> entry : nums1Cache.entrySet()) {
                if (nums2Cache.containsKey(tot - entry.getKey())){
                    count += entry.getValue() * nums2Cache.get(tot - entry.getKey());
                }
            }
        }else {
            for (Map.Entry<Integer, Integer> entry : nums2Cache.entrySet()) {
                if (nums1Cache.containsKey(tot - entry.getKey())){
                    count += entry.getValue() * nums1Cache.get(tot - entry.getKey());
                }
            }
        }
        return count;
    }
}

/**
 * 解答成功:
 * 	执行耗时:183 ms,击败了8.22% 的Java用户
 * 	内存消耗:75.5 MB,击败了24.66% 的Java用户
 */
class FindSumPairs {
    private final int[] nums1;
    private final int[] nums2;
    private final Map<Integer, Integer> cnt = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int x : nums2) {
            cnt.merge(x, 1, Integer::sum);
        }
    }

    public void add(int index, int val) {
        // 维护 nums2 每个元素的出现次数
        cnt.merge(nums2[index], -1, Integer::sum);
        nums2[index] += val;
        cnt.merge(nums2[index], 1, Integer::sum);
    }

    public int count(int tot) {
        int ans = 0;
        for (int x : nums1) {
            ans += cnt.getOrDefault(tot - x, 0);
        }
        return ans;
    }
}
