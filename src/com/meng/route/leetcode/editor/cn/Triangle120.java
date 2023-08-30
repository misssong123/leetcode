package com.meng.route.leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

class SolutionMinimumTotal120 {
    /**
     * 执行耗时:2 ms,击败了95.30% 的Java用户
     * 	内存消耗:42.6 MB,击败了92.10% 的Java用户
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> last = triangle.get(triangle.size() - 1);
        int len = last.size();
        int[] nums = new int[len];
        for(int i = 0 ; i < len ; i++){
            nums[i] = last.get(i);
        }
        for(int i = last.size()-2 ; i >=0 ; i-- ){
            List<Integer> temp = triangle.get(i);
            for(int j = 0 ; j <= i ; j++){
                nums[j] = temp.get(j) + Math.min(nums[j],nums[j+1]);
            }
        }
        return nums[0];
    }
}

