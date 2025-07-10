package com.meng.oneday.leetcode.editor.cn;

class MissingNumber268_07_10 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了55.05% 的Java用户
     * @param nums
     * @return
     */
    public int missingNumber268(int[] nums) {
        int n = nums.length;
        int res = 0;
        for(int i = 1; i <= n; i++){
            res ^= i;
        }
        for(int num : nums){
            res ^= num;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.5 MB,击败了44.35% 的Java用户
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (1 + n)*n/2;
        for(int num : nums){
            sum -= num;
        }
        return sum;
    }
}
