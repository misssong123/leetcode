package com.meng.oneday.leetcode.editor.cn;

class GetMinDistance1848 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了8.77% 的Java用户
     * @param nums
     * @param target
     * @param start
     * @return
     */
    public int getMinDistance1848(int[] nums, int target, int start) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res = Math.min(res, Math.abs(i - start));
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了42.10% 的Java用户
     * @param nums
     * @param target
     * @param start
     * @return
     */
    public int getMinDistance(int[] nums, int target, int start) {
        for (int k = 0; ; k++) {
            if (start >= k && nums[start - k] == target ||
                    start + k < nums.length && nums[start + k] == target) {
                return k;
            }
        }
    }
}
