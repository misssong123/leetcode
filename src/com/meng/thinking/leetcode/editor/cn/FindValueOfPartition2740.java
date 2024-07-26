package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class FindValueOfPartition2740 {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了100.00% 的Java用户
     * 	内存消耗:53.9 MB,击败了79.21% 的Java用户
     * @param nums
     * @return
     */
    public int findValueOfPartitionMy(int[] nums) {
        Arrays.sort(nums);
        int temp = nums[nums.length - 1];
        for(int i = 1 ; i < nums.length ; i++){
            temp = Math.min(temp,nums[i]-nums[i-1]);
        }
        return temp;
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了1.98% 的Java用户
     * 	内存消耗:53.9 MB,击败了75.25% 的Java用户
     * @param nums
     * @return
     */
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            res = Math.min(res, nums[i] - nums[i - 1]);
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
