package com.meng.interview150.leetcode.editor.cn;

class Interview134MyPow {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了99.73% 的Java用户
     * @param x
     * @param n
     * @return
     */
    public double myPowMy(double x, int n) {
        if(n == 0){
            return 1;
        }
        if(x ==1 || x ==0 || n == 1){
            return x;
        }
        double res = 1;
        long N = n;
        if (N < 0){
            N = -N;
            x = 1/x;
        }
        double factor = x;
        while (N>0){
            if(N%2 ==1){
                res *= factor;
            }
            factor *= factor;
            N=N>>1;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了50.22% 的Java用户
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}
