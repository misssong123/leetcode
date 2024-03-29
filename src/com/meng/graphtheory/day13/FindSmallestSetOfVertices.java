package com.meng.graphtheory.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1557. 可以到达所有点的最少点数目(中等)
 * 给你一个 有向无环图 ， n 个节点编号为 0 到 n-1 ，以及一个边数组 edges ，其中 edges[i] = [fromi, toi] 表示一条从点  fromi 到点 toi 的有向边。
 *
 * 找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。
 *
 * 你可以以任意顺序返回这些节点编号。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
 * 输出：[0,3]
 * 解释：从单个节点出发无法到达所有节点。从 0 出发我们可以到达 [0,1,2,5] 。从 3 出发我们可以到达 [3,4,2,5] 。所以我们输出 [0,3] 。
 * 示例 2：
 *
 *
 *
 * 输入：n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
 * 输出：[0,2,3]
 * 解释：注意到节点 0，3 和 2 无法从其他节点到达，所以我们必须将它们包含在结果点集中，这些点都能到达节点 1 和 4 。
 *
 *
 * 提示：
 *
 * 2 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
 * edges[i].length == 2
 * 0 <= fromi, toi < n
 * 所有点对 (fromi, toi) 互不相同。
 */
public class FindSmallestSetOfVertices {
    /**
     * 执行用时：
     * 22 ms
     * , 在所有 Java 提交中击败了
     * 19.01%
     * 的用户
     * 内存消耗：
     * 83.5 MB
     * , 在所有 Java 提交中击败了
     * 30.04%
     * 的用户
     * 通过测试用例：
     * 66 / 66
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> points = new HashSet<>();
        for(List<Integer> edge : edges){
            points.add(edge.get(1));
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            if (!points.contains(i)){
                res.add(i);
            }
        }
        return res;
    }

    /**
     *执行用时：
     * 21 ms
     * , 在所有 Java 提交中击败了
     * 26.20%
     * 的用户
     * 内存消耗：
     * 83.6 MB
     * , 在所有 Java 提交中击败了
     * 28.12%
     * 的用户
     * 通过测试用例：
     * 66 / 66
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findSmallestSetOfVertices1(int n, List<List<Integer>> edges) {
        List<Integer> ans = new ArrayList<Integer>();
        Set<Integer> endSet = new HashSet<Integer>();
        for (List<Integer> edge : edges) {
            endSet.add(edge.get(1));
        }
        for (int i = 0; i < n; i++) {
            if (!endSet.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

}
