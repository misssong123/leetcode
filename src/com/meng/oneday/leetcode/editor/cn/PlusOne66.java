package com.meng.oneday.leetcode.editor.cn;

class PlusOne66 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.5 MB,击败了60.43% 的Java用户
     * @param digits
     * @return
     */
    public int[] plusOne66(int[] digits) {
        for(int i = digits.length-1;i>=0;i--){
            if(digits[i]==9){
                digits[i]=0;
            }else{
                digits[i]++;
                return digits;
            }
        }
        int[] res = new int[digits.length+1];
        res[0]=1;
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.70 MB,击败了39.26%% 的Java用户
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {

            if (digits[i] < 9) {

                digits[i]++; // 进位

                return digits;

            }

            digits[i] = 0; // 进位数字的右边数字都变成 0

        }


        // digits 全是 9，加一后变成 100...00

        int[] ans = new int[n + 1];

        ans[0] = 1;

        return ans;
    }
}
