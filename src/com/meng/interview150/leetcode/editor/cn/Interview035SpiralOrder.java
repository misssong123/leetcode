package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview035SpiralOrder {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了55.23% 的Java用户
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrderMy(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length,n=matrix[0].length;
        int left = 0,right = n - 1,top = 0, bottom = m - 1;
        int index = 0;
        int x = 0 , y = 0;
        while (left<= right && top <= bottom){
            res.add(matrix[x][y]);
            if(index == 0){
                if (y == right){
                    index = 1;
                    top++;
                    x++;
                }else {
                    y++;
                }
            }else if (index ==1){
                if (x==bottom){
                    index = 2;
                    right--;
                    y--;
                }else {
                    x++;
                }
            }else if (index == 2){
                if (y == left){
                    index = 3;
                    bottom--;
                    x--;
                }else {
                    y--;
                }
            } else {
                if (x == top){
                    index = 0;
                    left++;
                    y++;
                }else {
                    x--;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了18.54% 的Java用户
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }
}
