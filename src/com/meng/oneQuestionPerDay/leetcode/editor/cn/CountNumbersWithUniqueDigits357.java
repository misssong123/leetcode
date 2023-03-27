package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class CountNumbersWithUniqueDigits357 {
    /**
     * 思想出现错误，未整理出思路
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        int res = 1,temp = 10;
        for(int i = 0 ; i < n ; i++){
            res *= (temp - i);
        }
        if (n > 1){
            if (n == 2){
                res++;
            }else {
                res = res + 1 + 9 * (n-1);
            }
        }
        return res;
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 44.10%
     * 的用户
     * 通过测试用例：
     * 9 / 9
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits1(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }
}
