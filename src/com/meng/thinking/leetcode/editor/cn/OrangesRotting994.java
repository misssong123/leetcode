package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class OrangesRotting994 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了29.92% 的Java用户
     * 	内存消耗:41.3 MB,击败了63.90% 的Java用户
     * @param grid
     * @return
     */
    public int orangesRottingMy(int[][] grid) {
        int count = 0;
        int m =  grid.length;
        int n =  grid[0].length;
        List<Integer> rottens = new ArrayList<>();
        boolean[] flags =  new boolean[m * n];
        int ans = 0;
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    rottens.add(i * n + j);
                    flags[i * n + j] = true;
                }else if(grid[i][j] == 1){
                    count++;
                }
            }
        }
        int[][] dx = {{0, 0, 1, -1},{1,-1,0,0}};
        while (!rottens.isEmpty()&&count > 0){
            List<Integer> temp = new ArrayList<>();
            for (Integer index : rottens){
                int x = index / n;
                int y = index % n;
                for(int i = 0 ; i < 4; i++){
                    int newX = x + dx[0][i];
                    int newY = y + dx[1][i];
                    if(newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1 && !flags[newX * n + newY]) {
                        temp.add(newX * n + newY);
                        grid[newX][newY] = 2;
                        count--;
                        flags[newX * n + newY] = true;
                    }
                }
            }
            rottens.clear();
            rottens.addAll(temp);
            ans++;
        }
        return count == 0 ? ans : -1;
    }
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了29.92% 的Java用户
     * 	内存消耗:41.4 MB,击败了55.21% 的Java用户
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        Map<Integer, Integer> depth = new HashMap<Integer, Integer>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }
        for (int[] row: grid) {
            for (int v: row) {
                if (v == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
