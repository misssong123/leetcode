package com.meng.level2.day01;

/**
 * 1706. 球会落何处
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 *
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 *
 * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 *
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * 输出：[1,-1,-1,-1,-1]
 * 解释：示例如图：
 * b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
 * b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 * b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 * b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 * b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 * 示例 2：
 *
 * 输入：grid = [[-1]]
 * 输出：[-1]
 * 解释：球被卡在箱子左侧边上。
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * 输出：[0,1,2,3,4,-1]
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 为 1 或 -1
 */
public class FindBall1706 {
    /**
     * 逻辑较为复杂，列举情况不全
     * @param grid
     * @return
     */
    public int[] findBall(int[][] grid) {
        int width = grid[0].length;
        int len = grid.length;
        int [] res = new int[width];
        if (width == 1){
            res[0] = -1;
            return res;
        }
        for(int i = 0 ; i < width ; i++){
            if ((i == 0 && grid[0][0] == -1) ||(i== len - 1 &&grid[0][i]==1)){
                res[i] = -1;
                continue;
            }
            res[i] = getPath(grid,0,i,0,width,len) ? 1 : -1;
        }
        return res;
    }
    private boolean getPath(int[][] grid,int x , int y , int depth,int length,int width){
        if (y == length){
            return true;
        }
        //越界出错
        if (x < 0 || x > width || y < 0){
            return false;
        }
        //碰壁出错
        if (x==0 && grid[y][x] ==-1){
            return false;
        }
        if (x==width-1 && grid[y][x] ==1){
            return false;
        }
        return false;
    }

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 70.35%
     * 的用户
     * 内存消耗：
     * 42.6 MB
     * , 在所有 Java 提交中击败了
     * 69.32%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     * @param grid
     * @return
     */
    public int[] findBall1(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            int col = j;  // 球的初始列
            for (int[] row : grid) {
                int dir = row[col];
                col += dir;  // 移动球
                if (col < 0 || col == n || row[col] != dir) {  // 到达侧边或 V 形
                    col = -1;
                    break;
                }
            }
            ans[j] = col;  // col >= 0 为成功到达底部
        }
        return ans;
    }



    /**
     * 模拟方法
     * @param grid
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 43 MB
     * , 在所有 Java 提交中击败了
     * 13.91%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    int M, N;
    public int[] findBall2(int[][] grid) {
        M = grid.length;
        N = grid[0].length;
        int[] res = new int[N];
        for (int col = 0; col < N; ++col) {
            res[col] = dfs(grid, 0, col);
        }
        return res;
    }

    public int dfs(int[][] grid, int i, int j) {
        // 第 1 种情况：到达最右端，卡住
        if (j == N - 1 && grid[i][j] == 1)
            return -1;
        // 第 2 种情况：到达最左端，卡住
        if (j == 0 && grid[i][j] == -1)
            return -1;
        // 第 3、4 种情况：形成 V 型，无法下落
        if (grid[i][j] != grid[i][j + grid[i][j]])
            return -1;
        // 第 6 种情况：到达最后一行，需要继续下落
        if (i == M - 1)
            return j + grid[i][j];
        // 第 5 种情况：未到达最后一行，继续下落
        return dfs(grid, i + 1, j + grid[i][j]);
    }

}
