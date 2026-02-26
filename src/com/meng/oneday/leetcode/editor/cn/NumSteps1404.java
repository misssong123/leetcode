package com.meng.oneday.leetcode.editor.cn;

class NumSteps1404 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了32.43% 的Java用户
     * 	内存消耗:42.1 MB,击败了45.95% 的Java用户
     * @param s
     * @return
     */
    public int numSteps1404(String s) {
        int res = 0,pre = 0;
        for (int i = s.length() - 1 ; i >= 0 ; i--){
            int val = s.charAt(i) - '0' + pre;
            if (i == 0){
                res += (val % 2 == 0 ? 1 : 0) ;
            }else{
                res += (val % 2 == 0 ? 1 : 2) ;
            }
            pre = val == 0 ? 0 : 1;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了70.27% 的Java用户
     * @param s
     * @return
     */
    public int numSteps(String s) {
        int n = s.length();
        int ans = n - 1; // 除了最高位，其余每一位都要执行一次「除以 2」
        int carry = 0;
        for (int i = n - 1; i > 0; i--) {
            int sum = s.charAt(i) - '0' + carry;
            ans += sum % 2; // 如果 s[i] 变成 1，需要执行「加上 1」
            carry = (sum + sum % 2) / 2;
        }
        // 如果在最高位还有进位，由于 1 + 1 = 10，需要再执行一次「除以 2」
        return ans + carry;
    }
}
