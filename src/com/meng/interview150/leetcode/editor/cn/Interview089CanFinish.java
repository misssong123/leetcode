package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview089CanFinish {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了17.83% 的Java用户
     * 	内存消耗:44.5 MB,击败了26.61% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishMy(int numCourses, int[][] prerequisites) {
       int[] nums = new int[numCourses];
       Map<Integer,Set<Integer>> map = new HashMap<>();
       int num = 0;
       //计算每个节点的入度以及依赖的节点
       for (int[] pre: prerequisites){
           if (!map.containsKey(pre[1])){
               map.put(pre[1],new HashSet<>());
           }
           nums[pre[0]]++;
           map.get(pre[1]).add(pre[0]);
       }
       //将入度为0的节点放入队列
        Stack<Integer> stack = new Stack<>();
       for(int i = 0; i < nums.length; i++){
           if (nums[i] == 0){
               stack.push(i);
               num++;
           }
       }
       while (!stack.isEmpty()){
           Integer pop = stack.pop();
           if (map.containsKey(pop)){
               for (int n : map.get(pop)){
                   nums[n]--;
                   if (nums[n] == 0) {
                       stack.push(n);
                       num++;
                   }
               }
           }
       }
       return num == numCourses;
    }
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了97.32% 的Java用户
     * 	内存消耗:43.5 MB,击败了62.80% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishOfficial(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
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
    int[] indeg;

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了64.45% 的Java用户
     * 	内存消耗:43.5 MB,击败了48.23% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
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
}
