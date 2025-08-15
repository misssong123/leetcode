package com.meng.oneday.leetcode.editor.cn;

class IsPowerOfFour342 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了11.36% 的Java用户
     * 	内存消耗:39.9 MB,击败了80.44% 的Java用户
     * @param n
     * @return
     */
    public boolean isPowerOfFour342(int n) {
        return n >= 1 &&
                Integer.toBinaryString(n).length() % 2 == 1
                && Integer.bitCount(n) == 1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了11.36% 的Java用户
     * 	内存消耗:40 MB,击败了57.57% 的Java用户
     * @param n
     * @return
     */
    public boolean isPowerOfFourOther1(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) > 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了11.36% 的Java用户
     * 	内存消耗:40 MB,击败了59.46% 的Java用户
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }
}
