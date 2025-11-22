package com.meng.oneday.leetcode.editor.cn;

class MinimumOperations3190 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了41.56% 的Java用户
     * 	内存消耗:43 MB,击败了12.99% 的Java用户
     * @param nums
     * @return
     */
    public int minimumOperations3190(int[] nums) {
        int res = 0;
        for (int num : nums){
            if (num % 3 != 0) {
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了41.56% 的Java用户
     * 	内存消耗:42.9 MB,击败了16.88% 的Java用户
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans += x % 3 != 0 ? 1 : 0;
        }
        return ans;
    }

}
