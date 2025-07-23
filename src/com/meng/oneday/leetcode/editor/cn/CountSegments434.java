package com.meng.oneday.leetcode.editor.cn;

class CountSegments434 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了83.19% 的Java用户
     * @param s
     * @return
     */
    public int countSegments(String s) {
        if (s == null || s.isEmpty()){
            return 0;
        }
        int res = 0;
        boolean pre = false;
        for(char c : s.toCharArray()){
            if(c == ' '&&pre){
                res++;
            }
            pre = c != ' ';
        }
        if (s.charAt(s.length() - 1) != ' '){
            res++;
        }
        return res;
    }
}
