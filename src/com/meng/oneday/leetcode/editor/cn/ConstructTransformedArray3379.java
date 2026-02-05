package com.meng.oneday.leetcode.editor.cn;

class ConstructTransformedArray3379 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.9 MB,击败了56.36% 的Java用户
     * @param nums
     * @return
     */
    public int[] constructTransformedArray3379(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for(int i = 0 ; i < n ; i++){
            if (nums[i] > 0){
                res[i] = nums[(nums[i] + i) % n];
            }else if (nums[i] <  0){
                res[i] = nums[(i + n - (Math.abs(nums[i]) % n)) % n];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.1 MB,击败了25.45% 的Java用户
     * @param nums
     * @return
     */
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[((i + nums[i]) % n + n) % n]; // 保证结果在 [0,n-1] 中
        }
        return result;
    }
}
