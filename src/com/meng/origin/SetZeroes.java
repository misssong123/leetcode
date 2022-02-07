package com.meng.origin;

import java.util.HashSet;
import java.util.Set;

/**
 * 73. 矩阵置零
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 * 进阶：
 *
 *     一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 *     一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 *     你能想出一个仅使用常量空间的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 *
 * 提示：
 *
 *     m == matrix.length
 *     n == matrix[0].length
 *     1 <= m, n <= 200
 *     -231 <= matrix[i][j] <= 231 - 1
 */
public class SetZeroes {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了40.62% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了63.44% 的用户
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int height = matrix.length;
        int len = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < len ; j++){
                if (matrix[i][j] == 0){
                    columns.add(i);
                    rows.add(j);
                }
            }
        }
        for(int i : columns){
            for(int j = 0 ; j < len ; j++){
                matrix[i][j] = 0;
            }
        }
        for(int i : rows){
            for(int j = 0 ; j<height ; j++){
                matrix[j][i] = 0;
            }
        }
    }
    /**
     * 方法一：使用标记数组
     *
     * 思路和算法
     *
     * 我们可以用两个标记数组分别记录每一行和每一列是否有零出现。
     *
     * 具体地，我们首先遍历该数组一次，如果某个元素为 000，那么就将该元素所在的行和列所对应标记数组的位置置为 true\text{true}true。最后我们再次遍历该数组，用标记数组更新原数组即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法一
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.91% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了69.18% 的用户
     */
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    /**
     * 方法二：使用两个标记变量
     *
     * 思路和算法
     *
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1)O(1)O(1) 的额外空间。但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 000。因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 000。
     *
     * 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.91% 的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了51.92% 的用户
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
    /**
     * 方法三：使用一个标记变量
     *
     * 思路和算法
     *
     * 我们可以对方法二进一步优化，只使用一个标记变量记录第一列是否原本存在 000。这样，第一列的第一个元素即可以标记第一行是否出现 000。但为了防止第一列的第一个元素被提前更新，我们需要从最后一行开始，倒序地处理矩阵元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法3
     * 执行用时：2 ms, 在所有 Java 提交中击败了40.62% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了85.50% 的用户
     */
    public void setZeroes3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }
}
