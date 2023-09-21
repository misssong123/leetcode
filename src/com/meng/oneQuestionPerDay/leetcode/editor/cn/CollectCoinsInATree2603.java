package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionCollectTheCoins2603 {
    /**
     * 无思路
     * @param coins
     * @param edges
     * @return
     */
    public int collectTheCoins1(int[] coins, int[][] edges) {
        return 2;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了92.11% 的Java用户
     * 	内存消耗:57.5 MB,击败了78.95% 的Java用户
     * @param coins
     * @param edges
     * @return
     */
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
            ++degree[x];
            ++degree[y];
        }

        int rest = n;
        /* 删除树中所有无金币的叶子节点，直到树中所有的叶子节点都是含有金币的 */
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1 && coins[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            --degree[u];
            --rest;
            for (int v : g[u]) {
                --degree[v];
                if (degree[v] == 1 && coins[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        /* 删除树中所有的叶子节点, 连续删除2次 */
        for (int x = 0; x < 2; ++x) {
            queue = new ArrayDeque<Integer>();
            for (int i = 0; i < n; ++i) {
                if (degree[i] == 1) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int u = queue.poll();
                --degree[u];
                --rest;
                for (int v : g[u]) {
                    --degree[v];
                }
            }
        }

        return rest == 0 ? 0 : (rest - 1) * 2;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
