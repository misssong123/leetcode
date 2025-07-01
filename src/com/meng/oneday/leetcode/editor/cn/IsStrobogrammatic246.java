package com.meng.oneday.leetcode.editor.cn;

class IsStrobogrammatic246 {
    private static final char[][] ans = {{'0','0'}, {'1','1'},{'2','a'},{'3','a'},{'4','a'}
            ,{'5','a'},{'6','9'},{'7','9'},{'8','8'},{'9','6'}};

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了62.90% 的Java用户
     * @param num
     * @return
     */
    public boolean isStrobogrammatic246(String num) {
        int l = 0 , r = num.length()-1;
        while (l<=r){
            int index = num.charAt(l)-'0';
            if (num.charAt(r) != ans[index][1]){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了62.90% 的Java用户
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        int length = num.length();
        StringBuffer sb = new StringBuffer();
        for (int i = length - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (c != '0' && c != '1' && c != '8' && c != '6' && c != '9') {
                return false;
            }
            sb.append(rotate(c));
        }
        String rotate = sb.toString();
        return num.equals(rotate);
    }

    public char rotate(char c) {
        if (c == '6' || c == '9') {
            return (char) ('6' + '9' - c);
        } else {
            return c;
        }
    }

}
