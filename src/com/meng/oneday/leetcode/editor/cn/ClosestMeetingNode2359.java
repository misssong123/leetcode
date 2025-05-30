package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ClosestMeetingNode2359 {
    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了9.76% 的Java用户
     * 	内存消耗:55.4 MB,击败了100.00% 的Java用户
     * @param edges
     * @param node1
     * @param node2
     * @return
     */
    public int closestMeetingNode2359(int[] edges, int node1, int node2) {
        if (node1 == node2){
            return node1;
        }
        int[] node1Len = dfs(edges, node1);
        int[] node2Len = dfs(edges, node2);
        int res = -1;
        int min = edges.length;
        for(int i = 0 ; i < edges.length ; i++){
            if (node1Len[i] != -1 && node2Len[i] != -1){
                int max = Math.max(node1Len[i],node2Len[i]);
                if (max < min){
                    min = max;
                    res = i;
                }
            }
        }
        return res;
    }

    private int[] dfs(int[] edges, int node) {
        int len = edges.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        res[node] = 0 ;
        boolean[] visited = new boolean[len];
        visited[node] = true;
        List<Integer> list = new ArrayList<>();
        list.add(node);
        int path = 1;
        while (!list.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int num : list) {
                if (edges[num] !=-1 && !visited[edges[num]]) {
                    temp.add(edges[num]);
                    visited[edges[num]] = true;
                    res[edges[num]] = path;
                }
            }
            list = temp;
            path++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了97.56% 的Java用户
     * 	内存消耗:56.3 MB,击败了59.76% 的Java用户
     * @param edges
     * @param node1
     * @param node2
     * @return
     */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] dis1 = calcDis(edges, node1);
        int[] dis2 = calcDis(edges, node2);

        int n = edges.length;
        int minDis = n;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int d = Math.max(dis1[i], dis2[i]);
            if (d < minDis) {
                minDis = d;
                ans = i;
            }
        }
        return ans;
    }

    private int[] calcDis(int[] edges, int x) {
        int n = edges.length;
        int[] dis = new int[n];
        Arrays.fill(dis, n); // n 表示无法到达或者尚未访问的节点
        // 从 x 出发，直到无路可走（x=-1）或者重复访问节点（dis[x]<n）
        for (int d = 0; x >= 0 && dis[x] == n; x = edges[x]) {
            dis[x] = d++;
        }
        return dis;
    }


}
