package com.meng.thinking.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MaxMoves2684 {
    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了16.67% 的Java用户
     * 	内存消耗:53.7 MB,击败了90.58% 的Java用户
     * @param grid
     * @return
     */
    public int maxMovesMy(int[][] grid) {
        int m = grid.length, n =  grid[0].length;
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < m ; i++){
            set.add(i);
        }
        int res = 0;
        for(int i = 1 ; i < n ; i++){
            Set<Integer> temp = new HashSet<>();
            for(int j : set){
                for(int index = -1 ; index < 2 ; index++){
                    if (j + index >= 0 && j + index < m
                            && grid[j][i - 1] < grid[j + index][i]){
                        temp.add(j + index);
                    }
                }
            }
            if (temp.isEmpty()){
                break;
            }
            res++;
            set.clear();
            set.addAll(temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了50.00% 的Java用户
     * 	内存消耗:56.1 MB,击败了5.80% 的Java用户
     * @param grid
     * @return
     */
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<Integer> q = new HashSet<>();
        for (int i = 0; i < m; i++) {
            q.add(i);
        }
        for (int j = 1; j < n; j++) {
            Set<Integer> q2 = new HashSet<>();
            for (int i : q) {
                for (int i2 = i - 1; i2 <= i + 1; i2++) {
                    if (0 <= i2 && i2 < m && grid[i][j - 1] < grid[i2][j]) {
                        q2.add(i2);
                    }
                }
            }
            q = q2;
            if (q.isEmpty()) {
                return j - 1;
            }
        }
        return n - 1;
    }

}
