package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class AlternatingSubarray2765 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了22.75% 的Java用户
     * @param nums
     * @return
     */
    public int alternatingSubarrayMy(int[] nums) {
        int ans = -1,temp = -1,len = nums.length,index =1;
        while (index < len){
            int num = 1;
            temp = 1;
            while (index < len && nums[index] - nums[index - 1] == num){
                index++;
                num *= -1;
                temp++;
            }
            if (index < len && nums[index] - nums[index - 1] == 1){
                index--;
            }
            if (temp != 1){
                ans = Math.max(ans,temp);
            }
            index++;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了8.98% 的Java用户
     * @param nums
     * @return
     */
    public int alternatingSubarray(int[] nums) {
        int res = -1;
        int n = nums.length;
        int firstIndex = 0;
        for (int i = 1; i < n; i++) {
            int length = i - firstIndex + 1;
            if (nums[i] - nums[firstIndex] == (length - 1) % 2) {
                res = Math.max(res, length);
            } else {
                if (nums[i] - nums[i - 1] == 1) {
                    firstIndex = i - 1;
                    res = Math.max(res, 2);
                } else {
                    firstIndex = i;
                }
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
