package com.meng.oneday.leetcode.editor.cn;

class IsUgly263 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40 MB,击败了60.30% 的Java用户
     * @param n
     * @return
     */
    public boolean isUgly263(int n) {
        if (n <=0 ){
            return false;
        }
        while (n > 1){
            if(n %2 == 0){
                n = n/2;
            }else if (n % 3 == 0){
                n = n/3;
            }else if (n % 5 == 0){
                n = n/5;
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了16.24% 的Java用户
     * 	内存消耗:40.1 MB,击败了29.77% 的Java用户
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return (n & (n - 1)) == 0;
    }
}
