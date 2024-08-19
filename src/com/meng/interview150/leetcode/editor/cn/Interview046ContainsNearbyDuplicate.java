package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Interview046ContainsNearbyDuplicate {
    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了86.12% 的Java用户
     * 	内存消耗:54.7 MB,击败了78.72% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicateMy(int[] nums, int k) {
        int len = nums.length;
        Map<Integer,Integer> cache = new HashMap<>(len);
        for(int i = 0;i < len;i++){
            if (cache.containsKey(nums[i])){
                if (i - cache.get(nums[i]) <= k){
                    return true;
                }
            }
            cache.put(nums[i],i);
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了99.86% 的Java用户
     * 	内存消耗:55.1 MB,击败了62.91% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
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
