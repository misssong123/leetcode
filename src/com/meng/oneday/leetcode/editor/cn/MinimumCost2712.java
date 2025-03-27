package com.meng.oneday.leetcode.editor.cn;

class MinimumCost2712 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了41.33% 的Java用户
     * 	内存消耗:44.7 MB,击败了72.00% 的Java用户
     * @param s
     * @return
     */
    public long minimumCost2712(String s) {
        long res = 0;
        for(int i = 0 ; i < s.length()-1 ; i++){
            if (s.charAt(i) != s.charAt(i+1)){
                res += Math.min(i+1,s.length()-i-1);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了80.00% 的Java用户
     * 	内存消耗:44.6 MB,击败了86.67% 的Java用户
     * @param S
     * @return
     */
    public long minimumCost(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            if (s[i - 1] != s[i]) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }

}
