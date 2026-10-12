package com.meng.oneday.leetcode.editor.cn;

class LargestEven3798 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.44% 的Java用户
     * 	内存消耗:43.4 MB,击败了46.15% 的Java用户
     * @param s
     * @return
     */
    public String largestEven(String s) {
        for (int i = s.length() - 1 ; i >= 0; i--) {
            if ((s.charAt(i) - '0') % 2 == 0) {
                return s.substring(0,i + 1);
            }
        }
        return "";
    }
}
