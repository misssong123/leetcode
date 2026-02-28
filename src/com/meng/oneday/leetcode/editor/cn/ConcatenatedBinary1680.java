package com.meng.oneday.leetcode.editor.cn;

class ConcatenatedBinary1680 {
    /**
     * 解答成功:

     * 	执行耗时:50 ms,击败了61.11% 的Java用户
     * 	内存消耗:41.6 MB,击败了77.78% 的Java用户
     * @param n
     * @return
     */
    public int concatenatedBinary1680(int n) {
        long res = 0;
        int mod = 1_000_000_007;
        for(int i = 1 ; i <= n ; i++){
        int high = 32 - Integer.numberOfLeadingZeros(i);
            res = ((res << high) | i) % mod;
        }
        return (int)res;
    }

    /**
     * 解答成功:
     * 	执行耗时:59 ms,击败了50.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了77.78% 的Java用户
     * @param n
     * @return
     */
    public int concatenatedBinaryOther(int n) {
        final int MOD = 1_000_000_007;
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int w = 32 - Integer.numberOfLeadingZeros(i);
            ans = (ans << w | i) % MOD;
        }
        return (int) ans;
    }


}
