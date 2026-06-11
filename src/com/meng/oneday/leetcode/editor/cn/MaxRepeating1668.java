package com.meng.oneday.leetcode.editor.cn;

class MaxRepeating1668 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.44% 的Java用户
     * 	内存消耗:42.7 MB,击败了25.14% 的Java用户
     * @param sequence
     * @param word
     * @return
     */
    public int maxRepeating1668(String sequence, String word) {
        if (word.length() > sequence.length()) {
            return 0;
        }
        int len = sequence.length();
        int[] match = new int[len];
        int max = 0;
        for (int i = word.length() - 1; i < len; i++) {
            if (sequence.substring(i - word.length() + 1, i + 1).equals(word)) {
                match[i] = i == word.length() - 1 ? 1 : match[i - word.length()] + 1;
                max = Math.max(max, match[i]);
            }
        }
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.44% 的Java用户
     * 	内存消耗:42.4 MB,击败了61.75% 的Java用户
     * @param ss
     * @param pp
     * @return
     */
    public int maxRepeating(String ss, String pp) {
        int n = ss.length(), m = pp.length(), ans = 0;
        int[] f = new int[n + 10];
        for (int i = 1; i <= n; i++) {
            if (i - m < 0) continue;
            if (ss.substring(i - m, i).equals(pp)) f[i] = f[i - m] + 1;
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

}
