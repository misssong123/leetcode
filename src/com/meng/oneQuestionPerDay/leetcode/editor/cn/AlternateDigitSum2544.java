package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class AlternateDigitSum2544 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 59.20%
     * 的用户
     * 通过测试用例：
     * 118 / 118
     * @param n
     * @return
     */
    public int alternateDigitSum(int n) {
        int num = 0,res = 0;
        while (n > 0){
            num++;
            int m = n % 10;
            res += (num % 2 ==0 ? -m :m);
            n = n / 10;
        }
        return num % 2 == 0 ? -res : res;
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 54.18%
     * 的用户
     * 通过测试用例：
     * 118 / 118
     * @param n
     * @return
     */
    public int alternateDigitSum1(int n) {
        int res = 0, sign = 1;
        while (n > 0) {
            res += n % 10 * sign;
            sign = -sign;
            n /= 10;
        }
        return -sign * res;
    }


}

