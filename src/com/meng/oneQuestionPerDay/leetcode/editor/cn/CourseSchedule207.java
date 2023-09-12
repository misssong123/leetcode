package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionCanFinish207 {
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了65.53% 的Java用户
     * 	内存消耗:42.6 MB,击败了76.15% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }
    int[] indeg;
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了92.11% 的Java用户
     * 	内存消耗:42.6 MB,击败了75.48% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
