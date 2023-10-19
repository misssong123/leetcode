package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;


class TupleSameProduct1726 {
    /**
     * 解答成功:
     * 	执行耗时:295 ms,击败了14.67% 的Java用户
     * 	内存消耗:63 MB,击败了54.67% 的Java用户
     * @param nums
     * @return
     */
    public int tupleSameProductMy(int[] nums) {
        if (nums.length < 4){
            return 0;
        }
        Map<Integer,Integer> cache = new HashMap<>();
        int res = 0;
        for(int i = 0 ; i < nums.length - 1 ; i++){
            for(int j = i + 1 ; j < nums.length ; j++ ){
                int num = nums[i] * nums[j];
                cache.put(num,cache.getOrDefault(num,0)+1);
                if (cache.get(num) > 1){
                    res += (cache.get(num) -1)*8;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:229 ms,击败了34.67% 的Java用户
     * 	内存消耗:61.1 MB,击败了76.00% 的Java用户
     * @param nums
     * @return
     */
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int key = nums[i] * nums[j];
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }
        }
        int ans = 0;
        for (Integer v : cnt.values()) {
            ans += v * (v - 1) * 4;
        }
        return ans;
    }

}

