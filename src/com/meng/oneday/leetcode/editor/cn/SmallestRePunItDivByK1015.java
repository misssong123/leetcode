package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class SmallestRePunItDivByK1015 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了37.88% 的Java用户
     * @param k
     * @return
     */
    public int smallestRepunitDivByK1015(int k) {
        if(k % 2 == 0 || k % 5 == 0 ){
            return -1;
        }
        int res = 1;
        int mod = 1 % k;
        int pre = mod;
        while(mod != 0){
            pre = (pre * 10) % k;
            if (pre == 0){
                return -1;
            }
            mod = (mod + pre) % k;
            res++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了25.76% 的Java用户
     * 	内存消耗:45.4 MB,击败了25.76% 的Java用户
     * @param k
     * @return
     */
    public int smallestRepunitDivByKOther(int k) {
        Set<Integer> seen = new HashSet<Integer>();
        int x = 1 % k;
        while (x > 0 && seen.add(x)) {
            x = (x * 10 + 1) % k;
        }
        return x > 0 ? -1 : seen.size() + 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.6 MB,击败了31.82% 的Java用户
     * @param k
     * @return
     */
    public int smallestRepunitDivByKOther2(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int x = 1 % k;
        for (int i = 1; ; i++) { // 一定有解
            if (x == 0) {
                return i;
            }
            x = (x * 10 + 1) % k;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了59.09% 的Java用户
     * @param k
     * @return
     */
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        int m = phi(k * 9);

        // 从小到大枚举不超过 sqrt(m) 的因子
        int i = 1;
        for (; i * i <= m; i++) {
            if (m % i == 0 && pow(10, i, k * 9) == 1) {
                return i;
            }
        }

        // 从小到大枚举不低于 sqrt(m) 的因子
        for (i--; ; i--) {
            if (m % i == 0 && pow(10, m / i, k * 9) == 1) {
                return m / i;
            }
        }
    }

    // 计算欧拉函数（n 以内的与 n 互质的数的个数）
    private int phi(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                res = res / i * (i - 1);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            res = res / n * (n - 1);
        }
        return res;
    }

    // 快速幂，返回 pow(x, n) % mod
    private long pow(long x, int n, long mod) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return res;
    }


}
