package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxIncreaseKeepingSkyline807 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了90.09% 的Java用户
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] maxX = new int[n];
        int[] maxY = new int[n];
        int ans = 0;
        //初始化
        for(int  i = 0 ; i < n ; i++){
            for (int  j = 0 ; j < n ; j++){
                maxX[i] = Math.max(maxX[i],grid[i][j]);
                maxY[j] = Math.max(maxY[j],grid[i][j]);
            }
        }
        //对比
        for(int  i = 0 ; i < n ; i++){
            for (int  j = 0 ; j < n ; j++){
                ans += Math.min(maxX[i],maxY[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
