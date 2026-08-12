package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MaxSubArrayLength2958 {
    /**
     * 解答成功:
     * 	执行耗时:65 ms,击败了68.95% 的Java用户
     * 	内存消耗:87.3 MB,击败了90.63% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maxSubarrayLength2958(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for(int i = 0 ; i < nums.length ; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if(map.get(nums[i]) <= k){
                max = Math.max(max, i - left + 1);
            }else{
                while(map.get(nums[i]) > k){
                    map.put(nums[left], map.get(nums[left]) - 1);
                    left++;
                }
            }
        }
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了85.16% 的Java用户
     * 	内存消耗:112.1 MB,击败了12.90% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0;
        int left = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            cnt.merge(nums[right], 1, Integer::sum); // cnt[nums[right]]++
            while (cnt.get(nums[right]) > k) {
                cnt.merge(nums[left], -1, Integer::sum); // cnt[nums[left]]--
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
