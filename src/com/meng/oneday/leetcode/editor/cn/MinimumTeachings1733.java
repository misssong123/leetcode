package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinimumTeachings1733 {
    /**
     * 解答成功:
     * 	执行耗时:93 ms,击败了11.76% 的Java用户
     * 	内存消耗:62.7 MB,击败了5.88% 的Java用户
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings1733(int n, int[][] languages, int[][] friendships) {
        List<int[]> needTeach = new ArrayList<>();
        Set<Integer> languageSet = new HashSet<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        //存储每个用户的语言
        for (int i = 1 ; i <= languages.length; i++) {
            int[] language = languages[i-1];
            Set<Integer> set = new HashSet<>();
            for (int l : language){
                set.add(l);
            }
            map.put(i, set);
        }
        //判断没有共同语言的用户
        for(int [] friend : friendships){
            Set<Integer> set1 = map.get(friend[0]);
            Set<Integer> set2 = map.get(friend[1]);
            if (Collections.disjoint(set1, set2)){
                needTeach.add(friend);
                languageSet.addAll(set1);
                languageSet.addAll(set2);
            }
        }
        int res = needTeach.size() * 2;
        Set<Integer> needPeople = new HashSet<>();
        for(int language : languageSet){
            for(int[] friend : needTeach){
                if (!map.get(friend[0]).contains(language)){
                    needPeople.add(friend[0]);
                }
                if (!map.get(friend[1]).contains(language)){
                    needPeople.add(friend[1]);
                }
            }
            res = Math.min(res, needPeople.size());
            needPeople.clear();
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了64.71% 的Java用户
     * 	内存消耗:55.1 MB,击败了82.35% 的Java用户
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachingsOther(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] learned = new boolean[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int x : languages[i]) {
                learned[i][x] = true;
            }
        }

        List<int[]> todoList = new ArrayList<>();
        next:
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1; // 减一，下标从 0 开始
            for (int x : languages[u]) {
                if (learned[v][x]) { // 两人可以相互沟通，无需学习语言
                    continue next;
                }
            }
            todoList.add(f);
        }

        int ans = m;
        for (int k = 1; k <= n; k++) { // 枚举需要教的语言 k
            Set<Integer> set = new HashSet<>();
            for (int[] f : todoList) {
                int u = f[0] - 1, v = f[1] - 1;
                if (!learned[u][k]) { // u 需要学习语言 k
                    set.add(u);
                }
                if (!learned[v][k]) { // v 需要学习语言 k
                    set.add(v);
                }
            }
            ans = Math.min(ans, set.size()); // set.size() 是需要学习语言 k 的人数
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:54.9 MB,击败了88.24% 的Java用户
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] learned = new boolean[m][n + 1]; // 记录每个人学的语言
        for (int i = 0; i < m; i++) {
            for (int x : languages[i]) {
                learned[i][x] = true;
            }
        }

        boolean[] vis = new boolean[m];
        int[] cnt = new int[n + 1];

        next:
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1; // 下标从 0 开始
            for (int x : languages[u]) {
                if (learned[v][x]) { // 两人可以相互沟通，无需学习语言
                    continue next;
                }
            }
            add(u, languages, vis, cnt);
            add(v, languages, vis, cnt);
        }

        int maxCnt = 0;
        for (int c : cnt) {
            maxCnt = Math.max(maxCnt, c);
        }

        return total - maxCnt;
    }

    private int total = 0;

    private int add(int u, int[][] languages, boolean[] vis, int[] cnt) {
        if (vis[u]) {
            return total;
        }
        vis[u] = true; // 避免重复统计
        total++;
        for (int x : languages[u]) {
            cnt[x]++;
        }
        return total;
    }

}
