package com.meng.oneday.leetcode.editor.cn;

class CountCommas3870 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了36.67% 的Java用户
     * 	内存消耗:41.9 MB,击败了63.33% 的Java用户
     * @param n
     * @return
     */
    public int countCommas3870(int n) {
        if (n < 1000){
            return 0;
        }
        return n - 1000 + 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.8 MB,击败了73.33% 的Java用户
     * @param n
     * @return
     */
    public int countCommas(int n) {
        int ans = 0;
        // 从低到高，枚举逗号的位置
        for (int low = 1000; low <= n; low *= 1000) {
            // [low, n] 中的每个数都在这个位置上有一个逗号
            ans += n - low + 1;
        }
        return ans;
    }
}
