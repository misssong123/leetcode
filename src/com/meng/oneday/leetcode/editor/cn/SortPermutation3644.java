package com.meng.oneday.leetcode.editor.cn;

class SortPermutation3644 {
    /**
     * 无思路
     * @param nums
     * @return
     */
    public int sortPermutation3644(int[] nums) {
        return 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了60.00% 的Java用户
     * 	内存消耗:108.3 MB,击败了40.00% 的Java用户
     * @param nums
     * @return
     */
    public int sortPermutation(int[] nums) {
        int ans = -1; // 二进制全为 1
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (i != x) {
                ans &= x;
            }
        }
        return Math.max(ans, 0);
    }

}
