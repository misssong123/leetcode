package com.meng.interview150.leetcode.editor.cn;

class Interview144IsInterleave {
    /**
     * 解答成功:
     * 	执行耗时:2339 ms,击败了6.40% 的Java用户
     * 	内存消耗:40.6 MB,击败了95.69% 的Java用户
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveMy(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        //判断字符组成是否相等
        int[] nums = new int[26];
        for (char c :s1.toCharArray()){
            nums[c - 'a']++;
        }
        for (char c :s2.toCharArray()){
            nums[c - 'a']++;
        }
        for (char c :s3.toCharArray()){
            nums[c - 'a']--;
            if (nums[c - 'a'] == -1){
                return false;
            }
        }
        //判断能否合成
        return dfs(s1,s2,s3,0,0,0);
    }

    private boolean dfs(String s1, String s2, String s3, int s1Index, int s2Index, int s3index) {
        if (s3index == s3.length()){
            return true;
        }
        boolean flag = false;
        if (s1Index < s1.length() && s1.charAt(s1Index) == s3.charAt(s3index)){
            flag = dfs(s1,s2,s3,s1Index + 1,s2Index,s3index + 1);
        }
        if (s2Index < s2.length() && s2.charAt(s2Index) == s3.charAt(s3index)){
            flag = flag || dfs(s1,s2,s3,s1Index,s2Index + 1,s3index + 1);
        }
        return flag;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了96.55% 的Java用户
     * 	内存消耗:40.7 MB,击败了71.55% 的Java用户
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
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
