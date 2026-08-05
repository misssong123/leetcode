package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class RemainingMethods3310 {
    /**
     * 解答成功:
     * 	执行耗时:244 ms,击败了5.45% 的Java用户
     * 	内存消耗:292.1 MB,击败了12.73% 的Java用户
     * @param n
     * @param k
     * @param invocations
     * @return
     */
    public List<Integer> remainingMethods3310(int n, int k, int[][] invocations) {
        //构建路径
        Set<Integer>[] edges = new HashSet[n];
        for (int i = 0 ; i < n ; i++){
            edges[i] = new HashSet<>();
        }
        for(int[] invocation : invocations) {
            edges[invocation[0]].add(invocation[1]);
        }
        //计算可疑方法
        Set<Integer> kEdges = new HashSet<>();
        boolean[] visited = new boolean[n];
        kEdges.add(k);
        visited[k] = true;
        dfs(kEdges,edges,k,visited);
        //计算可疑方法是否被引用
        boolean isUsed = false;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            if (kEdges.contains(i)){
                continue;
            }
            if (!Collections.disjoint(kEdges,edges[i])){
                isUsed = true;
                break;
            }
            ans.add(i);
        }
        if (isUsed){
            List<Integer> ans2 = new ArrayList<>();
            for (int i = 0 ; i < n ; i++){
                ans2.add(i);
            }
            return ans2;
        }
        return ans;
    }

    private void dfs(Set<Integer> kEdges, Set<Integer>[] edges, int k,boolean[] visited) {
        for (Integer edge : edges[k]) {
            if (!visited[edge]){
                kEdges.add(edge);
                visited[edge] = true;
                dfs(kEdges,edges,edge,visited);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:70 ms,击败了49.09% 的Java用户
     * 	内存消耗:289.5 MB,击败了23.64% 的Java用户
     * @param n
     * @param k
     * @param invocations
     * @return
     */
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : invocations) {
            g[e[0]].add(e[1]);
        }

        // 标记所有可疑方法
        boolean[] isSuspicious = new boolean[n];
        dfs(k, g, isSuspicious);

        // 检查是否有【非可疑方法】->【可疑方法】的边
        for (int[] e : invocations) {
            if (!isSuspicious[e[0]] && isSuspicious[e[1]]) {
                // 无法移除可疑方法
                List<Integer> ans = new ArrayList<>(n);
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }

        // 移除所有可疑方法
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    private void dfs(int x, List<Integer>[] g, boolean[] isSuspicious) {
        isSuspicious[x] = true;
        for (int y : g[x]) {
            if (!isSuspicious[y]) { // 避免无限递归
                dfs(y, g, isSuspicious);
            }
        }
    }
}
