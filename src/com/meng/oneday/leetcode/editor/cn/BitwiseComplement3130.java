package com.meng.oneday.leetcode.editor.cn;

class BitwiseComplement3130 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了71.97% 的Java用户
     * @param n
     * @return
     */
    public int bitwiseComplement3130(int n) {
        if(n == 0){
            return 1;
        }
        int bit = 1,or = 0;
        while (bit <= n){
            or |= bit;
            bit = bit << 1;

        }
        return or^n;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了99.24% 的Java用户
     * @param n
     * @return
     */
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int w = 32 - Integer.numberOfLeadingZeros(n);
        return ((1 << w) - 1) ^ n;
    }

}
