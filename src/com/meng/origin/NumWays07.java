package com.meng.origin;

import java.util.*;

/**
 * LCP 07. 传递信息
 *
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 *     有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 *     每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 *     每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 *
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 *
 * 示例 1：
 *
 *     输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 *
 *     输出：3
 *
 *     解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 *
 *     输入：n = 3, relation = [[0,2],[2,1]], k = 2
 *
 *     输出：0
 *
 *     解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 * 限制：
 *
 *     2 <= n <= 10
 *     1 <= k <= 5
 *     1 <= relation.length <= 90, 且 relation[i].length == 2
 *     0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 * @author lenovo
 */
public class NumWays07 {
    /**
     * 执行用时：12 ms, 在所有 Java 提交中击败了9.85% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了6.57% 的用户
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> cache = new HashMap<>();
        int len = relation.length;
        for(int i = 0 ; i < len ; i++){
            List<Integer> list = cache.getOrDefault(relation[i][0], new ArrayList<>());
            list.add(relation[i][1]);
            cache.put(relation[i][0],list);
        }
        System.out.println(cache);
        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        temp1.addAll(cache.get(0));
        int res = 0;
        while (k > 1 && temp1.size() > 0){
            k--;
            for(Integer temp : temp1){
                if (cache.get(temp) != null){
                    temp2.addAll(cache.get(temp));
                }
            }
            temp1.removeAll(temp1);
            temp1.addAll(temp2);
            temp2.removeAll(temp2);
        }
        if (k == 1){
            for(Integer temp : temp1){
                if (temp == n-1){
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 方法一：深度优先搜索
     *
     * 可以把传信息的关系看成有向图，每个玩家对应一个节点，每个传信息的关系对应一条有向边。如果 xxx 可以向 yyy 传信息，则对应从节点 xxx 到节点 yyy 的一条有向边。寻找从编号 000 的玩家经过 kkk 轮传递到编号 n−1n-1n−1 的玩家处的方案数，等价于在有向图中寻找从节点 000 到节点 n−1n-1n−1 的长度为 kkk 的路径数，同一条路径可以重复经过同一个节点。
     *
     * 可以使用深度优先搜索计算方案数。从节点 000 出发做深度优先搜索，每一步记录当前所在的节点以及经过的轮数，当经过 kkk 轮时，如果位于节点 n−1n-1n−1，则将方案数加 111。搜索结束之后，即可得到总的方案数。
     *
     * 具体实现方面，可以对传信息的关系进行预处理，使用列表存储有向边的关系，即可在 O(1)O(1)O(1) 的时间内得到特定节点的相邻节点（即可以沿着有向边一步到达的节点）。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi/solution/chuan-di-xin-xi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了56.72% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了73.73% 的用户
     */
    int ways, n, k;
    List<List<Integer>> edges;

    public int numWays1(int n, int[][] relation, int k) {
        ways = 0;
        this.n = n;
        this.k = k;
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }
        dfs(0, 0);
        return ways;
    }

    public void dfs(int index, int steps) {
        if (steps == k) {
            if (index == n - 1) {
                ways++;
            }
            return;
        }
        List<Integer> list = edges.get(index);
        for (int nextIndex : list) {
            dfs(nextIndex, steps + 1);
        }
    }

    /**
     * 方法二：广度优先搜索
     *
     * 也可以使用广度优先搜索计算方案数。从节点 000 出发做广度优先搜索，当遍历到 kkk 层时，如果位于节点 n−1n-1n−1，则将方案数加 111。搜索结束之后，即可得到总的方案数。
     *
     * class Solution {
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi/solution/chuan-di-xin-xi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * @param n
     * @param relation
     * @param k
     * @return
     * 执行用时：9 ms, 在所有 Java 提交中击败了15.22% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了5.08% 的用户
     */
    public int numWays2(int n, int[][] relation, int k) {
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }

        int steps = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        while (!queue.isEmpty() && steps < k) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                List<Integer> list = edges.get(index);
                for (int nextIndex : list) {
                    queue.offer(nextIndex);
                }
            }
        }

        int ways = 0;
        if (steps == k) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n - 1) {
                    ways++;
                }
            }
        }
        return ways;
    }

    public static void main(String[] args) {
        NumWays07 demo = new NumWays07();
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int n = 5;
        int k = 3;
        System.out.println(demo.numWays(n,relation,k));
    }
}
