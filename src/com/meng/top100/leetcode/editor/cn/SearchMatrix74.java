package com.meng.top100.leetcode.editor.cn;

class SearchMatrix74 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了13.53% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix74(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0,right = row * col - 1;
        while (left <= right){
            int mid = (left + right) >> 1;
            int x = mid / col;
            int y = mid % col;
            if (matrix[x][y] == target){
                return true;
            }else if (matrix[x][y] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了5.07% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int x = 0,y = col - 1;
        while (x < row && y >= 0){
            if (matrix[x][y] == target){
                return true;
            }else if (matrix[x][y] > target){
                y--;
            }else {
                x++;
            }
        }
        return false;
    }
}
