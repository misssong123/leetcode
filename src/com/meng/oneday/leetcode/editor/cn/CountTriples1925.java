package com.meng.oneday.leetcode.editor.cn;

class CountTriples1925 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了81.94% 的Java用户
     * 	内存消耗:41.5 MB,击败了23.61% 的Java用户
     * @param n
     * @return
     */
    public int countTriples1925(int n) {
        int res = 0;
        for(int i = 1 ; i <= n ; i++){
            for(int j = i + 1 ; j <= n ; j++){
                int k = (int) Math.sqrt(i * i + j * j);
                if (k >n){
                    if (j == i + 1){
                        return res;
                    }
                    break;
                }
                if (k * k == i * i + j * j){
                    res+=2;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了31.94% 的Java用户
     * @param n
     * @return
     */
    public int countTriples(int n) {
        int ans = 0;
        for (int u = 3; u * u < n * 2; u += 2) {
            for (int v = 1; v < u && u * u + v * v <= n * 2; v += 2) {
                if (gcd(u, v) == 1) {
                    ans += n * 2 / (u * u + v * v);
                }
            }
        }
        return ans * 2; // (a,b,c) 和 (b,a,c) 各算一次
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
