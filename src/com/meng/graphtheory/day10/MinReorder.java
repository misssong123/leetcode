package com.meng.graphtheory.day10;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 1466. 重新规划路线(中等)
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 *
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 *
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 *
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 *
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 2：
 *
 *
 *
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 3：
 *
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 *
 *
 * 提示：
 *
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 */
public class MinReorder {
    public int minReorder(int n, int[][] connections) {
        return -1;
    }

    /**
     *执行用时：
     * 36 ms
     * , 在所有 Java 提交中击败了
     * 86.91%
     * 的用户
     * 内存消耗：
     * 62.1 MB
     * , 在所有 Java 提交中击败了
     * 82.18%
     * 的用户
     * 通过测试用例：
     * 76 / 76
     */
    static class De {
        int v;
        int d;
        public De(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }

    public int minReorder1(int n, int[][] connections) {

        List<List<De>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < connections.length; i++) {
            int[] e = connections[i];
            adj.get(e[0]).add(new De(e[1], 1));
            adj.get(e[1]).add(new De(e[0], -1));
        }

        boolean[] used = new boolean[n];
        used[0] = true;
        Deque<Integer> deq = new LinkedList<>();
        deq.offer(0);

        int ans = 0;
        while (!deq.isEmpty()) {
            int cur = deq.poll();
            List<De> es = adj.get(cur);
            for (int i = 0; i < es.size(); i++) {
                De de = es.get(i);
                if (used[de.v]) continue;
                if (de.d == 1) {
                    // 逆向需要+1, 改造边的方向
                    ans++;
                }
                used[de.v] = true;
                deq.offer(de.v);
            }
        }

        return ans;

    }

}
