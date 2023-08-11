package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class IsSubsequence {
    /**
     * 详情
     * 1ms
     * 击败 88.96%使用 Java 的用户
     * 内存
     * 详情
     * 38.46mb
     * 击败 79.87%使用 Java 的用户
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0){
            return true;
        }
        if (s.length() < t.length()){
            //起始位置
            int index = 0;
            for(char c : t.toCharArray()){
                if(c == s.charAt(index)){
                    index++;
                }
                if (index == s.length()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 双指针
     * @param s
     * @param t
     * @return
     * 详情
     * 1ms
     * 击败 88.96%使用 Java 的用户
     * 内存
     * 详情
     * 38.24mb
     * 击败 97.44%使用 Java 的用户
     */
    public boolean isSubsequence1(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    /**
     * 动态规划
     * @param s
     * @param t
     * @return
     * 详情
     * 5ms
     * 击败 19.05%使用 Java 的用户
     * 内存
     * 详情
     * 41.68mb
     * 击败 5.02%使用 Java 的用户
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

}

