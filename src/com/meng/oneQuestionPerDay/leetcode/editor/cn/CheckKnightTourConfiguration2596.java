package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionCheckValidGrid2596 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了81.40% 的Java用户
     * 	内存消耗:42.1 MB,击败了23.26% 的Java用户
     * @param grid
     * @return
     */
    public boolean checkValidGrid1(int[][] grid) {
        int len = grid.length;
        if (grid[0][0] != 0){
            return false;
        }
        Map<Integer,int[]> cache = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            for(int j = 0 ; j < len ; j++){
                cache.put(grid[i][j],new int[]{i,j});
            }
        }
        int[] pre = {0,0};
        for(int i = 1 ; i < len * len ; i++){
            int[] index = cache.get(i);
            int x = Math.abs(index[0] - pre[0]);
            int y = Math.abs(index[1] - pre[1]);
            if (x+y != 3 || (x != 1 && x != 2)){
                return false;
            }
            pre = index;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了81.40% 的Java用户
     * 	内存消耗:41.7 MB,击败了96.12% 的Java用户
     * @param grid
     * @return
     */
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] indices = new int[n * n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                indices[grid[i][j]][0] = i;
                indices[grid[i][j]][1] = j;
            }
        }
        for (int i = 1; i < n * n; i++) {
            int dx = Math.abs(indices[i][0] - indices[i - 1][0]);
            int dy = Math.abs(indices[i][1] - indices[i - 1][1]);
            if (dx * dy != 2) {
                return false;
            }
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
