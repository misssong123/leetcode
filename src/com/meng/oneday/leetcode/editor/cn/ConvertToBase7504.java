package com.meng.oneday.leetcode.editor.cn;

class ConvertToBase7504 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了94.40% 的Java用户
     * 	内存消耗:42.1 MB,击败了28.47% 的Java用户
     * @param num
     * @return
     */
    public String convertToBase7504(int num) {
        if (num == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        long n = Math.abs(num);
        while (n > 0){
            sb.append(n % 7);
            n /= 7;
        }
        if (num < 0){
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
