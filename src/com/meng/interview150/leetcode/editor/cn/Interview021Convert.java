package com.meng.interview150.leetcode.editor.cn;

class Interview021Convert {
    /**
     * 解答成功:
     * 	执行耗时:48 ms,击败了8.37% 的Java用户
     * 	内存消耗:46.4 MB,击败了5.08% 的Java用户
     * @param s
     * @param numRows
     * @return
     */
    public String convertMy(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int len = s.length();
        char[][] chars = new char[numRows][len];
        int x = 0,y=0;
        boolean flag = true;
        for(char c : s.toCharArray()){
            chars[x][y] = c;
            if (flag){//向下
                if (x == numRows - 1){
                    flag = false;
                    x--;
                    y++;
                }else {
                    x++;
                }
            }else{//斜向上
                if (x == 0){
                    flag = true;
                    x++;
                }else {
                    x--;
                    y++;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(char[] row : chars){
            for(char c : row){
                if (c != '\u0000' ){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了44.48% 的Java用户
     * 	内存消耗:43.6 MB,击败了91.86% 的Java用户
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        StringBuffer[] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }
        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }
}
