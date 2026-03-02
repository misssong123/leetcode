package com.meng.oneday.leetcode.editor.cn;


class MinSwaps1536 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了57.89% 的Java用户
     * 	内存消耗:48.8 MB,击败了68.42% 的Java用户
     * @param grid
     * @return
     */
    public int minSwaps1536(int[][] grid) {
        int n = grid.length;
        int[] cnts = new int[n];
        for(int i = 0 ; i < n ; i++){;
            int cnt = 0;
            for(int j = n - 1 ; j >= 0 ; j--){
                if (grid[i][j] == 1){
                    break;
                }
                cnt++;
            }
            cnts[i] = cnt;
        }
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = n - 1 ; i > 0 ; i--){
            int step = 0;
            for (int j = 0 ; j < n ; j++){
                //未被使用
                if (!visited[j]){
                    if(cnts[j] >= i){
                        ans += step;
                        visited[j] = true;
                        break;
                    }
                    step++;
                }
                //未满足
                if (j == n - 1){
                    return -1;
                }
            }

        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了57.89% 的Java用户
     * 	内存消耗:48.7 MB,击败了89.47% 的Java用户
     * @param grid
     * @return
     */
    public int minSwapsOther(int[][] grid) {
        // 预处理每一行的尾零个数
        int n = grid.length;
        int[] tailZeros = new int[n];
        for (int i = 0; i < n; i++) {
            tailZeros[i] = n;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    tailZeros[i] = n - 1 - j;
                    break;
                }
            }
        }

        int ans = 0;
        next:
        for (int i = 0; i < n - 1; i++) {
            int needZeros = n - 1 - i;
            for (int j = i; j < n; j++) {
                if (tailZeros[j] >= needZeros) {
                    ans += j - i;
                    // 从 j 换到 i，原来 [i, j-1] 中的数据全体右移一位
                    System.arraycopy(tailZeros, i, tailZeros, i + 1, j - i);
                    continue next;
                }
            }
            return -1;
        }
        return ans;
    }

}
