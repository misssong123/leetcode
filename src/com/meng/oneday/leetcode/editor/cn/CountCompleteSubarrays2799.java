package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class CountCompleteSubarrays2799 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了96.70% 的Java用户
     * 	内存消耗:44.1 MB,击败了57.28% 的Java用户
     * @param nums
     * @return
     */
    public int countCompleteSubarrays2799(int[] nums) {
        int[] dp = new int[2001];
        int diff = 0,len = nums.length ;
        for(int num : nums){
            if (dp[num] == 0){
                diff++;
            }
            dp[num]++;
        }
        Arrays.fill(dp,0);
        int temp=0,res=0,left=0;
        for(int i = 0 ; i < len ; i++){
            int num = nums[i];
            if(dp[num] == 0){
                temp++;
            }
            dp[num]++;
            while (diff== temp && left <= i ){
                res += (len -i);
                dp[nums[left]]--;
                if (dp[nums[left]] == 0){
                    temp--;
                }
                left++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了85.83% 的Java用户
     * 	内存消耗:44.1 MB,击败了57.28% 的Java用户
     * @param nums
     * @return
     */
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        int k = set.size();

        Map<Integer, Integer> cnt = new HashMap<>(k);
        int ans = 0;
        int left = 0;
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
            while (cnt.size() == k) {
                int out = nums[left];
                if (cnt.merge(out, -1, Integer::sum) == 0) { // --cnt[out] == 0
                    cnt.remove(out);
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }


}
