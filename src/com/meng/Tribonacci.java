package com.meng;

/**
 * 1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 * 提示：
 *     0 <= n <= 37
 *     答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 */
public class Tribonacci {
    public static void main(String[] args) {

    }

    /**
     * 递归方法
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if (n<=0)
            return 0;
        else if (n<3)
            return 1;
        else
            return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
    }
    /**
     * 非递归方法
     * @param n
     * @return
     */
    public int tribonacciNoRecursion(int n) {
        if (n<=0)
            return 0;
        else if (n<3)
            return 1;
        else {
            int a=0,b=1,c=1,result=0;
            for (int j=3;j<=n;j++){
                result = a +b +c;
                a = b;
                b = c;
                c = result;
            }
            return result;
        }
    }
}
