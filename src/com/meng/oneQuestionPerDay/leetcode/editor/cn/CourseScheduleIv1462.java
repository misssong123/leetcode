package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class SolutionCheckIfPrerequisite1462 {
    /**
     * 时间
     * 详情
     * 59ms
     * 击败 12.73%使用 Java 的用户
     * 内存
     * 详情
     * 47.25MB
     * 击败 5.45%使用 Java 的用户
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisite1(int numCourses, int[][] prerequisites, int[][] queries) {
        Set<Integer>[] cache = new HashSet[numCourses];
        for(int i = 0 ; i < numCourses ; i++){
            cache[i] = new HashSet<>();
        }
        for(int[] pre : prerequisites){
            cache[pre[1]].add(pre[0]);
        }
        List<Boolean> res = new ArrayList<>();
        boolean[] flags = new boolean[numCourses];
        for (int[] query : queries ){
            if (cache[query[1]].size() == 0){
                res.add(false);
            }else if (cache[query[1]].contains(query[0])){
                res.add(true);
            }else {
                if (flags[query[1]]){
                    res.add(false);
                }else {
                    flags[query[1]] = true;
                    Set<Integer> newVal = new HashSet<>();
                    dfs(cache,cache[query[1]],newVal);
                    res.add(newVal.contains(query[0]));
                    cache[query[1]] = newVal;
                }
            }
        }
        return res;
    }

    private void dfs(Set<Integer>[] cache, Set<Integer> nums,Set<Integer> newVal) {
        for(int num : nums){
            if (newVal.add(num)){
                dfs(cache,cache[num],newVal);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了66.06% 的Java用户
     * 	内存消耗:46.1 MB,击败了41.82% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        int[] indgree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++indgree[p[1]];
            g[p[0]].add(p[1]);
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : g[cur]) {
                isPre[cur][ne] = true;
                for (int i = 0; i < numCourses; ++i) {
                    isPre[i][ne] = isPre[i][ne] | isPre[i][cur];
                }
                --indgree[ne];
                if (indgree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

    /**
     * 	执行耗时:10 ms,击败了99.39% 的Java用户
     * 	内存消耗:45.9 MB,击败了57.57% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        boolean[] vi = new boolean[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            g[p[0]].add(p[1]);
        }
        for (int i = 0; i < numCourses; ++i) {
            dfs(g, isPre, vi, i);
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

    public void dfs(List<Integer>[] g, boolean[][] isPre, boolean[] vi, int cur) {
        if (vi[cur]) {
            return;
        }
        vi[cur] = true;
        for (int ne : g[cur]) {
            dfs(g, isPre, vi, ne);
            isPre[cur][ne] = true;
            for (int i = 0; i < isPre.length; ++i) {
                isPre[cur][i] = isPre[cur][i] | isPre[ne][i];
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
