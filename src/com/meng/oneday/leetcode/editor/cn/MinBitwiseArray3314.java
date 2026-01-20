package com.meng.oneday.leetcode.editor.cn;

import java.util.List;

class MinBitwiseArray3314 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了30.43% 的Java用户
     * 	内存消耗:46.1 MB,击败了26.09% 的Java用户
     * @param nums
     * @return
     */
    public int[] minBitwiseArray3314(List<Integer> nums) {
        int [] res = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i);
            if (val % 2 == 0){
                res[i] = -1;
            }else{
                int pre = 1 << (Integer.toBinaryString(val).length() -1) ;
                for (int j = pre -1 ; j < val ; j++){
                    if ((j | j + 1) == val){
                        res[i] = j;
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.2 MB,击败了8.70% 的Java用户
     * @param nums
     * @return
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            if (x == 2) {
                ans[i] = -1;
            } else {
                int t = ~x;
                int lowbit = t & -t;
                ans[i] = x ^ (lowbit >> 1);
            }
        }
        return ans;
    }

}
