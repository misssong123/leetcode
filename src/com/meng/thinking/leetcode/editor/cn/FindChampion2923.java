package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class FindChampion2923 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了75.91% 的Java用户
     * @param grid
     * @return
     */
    public int findChampionMy(int[][] grid) {
        int n = grid.length;
        for(int i = 0 ; i < n ; i++){
            boolean flag = true;
            for(int j = 0 ; j < n ; j++){
                if (grid[j][i] == 1){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return i;
            }
        }

        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了33.58% 的Java用户
     * @param grid
     * @return
     */
    public int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int[] line = grid[i];
            int sum = 0;
            for (int num : line) {
                sum += num;
            }
            if (sum == n - 1) {
                return i;
            }
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
