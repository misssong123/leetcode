package com.meng.oneday.leetcode.editor.cn;

class MinimumSum2829 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了22.86% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int minimumSum2829(int n, int k) {
        int res = 0,pre = 1,middle = k /2;
        int[] cache = new int[51];
        for(int i = 0; i < n ; i++){
            if (pre < middle){
                cache[pre]++;
                res += pre++;
                continue;
            }
            if (pre >= k){
                res += pre++;
                continue;
            }
            while (cache[k-pre] > 0){
                pre++;
            }
            cache[pre]++;
            res += pre++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了60.00% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int minimumSum(int n, int k) {
        int m = Math.min(k / 2, n);
        return (m * (m + 1) + (k * 2 + n - m - 1) * (n - m)) / 2;
    }

}
