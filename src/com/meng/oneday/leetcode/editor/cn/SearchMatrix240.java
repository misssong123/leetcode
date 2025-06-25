package com.meng.oneday.leetcode.editor.cn;

class SearchMatrix240 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了99.99% 的Java用户
     * 	内存消耗:45.3 MB,击败了40.76% 的Java用户
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix240(int[][] matrix, int target) {
        int x = 0 , y = matrix[0].length - 1;
        while(x < matrix.length && y >= 0){
            if(matrix[x][y] == target){
                return true;
            }else if(matrix[x][y] > target){
                y--;
            }else{
                x++;
            }
        }
        return false;
    }
}
