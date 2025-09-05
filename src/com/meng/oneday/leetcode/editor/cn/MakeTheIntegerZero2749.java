package com.meng.oneday.leetcode.editor.cn;

class MakeTheIntegerZero2749 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了89.29% 的Java用户
     * @param num1
     * @param num2
     * @return
     */
    public int makeTheIntegerZero2749(int num1, int num2) {
        if (num1 <= num2){
            return -1;
        }
        for (long k = 1; k <= num1 - num2 * k; k++) {
            if (k >= Long.bitCount(num1 - num2 * k)) {
                return (int) k;
            }
        }
        return -1;
    }
}
