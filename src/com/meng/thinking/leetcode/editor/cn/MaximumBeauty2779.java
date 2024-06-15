package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumBeauty2779 {
    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了73.77% 的Java用户
     * 	内存消耗:58.8 MB,击败了59.02% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumBeautyMy(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0,right = 0,len = nums.length;
        int res = 0;
        while (true){
            while (right < len && nums[right] - nums[left] <= 2*k){
                right++;
            }
            res = Math.max(res,right - left);
            left++;
            if(right>=len){
                break;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:61.2 MB,击败了6.56% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumBeauty(int[] nums, int k) {
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[] diff = new int[m + 2];
        for (int x : nums) {
            diff[Math.max(x - k, 0)]++;
            diff[Math.min(x + k + 1, m + 1)]--;
        }
        int res = 0, count = 0;
        for (int x : diff) {
            count += x;
            res = Math.max(res, count);
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
