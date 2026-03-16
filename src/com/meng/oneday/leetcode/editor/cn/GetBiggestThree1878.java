package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GetBiggestThree1878 {
    /**
     * 解答成功:
     * 	执行耗时:134 ms,击败了24.14% 的Java用户
     * 	内存消耗:46.6 MB,击败了82.76% 的Java用户
     * @param grid
     * @return
     */
    public int[] getBiggestThree1878(int[][] grid) {
        int m = grid.length , n = grid[0].length;
        //对角线
        int[][] diagonal = new int[m][n];
        for(int i = m - 1 ; i >= 0 ; i--){
            int x = i , y = 0;
            int pre = 0;
            while(x < m && y < n){
                diagonal[x][y] = grid[x][y] + pre;
                pre = diagonal[x][y];
                x++;
                y++;
            }
        }
        for(int i = 1 ; i < n ; i++){
            int x = 0 , y = i;
            int pre = 0;
            while(x < m && y < n){
                diagonal[x][y] = grid[x][y] + pre;
                pre = diagonal[x][y];
                x++;
                y++;
            }
        }
        //斜对角线
        int[][] antiDiagonal = new int[m][n];
        for(int i = 0 ; i < n ; i++){
            int x = 0 , y = i;
            int pre = 0;
            while(x < m && y >= 0){
                antiDiagonal[x][y] = grid[x][y] + pre;
                pre = antiDiagonal[x][y];
                x++;
                y--;
            }
        }
        for(int i = 1 ; i < m ; i++){
            int x = i , y = n - 1;
            int pre = 0;
            while(x < m && y >= 0){
                antiDiagonal[x][y] = grid[x][y] + pre;
                pre = antiDiagonal[x][y];
                x++;
                y--;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                //变长为1
                list.add(grid[i][j]);
                int width = 1;
                while (j - width >= 0 && j + width < n && i + 2* width < m){
                    int point2X = i + width , point2Y = j - width;
                    int point3X = i + 2 * width ;
                    int point4X = i + width , point4Y = j + width;
                    int sum = diagonal[point3X][j] - diagonal[point2X][point2Y] +
                            diagonal[point4X][point4Y] - diagonal[i][j]
                            + antiDiagonal[point2X][point2Y]- antiDiagonal[i][j]
                            + antiDiagonal[point3X][j] - antiDiagonal[point4X][point4Y]+
                            grid[i][j] -grid[point3X][j];
                    list.add(sum);
                    width++;
                }
            }
        }
        list.sort((a, b) -> b - a);
        List<Integer> res = new ArrayList<>();
        for(int val : list){
            if (res.isEmpty() || val != res.get(res.size() - 1)){
                res.add(val);
            }
            if (res.size() == 3){
                break;
            }
        }
        //list 转换成 数组
        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
    private int x, y, z; // 最大，次大，第三大

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了96.55% 的Java用户
     * 	内存消耗:46.5 MB,击败了86.21% 的Java用户
     * @param grid
     * @return
     */
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] diagSum = new int[m + 1][n + 1]; // ↘ 前缀和
        int[][] antiSum = new int[m + 1][n + 1]; // ↙ 前缀和
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                diagSum[i + 1][j + 1] = diagSum[i][j] + v;
                antiSum[i + 1][j] = antiSum[i][j + 1] + v;
            }
        }

        // 枚举菱形正中心 (i,j)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(grid[i][j]); // 一个数也算菱形
                // 枚举菱形顶点到正中心的距离 k，注意菱形顶点不能出界
                // i-k >= 0 且 i+k <= m-1，所以 k <= min(i, m-1-i)，对于 j 同理
                int mx = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));
                for (int k = 1; k <= mx; k++) {
                    int a = queryDiagonal(diagSum, i - k, j, k); // 菱形右上的边
                    int b = queryDiagonal(diagSum, i, j - k, k); // 菱形左下的边
                    int c = queryAntiDiagonal(antiSum, i - k + 1, j - 1, k - 1); // 菱形左上的边
                    int d = queryAntiDiagonal(antiSum, i, j + k, k + 1); // 菱形右下的边
                    update(a + b + c + d);
                }
            }
        }

        int[] ans = new int[]{x, y, z};
        int len = 3;
        while (ans[len - 1] == 0) { // 不同的和少于三个
            len--;
        }
        return Arrays.copyOf(ans, len);
    }

    // 从 (x,y) 开始，向 ↘，连续 k 个数的和
    private int queryDiagonal(int[][] diagSum, int x, int y, int k) {
        return diagSum[x + k][y + k] - diagSum[x][y];
    }

    // 从 (x,y) 开始，向 ↙，连续 k 个数的和
    private int queryAntiDiagonal(int[][] antiSum, int x, int y, int k) {
        return antiSum[x + k][y + 1 - k] - antiSum[x][y + 1];
    }

    private void update(int v) {
        if (v > x) {
            z = y;
            y = x;
            x = v;
        } else if (v < x && v > y) {
            z = y;
            y = v;
        } else if (v < y && v > z) {
            z = v;
        }
    }
}
