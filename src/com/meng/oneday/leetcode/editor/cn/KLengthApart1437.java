package com.meng.oneday.leetcode.editor.cn;

class KLengthApart1437 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:64.3 MB,击败了5.82% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean kLengthApart1437(int[] nums, int k) {
        int pre = -k - 1;
        for(int i = 0 ; i < nums.length ;i++){
            if(nums[i] == 1){
                if(i - pre - 1 < k){
                    return false;
                }
                pre = i;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:64.2 MB,击败了5.82% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public boolean kLengthApart(int[] nums, int k) {
        int last1 = -k - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1) {
                continue;
            }
            if (i - last1 <= k) {
                return false;
            }
            last1 = i;
        }
        return true;
    }

}
