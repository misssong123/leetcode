package com.meng.oneday.leetcode.editor.cn;

class LargestOddNumber1903 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.4 MB,击败了21.21% 的Java用户
     * @param num
     * @return
     */
    public String largestOddNumber1903(String num) {
        for (int i = num.length()-1; i >=0 ; i--) {
            if((num.charAt(i)-'0')%2==1){
                return num.substring(0,i+1);
            }
        }
        return "";
    }
}
