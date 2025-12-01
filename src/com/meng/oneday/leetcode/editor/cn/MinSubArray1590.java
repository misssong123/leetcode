package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MinSubArray1590 {
    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了100.00% 的Java用户
     * 	内存消耗:92.6 MB,击败了58.72% 的Java用户
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        long sum = 0 ;
        for(int num : nums){
            sum += num;
        }
        int x = (int)(sum % p);
        if (x == 0) {
            return 0;
        }
        int n = nums.length, ans = n,pre = 0;
        Map<Integer,Integer> map = new HashMap<>(n+1,1);
        map.put(pre,-1);
        for (int i = 0 ; i < n ; i++){
            pre = (pre + nums[i]) % p;
            map.put(pre,i);
            int target = (pre - x + p) % p;
            if (map.containsKey(target)){
                ans = Math.min(ans,i - map.get(target));
            }
        }
        return ans == n ? -1 : ans;
    }
}
