package com.meng.oneday.leetcode.editor.cn;

class CountPartitions3432 {
    public int countPartitions3432(int[] nums) {
        int sum = 0 ;
        for (int num : nums) {
            sum += num;
        }
        int pre = 0,suf = sum,res = 0;
        for(int num : nums){
            pre += num;
            suf -= num;
            if(suf != 0 && (suf-pre) % 2 == 0){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了18.63% 的Java用户
     * @param nums
     * @return
     */
    public int countPartitions(int[] nums) {
        int s = 0 ;
        for (int num : nums) {
            s += num;
        }
        return s % 2 != 0 ? 0 : nums.length - 1;
    }

}
