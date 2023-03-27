package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class IsInterleave97 {
    /**
     * 题意理解失败
     * 错误理解：s3总和字符个数和s1+s2的数据相同即可
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()){
            return false;
        }
        int[] chars = new int[26];
        for(char c : s1.toCharArray()){
            chars[c-'a']++;
        }
        for(char c : s2.toCharArray()){
            chars[c-'a']++;
        }
        for(char c : s3.toCharArray()){
            chars[c-'a']--;
        }
        for(int num : chars){
            if (num != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 64.13%
     * 的用户
     * 内存消耗：
     * 39.5 MB
     * , 在所有 Java 提交中击败了
     * 85.67%
     * 的用户
     * 通过测试用例：
     * 106 / 106
     * 不止要双方字符个数相同，顺序也要存在保证
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveProve(String s1, String s2, String s3) {
        int n = s1.length() , m = s2.length(),t = s3.length();
        if (m + n != t){
            return false;
        }
        boolean[][] cache = new boolean[n+1][m+1];
        cache[0][0] = true;
        for(int i = 0 ; i <=  n ; i++){
            for(int j = 0 ; j <= m ; j++){
                int index = i + j - 1 ;//待对比的s3所在位置
                if (i > 0){
                    cache[i][j] = cache[i-1][j] && s1.charAt(i-1)==s3.charAt(index);
                }
                if (j > 0){
                    cache[i][j] = cache[i][j] || cache[i][j-1] && s2.charAt(j-1)==s3.charAt(index);
                }

            }
        }
        return cache[n][m];
    }

    /**
     * 动态规划简化版
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 89.68%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 60.52%
     * 的用户
     * 通过测试用例：
     * 106 / 106
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[] f = new boolean[m + 1];

        f[0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[m];
    }

}

