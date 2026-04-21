package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinimumHammingDistance1722 {
    /**
     * 解答成功:
     * 	执行耗时:40 ms,击败了86.96% 的Java用户
     * 	内存消耗:105 MB,击败了95.65% 的Java用户
     * @param source
     * @param target
     * @param allowedSwaps
     * @return
     */
    public int minimumHammingDistance1722(int[] source, int[] target, int[][] allowedSwaps) {
        //计算并查集
        int[] position = new int[source.length];
        //初始化
        for (int i = 0; i < position.length; i++) {
            position[i] = i;
        }
        //构建并查集
        for (int[] swap : allowedSwaps) {
            int p1 = find(position, swap[0]);
            int p2 = find(position, swap[1]);
            if (p1 != p2) {
                position[p2] = p1;
            }
        }
        //可交换下标的集合
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < position.length; i++) {
            int p = find(position, i);
            if (!map.containsKey(p)) {
                map.put(p, new ArrayList<>());
            }
            map.get(p).add(i);
        }
        int[] cnts = new int[100001];
        int res = 0;
        //计算最小距离
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            //统计source中每个数字出现的次数
            for (int i : entry.getValue()) {
                cnts[source[i]]++;
            }
            //统计target中每个数字出现的次数
            for (int i : entry.getValue()) {
                cnts[target[i]]--;
                if (cnts[target[i]] < 0) {
                    res++;
                }
            }
            //重置
            for (int i : entry.getValue()) {
                cnts[target[i]] = 0;
                cnts[source[i]] = 0;
            }
        }
        return res;
    }
    private int find(int[] position, int i) {
        int p = i;
        while (p != position[p]) {
            p = position[p];
        }
        //缩短距离
        position[i] = p;
        return p;
    }

    /**
     * 解答成功:
     * 	执行耗时:95 ms,击败了17.39% 的Java用户
     * 	内存消耗:126.9 MB,击败了21.74% 的Java用户
     * @param source
     * @param target
     * @param allowedSwaps
     * @return
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, item -> new ArrayList<>());
        for (int[] e : allowedSwaps) {
            int i = e[0];
            int j = e[1];
            g[i].add(j); // 建图
            g[j].add(i);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int x = 0; x < n; x++) {
            if (!vis[x]) {
                Map<Integer, Integer> diff = new HashMap<>();
                dfs(x, source, target, g, vis, diff);
                for (int c : diff.values()) {
                    ans += Math.abs(c);
                }
            }
        }
        return ans / 2; // 有 ans / 2 对多出来的元素
    }

    private void dfs(int x, int[] source, int[] target, List<Integer>[] g, boolean[] vis, Map<Integer, Integer> diff) {
        vis[x] = true; // 避免重复访问
        // 抵消相同的元素，最终剩下 source 和 target 各自多出来的元素（对称差）
        diff.merge(source[x], 1, Integer::sum);  // diff[source[x]]++;
        diff.merge(target[x], -1, Integer::sum); // diff[target[x]]--;
        for (int y : g[x]) {
            if (!vis[y]) {
                dfs(y, source, target, g, vis, diff);
            }
        }
    }

}
