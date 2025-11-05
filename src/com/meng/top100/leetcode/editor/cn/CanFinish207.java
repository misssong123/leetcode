package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class CanFinish207 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了70.99% 的Java用户
     * 	内存消耗:46.3 MB,击败了5.04% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish207(int numCourses, int[][] prerequisites) {
        //初始化
        int[] dependency = new int[numCourses];
        List<Integer>[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }
        //构建关系
        for(int[] pre : prerequisites){
            edges[pre[1]].add(pre[0]);
            dependency[pre[0]]++;
        }
        //初始化节点
        Deque<Integer> queue = new ArrayDeque<>(numCourses);
        for(int i = 0 ; i < numCourses ; i++){
            if(dependency[i] == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int p = queue.poll();
            for (int d : edges[p]) {
                dependency[d]--;
                if(dependency[d] == 0){
                    queue.offer(d);
                }
            }
        }
        for(int i : dependency){
            if(i != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了91.72% 的Java用户
     * 	内存消耗:46.6 MB,击败了5.04% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] g = new ArrayList[numCourses];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] p : prerequisites) {
            g[p[1]].add(p[0]);
        }

        int[] colors = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (colors[i] == 0 && dfs(i, g, colors)) {
                return false; // 有环
            }
        }
        return true; // 没有环
    }

    // 返回 true 表示找到了环
    private boolean dfs(int x, List<Integer>[] g, int[] colors) {
        colors[x] = 1; // x 正在访问中
        for (int y : g[x]) {
            if (colors[y] == 1 || colors[y] == 0 && dfs(y, g, colors)) {
                return true; // 找到了环
            }
        }
        colors[x] = 2; // x 完全访问完毕，从 x 出发无法找到环
        return false; // 没有找到环
    }
}
