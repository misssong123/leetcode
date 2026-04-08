package com.meng.oneday.leetcode.editor.cn;

class XorAfterQueries3653 {
    /**
     * 解答成功:
     * 	执行耗时:77 ms,击败了27.78% 的Java用户
     * 	内存消耗:47.1 MB,击败了66.67% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int xorAfterQueries3653(int[] nums, int[][] queries) {
        //处理查询
        for (int[] query : queries) {
            int sIndex = query[0];
            int eIndex = query[1];
            for (int i = sIndex; i <= eIndex; i += query[2]) {
                nums[i] = (int)((long)nums[i] * query[3]% 1000000007);
            }
        }
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
