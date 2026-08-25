package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MissingMultiple3718 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了72.73% 的Java用户
     * 	内存消耗:44.9 MB,击败了18.18% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int missingMultiple3718(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1 ; ; i++){
            if (!set.contains(i*k)){
                return i*k;
            }
        }
    }
}
