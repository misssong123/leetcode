package com.meng.dynamicprogramming.two02;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 提示：
 *
 * 1 <= n <= 45
 */
public class ClimbStairs {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 32.74%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n<=2){
            return n;
        }
        int first = 1,second=2,index =3;
        while (index <= n ){
            second = first + second;
            first = second -first;
            index++;
        }
        return second;
    }

    /**
     * 方法三：通项公式
     * 思路
     *
     * 之前的方法我们已经讨论了 f(n)f(n) 是齐次线性递推，根据递推方程 f(n) = f(n - 1) + f(n - 2)f(n)=f(n−1)+f(n−2)，我们可以写出这样的特征方程：
     *
     * x^2 = x + 1
     * x
     * 2
     *  =x+1
     *
     * 求得 x_1 = \frac{1+\sqrt{5}}{2}x
     * 1
     * ​
     *  =
     * 2
     * 1+
     * 5
     * ​
     *
     * ​
     *  ，x_2 = \frac{1-\sqrt{5}}{2}x
     * 2
     * ​
     *  =
     * 2
     * 1−
     * 5
     * ​
     *
     * ​
     *  ，设通解为 f(n) = c_1 x_1 ^n + c_2 x_2 ^ nf(n)=c
     * 1
     * ​
     *  x
     * 1
     * n
     * ​
     *  +c
     * 2
     * ​
     *  x
     * 2
     * n
     * ​
     *  ，代入初始条件 f(1) = 1f(1)=1，f(2) = 1f(2)=1，得 c_1 = \frac{1}{\sqrt{5}}c
     * 1
     * ​
     *  =
     * 5
     * ​
     *
     * 1
     * ​
     *  ，c_2 = -\frac{1}{\sqrt{5}}c
     * 2
     * ​
     *  =−
     * 5
     * ​
     *
     * 1
     * ​
     *  ，我们得到了这个递推数列的通项公式：
     *
     * f(n) = \frac{1}{\sqrt{5}}\left[ \left(\frac{1+\sqrt{5}}{2}\right)^{n} - \left(\frac{1-\sqrt{5}}{2}\right)^{n} \right]
     * f(n)=
     * 5
     * ​
     *
     * 1
     * ​
     *  [(
     * 2
     * 1+
     * 5
     * ​
     *
     * ​
     *  )
     * n
     *  −(
     * 2
     * 1−
     * 5
     * ​
     *
     * ​
     *  )
     * n
     *  ]
     *
     * 接着我们就可以通过这个公式直接求第 nn 项了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
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
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 38.13%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public int climbStairs1(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }

}
