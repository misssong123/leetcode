package com.meng.oneday.leetcode.editor.cn;

class IsZeroArray3355 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了72.64% 的Java用户
     * 	内存消耗:92.8 MB,击败了41.29% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public boolean isZeroArray3355(int[] nums, int[][] queries) {
        int len = nums.length;
        int[] diff = new int[len];
        for(int[] query : queries){
            diff[query[0]]--;
            if (query[1] < len - 1){
                diff[query[1]+1]++;
            }
        }
        int sum = 0;
        for(int i = 0 ; i < len ; i++){
            sum += diff[i];
            if (nums[i] + sum > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了72.64% 的Java用户
     * 	内存消耗:93.1 MB,击败了9.95% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int[] q : queries) {
            // 区间 [l,r] 中的数都加一
            diff[q[0]]++;
            diff[q[1] + 1]--;
        }

        int sumD = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i];
            // 此时 sumD 表示 nums[i] 要减掉多少
            if (nums[i] > sumD) { // nums[i] 无法变成 0
                return false;
            }
        }
        return true;
    }

}
