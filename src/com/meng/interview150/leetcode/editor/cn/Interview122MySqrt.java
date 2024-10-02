package com.meng.interview150.leetcode.editor.cn;

class Interview122MySqrt {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.55% 的Java用户
     * 	内存消耗:39.9 MB,击败了39.47% 的Java用户
     * @param x
     * @return
     */
    public int mySqrtMy(int x) {
        if(x == 0 ){
            return 0;
        }
        if (x < 4){
            return 1;
        }
        int left = 1,right = x;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (mid == x / mid){
                return mid;
            }else if (mid < x / mid){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left > x /left ? left - 1 : left;
    }

    /**
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 91.55%
     * 复杂度分析
     * 消耗内存分布
     * 40.07
     * MB
     * 击败
     * 5.08%
     * @param x
     * @return
     */
    public int mySqrt1(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 91.55%
     * 复杂度分析
     * 消耗内存分布
     * 40.00
     * MB
     * 击败
     * 18.20%
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

}
