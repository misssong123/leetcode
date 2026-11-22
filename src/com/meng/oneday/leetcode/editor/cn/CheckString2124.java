package com.meng.oneday.leetcode.editor.cn;

class CheckString2124 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了17.65% 的Java用户
     * @param s
     * @return
     */
    public boolean checkString2124(String s) {
        boolean flag = false;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                flag = true;
            }else if (c == 'a' && flag) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了47.06% 的Java用户
     * @param s
     * @return
     */
    public boolean checkString(String s) {
        return !s.contains("ba");
    }

}
