package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class SortMatrix3446 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了67.61% 的Java用户
     * 	内存消耗:44.6 MB,击败了61.97% 的Java用户
     * @param grid
     * @return
     */
    public int[][] sortMatrix3446(int[][] grid) {
        List<Integer> temp =  new ArrayList<>();
        int m = grid.length ,n = grid[0].length;
        //右上角
        for(int i = n - 2 ; i > 0 ; i--){
            int x = 0 , y = i;
            while (x < m && y < n){
                temp.add(grid[x][y]);
                x++;
                y++;
            }
            Collections.sort(temp);
            x = 0;
            y = i;
            int index = 0;
            while (x < m && y < n){
                grid[x][y] = temp.get(index++);
                x++;
                y++;
            }
            temp.clear();
        }
        //左下角
        for(int i = 0 ; i < m ; i++){
            int x = i , y = 0;
            while (x < m && y < n){
                temp.add(grid[x][y]);
                x++;
                y++;
            }
            temp.sort((a, b) -> b - a);
            x = i;
            y = 0;
            int index = 0;
            while (x < m && y < n){
                grid[x][y] = temp.get(index++);
                x++;
                y++;
            }
            temp.clear();
        }
        return grid;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了56.34% 的Java用户
     * 	内存消耗:44.4 MB,击败了100.00% 的Java用户
     * @param grid
     * @return
     */
    public int[][] sortMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 第一排在右上，最后一排在左下
        // 每排从左上到右下
        // 令 k=i-j+n，那么右上角 k=1，左下角 k=m+n-1
        for (int k = 1; k < m + n; k++) {
            // 核心：计算 j 的最小值和最大值
            int minJ = Math.max(n - k, 0); // i=0 的时候，j=n-k，但不能是负数
            int maxJ = Math.min(m + n - 1 - k, n - 1); // i=m-1 的时候，j=m+n-1-k，但不能超过 n-1
            List<Integer> a = new ArrayList<>(maxJ - minJ + 1); // 预分配空间
            for (int j = minJ; j <= maxJ; j++) {
                a.add(grid[k + j - n][j]); // 根据 k 的定义得 i=k+j-n
            }
            a.sort(minJ > 0 ? null : Comparator.reverseOrder());
            for (int j = minJ; j <= maxJ; j++) {
                grid[k + j - n][j] = a.get(j - minJ);
            }
        }
        return grid;
    }

}
