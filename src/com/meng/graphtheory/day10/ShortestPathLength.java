package com.meng.graphtheory.day10;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 847. 访问所有节点的最短路径(困难)
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 *
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 *
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：graph = [[1,2,3],[0],[0],[0]]
 * 输出：4
 * 解释：一种可能的路径为 [1,0,2,0,3]
 * 示例 2：
 *
 *
 *
 * 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * 输出：4
 * 解释：一种可能的路径为 [0,1,4,2,3]
 *
 *
 * 提示：
 *
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] 不包含 i
 * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * 输入的图总是连通图
 */
public class ShortestPathLength {
    public int shortestPathLength(int[][] graph) {
        return -1;
    }

    /**
     * 方法一：状态压缩 + 广度优先搜索
     * 思路与算法
     *
     * 由于题目需要我们求出「访问所有节点的最短路径的长度」，并且图中每一条边的长度均为 11，因此我们可以考虑使用广度优先搜索的方法求出最短路径。
     *
     * 在常规的广度优先搜索中，我们会在队列中存储节点的编号。对于本题而言，最短路径的前提是「访问了所有节点」，因此除了记录节点的编号以外，我们还需要记录每一个节点的经过情况。因此，我们使用三元组 (u, \textit{mask}, \textit{dist})(u,mask,dist) 表示队列中的每一个元素，其中：
     *
     * uu 表示当前位于的节点编号；
     *
     * \textit{mask}mask 是一个长度为 nn 的二进制数，表示每一个节点是否经过。如果 \textit{mask}mask 的第 ii 位是 11，则表示节点 ii 已经过，否则表示节点 ii 未经过；
     *
     * \textit{dist}dist 表示到当前节点为止经过的路径长度。
     *
     * 这样一来，我们使用该三元组进行广度优先搜索，即可解决本题。初始时，我们将所有的 (i, 2^i, 0)(i,2
     * i
     *  ,0) 放入队列，表示可以从任一节点开始。在搜索的过程中，如果当前三元组中的 \textit{mask}mask 包含 nn 个 11（即 \textit{mask} = 2^n - 1mask=2
     * n
     *  −1），那么我们就可以返回 \textit{dist}dist 作为答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/shortest-path-visiting-all-nodes/solution/fang-wen-suo-you-jie-dian-de-zui-duan-lu-mqc2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param graph
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 85.85%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 43.79%
     * 的用户
     * 通过测试用例：
     * 51 / 51
     */
    public int shortestPathLength1(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：预处理点对间最短路 + 状态压缩动态规划
     * 思路与算法
     *
     * 由于题目中给定的图是连通图，那么我们可以计算出任意两个节点之间 u, vu,v 间的最短距离，记为 d(u, v)d(u,v)。这样一来，我们就可以使用动态规划的方法计算出最短路径。
     *
     * 对于任意一条经过所有节点的路径，它的某一个子序列（可以不连续）一定是 0, 1, \cdots, n - 10,1,⋯,n−1 的一个排列。我们称这个子序列上的节点为「关键节点」。在动态规划的过程中，我们也是通过枚举「关键节点」进行状态转移的。
     *
     * 我们用 f[u][\textit{mask}]f[u][mask] 表示从任一节点开始到节点 uu 为止，并且经过的「关键节点」对应的二进制表示为 \textit{mask}mask 时的最短路径长度。由于 uu 是最后一个「关键节点」，那么在进行状态转移时，我们可以枚举上一个「关键节点」vv，即：
     *
     * f[u][\textit{mask}] = \min_{v \in \textit{mask}, v \neq u} \big\{ f[v][\textit{mask}\backslash u] + d(v, u) \big\}
     * f[u][mask]=
     * v∈mask,v
     * 
     * ​
     *  =u
     * min
     * ​
     *  {f[v][mask]+d(v,u)}
     *
     * 其中 \textit{mask} \backslash umask 表示将 \textit{mask}mask 的第 uu 位从 11 变为 00 后的二进制表示。也就是说，「关键节点」vv 在 \textit{mask}mask 中的对应位置必须为 11，将 f[v][\textit{mask} \backslash u]f[v][mask] 加上从 vv 走到 uu 的最短路径长度为 d(v, u)d(v,u)，取最小值即为 f[u][\textit{mask}]f[u][mask]。
     *
     * 最终的答案即为：
     *
     * \min_u f[u][2^n - 1]
     * u
     * min
     * ​
     *  f[u][2
     * n
     *  −1]
     *
     * 细节
     *
     * 当 \textit{mask}mask 中只包含一个 11 时，我们无法枚举满足要求的上一个「关键节点」vv。这里的处理方式与方法一中的类似：若 \textit{mask}mask 中只包含一个 11，说明我们位于开始的节点，还未经过任何路径，因此状态转移方程直接写为：
     *
     * f[u][\textit{mask}] = 0
     * f[u][mask]=0
     *
     * 此外，在状态转移方程中，我们需要多次求出 d(v, u)d(v,u)，因此我们可以考虑在动态规划前将所有的 d(v, u)d(v,u) 预处理出来。这里有两种可以使用的方法，时间复杂度均为 O(n^3)O(n
     * 3
     *  )：
     *
     * 我们可以使用 \texttt{Floyd}Floyd 算法求出所有点对之间的最短路径长度；
     *
     * 我们可以进行 nn 次广度优先搜索，第 ii 次从节点 ii 出发，也可以得到所有点对之间的最短路径长度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/shortest-path-visiting-all-nodes/solution/fang-wen-suo-you-jie-dian-de-zui-duan-lu-mqc2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param graph
     * @return
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 14.53%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 96.18%
     * 的用户
     * 通过测试用例：
     * 51 / 51
     */
    public int shortestPathLength2(int[][] graph) {
        int n = graph.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(d[i], n + 1);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) {
                d[i][j] = 1;
            }
        }
        // 使用 floyd 算法预处理出所有点对之间的最短路径长度
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        int[][] f = new int[n][1 << n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE / 2);
        }
        for (int mask = 1; mask < (1 << n); ++mask) {
            // 如果 mask 只包含一个 1，即 mask 是 2 的幂
            if ((mask & (mask - 1)) == 0) {
                int u = Integer.bitCount((mask & (-mask)) - 1);
                f[u][mask] = 0;
            } else {
                for (int u = 0; u < n; ++u) {
                    if ((mask & (1 << u)) != 0) {
                        for (int v = 0; v < n; ++v) {
                            if ((mask & (1 << v)) != 0 && u != v) {
                                f[u][mask] = Math.min(f[u][mask], f[v][mask ^ (1 << u)] + d[v][u]);
                            }
                        }
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int u = 0; u < n; ++u) {
            ans = Math.min(ans, f[u][(1 << n) - 1]);
        }
        return ans;
    }

}
