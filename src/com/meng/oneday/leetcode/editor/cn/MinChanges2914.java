package com.meng.oneday.leetcode.editor.cn;

class MinChanges2914 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.1 MB,击败了23.53% 的Java用户
     * @param s
     * @return
     */
    public int minChanges2914(String s) {
        int res = 0;
        for(int i = 0; i < s.length() -1 ; i+=2){
            if (s.charAt(i) != s.charAt(i+1)){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.5 MB,击败了5.88% 的Java用户
     * @param S
     * @return
     */
    public int minChanges(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        for (int i = 0; i < s.length; i += 2) {
            if (s[i] != s[i + 1]) {
                ans++;
            }
        }
        return ans;
    }
}
