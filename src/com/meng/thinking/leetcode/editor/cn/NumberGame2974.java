package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

class NumberGame2974 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了52.77% 的Java用户
     * 	内存消耗:43.9 MB,击败了54.52% 的Java用户
     * @param nums
     * @return
     */
    public int[] numberGameMy(int[] nums) {
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++){
            if (i%2==0){
                res[i] = nums[i+1];
            }else {
                res[i] = nums[i-1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44 MB,击败了42.86% 的Java用户
     * @param nums
     * @return
     */
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
        }
        return nums;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
