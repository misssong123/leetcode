package com.meng.interview150.leetcode.editor.cn;

class Interview126SingleNumber {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.57% 的Java用户
     * 	内存消耗:45.1 MB,击败了49.84% 的Java用户
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
