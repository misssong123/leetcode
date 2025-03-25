package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class DifferenceOfDistinctValues2711 {
    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了34.48% 的Java用户
     * 	内存消耗:44.6 MB,击败了79.31% 的Java用户
     * @param grid
     * @return
     */
    public int[][] differenceOfDistinctValues2711(int[][] grid) {
        int m = grid.length , n = grid[0].length;
        int[][] result = new int[m][n];
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < m; i++){
            for(int j = 0 ; j < n ; j++){
                int x = i , y = j;
                while (x - 1 >=0 && y - 1 >= 0){
                    set.add(grid[x-1][y-1]);
                    x--;
                    y--;
                }
                result[i][j] = set.size();
                set.clear();
                x = i;
                y = j;
                while (x + 1 < m && y + 1 < n){
                    set.add(grid[x+1][y+1]);
                    x++;
                    y++;
                }
                result[i][j] = Math.abs(set.size() - result[i][j]);
                set.clear();
            }
        }
        return result;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了72.41% 的Java用户
     * 	内存消耗:44.6 MB,击败了82.76% 的Java用户
     * @param grid
     * @return
     */
    public int[][] differenceOfDistinctValuesOther(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];
        Set<Integer> set = new HashSet<>();

        // 第一排在右上，最后一排在左下
        // 每排从左上到右下
        // 令 k=i-j+n，那么右上角 k=1，左下角 k=m+n-1
        for (int k = 1; k < m + n; k++) {
            // 核心：计算 j 的最小值和最大值
            int minJ = Math.max(n - k, 0); // i=0 的时候，j=n-k，但不能是负数
            int maxJ = Math.min(m + n - 1 - k, n - 1); // i=m-1 的时候，j=m+n-1-k，但不能超过 n-1

            set.clear();
            for (int j = minJ; j <= maxJ; j++) {
                int i = k + j - n;
                ans[i][j] = set.size(); // topLeft[i][j] == set.size()
                set.add(grid[i][j]);
            }

            set.clear();
            for (int j = maxJ; j >= minJ; j--) {
                int i = k + j - n;
                ans[i][j] = Math.abs(ans[i][j] - set.size()); // bottomRight[i][j] == set.size()
                set.add(grid[i][j]);
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.6 MB,击败了93.10% 的Java用户
     * @param grid
     * @return
     */
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];

        for (int k = 1; k < m + n; k++) {
            int minJ = Math.max(n - k, 0);
            int maxJ = Math.min(m + n - 1 - k, n - 1);

            long set = 0;
            for (int j = minJ; j <= maxJ; j++) {
                int i = k + j - n;
                ans[i][j] = Long.bitCount(set); // 计算 set 中 1 的个数
                set |= 1L << grid[i][j];  // 把 grid[i][j] 加到 set 中
            }

            set = 0;
            for (int j = maxJ; j >= minJ; j--) {
                int i = k + j - n;
                ans[i][j] = Math.abs(ans[i][j] - Long.bitCount(set));
                set |= 1L << grid[i][j];
            }
        }
        return ans;
    }

}
