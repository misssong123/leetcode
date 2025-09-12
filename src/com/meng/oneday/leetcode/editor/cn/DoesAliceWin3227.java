package com.meng.oneday.leetcode.editor.cn;

class DoesAliceWin3227 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了90.24% 的Java用户
     * 	内存消耗:44.6 MB,击败了65.85% 的Java用户
     * @param s
     * @return
     */
    public boolean doesAliceWin3227(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了90.24% 的Java用户
     * 	内存消耗:44.6 MB,击败了65.85% 的Java用户
     * @param s
     * @return
     */
    public boolean doesAliceWin(String s) {
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }
        return false;
    }
}
