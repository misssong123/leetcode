package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SpiralOrder54 {
    private final static int[][] dirs = {{0,1,0},{1,0,0},{0,-1,0},{-1,0,0}};
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了75.19% 的Java用户
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder54(int[][] matrix) {
        int m = matrix.length , n = matrix[0].length,dir = 0;
        List<Integer> res = new ArrayList<>();
        dirs[0][2] = n -1;
        dirs[1][2] = m -1;
        dirs[2][2] = 0;
        dirs[3][2] = 1;
        int x = 0 , y = 0;
        for(int i = 0 ; i < m * n ; i++){
            res.add(matrix[x][y]);
            int newX = x + dirs[dir][0];
            int newY = y + dirs[dir][1];
            switch (dir){
                case 0:
                    if(newY > dirs[dir][2]){
                        dirs[0][2]--;
                        dir = 1;
                        newX = x + dirs[dir][0];
                        newY = y + dirs[dir][1];
                    }
                    break;
                case 1:
                    if(newX > dirs[dir][2]){
                        dirs[dir][2]--;
                        dir = 2;
                        newX = x + dirs[dir][0];
                        newY = y + dirs[dir][1];
                    }
                    break;
                case 2:
                    if(newY < dirs[dir][2]){
                        dirs[dir][2]++;
                        dir = 3;
                        newX = x + dirs[dir][0];
                        newY = y + dirs[dir][1];
                    }
                    break;
                case 3:
                    if(newX < dirs[dir][2]){
                        dirs[dir][2]++;
                        dir = 0;
                        newX = x + dirs[dir][0];
                        newY = y + dirs[dir][1];
                    }
                    break;
            }
            x = newX;
            y = newY;
        }
        return res;
    }
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右下左上

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了72.31% 的Java用户
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>(m * n); // 预分配空间
        int i = 0;
        int j = 0;
        int di = 0;
        for (int k = 0; k < m * n; k++) { // 一共走 mn 步
            ans.add(matrix[i][j]);
            matrix[i][j] = Integer.MAX_VALUE; // 标记，表示已经访问过（已经加入答案）
            int x = i + DIRS[di][0];
            int y = j + DIRS[di][1]; // 下一步的位置
            // 如果 (x, y) 出界或者已经访问过
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                di = (di + 1) % 4; // 右转 90°
            }
            i += DIRS[di][0];
            j += DIRS[di][1]; // 走一步
        }
        return ans;
    }
}
