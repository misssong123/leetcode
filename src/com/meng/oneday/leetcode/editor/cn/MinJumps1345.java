package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinJumps1345 {
    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了98.53% 的Java用户
     * 	内存消耗:79.7 MB,击败了48.53% 的Java用户
     * @param arr
     * @return
     */
    public int minJumps1345(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        boolean[] visited = new boolean[len];
        int step = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                int index = queue.poll();
                //前一个
                if (index > 0 && !visited[index - 1]) {
                    visited[index - 1] = true;
                    queue.add(index - 1);
                }
                //后一个
                if (index + 1 == len - 1) {
                    return step;
                }
                if (index < len - 1 && !visited[index + 1]) {
                    visited[index + 1] = true;
                    queue.add(index + 1);
                }
                //相同
                if (map.containsKey(arr[index])) {
                    for (int j : map.get(arr[index])) {
                        if (!visited[j]) {
                            if (j == len - 1) {
                                return step;
                            }
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
                //避免重复计算
                map.remove(arr[index]);
            }
            step++;
        }
        return step;
    }

    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了98.53% 的Java用户
     * 	内存消耗:76.9 MB,击败了79.41% 的Java用户
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> pos = new HashMap<>(); // 元素值 -> [下标]
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(arr[i], item -> new ArrayList<>()).add(i);
        }

        boolean[] vis = new boolean[n];
        vis[0] = true;
        List<Integer> q = Arrays.asList(0); // 起点

        for (int ans = 0; ; ans++) {
            List<Integer> tmp = q;
            q = new ArrayList<>();
            for (int i : tmp) {
                if (i == n - 1) { // 到达终点
                    return ans;
                }

                // 往右
                if (!vis[i + 1]) {
                    vis[i + 1] = true;
                    q.add(i + 1);
                }

                // 往左
                if (i > 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    q.add(i - 1);
                }

                // 访问所有元素值为 arr[i] 的点（下标）
                int x = arr[i];
                List<Integer> idx = pos.get(x);
                if (idx == null) { // 之前访问过
                    continue;
                }
                for (int j : idx) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.add(j);
                    }
                }
                pos.remove(x); // 避免重复访问
            }
        }
    }
}
