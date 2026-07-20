package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ShiftGrid1260 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了46.49% 的Java用户
     * 	内存消耗:46.7 MB,击败了6.14% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid1260(int[][] grid, int k) {
        int m = grid.length , n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        for (int[] ints : grid) {
            List<Integer> list = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                list.add(ints[j]);
            }
            res.add(list);
        }
        k = k % (m * n);
        for(int i = 0 ; i < m * n ; i++){
            int index = (i + k) % (m * n);
            int row = index / n;
            int col = index % n;
            res.get(row).set(col,grid[i / n][i % n]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了17.54% 的Java用户
     * 	内存消耗:46.3 MB,击败了81.58% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGridOther1(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }

        int size = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int p = (i * n + j + k) % size;
                ans.get(p / n).set(p % n, grid[i][j]);
            }
        }
        return ans;
    }
    // 注意：由于返回值不是 int[][]，无法原地

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了85.09% 的Java用户
     * 	内存消耗:46.7 MB,击败了5.26% 的Java用户
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // 189. 轮转数组
        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;
        k %= size; // 轮转 k 次等同于轮转 k % size 次
        reverse(grid, n, 0, size - 1);
        reverse(grid, n, 0, k - 1);
        reverse(grid, n, k, size - 1);

        List<List<Integer>> ans = new ArrayList<>(m);
        for (int[] row : grid) {
            List<Integer> r = new ArrayList<>(n);
            for (int x : row) {
                r.add(x);
            }
            ans.add(r);
        }
        return ans;
    }

    private void reverse(int[][] grid, int n, int l, int r) {
        while (l < r) {
            int x1 = l / n;
            int y1 = l % n;
            int x2 = r / n;
            int y2 = r % n;
            int tmp = grid[x1][y1];
            grid[x1][y1] = grid[x2][y2];
            grid[x2][y2] = tmp;
            l++;
            r--;
        }
    }


}
