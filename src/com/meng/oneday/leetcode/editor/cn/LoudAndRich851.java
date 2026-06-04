package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class LoudAndRich851 {
    /**
     * 解答成功:
     * 	执行耗时:1294 ms,击败了5.50% 的Java用户
     * 	内存消耗:58.3 MB,击败了8.26% 的Java用户
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich851(int[][] richer, int[] quiet) {
        int len = quiet.length;
        List<Integer>[] graph = new List[len];
        for(int i = 0; i < len; i++){
            graph[i] = new ArrayList<>();
        }
        //记录富有人数
        for (int[] rich : richer) {
            graph[rich[1]].add(rich[0]);
        }
        int[] ans = new int[len];
        for (int i = 0; i < len;i++){
            if (graph[i].isEmpty()){
                ans[i] = i;
            }else{
                //计算富有人
                Set<Integer> set = calRich(graph, i);
                //计算安静人
                int min = -1;
                for (int num : set) {
                    if(min == -1 || quiet[num] < quiet[min]){
                        min = num;
                    }
                }
                ans[i] = quiet[i] < quiet[min] ? i : min;
            }
        }
        return ans;
    }

    private Set<Integer> calRich(List<Integer>[] graph, int i) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = graph[i];
        while (!list.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for (int num : list) {
                set.add(num);
                for(int n : graph[num]){
                    if (!set.contains(n)){
                        temp.add(n);
                    }
                }
            }
            list = temp;
        }
        return set;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了93.58% 的Java用户
     * 	内存消耗:54.1 MB,击败了79.82% 的Java用户
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRichOther(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] r : richer) {
            g[r[1]].add(r[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            dfs(i, quiet, g, ans);
        }
        return ans;
    }

    public void dfs(int x, int[] quiet, List<Integer>[] g, int[] ans) {
        if (ans[x] != -1) {
            return;
        }
        ans[x] = x;
        for (int y : g[x]) {
            dfs(y, quiet, g, ans);
            if (quiet[ans[y]] < quiet[ans[x]]) {
                ans[x] = ans[y];
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了71.56% 的Java用户
     * 	内存消耗:56.8 MB,击败了33.95% 的Java用户
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        int[] inDeg = new int[n];
        for (int[] r : richer) {
            g[r[0]].add(r[1]);
            ++inDeg[r[1]];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = i;
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g[x]) {
                if (quiet[ans[x]] < quiet[ans[y]]) {
                    ans[y] = ans[x]; // 更新 x 的邻居的答案
                }
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return ans;
    }

}
