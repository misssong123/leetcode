package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximizeSum2656 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了56.19% 的Java用户
     * 	内存消耗:42.6 MB,击败了30.94% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximizeSumMy(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int res = 0;
        for(int i = 0; i < k ; i++){
            res += max + i;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了56.19% 的Java用户
     * 	内存消耗:42.6 MB,击败了31.44% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximizeSum(int[] nums, int k) {
        int m = Arrays.stream(nums).max().getAsInt();
        return (2 * m + k - 1) * k / 2;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
