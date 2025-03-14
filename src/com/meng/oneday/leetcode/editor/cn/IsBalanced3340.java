package com.meng.oneday.leetcode.editor.cn;

class IsBalanced3340 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了73.85% 的Java用户
     * 	内存消耗:41.4 MB,击败了44.10% 的Java用户
     * @param num
     * @return
     */
    public boolean isBalanced3340(String num) {
        int res = 0;
        for(int i = 0 ; i < num.length() ; i++){
            int n = num.charAt(i) - '0';
            if(i%2 == 0){
                res += n;
            }else {
                res -= n;
            }
        }
        return res == 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了73.85% 的Java用户
     * 	内存消耗:41.5 MB,击败了40.00% 的Java用户
     * @param num
     * @return
     */
    boolean isBalanced(String num) {
        int s = 0;
        char[] digits = num.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            int c = digits[i] - '0';
            s += i % 2 > 0 ? c : -c;
        }
        return s == 0;
    }

}
