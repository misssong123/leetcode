package com.meng.interview150.leetcode.editor.cn;

class Interview130TrailingZeroes {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了42.87% 的Java用户
     * 	内存消耗:39.8 MB,击败了20.42% 的Java用户
     * @param n
     * @return
     */
    public int trailingZeroesMy(int n) {
        int res = 0;
        for(int i = 5 ; i <= n ; i+= 5){
            for (int x = i ; x % 5 == 0 ; x /= 5){
                res++;
            }
        }
        return res;
    }
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

}
