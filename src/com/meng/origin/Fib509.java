package com.meng.origin;

/**
 * 509. 斐波那契数
 *
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 *
 * 给你 n ，请计算 F(n) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *
 * 示例 2：
 *
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *
 * 示例 3：
 *
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *
 *
 *
 * 提示：
 *
 *     0 <= n <= 30
 */
public class Fib509 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了48.36% 的用户
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n<2)
            return n;
        int first = 0 , second = 1;
        for(int i = 2 ;i<=n;i++){
            int temp = first+second;
            first = second;
            second = temp;
        }
        return second;
    }
    /**
     * 方法一：动态规划
     *
     * 斐波那契数的边界条件是 F(0)=0F(0)=0F(0)=0 和 F(1)=1F(1)=1F(1)=1。当 n>1n>1n>1 时，每一项的和都等于前两项的和，因此有如下递推关系：
     *
     * F(n)=F(n−1)+F(n−2)F(n)=F(n-1)+F(n-2) F(n)=F(n−1)+F(n−2)
     *
     * 由于斐波那契数存在递推关系，因此可以使用动态规划求解。动态规划的状态转移方程即为上述递推关系，边界条件为 F(0)F(0)F(0) 和 F(1)F(1)F(1)。
     *
     * 根据状态转移方程和边界条件，可以得到时间复杂度和空间复杂度都是 O(n)O(n)O(n) 的实现。由于 F(n)F(n)F(n) 只和 F(n−1)F(n-1)F(n−1) 与 F(n−2)F(n-2)F(n−2) 有关，因此可以使用「滚动数组思想」把空间复杂度优化成 O(1)O(1)O(1)
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     */
    public int fib1(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    /**
     * 方法二：矩阵快速幂
     *
     * 方法一的时间复杂度是 O(n)O(n)O(n)。使用矩阵快速幂的方法可以降低时间复杂度。
     *
     * 首先我们可以构建这样一个递推关系：
     *
     * [1110][F(n)F(n−1)]=[F(n)+F(n−1)F(n)]=[F(n+1)F(n)]\left[ \begin{matrix} 1 & 1 \\ 1 & 0 \end{matrix} \right] \left[ \begin{matrix} F(n)\\ F(n - 1) \end{matrix} \right] = \left[ \begin{matrix} F(n) + F(n - 1)\\ F(n) \end{matrix} \right] = \left[ \begin{matrix} F(n + 1)\\ F(n) \end{matrix} \right] [11​10​][F(n)F(n−1)​]=[F(n)+F(n−1)F(n)​]=[F(n+1)F(n)​]
     *
     * 因此：
     *
     * [F(n+1)F(n)]=[1110]n[F(1)F(0)]\left[ \begin{matrix} F(n + 1)\\ F(n) \end{matrix} \right] = \left[ \begin{matrix} 1 & 1 \\ 1 & 0 \end{matrix} \right] ^n \left[ \begin{matrix} F(1)\\ F(0) \end{matrix} \right] [F(n+1)F(n)​]=[11​10​]n[F(1)F(0)​]
     *
     * 令：
     *
     * M=[1110]M = \left[ \begin{matrix} 1 & 1 \\ 1 & 0 \end{matrix} \right] M=[11​10​]
     *
     * 因此只要我们能快速计算矩阵 MMM 的 nnn 次幂，就可以得到 F(n)F(n)F(n) 的值。如果直接求取 MnM^nMn，时间复杂度是 O(n)O(n)O(n)，可以定义矩阵乘法，然后用快速幂算法来加速这里 MnM^nMn 的求取。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了72.47% 的用户
     */
    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
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
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
    /**
     * 方法三：通项公式
     *
     * 斐波那契数 F(n)F(n)F(n) 是齐次线性递推，根据递推方程 F(n)=F(n−1)+F(n−2)F(n)=F(n-1)+F(n-2)F(n)=F(n−1)+F(n−2)，可以写出这样的特征方程：
     *
     * x2=x+1x^2=x+1 x2=x+1
     *
     * 求得 x1=1+52x_1 = \frac{1+\sqrt{5}}{2}x1​=21+5
     * ​​，x2=1−52x_2 = \frac{1-\sqrt{5}}{2}x2​=21−5
     * ​​。设通解为 F(n)=c1x1n+c2x2nF(n)=c_1x_1^n+c_2x_2^nF(n)=c1​x1n​+c2​x2n​，代入初始条件 F(0)=0F(0)=0F(0)=0，F(1)=1F(1)=1F(1)=1，得 c1=15c_1=\frac{1}{\sqrt{5}}c1​=5
     * ​1​，c2=−15c_2=-\frac{1}{\sqrt{5}}c2​=−5
     *
     * ​1​。因此斐波那契数的通项公式如下：
     *
     * F(n)=15[(1+52)n−(1−52)n]F(n)=\frac{1}{\sqrt{5}}\left[ \left(\frac{1+\sqrt{5}}{2}\right)^{n} - \left(\frac{1-\sqrt{5}}{2}\right)^{n} \right] F(n)=5
     * ​1​[(21+5
     * ​​)n−(21−5
     *
     * ​​)n]
     *
     * 得到通项公式之后，就可以通过公式直接求解第 nnn 项。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了85.87% 的用户
     */
    public int fib3(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibN / sqrt5);
    }

}
