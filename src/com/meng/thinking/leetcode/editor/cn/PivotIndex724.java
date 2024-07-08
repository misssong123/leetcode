package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class PivotIndex724 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了30.48% 的Java用户
     * 	内存消耗:44.4 MB,击败了33.14% 的Java用户
     * @param nums
     * @return
     */
    public int pivotIndexMy(int[] nums) {
        int right = Arrays.stream(nums).sum()-nums[0],left = 0;
        for (int i = 0 ; i < nums.length ; i++){
            if (left == right){
                return i;
            }
            if (i < nums.length - 1){
                right -= nums[i+1];
            }
            left += nums[i];
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了30.48% 的Java用户
     * 	内存消耗:44.1 MB,击败了97.09% 的Java用户
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
