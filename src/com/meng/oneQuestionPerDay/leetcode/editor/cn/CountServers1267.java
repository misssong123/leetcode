package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CountServers1267 {
    /**
     * 时间
     * 详情
     * 5ms
     * 击败 32.31%使用 Java 的用户
     * 内存
     * 详情
     * 46.47MB
     * 击败 87.69%使用 Java 的用户
     * @param grid
     * @return
     */
    public int countServers(int[][] grid) {
        int x = grid.length,y = grid[0].length;
        Map<Integer,Integer> xCache = new HashMap<>();
        Map<Integer,Integer> yCache = new HashMap<>();
        for(int i = 0 ; i < x ; i++){
            for(int j = 0 ; j < y ; j++){
                if (grid[i][j] == 1){
                    xCache.put(i,xCache.getOrDefault(i,0)+1);
                    yCache.put(j,yCache.getOrDefault(j,0)+1);
                }
            }
        }
        int ans = 0;
        for(int i = 0 ; i < x ; i++){
            for(int j = 0 ; j < y ; j++){
                if (grid[i][j] == 1&&(xCache.get(i) > 1 || yCache.get(j)>1)){
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 时间
     * 详情
     * 5ms
     * 击败 32.31%使用 Java 的用户
     * 内存
     * 详情
     * 46.43MB
     * 击败 88.46%使用 Java 的用户
     * @param grid
     * @return
     */
    public int countServers1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Map<Integer, Integer> rows = new HashMap<Integer, Integer>();
        Map<Integer, Integer> cols = new HashMap<Integer, Integer>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    rows.put(i, rows.getOrDefault(i, 0) + 1);
                    cols.put(j, cols.getOrDefault(j, 0) + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && (rows.get(i) > 1 || cols.get(j) > 1)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

}

