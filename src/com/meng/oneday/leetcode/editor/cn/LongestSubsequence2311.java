package com.meng.oneday.leetcode.editor.cn;

class LongestSubsequence2311 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了88.57% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int longestSubsequence2311(String s, int k) {
        int res = 0;
        long step = 1,sum = 0;
        boolean flag = true;
        for(int i = s.length() - 1 ; i>=0 ; i--){
            if (s.charAt(i) == '0'){
                res++;
            }else if (flag){
                if (sum + step <= k){
                    sum += step;
                    res++;
                }else {
                    flag = false;
                }
            }
            if (flag&&step<=k){
                step <<= 1;
            }

        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了97.14% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
        if (n < m) {
            return n; // 全选
        }

        int sufVal = Integer.parseInt(s.substring(n - m), 2);
        int ans = sufVal <= k ? m : m - 1; // 后缀长度

        for (int i = 0; i < n - m; i++) {
            ans += '1' - s.charAt(i); // 添加前导零
        }
        return ans;
    }
}
