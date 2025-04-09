package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MinOperations3375 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了83.33% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int minOperations3375(int[] nums, int k) {
        int[] cache = new int[101];
        for(int num : nums){
            cache[num] = 1;
            if (num < k){
                return -1;
            }
        }
        int res = 0;
        for(int i = k +1 ; i < 101; i++){
            res += cache[i];
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了86.36% 的Java用户
     * 	内存消耗:43.5 MB,击败了77.27% 的Java用户
     */
    public int minOperations(int[] nums, int k) {
        Set<Integer> st = new HashSet<>();
        for (int x : nums) {
            if (x < k) {
                return -1;
            } else if (x > k) {
                st.add(x);
            }
        }
        return st.size();
    }

}
