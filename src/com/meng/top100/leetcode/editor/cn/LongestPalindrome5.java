package com.meng.top100.leetcode.editor.cn;

class LongestPalindrome5 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了85.18% 的Java用户
     * 	内存消耗:46.2 MB,击败了14.03% 的Java用户
     * @param s
     * @return
     */
    public String longestPalindrome5(String s) {
        char[] chars = s.toCharArray();
        String str = "";
        for(int i = 0 ; i < chars.length; i++){
            //以自身为中心
            String temp = getLen(chars,i,i);
            if (temp.length() > str.length()){
                str = temp;
            }
            //以自身和相邻的字符为中心
            if (i < chars.length - 1 && chars[i] == chars[i+1]){
                temp =getLen(chars,i,i+1);
                if (temp.length() > str.length()){
                    str = temp;
                }
            }
        }
        return str;
    }

    private String getLen(char[] chars, int start, int end) {
        while(start >= 0 && end < chars.length && chars[start] == chars[end]){
            start--;
            end++;
        }
        return new String(chars,start+1,end-start-1);
    }

    /**
     * 解答成功:
     * 	执行耗时:122 ms,击败了36.56% 的Java用户
     * 	内存消耗:47 MB,击败了12.24% 的Java用户
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
