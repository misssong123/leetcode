package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class MinReorder1466 {
    /**
     * 解答成功:
     * 	执行耗时:112 ms,击败了6.80% 的Java用户
     * 	内存消耗:76.2 MB,击败了5.99% 的Java用户
     * @param n
     * @param connections
     * @return
     */
    public int minReorderMy(int n, int[][] connections) {
        List<Integer>[] cache = new List[n];
        for(int i = 0 ; i < n ; i++){
            cache[i] = new ArrayList<>();
        }
        Set<String> routes = new HashSet<>();
        for(int[] conn : connections){
            cache[conn[0]].add(conn[1]);
            cache[conn[1]].add(conn[0]);
            routes.add(conn[0]+"#"+conn[1]);
        }
        boolean[] flags = new boolean[n];
        flags[0] = true;
        List<Integer> list = new ArrayList<>(cache[0]);
        int res = 0;
        for(int nums : list){
            if (!routes.contains(nums+"#"+0)){
                res++;
            }
        }
        while (list.size() > 0){
            List<Integer> temp = new ArrayList<>();
            for(int num: list){
                if (flags[num]){
                    continue;
                }
                flags[num] = true;
                for (int m : cache[num]){
                    if (flags[m]){
                        continue;
                    }
                    temp.add(m);
                    if (!routes.contains(m+"#"+num)){
                        res++;
                    }
                }
            }
            list.clear();
            list.addAll(temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了89.34% 的Java用户
     * 	内存消耗:69.8 MB,击败了45.61% 的Java用户
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<int[]>();
        }
        for (int[] edge : connections) {
            e[edge[0]].add(new int[]{edge[1], 1});
            e[edge[1]].add(new int[]{edge[0], 0});
        }
        return dfs(0, -1, e);
    }

    public int dfs(int x, int parent, List<int[]>[] e) {
        int res = 0;
        for (int[] edge : e[x]) {
            if (edge[0] == parent) {
                continue;
            }
            res += edge[1] + dfs(edge[0], x, e);
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
