package com.meng.oneday.leetcode.editor.cn;

class SumZero1304 {

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了50.41% 的Java用户
     * @param n
     * @return
     */
    public int[] sumZero1304(int n) {
        if (n == 1){
            return new int[1];
        }
        int[] res = new int[n];
        for(int i = 1 ; i <= n ; i += 2){
            res[i-1] = i;
            if (i <n){
                res[i] = -i;
            }else {
                res[i-2] -= i;
            }
        }
        return res;
    }

    /**
     * 0
     * ms
     * 击败
     * 100.00%
     * 复杂度分析
     * 消耗内存分布
     * 40.95
     * MB
     * 击败
     * 24.39%
     * @param n
     * @return
     */
    public int[] sumZeroOther1(int n) {
        int[] a = new int[n];
        int m = n / 2;
        for (int i = 0; i < m; i++) {
            a[i] = i + 1;
            a[i + m] = -i - 1;
        }
        return a;
    }

    /**
     * 执行用时分布
     * 0
     * ms
     * 击败
     * 100.00%
     * 复杂度分析
     * 消耗内存分布
     * 40.93
     * MB
     * 击败
     * 26.83%
     * @param n
     * @return
     */
    public int[] sumZero(int n) {
        int[] a = new int[n];
        a[0] = -n * (n - 1) / 2;
        for (int i = 1; i < n; i++) {
            a[i] = i;
        }
        return a;
    }


}
