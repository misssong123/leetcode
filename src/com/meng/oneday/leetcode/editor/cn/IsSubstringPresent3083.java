package com.meng.oneday.leetcode.editor.cn;

class IsSubstringPresent3083 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.40% 的Java用户
     * 	内存消耗:42 MB,击败了33.62% 的Java用户
     * @param s
     * @return
     */
    public boolean isSubstringPresent(String s) {
        boolean[][] vis = new boolean[26][26];
        for(int i = 0 ; i < s.length()-1 ; i++){
            int x = s.charAt(i) - 'a';
            int y = s.charAt(i+1) - 'a';
            vis[x][y] = true;
            if (vis[y][x]){
                return true;
            }

        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.40% 的Java用户
     * 	内存消耗:41.2 MB,击败了85.71% 的Java用户
     * @param S
     * @return
     */
    public boolean isSubstringPresentOther1(String S) {
        char[] s = S.toCharArray();
        int[] vis = new int[26];
        for (int i = 1; i < s.length; i++) {
            int x = s[i - 1] - 'a';
            int y = s[i] - 'a';
            vis[x] |= 1 << y;
            if ((vis[y] >> x & 1) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.40% 的Java用户
     * 	内存消耗:42.4 MB,击败了20.17% 的Java用户
     * @param S
     * @return
     */
    public boolean isSubstringPresentOther2(String S) {
        char[] s = S.toCharArray();
        boolean[][] vis = new boolean[26][26];
        for (int i = 1; i < s.length; i++) {
            int x = s[i - 1] - 'a';
            int y = s[i] - 'a';
            vis[x][y] = true;
            if (vis[y][x]) {
                return true;
            }
        }
        return false;
    }

}
