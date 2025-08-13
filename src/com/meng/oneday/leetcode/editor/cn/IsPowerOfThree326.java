package com.meng.oneday.leetcode.editor.cn;

class IsPowerOfThree326 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了18.42% 的Java用户
     * 	内存消耗:43.2 MB,击败了48.58% 的Java用户
     * @param n
     * @return
     */
    public boolean isPowerOfThree326(int n) {
        if (n <=1){
            return n == 1;
        }
        while (n > 1){
            if (n % 3 != 0){
                return false;
            }
            n /= 3;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了10.74% 的Java用户
     * 	内存消耗:43.5 MB,击败了5.37% 的Java用户
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
