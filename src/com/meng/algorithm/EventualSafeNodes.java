package com.meng.algorithm;

import java.util.*;

/**
 * 802. 找到最终的安全状态
 *
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
 *
 * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
 *
 * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 *
 * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
 *
 *
 *
 * 示例 1：
 * Illustration of graph
 *
 * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * 输出：[2,4,5,6]
 * 解释：示意图如上。
 *
 * 示例 2：
 *
 * 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * 输出：[4]
 *
 *
 *
 * 提示：
 *
 *     n == graph.length
 *     1 <= n <= 104
 *     0 <= graph[i].length <= n
 *     graph[i] 按严格递增顺序排列。
 *     图中可能包含自环。
 *     图中边的数目在范围 [1, 4 * 104] 内。
 */
public class EventualSafeNodes {
    /**
     * 执行用时：38 ms, 在所有 Java 提交中击败了31.10% 的用户
     * 内存消耗：47.9 MB, 在所有 Java 提交中击败了76.94% 的用户
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len  = graph.length;
        int [] nums = new int[len];
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        LinkedList<Integer> cache = new LinkedList<>();
        for(int i = 0 ; i < len ; i++){
            List<Integer> list = new ArrayList<>();
            lists.add(list);
        }
        for(int i = 0 ; i < len ; i++){
            for(int num : graph[i]){
                lists.get(num).add(i);
            }
            nums[i] = graph[i].length;
            if (nums[i] == 0){
                cache.push(i);
                res.add(i);
            }
        }
        while (cache.size()>0){
            Integer index = cache.poll();
            for(int num : lists.get(index)){
                nums[num]--;
                if (nums[num] == 0){
                    cache.push(num);
                    res.add(num);
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    /**
     * 方法一：深度优先搜索 + 三色标记法
     *
     * 根据题意，若起始节点位于一个环内，或者能到达一个环，则该节点不是安全的。否则，该节点是安全的。
     *
     * 我们可以使用深度优先搜索来找环，并在深度优先搜索时，用三种颜色对节点进行标记，标记的规则如下：
     *
     *     白色（用 000 表示）：该节点尚未被访问；
     *     灰色（用 111 表示）：该节点位于递归栈中，或者在某个环上；
     *     黑色（用 222 表示）：该节点搜索完毕，是一个安全节点。
     *
     * 当我们首次访问一个节点时，将其标记为灰色，并继续搜索与其相连的节点。
     *
     * 如果在搜索过程中遇到了一个灰色节点，则说明找到了一个环，此时退出搜索，栈中的节点仍保持为灰色，这一做法可以将「找到了环」这一信息传递到栈中的所有节点上。
     *
     * 如果搜索过程中没有遇到灰色节点，则说明没有遇到环，那么递归返回前，我们将其标记由灰色改为黑色，即表示它是一个安全的节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-eventual-safe-states/solution/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-yzfz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param graph
     * @return
     * 执行用时：4 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：48.3 MB, 在所有 Java 提交中击败了41.55% 的用户
     */
    public List<Integer> eventualSafeNodes1(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    /**
     * 方法二：拓扑排序
     *
     * 根据题意，若一个节点没有出边，则该节点是安全的；若一个节点出边相连的点都是安全的，则该节点也是安全的。
     *
     * 根据这一性质，我们可以将图中所有边反向，得到一个反图，然后在反图上运行拓扑排序。
     *
     * 具体来说，首先得到反图 rg\textit{rg}rg 及其入度数组 inDeg\textit{inDeg}inDeg。将所有入度为 000 的点加入队列，然后不断取出队首元素，将其出边相连的点的入度减一，若该点入度减一后为 000，则将该点加入队列，如此循环直至队列为空。循环结束后，所有入度为 000 的节点均为安全的。我们遍历入度数组，并将入度为 000 的点加入答案列表。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-eventual-safe-states/solution/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-yzfz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param graph
     * @return
     * 执行用时：15 ms, 在所有 Java 提交中击败了47.18% 的用户
     * 内存消耗：47 MB, 在所有 Java 提交中击败了90.35% 的用户
     */
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> rg = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            rg.add(new ArrayList<Integer>());
        }
        int[] inDeg = new int[n];
        for (int x = 0; x < n; ++x) {
            for (int y : graph[x]) {
                rg.get(y).add(x);
            }
            inDeg[x] = graph[x].length;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int x : rg.get(y)) {
                if (--inDeg[x] == 0) {
                    queue.offer(x);
                }
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2},{2,3},{5},{0},{5},{},{}};
        EventualSafeNodes demo = new EventualSafeNodes();
        System.out.println(demo.eventualSafeNodes(nums));
    }
}
