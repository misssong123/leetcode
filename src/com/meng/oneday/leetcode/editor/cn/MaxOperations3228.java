package com.meng.oneday.leetcode.editor.cn;

class MaxOperations3228 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了57.63% 的Java用户
     * 	内存消耗:47.3 MB,击败了5.09% 的Java用户
     * @param s
     * @return
     */
    public int maxOperations3228(String s) {
        int oneCnt = 0;
        int index = 0;
        int res = 0;
        for (int i = 0 ; i < s.length() ;i++){
            if (s.charAt(i) == '1'){
                if (i - index > 1){
                    res += oneCnt;
                }
                oneCnt++;
                index = i;
            }else if (i == s.length() - 1){
                res += oneCnt;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.7 MB,击败了8.48% 的Java用户
     * @param S
     * @return
     */
    public int maxOperations(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int cnt1 = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '1') {
                cnt1++;
            } else if (i > 0 && s[i - 1] == '1') {
                ans += cnt1;
            }
        }
        return ans;
    }
}
