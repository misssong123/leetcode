package com.meng.oneday.leetcode.editor.cn;

import java.util.PriorityQueue;

class MaxMatrixSum1975 {
    /**
     * 思路有误
     * @param matrix
     * @return
     */
    public long maxMatrixSumError(int[][] matrix) {
        int m = matrix.length , n = matrix[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if (matrix[i][j] < 0){
                    queue.add(new int[]{matrix[i][j],i * n + j});
                }
            }
        }
        return 0L;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了35.00% 的Java用户
     * 	内存消耗:66.7 MB,击败了40.00% 的Java用户
     * @param matrix
     * @return
     */
    public long maxMatrixSum1975(int[][] matrix) {
        int m = matrix.length , n = matrix[0].length;
        int negCnt = 0;
        long sum = 0L;
        int min = 100000;
        for (int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                if (matrix[i][j] < 0){
                    negCnt++;
                }
                int val = Math.abs(matrix[i][j]);
                sum += val;
                min = Math.min(min,val);
            }
        }
        if (negCnt % 2 != 0){
            sum = sum - 2L * min;
        }
        return sum;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:66.6 MB,击败了60.00% 的Java用户
     * @param matrix
     * @return
     */
    public long maxMatrixSum(int[][] matrix) {
        long total = 0;
        int negCnt = 0;
        int mn = Integer.MAX_VALUE;
        for (int[] row : matrix) {
            for (int x : row) {
                if (x < 0) {
                    negCnt++;
                    x = -x; // 先把负数都变成正数
                }
                mn = Math.min(mn, x);
                total += x;
            }
        }
        if (negCnt % 2 > 0) {
            total -= mn * 2; // 给绝对值最小的数添加负号
        }
        return total;
    }
}
