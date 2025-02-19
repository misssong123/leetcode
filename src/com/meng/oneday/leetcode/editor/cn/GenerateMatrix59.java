package com.meng.oneday.leetcode.editor.cn;

class GenerateMatrix59 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了7.69% 的Java用户
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        //坐标
        int x = 0 , y = 0;
        //方向
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;
        //临界值
        int left = 0, right = n - 1, top = 1, bottom = n - 1;
        for (int i = 1; i <= n * n; i++) {
            res[x][y] = i;
            int nextX = x + dirs[dirIndex][0];
            int nextY = y + dirs[dirIndex][1];
            //判断是否到达边界
            if (dirIndex == 0) {
                if(nextY > right) {
                    right--;
                    dirIndex = 1;
                }
            }else if (dirIndex == 1) {
                if(nextX > bottom){
                    bottom--;
                    dirIndex = 2;
                }
            }else if (dirIndex == 2) {
                if (nextY < left) {
                    left++;
                    dirIndex = 3;
                }
            }else {
                if (nextX < top) {
                    top++;
                    dirIndex = 0;
                }
            }
            x += dirs[dirIndex][0];
            y += dirs[dirIndex][1];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了45.94% 的Java用户
     * @param n
     * @return
     */
    public int[][] generateMatrixOther(int n) {
        int num = 1;
        int[][] matrix = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                matrix[top][column] = num;
                num++;
            }
            for (int row = top + 1; row <= bottom; row++) {
                matrix[row][right] = num;
                num++;
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    matrix[bottom][column] = num;
                    num++;
                }
                for (int row = bottom; row > top; row--) {
                    matrix[row][left] = num;
                    num++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return matrix;
    }

}
