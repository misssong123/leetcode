package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class FindOrderLCF113 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了92.65% 的Java用户
     * 	内存消耗:45.9 MB,击败了5.15% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrderLCF113(int numCourses, int[][] prerequisites) {
        int l = 0;
        int r = 0;
        int[] res = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
            graph[prerequisite[1]].add(prerequisite[0]);
        }
        //初始化
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0){
                res[r++] = i;
            }
        }
        while (l < r){
            int cur = res[l];
            for (int next : graph[cur]){
                if (--inDegree[next] == 0){
                    res[r++] = next;
                }
            }
            l++;
        }
        return r == numCourses ? res : new int[0];
    }
    // 存储有向图
    List<List<Integer>> edges;
    // 存储每个节点的入度
    int[] indeg;
    // 存储答案
    int[] result;
    // 答案下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        result = new int[numCourses];
        index = 0;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        // 将所有入度为 0 的节点放入队列中
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            // 从队首取出一个节点
            int u = queue.poll();
            // 放入答案中
            result[index++] = u;
            for (int v: edges.get(u)) {
                --indeg[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }
}
