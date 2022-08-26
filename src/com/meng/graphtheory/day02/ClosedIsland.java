package com.meng.graphtheory.day02;

import java.util.ArrayList;
import java.util.List;

/**
 * 1254. 统计封闭岛屿的数目(中等)
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 *
 * 请返回 封闭岛屿 的数目。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1,1,1],
 *              [1,0,0,0,0,0,1],
 *              [1,0,1,1,1,0,1],
 *              [1,0,1,0,1,0,1],
 *              [1,0,1,1,1,0,1],
 *              [1,0,0,0,0,0,1],
 *              [1,1,1,1,1,1,1]]
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class ClosedIsland {
    int[] X = {-1,0,1,0}, Y = {0,1,0,-1};
    List<int[]> temp = new ArrayList<>();

    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 9.46%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 92.30%
     * 的用户
     * 通过测试用例：
     * 47 / 47
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int sum = 0;
        int lines = grid.length;
        int rows = grid[0].length;
        for(int i = 0 ; i < lines ; i++){
           if (grid[i][0]==0){
               dfs(i,0,grid);
           }
            if (grid[i][rows-1]==0){
                dfs(i,rows-1,grid);
            }
        }
        for(int i = 0 ; i < rows ; i++){
            if (grid[0][i]==0){
                dfs(0,i,grid);
            }
            if (grid[lines-1][i]==0){
                dfs(lines-1,i,grid);
            }
        }
        for(int i = 1 ; i < lines - 1 ; i++){
            for(int j = 1 ; j < rows - 1 ; j++){
                if (grid[i][j] == 0){
                    dfs(i,j,grid);
                    sum++;
                }
            }
        }
        return sum;
    }
    private void dfs(int x , int y , int[][] grid){
        temp.add(new int[]{x,y});
        while (temp.size() > 0){
            int size = temp.size();
            for(int k = 0 ; k < size ; k++){
                int[] point = temp.remove(0);
                if (grid[point[0]][point[1]] == 1){
                    continue;
                }
                grid[point[0]][point[1]] = 1;
                for(int m = 0 ; m < 4 ; m++){
                    int newX = point[0] + X[m];
                    int newY = point[1] + Y[m];
                    if(newX>=0 && newX < grid.length && newY>=0 && newY < grid[0].length && grid[newX][newY]==0){
                        temp.add(new int[]{newX,newY});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
    /**
     * 一个常规dfs图论题，在遍历的同时设定boolean变量f ，对于每个dfs分支返回值，对f做一次 "f = f & dfs()" ，即 与 运算， 因此只要有一个分支结果判断为false，最终返回值就是false，代表这个岛屿不封闭。
     *
     * 不可以在找到不封闭条件时直接return出来，因为可能没有dfs完全，而图论的dfs一般是一边dfs一边标记，因此不完全的dfs是会影响后续结果的，我们需要让它递归直到完全结束，而这个代码中我们一直要维护的其实就是f这个布尔变量，它从始至终都在做与运算，所以不必担心最后结果会出问题，因为只要遇到不封闭的条件就注定了最终返回的是false（就像无数个1和一个零一起做相乘运算）
     *
     * 作者：cra2y-hypatiavuz
     * 链接：https://leetcode.cn/problems/number-of-closed-islands/solution/by-cra2y-hypatiavuz-dgvn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 44.22%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 36.71%
     * 的用户
     * 通过测试用例：
     * 47 / 47
     */
    private int m,n;
    private int ans=0;  //保存 最终答案
    private int[] dx = {-1,0,1,0}, dy = {0,1,0,-1}; //偏移量

    public int closedIsland1(int[][] grid) {
        m = grid.length;
        n = grid[0].length;               //保存地图边长
        if(m <= 2 || n <= 2) return 0;    //特判，地图有一边长不大于2都不可能有封闭岛屿
        for(int i= 1; i < m-1;i++)        //由于我们要统计的是封闭岛屿，所以我们可以只遍历不含边界的地块，进一步提升效率
            for(int j = 1; j< n-1;j++ )
                if(grid[i][j] == 0)
                    if(dfs(grid,i,j)) ans++;  //dfs这个区域
        return ans;
    }
    public boolean dfs(int[][] grid ,int x,int y)   //返回值为布尔值。false表示当前岛屿是不封闭状态
    {
        if((x == 0 || x == m-1 || y == 0 || y == n -1) && grid[x][y] == 0) return false;    //如果当前为边界并且是陆地直接返回false
        boolean f = true;                   //作为当前dfs分支的返回值
        grid[x][y] = 1;                     //标记为海洋
        for(int i  =0 ;i <4;i++ )           //向四个方向遍历
        {
            int x1 = x + dx[i], y1 =  y+ dy[i];
            if(x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || grid[x1][y1] == 1) continue;
            f = f & dfs(grid,x1,y1);       //关键步骤，与运算
        }
        return f;
    }

}
