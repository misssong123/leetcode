package com.meng.top100.leetcode.editor.cn;

class TrailingZeroes172 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了34.75% 的Java用户
     * 	内存消耗:42 MB,击败了5.08% 的Java用户
     * @param n
     * @return
     */
    public int trailingZeroes172(int n) {
        if (n == 0){
            return 0;
        }
        int res = 0;
        for(int i = 5 ; i <= n ; i+=5){
            int temp = i;
            while(temp % 5 == 0){
                res++;
                temp /= 5;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了5.08% 的Java用户
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            // 循环 k 次后，n 变成了 floor(n/5^k)
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
