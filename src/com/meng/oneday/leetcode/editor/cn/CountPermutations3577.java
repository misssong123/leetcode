package com.meng.oneday.leetcode.editor.cn;

class CountPermutations3577 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:90.3 MB,击败了36.00% 的Java用户
     * @param complexity
     * @return
     */
    public int countPermutations3577(int[] complexity) {
        int n = complexity.length;
        long res = 1;
        for(int i = 1 ; i < n ; i++){
            if (complexity[i] <= complexity[0]){
                return 0;
            }
        }
        int mod = 1000000007;
        for(int i = 1 ; i < n ; i++){
            res = (res * i) % mod;
        }
        return (int)res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了48.00% 的Java用户
     * 	内存消耗:90.4 MB,击败了8.00% 的Java用户
     * @param complexity
     * @return
     */
    public int countPermutationsOther(int[] complexity) {
        final int MOD = 1_000_000_007;
        long ans = 1;
        for (int i = 1; i < complexity.length; i++) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
            ans = ans * i % MOD;
        }
        return (int) ans;
    }
}
