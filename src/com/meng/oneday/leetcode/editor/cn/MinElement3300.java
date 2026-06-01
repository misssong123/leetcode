package com.meng.oneday.leetcode.editor.cn;

class MinElement3300 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了22.22% 的Java用户
     * 	内存消耗:44.3 MB,击败了33.33% 的Java用户
     * @param nums
     * @return
     */
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums){
            min = Math.min(min,getSum(num));
        }
        return min;
    }
    private int getSum(int num){
        int sum = 0;
        while (num > 0 ){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
