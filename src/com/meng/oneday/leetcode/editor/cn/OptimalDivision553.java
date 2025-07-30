package com.meng.oneday.leetcode.editor.cn;

class OptimalDivision553 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了56.31% 的Java用户
     * 	内存消耗:40.9 MB,击败了42.72% 的Java用户
     * @param nums
     * @return
     */
    public String optimalDivision553(int[] nums) {
        StringBuilder sb = new StringBuilder();
        int len = nums.length;
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }
        if (nums.length == 2) {
            return nums[0] + "/" + nums[1];
        }
        for(int i = 0 ; i < len; i++) {
            if (i == 0){
                sb.append(nums[i]).append("/(");
            }else if (i == len - 1){
                sb.append(nums[i]).append(")");
            }else {
                sb.append(nums[i]).append("/");
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了66.02% 的Java用户
     * 	内存消耗:40.4 MB,击败了76.70% 的Java用户
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nums[i]);
            if (i + 1 < n) sb.append("/");
        }
        if (n > 2) {
            sb.insert(sb.indexOf("/") + 1, "("); sb.append(")");
        }
        return sb.toString();
    }
}
