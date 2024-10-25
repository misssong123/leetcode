package com.meng.interview150.leetcode.editor.cn;

class Interview135SingleNumber {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了86.99% 的Java用户
     * 	内存消耗:44.3 MB,击败了49.58% 的Java用户
     * @param nums
     * @return
     */
    public int singleNumberMy(int[] nums) {
        int res = 0;
        for(int i = 0 ; i < 32 ; i++){
            int temp = 0;
            for(int num :nums){
                temp += (num>>i)&1;
            }
            res |= temp%3 << i;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了65.30% 的Java用户
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            b = (b ^ x) & ~a;
            a = (a ^ x) & ~b;
        }
        return b;
    }
}
