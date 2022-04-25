package com.meng.dynamicprogramming.fifteen15;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 *
 * 输入：m = 3, n = 3
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 */
public class UniquePaths {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 41.72%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
       if (m==1 || n==1){
           return 1;
       }
       int[][] nums = new int[m][n];
       for(int i = 0 ; i < m ; i++){
           nums[i][0] = 1;
       }
       for(int i = 0 ; i < n ; i++){
           nums[0][i] = 1;
       }
       for(int i = 1 ; i < m ; i++){
           for(int j = 1 ; j < n ; j++){
               nums[i][j] = nums[i-1][j] + nums[i][j-1];
           }
       }
       return nums[m-1][n-1];
    }

    /**
     * 方法二：组合数学
     * 思路与算法
     *
     * 从左上角到右下角的过程中，我们需要移动 m+n-2m+n−2 次，其中有 m-1m−1 次向下移动，n-1n−1 次向右移动。因此路径的总数，就等于从 m+n-2m+n−2 次移动中选择 m-1m−1 次向下移动的方案数，即组合数：
     *
     * {\Large C}_{m+n-2}^{m-1} = \binom{m+n-2}{m-1} = \frac{(m+n-2)(m+n-3)\cdots n}{(m-1)!} = \frac{(m+n-2)!}{(m-1)!(n-1)!}
     * C
     * m+n−2
     * m−1
     * ​
     *  =(
     * m−1
     * m+n−2
     * ​
     *  )=
     * (m−1)!
     * (m+n−2)(m+n−3)⋯n
     * ​
     *  =
     * (m−1)!(n−1)!
     * (m+n−2)!
     * ​
     *
     *
     * 因此我们直接计算出这个组合数即可。计算的方法有很多种：
     *
     * 如果使用的语言有组合数计算的 API，我们可以调用 API 计算；
     *
     * 如果没有相应的 API，我们可以使用 \frac{(m+n-2)(m+n-3)\cdots n}{(m-1)!}
     * (m−1)!
     * (m+n−2)(m+n−3)⋯n
     * ​
     *   进行计算。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param m
     * @param n
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38 MB
     * , 在所有 Java 提交中击败了
     * 81.26%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public int uniquePaths1(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

}
