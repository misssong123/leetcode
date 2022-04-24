package com.meng.dynamicprogramming.fourteen14;

/**
 * 1314. 矩阵区域和
 * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k 且
 * (r, c) 在矩阵内。
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[12,21,16],[27,45,33],[24,39,28]]
 * 示例 2：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
 * 输出：[[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, k <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {
    /**
     * 执行用时：
     * 83 ms
     * , 在所有 Java 提交中击败了
     * 15.10%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 74.13%
     * 的用户
     * 通过测试用例：
     * @param mat
     * @param k
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                res[i][j] = calculation(i-k,i+k,j-k,j+k,m,n,mat);
            }
        }
        return res;
    }

    private int calculation(int leftI, int rightI, int leftJ, int rightJ,int m , int n , int[][] mat) {
        int sum = 0;
        leftI = Math.max(leftI,0);
        rightI = Math.min(m-1,rightI);
        leftJ = Math.max(leftJ,0);
        rightJ = Math.min(n-1,rightJ);
        for(int i = leftI ; i <= rightI; i++){
            for(int j = leftJ ; j <= rightJ ; j++){
                sum+=mat[i][j];
            }
        }
        return sum;
    }

    /**
     * 方法一：二维前缀和
     * 我们用数组 P 表示数组 mat 的二维前缀和，P 的维数为 (m + 1) * (n + 1)，其中 P[i][j] 表示数组 mat 中以 (0, 0) 为左上角，(i - 1, j - 1) 为右下角的矩形子数组的元素之和。
     *
     * 题目需要对数组 mat 中的每个位置，计算以 (i - K, j - K) 为左上角，(i + K, j + K) 为右下角的矩形子数组的元素之和，我们可以在前缀和数组的帮助下，通过：
     *
     *
     * sum = P[i + K + 1][j + K + 1] - P[i - K][j + K + 1] - P[i + K + 1][j - K] + P[i - K][j - K]
     * 得到元素之和。注意到 i + K + 1、j + K - 1、i - K 和 j - K 这些下标有可能不在矩阵内，因此对于所有的横坐标，我们需要将其规范在 [0, m] 的区间内；对于所有的纵坐标，我们需要将其规范在 [0, n] 的区间内。具体地：
     *
     * i + K + 1 和 j + K - 1 分别可能超过 m 和 n，因此我们需要对这两个坐标与 m 和 n 取较小值，忽略不在矩阵内的部分；
     *
     * i - K 和 j - K 可能小于 0，因此我们需要对这两个坐标与 0 取较大值，忽略不在矩阵内的部分。
     *
     * 更一般的做法是，我们对所有的横坐标与 m 取较小值，纵坐标与 n 取较小值，再将所有坐标与 0 取较大值，就可以将这些坐标规范在前缀和数组 P 的范围内。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/matrix-block-sum/solution/ju-zhen-qu-yu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param mat
     * @param k
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 95.96%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 82.11%
     * 的用户
     * 通过测试用例：
     * 12 / 12
     */
    public int[][] matrixBlockSum1(int[][] mat, int k) {
        int n=mat.length,m=mat[0].length;
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1]+mat[i-1][j-1]-dp[i-1][j-1];
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                int x1=i-k,y1=j-k,x2=i+k,y2=j+k;
                if(x1<1) x1=1;
                if(y1<1) y1=1;
                if(x2>n) x2=n;
                if(y2>m) y2=m;
                mat[i-1][j-1]=dp[x2][y2]+dp[x1-1][y1-1]-dp[x1-1][y2]-dp[x2][y1-1];
            }
        }
        return mat;
    }
}
