package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MinimumOperations1963 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了65.33% 的Java用户
     * @param nums
     * @return
     */
    public int minimumOperations1963(int[] nums) {
        int[] cache = new int[101];
        int max = -3;
        for(int i = nums.length - 1; i >= 0 ; i--){
            if (++cache[nums[i]]>1){
                max = i;
                break;
            }
        }
        return (max + 3)/3;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了92.89% 的Java用户
     * 	内存消耗:43.3 MB,击败了67.11% 的Java用户
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!seen.add(nums[i])) { // nums[i] 在 seen 中
                return i / 3 + 1;
            }
        }
        return 0;
    }

}
