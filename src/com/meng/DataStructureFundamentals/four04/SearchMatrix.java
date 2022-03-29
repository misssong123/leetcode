package com.meng.DataStructureFundamentals.four04;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 */
public class SearchMatrix {
    /**
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 12.83%
     * 的用户
     * 内存消耗：
     * 47 MB
     * , 在所有 Java 提交中击败了
     * 54.71%
     * 的用户
     * 通过测试用例：
     * 129 / 129
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法二：二分查找
     * 思路与算法
     *
     * 由于矩阵 \textit{matrix}matrix 中每一行的元素都是升序排列的，因此我们可以对每一行都使用一次二分查找，判断 \textit{target}target 是否在该行中，从而判断 \textit{target}target 是否出现。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-so-9hcx/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * @param target
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 36.46%
     * 的用户
     * 内存消耗：
     * 46.9 MB
     * , 在所有 Java 提交中击败了
     * 62.57%
     * 的用户
     * 通过测试用例：
     * 129 / 129
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     *方法三：Z 字形查找
     * 思路与算法
     *
     * 我们可以从矩阵 \textit{matrix}matrix 的右上角 (0, n-1)(0,n−1) 进行搜索。在每一步的搜索过程中，如果我们位于位置 (x, y)(x,y)，那么我们希望在以 \textit{matrix}matrix 的左下角为左下角、以 (x, y)(x,y) 为右上角的矩阵中进行搜索，即行的范围为 [x, m - 1][x,m−1]，列的范围为 [0, y][0,y]：
     *
     * 如果 \textit{matrix}[x, y] = \textit{target}matrix[x,y]=target，说明搜索完成；
     *
     * 如果 \textit{matrix}[x, y] > \textit{target}matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 yy 列的元素都是严格大于 \textit{target}target 的，因此我们可以将它们全部忽略，即将 yy 减少 11；
     *
     * 如果 \textit{matrix}[x, y] < \textit{target}matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 xx 行的元素都是严格小于 \textit{target}target 的，因此我们可以将它们全部忽略，即将 xx 增加 11。
     *
     * 在搜索的过程中，如果我们超出了矩阵的边界，那么说明矩阵中不存在 \textit{target}target。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-so-9hcx/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * @param target
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 36.46%
     * 的用户
     * 内存消耗：
     * 47.2 MB
     * , 在所有 Java 提交中击败了
     * 37.78%
     * 的用户
     * 通过测试用例：
     * 129 / 129
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }

}
