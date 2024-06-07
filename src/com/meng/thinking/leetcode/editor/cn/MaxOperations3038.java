package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxOperations3038 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了86.40% 的Java用户
     * @param nums
     * @return
     */
    public int maxOperationsMy(int[] nums) {
        int ans = 1;
        int target = nums[0]+nums[1];
        for(int i = 3 ; i < nums.length; i+=2){
            if (nums[i-1] + nums[i]==target){
                ans++;
            }else {
                break;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了78.51% 的Java用户
     * @param nums
     * @return
     */
    public int maxOperations(int[] nums) {
        int n = nums.length, t = 0;
        for (int i = 1; i < n; i += 2) {
            if (nums[i] + nums[i - 1] != nums[1] + nums[0]) {
                break;
            }
            t++;
        }
        return t;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
