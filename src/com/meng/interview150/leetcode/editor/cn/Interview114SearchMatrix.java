package com.meng.interview150.leetcode.editor.cn;

class Interview114SearchMatrix {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了89.96% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixMy(int[][] matrix, int target) {
        int x = 0,y = matrix[0].length-1,len = matrix.length;
        while (x<len&&y>=0){
            if(matrix[x][y] ==target){
                return true;
            }if (matrix[x][y]>target){
                y--;
            }else {
                x++;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了84.60% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
