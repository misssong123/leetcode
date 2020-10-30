package com.meng;

/**
 * 463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *
 *
 * 示例 :
 *
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * 输出: 16
 *
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IslandPerimeter {
    /**
     * 思路：用陆地数量*4减去重复边数*2
     * 重复边数只需要考虑其右侧和下侧是否为陆地即可
     * @param grid
     * @return
    执行用时：7 ms, 在所有 Java 提交中击败了97.52% 的用户
    内存消耗：39.5 MB, 在所有 Java 提交中击败了78.15% 的用户
     */
    public int islandPerimeter(int[][] grid) {
        int count = 0 , num = 0;
        int columns = grid.length,rows = grid[0].length;
        for(int i = 0 ; i < columns ; i++){
            for (int j = 0 ; j < rows ; j++){
                if (grid[i][j]==1){
                    num++;
                    if (j+1<rows && grid[i][j+1]==1)
                        count++;
                    if (i+1<columns && grid[i+1][j]==1)
                        count++;
                }
            }
        }
        return num*4 - 2*count;
    }
    /**
     * 官方解法1
     * 方法一：迭代
     *
     * 思路与算法
     *
     * 对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。 因此，我们可以遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 111）加入答案 ans\textit{ans}ans 中即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-de-zhou-chang-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：9 ms, 在所有 Java 提交中击败了56.99% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了64.33% 的用户
     */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter1(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
    /**
     * 官方解法2
     * 方法二：深度优先搜索
     *
     * 思路与算法
     *
     * 我们也可以将方法一改成深度优先搜索遍历的方式，此时遍历的方式可扩展至统计多个岛屿各自的周长。需要注意的是为了防止陆地格子在深度优先搜索中被重复遍历导致死循环，我们需要将遍历过的陆地格子标记为已经遍历过，下面的代码中我们设定值为 222 的格子为已经遍历过的陆地格子。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-de-zhou-chang-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：13 ms, 在所有 Java 提交中击败了10.95% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了5.05% 的用户
     */
    public int islandPerimeter2(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    ans += dfs2(i, j, grid, n, m);
                }
            }
        }
        return ans;
    }

    public int dfs2(int x, int y, int[][] grid, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for (int i = 0; i < 4; ++i) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs2(tx, ty, grid, n, m);
        }
        return res;
    }
}
