package com.meng.graphtheory.day02;

import java.util.*;

/**
 * 695. 岛屿的最大面积(中等)
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * 示例 2：
 *
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 */
public class MaxAreaOfIsland {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 16.32%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 40.36%
     * 的用户
     * 通过测试用例：
     * 728 / 728
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int lines = grid.length;
        int rows = grid[0].length;
        int [] x = {0,0,1,-1};
        int [] y = {1,-1,0,0};
        for(int i = 0 ; i < lines ; i++){
            for(int j = 0 ; j < rows ; j++){
                if (grid[i][j] == 1){
                    int temp = 0;
                    List<int[]> cache = new ArrayList<>();
                    cache.add(new int[]{i,j});
                    while (cache.size() > 0){
                        int size = cache.size();
                        for(int k = 0 ; k < size ; k++){
                            int[] point = cache.remove(0);
                            if (grid[point[0]][point[1]] == 0){
                                continue;
                            }
                            temp++;
                            grid[point[0]][point[1]] = 0;
                            for(int m = 0 ; m < 4 ; m++){
                                int newX = point[0] + x[m];
                                int newY = point[1] + y[m];
                                if(newX>=0 && newX < lines && newY>=0 && newY < rows && grid[newX][newY]==1){
                                    cache.add(new int[]{newX,newY});
                                }
                            }
                        }
                    }
                    res = Math.max(res,temp);
                }
            }
        }
        return res;
    }

    private void printLand(int[][]grid){
        for(int[] points : grid){
            System.out.println(Arrays.toString(points));
        }
        System.out.println("-------------------------------------------------------");
    }

    public static void main(String[] args) {
        MaxAreaOfIsland demo = new MaxAreaOfIsland();
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(demo.maxAreaOfIsland(grid));
    }
    /**
     * 方法一：深度优先搜索
     * 算法
     *
     * 我们想知道网格中每个连通形状的面积，然后取最大值。
     *
     * 如果我们在一个土地上，以 44 个方向探索与之相连的每一个土地（以及与这些土地相连的土地），那么探索过的土地总数将是该连通形状的面积。
     *
     * 为了确保每个土地访问不超过一次，我们每次经过一块土地时，将这块土地的值置为 00。这样我们就不会多次访问同一土地。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/max-area-of-island/solution/dao-yu-de-zui-da-mian-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 55.17%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 70.84%
     * 的用户
     * 通过测试用例：
     * 728 / 728
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }

    public int dfs(int[][] grid, int cur_i, int cur_j) {
        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
            return 0;
        }
        grid[cur_i][cur_j] = 0;
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        int ans = 1;
        for (int index = 0; index != 4; ++index) {
            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
            ans += dfs(grid, next_i, next_j);
        }
        return ans;
    }

    /**
     * 方法二：深度优先搜索 + 栈
     * 算法
     *
     * 我们可以用栈来实现深度优先搜索算法。这种方法本质与方法一相同，唯一的区别是：
     *
     * 方法一通过函数的调用来表示接下来想要遍历哪些土地，让下一层函数来访问这些土地。而方法二把接下来想要遍历的土地放在栈里，然后在取出这些土地的时候访问它们。
     *
     * 访问每一片土地时，我们将对围绕它四个方向进行探索，找到还未访问的土地，加入到栈 \textit{stack}stack 中；
     *
     * 另外，只要栈 \textit{stack}stack 不为空，就说明我们还有土地待访问，那么就从栈中取出一个元素并访问。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/max-area-of-island/solution/dao-yu-de-zui-da-mian-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 5.00%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 84.48%
     * 的用户
     * 通过测试用例：
     * 728 / 728
     */
    public int maxAreaOfIsland2(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Deque<Integer> stacki = new LinkedList<Integer>();
                Deque<Integer> stackj = new LinkedList<Integer>();
                stacki.push(i);
                stackj.push(j);
                while (!stacki.isEmpty()) {
                    int cur_i = stacki.pop(), cur_j = stackj.pop();
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                        continue;
                    }
                    ++cur;
                    grid[cur_i][cur_j] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                        stacki.push(next_i);
                        stackj.push(next_j);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    /**
     * 方法三：广度优先搜索
     * 算法
     *
     * 我们把方法二中的栈改为队列，每次从队首取出土地，并将接下来想要遍历的土地放在队尾，就实现了广度优先搜索算法。
     * @param grid
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 5.00%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 46.73%
     * 的用户
     * 通过测试用例：
     * 728 / 728
     */
    public int maxAreaOfIsland3(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Queue<Integer> queuei = new LinkedList<Integer>();
                Queue<Integer> queuej = new LinkedList<Integer>();
                queuei.offer(i);
                queuej.offer(j);
                while (!queuei.isEmpty()) {
                    int cur_i = queuei.poll(), cur_j = queuej.poll();
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                        continue;
                    }
                    ++cur;
                    grid[cur_i][cur_j] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                        queuei.offer(next_i);
                        queuej.offer(next_j);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }


}
