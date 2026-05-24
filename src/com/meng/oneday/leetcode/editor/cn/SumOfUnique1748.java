package com.meng.oneday.leetcode.editor.cn;

class SumOfUnique1748 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了96.43% 的Java用户
     * @param nums
     * @return
     */
    public int sumOfUnique1748(int[] nums) {
        int[] cnts = new int[101];
        for (int num : nums) {
            cnts[num]++;
        }
        int res = 0;
        for (int i = 0; i < 101; i++) {
            if (cnts[i] == 1) {
                res += i;
            }
        }
        return res;
    }
}
