package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class NumArray303 {
    int[] preSum;
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了33.80% 的Java用户
     * 	内存消耗:48 MB,击败了80.24% 的Java用户
     * @param nums
     */
    public NumArray303(int[] nums) {
        preSum = new int[nums.length+1];
        for(int i=1;i<=nums.length;i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
    }
    public int sumRange(int left, int right) {
        return  preSum[right+1] - preSum[left];
    }
}

/**
 * 解答成功:
 * 	执行耗时:7 ms,击败了100.00% 的Java用户
 * 	内存消耗:48.1 MB,击败了56.59% 的Java用户
 */
class NumArray {
    int[] sums;

    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}


//leetcode submit region end(Prohibit modification and deletion)
