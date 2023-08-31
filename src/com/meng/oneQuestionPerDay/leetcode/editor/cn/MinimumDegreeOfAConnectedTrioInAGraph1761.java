package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class SolutionMinTrioDegree1761 {
    /**
     * 1.计算单点的关联节点和入度
     * 2.计算组成的三元组
     * @param n
     * @param edges
     * @return
     * 超时
     */
    public int minTrioDegree1(int n, int[][] edges) {
        if (n<3 || edges.length < 3){
            return -1;
        }
        Map<Integer, List<Integer>> point = new HashMap<>();
        Map<Integer,Integer> degree = new HashMap<>();
        Set<Integer> used = new HashSet<>();
        for(int[] edge : edges){
            int p1 = edge[0];
            int p2 = edge[1];
            List<Integer> p1List = point.getOrDefault(p1, new ArrayList<>());
            List<Integer> p2List = point.getOrDefault(p2, new ArrayList<>());
            p1List.add(p2);
            p2List.add(p1);
            point.put(p1,p1List);
            point.put(p2,p2List);
            degree.put(p1,degree.getOrDefault(p1,0)+1);
            degree.put(p2,degree.getOrDefault(p2,0)+1);
        }
        int ans = Integer.MAX_VALUE;
        for(Map.Entry<Integer,List<Integer>> entry : point.entrySet()){
            if (entry.getValue().size()>1){
                List<Integer> temp = entry.getValue();
                int size = temp.size();
                for(int i = 0 ; i < size -1 ; i ++){
                    if (used.contains(temp.get(i))){
                        continue;
                    }
                    if (degree.get(temp.get(i))<2){
                        used.add(temp.get(i));
                        continue;
                    }
                    List<Integer> fList = point.get(temp.get(i));
                    for(int j = i + 1 ; j < size ; j++ ){
                        if (used.contains(temp.get(j))){
                            continue;
                        }
                        if (degree.get(temp.get(j))<2){
                            used.add(temp.get(j));
                            continue;
                        }
                        if (fList.contains(temp.get(j))){//计算当前度
                            int p = degree.get(entry.getKey()) + degree.get(temp.get(i)) + degree.get(temp.get(j))-6;
                            ans = Math.min(ans,p);
                            if (ans == 0){
                                return 0;
                            }
                        }
                    }
                }
            }
            used.add(entry.getKey());
        }
        return ans == Integer.MAX_VALUE?-1:ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:366 ms,击败了20.53% 的Java用户
     * 	内存消耗:69.9 MB,击败了13.39% 的Java用户
     * @param n
     * @param edges
     * @return
     */
    public int minTrioDegree(int n, int[][] edges) {
        if (n<3 || edges.length < 3){
            return -1;
        }
        Set<Integer>[] index = new HashSet[n+1];
        for(int i = 1 ; i <= n ; i++){
            index[i] = new HashSet<>();
        }
        for(int[] edge : edges){
            int p1 = edge[0];
            int p2 = edge[1];
            index[p1].add(p2);
            index[p2].add(p1);
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        for(int i = 1 ; i <= n ; i++){
            if (index[i].size() >= 2){
                list.add(i);
            }
        }
        for(int i = 0 ; i < list.size()-2 ; i++){
            int first = list.get(i);
            for(int j = i+1 ; j < list.size()-1; j++){
                int second = list.get(j);
                if (!index[first].contains(second)){
                    continue;
                }
                for(int k = j+1 ; k < list.size() ; k++){
                    int third = list.get(k);
                    if (index[second].contains(third)&&index[first].contains(third)){
                        ans = Math.min(index[first].size() + index[second].size()+ index[third].size()-6,ans);
                        if (ans == 0){
                            return 0;
                        }
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE?-1:ans;
    }

    /**
     *时间
     * 详情
     * 31ms
     * 击败 47.32%使用 Java 的用户
     * 内存
     * 详情
     * 64.24MB
     * 击败 76.78%使用 Java 的用户
     * @param n
     * @param edges
     * @return
     */
    public int minTrioDegree2(int n, int[][] edges) {
        int[][] g = new int[n][n];
        int[] degree = new int[n];

        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            g[x][y] = g[y][x] = 1;
            ++degree[x];
            ++degree[y];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (g[i][j] == 1) {
                    for (int k = j + 1; k < n; ++k) {
                        if (g[i][k] == 1 && g[j][k] == 1) {
                            ans = Math.min(ans, degree[i] + degree[j] + degree[k] - 6);
                        }
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     *时间
     * 详情
     * 238ms
     * 击败 25.89%使用 Java 的用户
     * 内存
     * 详情
     * 67.93MB
     * 击败 18.75%使用 Java 的用户
     * @param n
     * @param edges
     * @return
     */
    public int minTrioDegree3(int n, int[][] edges) {
        // 原图
        Set<Integer>[] g = new Set[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashSet<Integer>();
        }
        // 定向后的图
        List<Integer>[] h = new List[n];
        for (int i = 0; i < n; ++i) {
            h[i] = new ArrayList<Integer>();
        }
        int[] degree = new int[n];

        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            g[x].add(y);
            g[y].add(x);
            ++degree[x];
            ++degree[y];
        }
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            if (degree[x] < degree[y] || (degree[x] == degree[y] && x < y)) {
                h[x].add(y);
            } else {
                h[y].add(x);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j : h[i]) {
                for (int k : h[j]) {
                    if (g[i].contains(k)) {
                        ans = Math.min(ans, degree[i] + degree[j] + degree[k] - 6);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}

