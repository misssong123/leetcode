package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class FindMissingAndRepeatedValue2965 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.98% 的Java用户
     * 	内存消耗:44.4 MB,击败了73.74% 的Java用户
     * @param grid
     * @return
     */
    public int[] findMissingAndRepeatedValuesMy(int[][] grid) {
        int  n = grid.length;
        int[] cache = new int[n*n];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                cache[grid[i][j] - 1]++;
            }
        }
        int[] res =  new int[2];
        for (int i = 0; i < n*n; ++i){
            if(cache[i]==0){
                res[1] = i+1;
                continue;
            }
            if (cache[i] == 2){
                res[0] = i+1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.98% 的Java用户
     * 	内存消耗:44.5 MB,击败了35.86% 的Java用户
     * @param grid
     * @return
     */
    public int[] findMissingAndRepeatedValues1(int[][] grid) {
        int n = grid.length;
        int xorAll = 0;
        for (int[] row : grid) {
            for (int x : row) {
                xorAll ^= x;
            }
        }
        xorAll ^= n % 2 > 0 ? 1 : n * n;
        int shift = Integer.numberOfTrailingZeros(xorAll);

        int[] ans = new int[2];
        for (int x = 1; x <= n * n; x++) {
            ans[x >> shift & 1] ^= x;
        }
        for (int[] row : grid) {
            for (int x : row) {
                ans[x >> shift & 1] ^= x;
            }
        }

        for (int[] row : grid) {
            for (int x : row) {
                if (x == ans[0]) {
                    return ans;
                }
            }
        }
        return new int[]{ans[1], ans[0]};
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.98% 的Java用户
     * 	内存消耗:44.4 MB,击败了73.74% 的Java用户
     * @param grid
     * @return
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int m = n * n;
        int d1 = -m * (m + 1) / 2;
        long d2 = (long) -m * (m + 1) * (m * 2 + 1) / 6;
        for (int[] row : grid) {
            for (int x : row) {
                d1 += x;
                d2 += x * x;
            }
        }
        int d = (int) (d2 / d1);
        return new int[]{(d + d1) / 2, (d - d1) / 2};
    }

}
//leetcode submit region end(Prohibit modification and deletion)
