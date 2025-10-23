package com.meng.oneday.leetcode.editor.cn;

class HasSameDigits3461 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了56.32% 的Java用户
     * 	内存消耗:42.1 MB,击败了66.67% 的Java用户
     * @param s
     * @return
     */
    public boolean hasSameDigits3461(String s) {
        StringBuilder sb =  new StringBuilder();
        while (s.length() > 2){
            for (int i = 0 ; i < s.length() -1 ; i++){
                sb.append(((s.charAt(i) - '0') + (s.charAt(i+1) - '0'))%10);
            }
            s = sb.toString();
            sb.setLength(0);
        }
        return s.charAt(0) == s.charAt(1);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了89.66% 的Java用户
     * @param s
     * @return
     */
    public boolean hasSameDigits(String s) {
        int n = s.length();
        char[] sArray = s.toCharArray();
        for (int i = 1; i <= n - 2; i++) {
            for (int j = 0; j <= n - 1 - i; j++) {
                int digit1 = sArray[j] - '0';
                int digit2 = sArray[j + 1] - '0';
                sArray[j] = (char) (((digit1 + digit2) % 10) + '0');
            }
        }
        return sArray[0] == sArray[1];
    }
}
