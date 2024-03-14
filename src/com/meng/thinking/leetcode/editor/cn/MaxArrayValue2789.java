package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxArrayValue2789 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.3 MB,击败了74.10% 的Java用户
     * @param nums
     * @return
     */
    public long maxArrayValueMy(int[] nums) {
        int n = nums.length;
        long temp = nums[n-1];
        for(int i = n-2 ;i>=0 ; i--){
            if (nums[i]<=temp){
                temp += nums[i];
            }else {
                temp = nums[i];
            }
        }
        return temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.4 MB,击败了69.88% 的Java用户
     * @param nums
     * @return
     */
    public long maxArrayValue(int[] nums) {
        long sum = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            sum = nums[i] <= sum ? nums[i] + sum : nums[i];
        }
        return sum;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
