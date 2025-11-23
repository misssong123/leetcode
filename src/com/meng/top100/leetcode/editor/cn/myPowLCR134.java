package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class MyPowLCR134 {
    /**
     * 内存受限
     * @param x
     * @param n
     * @return
     */
    public double myPowLCR134(double x, int n) {
        if (n == 0){
            return 1;
        }
        boolean isNegative = n < 0;
        n = Math.abs(n);
        List<Double> baseList = new ArrayList<>();
        baseList.add(x);
        int pow = 2;
        while(pow < n){
            baseList.add(baseList.get(baseList.size() - 1) * baseList.get(baseList.size() - 1));
            pow <<= 1;
        }
        int index = baseList.size() - 1;
        double res = baseList.get(index);
        pow = pow / 2;
        n = n - pow;
        while (n > 0){
            if (n >= pow){
                res *= baseList.get(index);
                n -= pow;
            }
            index--;
            pow >>= 1;
        }
        return isNegative ? 1 / res : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.5 MB,击败了10.45% 的Java用户
     * @param x
     * @param N
     * @return
     */
    public double myPowOther(double x, int N) {
        double ans = 1;
        long n = N;
        if (n < 0) { // x^-n = (1/x)^n
            n = -n;
            x = 1 / x;
        }
        while (n != 0) { // 从低到高枚举 n 的每个比特位
            if ((n & 1) == 1) { // 这个比特位是 1
                ans *= x; // 把 x 乘到 ans 中
            }
            x *= x; // x 自身平方
            n >>= 1; // 继续枚举下一个比特位
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.7 MB,击败了6.47% 的Java用户
     * @param x
     * @param N
     * @return
     */
    public double myPow(double x, int N) {
        double ans = 1;
        boolean isNegative = N < 0;
        long n = Math.abs((long)N);
        while (n != 0) { // 从低到高枚举 n 的每个比特位
            if ((n & 1) == 1) { // 这个比特位是 1
                ans *= x; // 把 x 乘到 ans 中
            }
            x *= x; // x 自身平方
            n >>= 1; // 继续枚举下一个比特位
        }
        return isNegative ? 1 / ans : ans;
    }
}
