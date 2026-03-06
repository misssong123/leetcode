package com.meng.oneday.leetcode.editor.cn;

class CheckOnesSegment1784 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了96.43% 的Java用户
     * @param s
     * @return
     */
    public boolean checkOnesSegment1784(String s) {
        int pre = -1;
        for (int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '1'){
                if(pre == -1 || pre == i - 1){
                    pre = i;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了12.50% 的Java用户
     * @param s
     * @return
     */
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }

}
