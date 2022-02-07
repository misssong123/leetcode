package com.meng.origin;

public class UniquePaths {
    /**
     * 递归,类似于楼梯的问题
     * 超出时间限制
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if(m<=0|| n<=0)
            return 0;
        if (m==1&&n==1)
            return 1;
        return uniquePaths(m-1,n)+uniquePaths(m,n-1);
    }
    /**
     * 动态规划
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了56.03% 的用户
     */
    public int uniquePaths1(int m, int n) {
        int [][]res = new int[m][n];
        //初始化，需要将第一行和第一列的值初始化为1
        for(int i = 0 ; i < n ;i++)
            res[0][i] = 1;
        for(int i = 1 ; i < m ; i++)
            res[i][0] = 1;
        for(int i = 1 ; i <  m ; i++)
            for (int j = 1 ; j < n ; j++)
                res[i][j] = res[i-1][j] + res[i][j-1];
        return res[m-1][n-1];
    }
    /**
     * 官方解法一
     * 方法一：动态规划
     *
     * 思路与算法
     *
     * 我们用 f(i,j)f(i, j)f(i,j) 表示从左上角走到 (i,j)(i, j)(i,j) 的路径数量，其中 iii 和 jjj 的范围分别是 [0,m)[0, m)[0,m) 和 [0,n)[0, n)[0,n)。
     *
     * 由于我们每一步只能从向下或者向右移动一步，因此要想走到 (i,j)(i, j)(i,j)，如果向下走一步，那么会从 (i−1,j)(i-1, j)(i−1,j) 走过来；如果向右走一步，那么会从 (i,j−1)(i, j-1)(i,j−1) 走过来。因此我们可以写出动态规划转移方程：
     *
     * f(i,j)=f(i−1,j)+f(i,j−1)f(i, j) = f(i-1, j) + f(i, j-1) f(i,j)=f(i−1,j)+f(i,j−1)
     *
     * 需要注意的是，如果 i=0i=0i=0，那么 f(i−1,j)f(i-1,j)f(i−1,j) 并不是一个满足要求的状态，我们需要忽略这一项；同理，如果 j=0j=0j=0，那么 f(i,j−1)f(i,j-1)f(i,j−1) 并不是一个满足要求的状态，我们需要忽略这一项。
     *
     * 初始条件为 f(0,0)=1f(0,0)=1f(0,0)=1，即从左上角走到左上角有一种方法。
     *
     * 最终的答案即为 f(m−1,n−1)f(m-1,n-1)f(m−1,n−1)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了62.64% 的用户
     */
    public int uniquePaths2(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
    /**
     * 官方解法二
     * 方法二：组合数学
     *
     * 思路与算法
     *
     * 从左上角到右下角的过程中，我们需要移动 m+n−2m+n-2m+n−2 次，其中有 m−1m-1m−1 次向下移动，n−1n-1n−1 次向右移动。因此路径的总数，就等于从 m+n−2m+n-2m+n−2 次移动中选择 m−1m-1m−1 次向下移动的方案数，即组合数：
     *
     * Cm+n−2m−1=(m+n−2m−1)=(m+n−2)(m+n−3)⋯n(m−1)!=(m+n−2)!(m−1)!(n−1)!{\Large C}_{m+n-2}^{m-1} = \binom{m+n-2}{m-1} = \frac{(m+n-2)(m+n-3)\cdots n}{(m-1)!} = \frac{(m+n-2)!}{(m-1)!(n-1)!} Cm+n−2m−1​=(m−1m+n−2​)=(m−1)!(m+n−2)(m+n−3)⋯n​=(m−1)!(n−1)!(m+n−2)!​
     *
     * 因此我们直接计算出这个组合数即可。计算的方法有很多种：
     *
     *     如果使用的语言有组合数计算的 API，我们可以调用 API 计算；
     *
     *     如果没有相应的 API，我们可以使用 (m+n−2)(m+n−3)⋯n(m−1)!\frac{(m+n-2)(m+n-3)\cdots n}{(m-1)!}(m−1)!(m+n−2)(m+n−3)⋯n​ 进行计算。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了89.89% 的用户
     */
    public int uniquePaths3(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

}
