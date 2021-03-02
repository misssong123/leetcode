package com.meng;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 *
 * Range Sum Query 2D
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 *
 *
 *
 * 示例：
 *
 * 给定 matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 *
 *
 *
 * 提示：
 *
 *     你可以假设矩阵不可变。
 *     会多次调用 sumRegion 方法。
 *     你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumMatrix {
    /**
     * 执行用时：15 ms, 在所有 Java 提交中击败了60.40% 的用户
     * 内存消耗：43.9 MB, 在所有 Java 提交中击败了78.22% 的用户
     */
    int [][] result ;
    public NumMatrix(int[][] matrix) {
        int height = matrix.length;
        if (height > 0){
            int len = matrix[0].length;
            result = new int[height][len+1];
            for(int i = 0; i < height ; i++){
                for(int j = 0 ;j < len ; j++){
                    result[i][j+1] = result[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for(int i = row1 ; i <= row2 ; i++){
            res += result[i][col2+1] - result[i][col1];
        }
        return res;
    }
    /**
     * 方法一虽然利用了前缀和，但是每次检索的时间复杂度是 O(m)O(m)O(m)，仍然没有降到 O(1)O(1)O(1)。为了将每次检索的时间复杂度降到 O(1)O(1)O(1)，需要使用二维前缀和，在初始化的时候计算二维前缀和数组。
     *
     * 假设 mmm 和 nnn 分别是矩阵 matrix\textit{matrix}matrix 的行数和列数。定义当 0≤i<m0 \le i<m0≤i<m 且 0≤j<n0 \le j<n0≤j<n 时，f(i,j)f(i,j)f(i,j) 为矩阵 matrix\textit{matrix}matrix 的以 (i,j)(i,j)(i,j) 为右下角的子矩阵的元素之和：
     *
     * f(i,j)=∑p=0i∑q=0jmatrix[p][q]f(i,j)=\sum\limits_{p=0}^i \sum\limits_{q=0}^j \textit{matrix}[p][q] f(i,j)=p=0∑i​q=0∑j​matrix[p][q]
     *
     * 当 i=0i=0i=0 或 j=0j=0j=0 时，计算 f(i,j)f(i,j)f(i,j) 只需要对矩阵 matrix\textit{matrix}matrix 的最上边的行和最左边的列分别计算前缀和即可。当 iii 和 jjj 都大于 000 时，如何计算 f(i,j)f(i,j)f(i,j) 的值？
     *
     * 当 iii 和 jjj 都大于 000 时，假设计算 f(i,j)f(i,j)f(i,j) 时已经知道了 f(i−1,j)f(i-1,j)f(i−1,j)、f(i,j−1)f(i,j-1)f(i,j−1) 和 f(i−1,j−1)f(i-1,j-1)f(i−1,j−1) 的值。为了计算 f(i,j)f(i,j)f(i,j)，自然而然会想到使用 f(i−1,j)f(i-1,j)f(i−1,j)、f(i,j−1)f(i,j-1)f(i,j−1) 和 matrix[i][j]\textit{matrix}[i][j]matrix[i][j] 的值。
     *
     * 注意到 f(i−1,j)f(i-1,j)f(i−1,j) 和 f(i,j−1)f(i,j-1)f(i,j−1) 这两项涉及到的矩阵 matrix\textit{matrix}matrix 的元素有重合，矩阵 matrix\textit{matrix}matrix 的以 (i−1,j−1)(i-1,j-1)(i−1,j−1) 为右下角的子矩阵都在这两项中出现。因此如果计算 f(i−1,j)+f(i,j−1)+matrix[i][j]f(i-1,j)+f(i,j-1)+\textit{matrix}[i][j]f(i−1,j)+f(i,j−1)+matrix[i][j]，则该结果值比 f(i,j)f(i,j)f(i,j) 多了 f(i−1,j−1)f(i-1,j-1)f(i−1,j−1)，因此 f(i,j)f(i,j)f(i,j) 的计算如下：
     *
     * f(i,j)=f(i−1,j)+f(i,j−1)−f(i−1,j−1)+matrix[i][j]f(i,j)=f(i-1,j)+f(i,j-1)-f(i-1,j-1)+\textit{matrix}[i][j] f(i,j)=f(i−1,j)+f(i,j−1)−f(i−1,j−1)+matrix[i][j]
     *
     * 具体推导如下：
     *
     *  f(i,j)=∑p=0i−1∑q=0j−1matrix[p][q]+∑p=0i−1matrix[p][j]+∑q=0j−1matrix[i][q]+matrix[i][j]=(∑p=0i−1∑q=0j−1matrix[p][q]+∑p=0i−1matrix[p][j])+(∑p=0i−1∑q=0j−1matrix[p][q]+∑q=0j−1matrix[i][q])−∑p=0i−1∑q=0j−1matrix[p][q]+matrix[i][j]=∑p=0i−1∑q=0jmatrix[p][q]+∑p=0i∑q=0j−1matrix[p][q]−∑p=0i−1∑q=0j−1matrix[p][q]+matrix[i][j]=f(i−1,j)+f(i,j−1)−f(i−1,j−1)+matrix[i][j]\begin{aligned} &\quad \ f(i,j) \\ &=\sum\limits_{p=0}^{i-1} \sum\limits_{q=0}^{j-1} \textit{matrix}[p][q]+\sum\limits_{p=0}^{i-1} \textit{matrix}[p][j]+\sum\limits_{q=0}^{j-1} \textit{matrix}[i][q]+\textit{matrix}[i][j] \\ &=\Big(\sum\limits_{p=0}^{i-1} \sum\limits_{q=0}^{j-1} \textit{matrix}[p][q]+\sum\limits_{p=0}^{i-1} \textit{matrix}[p][j]\Big) \\ &\quad+\Big(\sum\limits_{p=0}^{i-1} \sum\limits_{q=0}^{j-1} \textit{matrix}[p][q]+\sum\limits_{q=0}^{j-1} \textit{matrix}[i][q]\Big) \\ &\quad-\sum\limits_{p=0}^{i-1} \sum\limits_{q=0}^{j-1} \textit{matrix}[p][q] \\ &\quad+\textit{matrix}[i][j] \\ &=\sum\limits_{p=0}^{i-1} \sum\limits_{q=0}^j \textit{matrix}[p][q]+\sum\limits_{p=0}^i \sum\limits_{q=0}^{j-1} \textit{matrix}[p][q]-\sum\limits_{p=0}^{i-1} \sum\limits_{q=0}^{j-1} \textit{matrix}[p][q]+\textit{matrix}[i][j] \\ &=f(i-1,j)+f(i,j-1)-f(i-1,j-1)+\textit{matrix}[i][j] \end{aligned} ​ f(i,j)=p=0∑i−1​q=0∑j−1​matrix[p][q]+p=0∑i−1​matrix[p][j]+q=0∑j−1​matrix[i][q]+matrix[i][j]=(p=0∑i−1​q=0∑j−1​matrix[p][q]+p=0∑i−1​matrix[p][j])+(p=0∑i−1​q=0∑j−1​matrix[p][q]+q=0∑j−1​matrix[i][q])−p=0∑i−1​q=0∑j−1​matrix[p][q]+matrix[i][j]=p=0∑i−1​q=0∑j​matrix[p][q]+p=0∑i​q=0∑j−1​matrix[p][q]−p=0∑i−1​q=0∑j−1​matrix[p][q]+matrix[i][j]=f(i−1,j)+f(i,j−1)−f(i−1,j−1)+matrix[i][j]​
     *
     * 因此在初始化的时候，即可对所有 0≤i<m0 \le i<m0≤i<m 和 0≤j<n0 \le j<n0≤j<n 计算得到 f(i,j)f(i,j)f(i,j) 的值。
     *
     * fig1
     *
     * 检索时，应利用预处理得到的 fff 的值。当 row1=0\textit{row}_1=0row1​=0 且 col1=0\textit{col}_1=0col1​=0 时，sumRegion(row1,col1,row2,col2)=f(row2,col2)\text{sumRegion}(\textit{row}_1,\textit{col}_1,\textit{row}_2,\textit{col}_2)=f(\textit{row}_2,\textit{col}_2)sumRegion(row1​,col1​,row2​,col2​)=f(row2​,col2​)。
     *
     * 当 row1≤row2\textit{row}_1 \le \textit{row}_2row1​≤row2​ 且 col1≤col2\textit{col}_1 \le \textit{col}_2col1​≤col2​ 时，sumRegion(row1,col1,row2,col2)\text{sumRegion}(\textit{row}_1,\textit{col}_1,\textit{row}_2,\textit{col}_2)sumRegion(row1​,col1​,row2​,col2​) 可以写成如下形式：
     *
     *  sumRegion(row1,col1,row2,col2)=sumRegion(0,0,row2,col2)−sumRegion(0,col1,row1−1,col2)−sumRegion(row1,0,row2,col1−1)−sumRegion(0,0,row1−1,col1−1)=sumRegion(0,0,row2,col2)−(sumRegion(0,col1,row1−1,col2)+sumRegion(0,0,row1−1,col1−1))−(sumRegion(row1,0,row2,col1−1)+sumRegion(0,0,row1−1,col1−1))−sumRegion(0,0,row1−1,col1−1)+2×sumRegion(row1,0,row2,col1−1)=sumRegion(0,0,row2,col2)−sumRegion(0,0,row1−1,col2)−sumRegion(0,0,row2,col1−1)+sumRegion(0,0,row1−1,col1−1)=f(row2,col2)−f(row1−1,col2)−f(row2,col1−1)+f(row1−1,col1−1)\begin{aligned} &\quad \ \text{sumRegion}(\textit{row}_1,\textit{col}_1,\textit{row}_2,\textit{col}_2) \\ &=\text{sumRegion}(0,0,\textit{row}_2,\textit{col}_2) \\ &\quad-\text{sumRegion}(0,\textit{col}_1,\textit{row}_1-1,\textit{col}_2) \\ &\quad-\text{sumRegion}(\textit{row}_1,0,\textit{row}_2,\textit{col}_1-1) \\ &\quad-\text{sumRegion}(0,0,\textit{row}_1-1,\textit{col}_1-1) \\ &=\text{sumRegion}(0,0,\textit{row}_2,\textit{col}_2) \\ &\quad-(\text{sumRegion}(0,\textit{col}_1,\textit{row}_1-1,\textit{col}_2)+\text{sumRegion}(0,0,\textit{row}_1-1,\textit{col}_1-1)) \\ &\quad-(\text{sumRegion}(\textit{row}_1,0,\textit{row}_2,\textit{col}_1-1)+\text{sumRegion}(0,0,\textit{row}_1-1,\textit{col}_1-1)) \\ &\quad-\text{sumRegion}(0,0,\textit{row}_1-1,\textit{col}_1-1) \\ &\quad+2 \times \text{sumRegion}(\textit{row}_1,0,\textit{row}_2,\textit{col}_1-1) \\ &=\text{sumRegion}(0,0,\textit{row}_2,\textit{col}_2) \\ &\quad-\text{sumRegion}(0,0,\textit{row}_1-1,\textit{col}_2) \\ &\quad-\text{sumRegion}(0,0,\textit{row}_2,\textit{col}_1-1) \\ &\quad+\text{sumRegion}(0,0,\textit{row}_1-1,\textit{col}_1-1) \\ &=f(\textit{row}_2,\textit{col}_2)-f(\textit{row}_1-1,\textit{col}_2)-f(\textit{row}_2,\textit{col}_1-1)+f(\textit{row}_1-1,\textit{col}_1-1) \end{aligned} ​ sumRegion(row1​,col1​,row2​,col2​)=sumRegion(0,0,row2​,col2​)−sumRegion(0,col1​,row1​−1,col2​)−sumRegion(row1​,0,row2​,col1​−1)−sumRegion(0,0,row1​−1,col1​−1)=sumRegion(0,0,row2​,col2​)−(sumRegion(0,col1​,row1​−1,col2​)+sumRegion(0,0,row1​−1,col1​−1))−(sumRegion(row1​,0,row2​,col1​−1)+sumRegion(0,0,row1​−1,col1​−1))−sumRegion(0,0,row1​−1,col1​−1)+2×sumRegion(row1​,0,row2​,col1​−1)=sumRegion(0,0,row2​,col2​)−sumRegion(0,0,row1​−1,col2​)−sumRegion(0,0,row2​,col1​−1)+sumRegion(0,0,row1​−1,col1​−1)=f(row2​,col2​)−f(row1​−1,col2​)−f(row2​,col1​−1)+f(row1​−1,col1​−1)​
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable/solution/er-wei-qu-yu-he-jian-suo-ju-zhen-bu-ke-b-2z5n/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：15 ms, 在所有 Java 提交中击败了60.40% 的用户
     * 内存消耗：44.2 MB, 在所有 Java 提交中击败了27.14% 的用户
     */
    class NumMatrix1 {
        int[][] sums;

        public NumMatrix1(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
        }
    }

}
