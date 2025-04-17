package com.meng.oneday.leetcode.editor.cn;

import java.util.List;

class Solution {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了19.19% 的Java用户
     * @param nums
     * @return
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] cahce = new int[102];
        for(List<Integer> num : nums){
            cahce[num.get(0)]++;
            cahce[num.get(1)+1]--;
        }
        int res = 0,temp = 0;
        for(int num : cahce){
            temp += num;
            if (temp > 0){
                res++;
            }
        }
        return res;
    }
}
