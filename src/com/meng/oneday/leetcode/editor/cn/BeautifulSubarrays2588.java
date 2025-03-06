package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class BeautifulSubarrays2588 {
    /**
     * 超时
     * @param nums
     * @return
     */
    public long beautifulSubarraysMy(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[2][len];
        long res = 0;
        int index = 0;
        //计算每一个数字的二进制数字为1的下标位置
        for(int i = 0 ; i < len ; i++){
            dp[index][i] = nums[i];
            if (dp[index][i] == 0){
                res++;
            }
        }
        for(int i = 2 ; i <= len ; i++){
            int orIndex = index ^ 1;
            for(int k = i-1 ; k < len ; k++ ){
                dp[orIndex][k] = dp[index][k-1] ^ nums[k];
                if (dp[orIndex][k]==0){
                    res++;
                }
            }
            index =  orIndex;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了87.06% 的Java用户
     * 	内存消耗:55.1 MB,击败了97.01% 的Java用户
     * @param nums
     * @return
     */
    public long beautifulSubarrays(int[] nums) {
        long ans = 0;
        int s = 0;
        Map<Integer, Integer> cnt = new HashMap<>(nums.length + 1); // 预分配空间
        cnt.put(0, 1);
        for (int x : nums) {
            s ^= x;
            int c = cnt.getOrDefault(s, 0);
            ans += c;
            cnt.put(s, c + 1);
        }
        return ans;
    }
}
