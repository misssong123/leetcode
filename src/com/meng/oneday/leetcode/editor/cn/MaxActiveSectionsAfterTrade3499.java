package com.meng.oneday.leetcode.editor.cn;

class MaxActiveSectionsAfterTrade3499 {
    /**
     * 解答成功:
     * 	执行耗时:55 ms,击败了100.00% 的Java用户
     * 	内存消耗:47.3 MB,击败了30.56% 的Java用户
     * @param s
     * @return
     */
    public int maxActiveSectionsAfterTrade3499(String s) {
        int res = 0;
        int pre = 0, temp = 0,max = 0;
        for(char c : s.toCharArray()){
            if (c == '0'){
                temp++;
            }else{
                res ++;
                if (pre != 0 && temp != 0){
                    max = Math.max(max,temp + pre);
                }
                if (temp != 0){
                    pre = temp;
                    temp = 0;
                }

            }
        }
        if (pre != 0 && temp != 0){
            max = Math.max(max,temp + pre);
        }
        return res + max;
    }

    /**
     * 解答成功:
     * 	执行耗时:61 ms,击败了61.11% 的Java用户
     * 	内存消耗:47 MB,击败了61.11% 的Java用户
     * @param S
     * @return
     */
    public int maxActiveSectionsAfterTrade(String S) {
        char[] s = S.toCharArray();
        int total1 = 0;
        int mx = 0;
        int pre0 = Integer.MIN_VALUE;
        int cnt = 0;
        for (int i = 0; i < s.length; i++) {
            cnt++;
            if (i == s.length - 1 || s[i] != s[i + 1]) { // i 是这一段的末尾
                if (s[i] == '1') {
                    total1 += cnt;
                } else {
                    mx = Math.max(mx, pre0 + cnt);
                    pre0 = cnt;
                }
                cnt = 0;
            }
        }
        return total1 + mx;
    }

}
