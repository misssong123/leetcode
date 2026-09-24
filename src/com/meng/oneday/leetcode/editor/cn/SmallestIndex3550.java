package com.meng.oneday.leetcode.editor.cn;

class SmallestIndex3550 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.8 MB,击败了67.74% 的Java用户
     * @param nums
     * @return
     */
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(isEqual(nums[i],i)){
                return i;
            }
        }
        return -1;
    }

    private boolean isEqual(int num, int i) {
        int sum = 0;
        while (num>0 && sum <= i){
            sum += num%10;
            num /= 10;
        }
        return sum == i;
    }
}
