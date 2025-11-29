package com.meng.oneday.leetcode.editor.cn;

class MinOperations3512 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了71.83% 的Java用户
     * 	内存消耗:46.1 MB,击败了16.90% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        return sum % k;
    }
}
