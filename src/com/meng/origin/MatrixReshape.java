package com.meng.origin;

/**
 * 566. 重塑矩阵
 *
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 *
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 *
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 *
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 * 示例 1:
 *
 * 输入:
 * nums =
 * [[1,2],
 *  [3,4]]
 * r = 1, c = 4
 * 输出:
 * [[1,2,3,4]]
 * 解释:
 * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
 *
 * 示例 2:
 *
 * 输入:
 * nums =
 * [[1,2],
 *  [3,4]]
 * r = 2, c = 4
 * 输出:
 * [[1,2],
 *  [3,4]]
 * 解释:
 * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 *
 * 注意：
 *
 *     给定矩阵的宽和高范围在 [1, 100]。
 *     给定的 r 和 c 都是正数。
 */
public class MatrixReshape {
    /**
     执行用时：2 ms, 在所有 Java 提交中击败了47.97% 的用户
     内存消耗：39.3 MB, 在所有 Java 提交中击败了92.46% 的用户
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int height = nums.length , len = nums[0].length;
        if (height * len != r*c)
            return nums;
        else {
            int[][] res = new int[r][c];
            for(int i = 0 ; i < height ; i++){
                for(int j = 0 ; j < len ; j++){
                    int cur = i*len + j;
                    int x = cur / c;
                    int y = cur % c;
                    if (x == r)
                        return res;
                    res[x][y] = nums[i][j];
                }
            }
            return res;
        }
    }

    /**
     * 方法一：二维数组的一维表示
     *
     * 思路与算法
     *
     * 对于一个行数为 mmm，列数为 nnn，行列下标都从 000 开始编号的二维数组，我们可以通过下面的方式，将其中的每个元素 (i,j)(i, j)(i,j) 映射到整数域内，并且它们按照行优先的顺序一一对应着 [0,mn)[0, mn)[0,mn) 中的每一个整数。形象化地来说，我们把这个二维数组「排扁」成了一个一维数组。如果读者对机器学习有一定了解，可以知道这就是 flatten\texttt{flatten}flatten 操作。
     *
     * 这样的映射即为：
     *
     * (i,j)→i×n+j(i, j) \to i \times n+j (i,j)→i×n+j
     *
     * 同样地，我们可以将整数 xxx 映射回其在矩阵中的下标，即
     *
     * {i=x / nj=x % n\begin{cases} i = x ~/~ n \\ j = x ~\%~ n \end{cases} {i=x / nj=x % n​
     *
     * 其中 /// 表示整数除法，%\%% 表示取模运算。
     *
     * 那么题目需要我们做的事情相当于：
     *
     *     将二维数组 nums\textit{nums}nums 映射成一个一维数组；
     *
     *     将这个一维数组映射回 rrr 行 ccc 列的二维数组。
     *
     * 我们当然可以直接使用一个一维数组进行过渡，但我们也可以直接从二维数组 nums\textit{nums}nums 得到 rrr 行 ccc 列的重塑矩阵：
     *
     *     设 nums\textit{nums}nums 本身为 mmm 行 nnn 列，如果 mn≠rcmn \neq rcmn​=rc，那么二者包含的元素个数不相同，因此无法进行重塑；
     *
     *     否则，对于 x∈[0,mn)x \in [0, mn)x∈[0,mn)，第 xxx 个元素在 nums\textit{nums}nums 中对应的下标为 (x / n,x % n)(x ~/~ n, x~\%~ n)(x / n,x % n)，而在新的重塑矩阵中对应的下标为 (x / c,x % c)(x ~/~ c, x~\%~ c)(x / c,x % c)。我们直接进行赋值即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reshape-the-matrix/solution/zhong-su-ju-zhen-by-leetcode-solution-gt0g/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param nums
     * @param r
     * @param c
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了22.00% 的用户
     */
    public int[][] matrixReshape1(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = nums[x / n][x % n];
        }
        return ans;
    }

}
