package com.meng.oneQuestionPerDay.leetcode.editor.cn;


class CheckXMatrix2319 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.60%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 32.13%
     * 的用户
     * 通过测试用例：
     * 84 / 84
     * @param grid
     * @return
     */
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ; j++){
                if (i == j || (i+j == n-1)){
                    if (grid[i][j] == 0){
                        return false;
                    }
                }else {
                    if (grid[i][j]!=0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
