package com.meng.origin;

/**
 * 766. 托普利茨矩阵
 *
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 *
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 *
 *
 *
 * 提示：
 *
 *     m == matrix.length
 *     n == matrix[i].length
 *     1 <= m, n <= 20
 *     0 <= matrix[i][j] <= 99
 *
 *
 *
 * 进阶：
 *
 *     如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 *     如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 */
public class IsToeplitzMatrix {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了38.98% 的用户
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int weight = matrix.length , len = matrix[0].length;
        if (weight == 1 || len == 1)
            return true;
        int [] temp = new int[len];
        for(int i = 0 ; i < len ; i++){
            temp[i] = matrix[0][i];
        }
        for(int i = 1 ; i < weight ; i++){
            for(int j = 1 ; j < len ; j++){
                if (matrix[i][j] != temp[j-1]){
                    return false;
                }
                temp[j-1] = matrix[i][j-1];
            }
            temp[len-1] = matrix[i][len-1];
        }
        return true;
    }
    /**
     * 方法一：遍历
     *
     * 根据定义，当且仅当矩阵中每个元素都与其左上角相邻的元素（如果存在）相等时，该矩阵为托普利茨矩阵。因此，我们遍历该矩阵，将每一个元素和它左上角的元素相比对即可。。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/toeplitz-matrix/solution/tuo-pu-li-ci-ju-zhen-by-leetcode-solutio-57bb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了16.16% 的用户
     */
    public boolean isToeplitzMatrix1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 按「线」遍历
     * 其他解法
     * 如果稍微增加一点点难度：限制每个格子只能被读取一次呢？
     *
     * 这时候我们也可以按照「线」为单位进行检查。
     *
     * 当一条完整的斜线值都相等，我们再对下一条斜线做检查。
     *
     * 这样对于每个格子，我们都是严格只读取一次（如果整个矩阵是存在磁盘中，且不考虑操作系统的按页读取等机制，那么 IO 成本将下降为原来的一半）。
     *
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/toeplitz-matrix/solution/cong-ci-pan-du-qu-cheng-ben-fen-xi-liang-f20w/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int row = m, col = n;
        while (col-- > 0) {
            for (int i = 0, j = col, val = matrix[i++][j++]; i < m && j < n; i++, j++) {
                if (matrix[i][j] != val) return false;
            }
        }
        while (row-- > 0) {
            for (int i = row, j = 0, val = matrix[i++][j++]; i < m && j < n; i++, j++) {
                if (matrix[i][j] != val) return false;
            }
        }
        return true;
    }
}
