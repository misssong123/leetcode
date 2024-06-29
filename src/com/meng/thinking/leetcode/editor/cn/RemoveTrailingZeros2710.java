package com.meng.thinking.leetcode.editor.cn;

class RemoveTrailingZeros2710 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了89.36% 的Java用户
     * @param num
     * @return
     */
    public String removeTrailingZeros(String num) {
        int n = num.length()-1;
        while (n>=0 && num.charAt(n) == '0'){
            n--;
        }
        return num.substring(0, n+1);
    }
}
