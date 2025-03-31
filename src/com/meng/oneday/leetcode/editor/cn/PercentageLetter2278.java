package com.meng.oneday.leetcode.editor.cn;

class PercentageLetter2278 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了63.79% 的Java用户
     * @param s
     * @param letter
     * @return
     */
    public int percentageLetter2278(String s, char letter) {
        int cnt = 0;
        for(char c : s.toCharArray()){
            if (c == letter){
                cnt++;
            }
        }
        return cnt * 100 / s.length();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了8.62% 的Java用户
     * 	内存消耗:41 MB,击败了5.17% 的Java用户
     * @param s
     * @param letter
     * @return
     */
    public int percentageLetter(String s, char letter) {
        return (int) s.chars().filter(c -> c == letter).count() * 100 / s.length();
    }

}
