package com.meng.oneday.leetcode.editor.cn;

class MinMoves3736 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.4 MB,击败了90.16% 的Java用户
     * @param nums
     * @return
     */
    public int minMoves3736(int[] nums) {
        //获取最大值
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        //获取差值
        int res = 0;
        for (int num : nums) {
            res += max - num;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了39.35% 的Java用户
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int x : nums) {
            max = Math.max(max, x);
            sum += x;
        }
        return max * nums.length - sum;
    }

}
