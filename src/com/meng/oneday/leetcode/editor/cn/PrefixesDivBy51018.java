package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class PrefixesDivBy51018 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.3 MB,击败了18.52% 的Java用户
     * @param nums
     * @return
     */
    public List<Boolean> prefixesDivBy51018(int[] nums) {
        List<Boolean> ans = new ArrayList<>(nums.length); // 预分配空间
        int x = 0;
        for (int bit : nums) {
            x = (x << 1 | bit) % 5;
            ans.add(x == 0);
        }
        return ans;
    }
}
