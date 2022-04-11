package com.meng.dynamicprogramming.one01;

/**
 * 1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：n = 25
 * 输出：1389537
 *
 *
 * 提示：
 *
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 */
public class Tribonacci {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 30.96%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if(n == 0){
            return 0;
        }
        if(n <= 2){
            return 1;
        }
        int first = 0,second = 1, third = 1;
        int index = 3;
        while (index <= n){
            int temp = first;
            first = second;
            second = third;
            third = temp + first + second;
            index++;
        }
        return third;
    }

    /**
     * 方法二：矩阵快速幂
     * 方法一的时间复杂度是 O(n)O(n)。使用矩阵快速幂的方法可以降低时间复杂度。
     *
     * 首先我们可以构建这样一个递推关系：
     *
     * \left[ \begin{matrix} 1 & 1 & 1 \\ 1 & 0 & 0 \\ 0 & 1 & 0 \end{matrix} \right] \left[ \begin{matrix} T(n) \\ T(n - 1) \\ T(n - 2) \end{matrix} \right] = \left[ \begin{matrix} T(n) + T(n - 1) + T(n - 2) \\ T(n) \\ T(n - 1) \end{matrix} \right] = \left[ \begin{matrix} T(n + 1) \\ T(n) \\ T(n - 1) \end{matrix} \right]
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * 1
     * 1
     * 0
     * ​
     *
     * 1
     * 0
     * 1
     * ​
     *
     * 1
     * 0
     * 0
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * T(n)
     * T(n−1)
     * T(n−2)
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *  =
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * T(n)+T(n−1)+T(n−2)
     * T(n)
     * T(n−1)
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *  =
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * T(n+1)
     * T(n)
     * T(n−1)
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *
     *
     * 因此：
     *
     * \left[ \begin{matrix} T(n + 2) \\ T(n + 1) \\ T(n) \end{matrix} \right] = \left[ \begin{matrix} 1 & 1 & 1 \\ 1 & 0 & 0 \\ 0 & 1 & 0 \end{matrix} \right]^n \left[ \begin{matrix} T(2) \\ T(1) \\ T(0) \end{matrix} \right]
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * T(n+2)
     * T(n+1)
     * T(n)
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *  =
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * 1
     * 1
     * 0
     * ​
     *
     * 1
     * 0
     * 1
     * ​
     *
     * 1
     * 0
     * 0
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *
     * n
     *
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * T(2)
     * T(1)
     * T(0)
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *
     *
     * 令：
     *
     * M = \left[ \begin{matrix} 1 & 1 & 1 \\ 1 & 0 & 0 \\ 0 & 1 & 0 \end{matrix} \right]
     * M=
     * ⎣
     * ⎢
     * ⎡
     * ​
     *
     * 1
     * 1
     * 0
     * ​
     *
     * 1
     * 0
     * 1
     * ​
     *
     * 1
     * 0
     * 0
     * ​
     *
     * ⎦
     * ⎥
     * ⎤
     * ​
     *
     *
     * 因此只要我们能快速计算矩阵 MM 的 nn 次幂，就可以得到 T(n)T(n) 的值。如果直接求取 M^nM
     * n
     *  ，时间复杂度是 O(n)O(n)，可以定义矩阵乘法，然后用快速幂算法来加速这里 M^nM
     * n
     *   的求取。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number/solution/di-n-ge-tai-bo-na-qi-shu-by-leetcode-sol-kn16/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 20.53%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     */
    public int tribonacci1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int[][] q = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
        int[][] res = pow(q, n);
        return res[0][2];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
            }
        }
        return c;
    }
}
