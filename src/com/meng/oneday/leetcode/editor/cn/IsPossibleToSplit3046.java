package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class IsPossibleToSplit3046 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了61.90% 的Java用户
     * 	内存消耗:44 MB,击败了57.14% 的Java用户
     * @param nums
     * @return
     */
    public boolean isPossibleToSplit3046(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
            if (map.get(num) > 2){
                return false;
            }
        }
        return true;
    }
}
