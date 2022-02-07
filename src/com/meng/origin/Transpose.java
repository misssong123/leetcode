package com.meng.origin;

/**
 * 867. 转置矩阵
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 *
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3],[4,5,6]]
 * 输出：[[1,4],[2,5],[3,6]]
 *
 *
 *
 * 提示：
 *
 *     m == matrix.length
 *     n == matrix[i].length
 *     1 <= m, n <= 1000
 *     1 <= m * n <= 105
 *     -109 <= matrix[i][j] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/transpose-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Transpose {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了66.00% 的用户
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int height = matrix.length , len = matrix[0].length;
        int [][] result = new int[len][height];
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < len ; j++){
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }
    /**
     * 方法一：模拟
     *
     * 如果矩阵 matrix\textit{matrix}matrix 为 mmm 行 nnn 列，则转置后的矩阵 matrixT\textit{matrix}^\text{T}matrixT 为 nnn 行 mmm 列，且对任意 0≤i<m0 \le i<m0≤i<m 和 0≤j<n0 \le j<n0≤j<n，都有 matrixT[j][i]=matrix[i][j]\textit{matrix}^\text{T}[j][i]=\textit{matrix}[i][j]matrixT[j][i]=matrix[i][j]。
     *
     * 创建一个 nnn 行 mmm 列的新矩阵，根据转置的规则对新矩阵中的每个元素赋值，则新矩阵为转置后的矩阵。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/transpose-matrix/solution/zhuan-zhi-ju-zhen-by-leetcode-solution-85s2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了24.32% 的用户
     */
    public int[][] transpose1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] transposed = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
}
