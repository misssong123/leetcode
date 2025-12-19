package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class FindAllPeople2092 {
    /**
     * 解答成功:
     * 	执行耗时:2460 ms,击败了5.07% 的Java用户
     * 	内存消耗:200.8 MB,击败了5.08% 的Java用户
     * @param n
     * @param meetings
     * @param firstPerson
     * @return
     */
    public List<Integer> findAllPeople2092(int n, int[][] meetings, int firstPerson) {
        //初始化知道秘密的人
        Set<Integer> set = new HashSet<>();
        set.add(firstPerson);
        set.add(0);
        //初始化并查集
        int[] parent = new int[n];
        for(int i = 0 ; i < n ; i++){
            parent[i] = i;
        }
        //按照会议时间进行排序
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        for(int i = 0 ; i < meetings.length ; i++){
            //统计相同时间的会议
            List<Integer> list = new ArrayList<>();
            list.add(i);
            while (i + 1 < meetings.length && meetings[i][2] == meetings[i + 1][2]){
                list.add(++i);
            }
            if (list.size() == 1){
                if (set.contains(meetings[i][0]) || set.contains(meetings[i][1])){
                    set.add(meetings[i][0]);
                    set.add(meetings[i][1]);
                }
            }else{
                //形成并查集
                for (Integer value : list) {
                    int x = find(meetings[value][0],parent);
                    int y = find(meetings[value][1],parent);
                    if (x != y){
                        parent[x] = y;
                    }
                }
                //合并集合
                Map<Integer,Set<Integer>> map = new HashMap<>(list.size()/2);
                for (Integer integer : list) {
                    int a = find(meetings[integer][0], parent);
                    int b = find(meetings[integer][1], parent);
                    map.computeIfAbsent(a, k -> new HashSet<>()).add(meetings[integer][0]);
                    map.computeIfAbsent(b, k -> new HashSet<>()).add(meetings[integer][1]);
                }
                for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
                    if(entry.getValue().stream().anyMatch(set::contains)){
                        set.addAll(entry.getValue());
                    }else{
                        for (int index : entry.getValue()) {
                            parent[index] = index;
                        }
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
    private int find(int x,int[] parent){
        int origin = x;
        while (parent[x] != -1 && parent[x] != x){
            x = parent[x];
        }
        parent[origin] = x;
        return x;
    }

    public List<Integer> findAllPeopleOther(int n, int[][] meetings, int firstPerson) {
        // 按照 time 从小到大排序
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        // 一开始 0 和 firstPerson 都知道秘密
        Set<Integer> haveSecret = new HashSet<>();
        haveSecret.add(0);
        haveSecret.add(firstPerson);

        // 分组循环
        int m = meetings.length;
        for (int i = 0; i < m;) {
            // 在同一时间发生的会议，建图
            Map<Integer, List<Integer>> g = new HashMap<>();
            int time = meetings[i][2];
            for (; i < m && meetings[i][2] == time; i++) {
                int x = meetings[i][0];
                int y = meetings[i][1];
                g.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                g.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
            }

            // 每个连通块只要有一个人知道秘密，那么整个连通块的人都知道秘密
            Set<Integer> vis = new HashSet<>(); // 避免重复访问节点
            for (int x : g.keySet()) {
                // 从知道秘密的专家出发，DFS 标记其余专家
                if (haveSecret.contains(x) && !vis.contains(x)) {
                    dfs(x, g, vis, haveSecret);
                }
            }
        }

        // 可以按任何顺序返回答案
        return new ArrayList<>(haveSecret);
    }

    private void dfs(int x, Map<Integer, List<Integer>> g, Set<Integer> vis, Set<Integer> haveSecret) {
        vis.add(x);
        haveSecret.add(x);
        for (int y : g.get(x)) {
            if (!vis.contains(y)) {
                dfs(y, g, vis, haveSecret);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了43.48% 的Java用户
     * 	内存消耗:175.5 MB,击败了10.87% 的Java用户
     * @param n
     * @param meetings
     * @param firstPerson
     * @return
     */
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 按照 time 从小到大排序
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        UnionFind uf = new UnionFind(n);
        // 一开始 0 和 firstPerson 都知道秘密
        uf.merge(firstPerson, 0);

        // 分组循环
        int m = meetings.length;
        for (int i = 0; i < m; ) {
            int start = i;
            int time = meetings[i][2];
            // 合并在同一时间发生的会议
            for (; i < m && meetings[i][2] == time; i++) {
                uf.merge(meetings[i][0], meetings[i][1]);
            }

            // 如果节点不和 0 在同一个集合，那么撤销合并，恢复成初始值
            for (int j = start; j < i; j++) {
                int x = meetings[j][0];
                int y = meetings[j][1];
                if (!uf.isSame(x, 0)) {
                    uf.reset(x);
                }
                if (!uf.isSame(y, 0)) {
                    uf.reset(y);
                }
            }
        }

        // 和 0 在同一个集合的专家都知道秘密
        List<Integer> ans = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (uf.isSame(k, 0)) {
                ans.add(k);
            }
        }
        return ans;
    }

}


class UnionFind {
    private final int[] fa; // 代表元

    UnionFind(int n) {
        // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
        // 集合 i 的代表元是自己
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
    }

    // 返回 x 所在集合的代表元
    // 同时做路径压缩，也就是把 x 所在集合中的所有元素的 fa 都改成代表元
    public int find(int x) {
        // 如果 fa[x] == x，则表示 x 是代表元
        if (fa[x] != x) {
            fa[x] = find(fa[x]); // fa 改成代表元
        }
        return fa[x];
    }

    // 判断 x 和 y 是否在同一个集合
    public boolean isSame(int x, int y) {
        // 如果 x 的代表元和 y 的代表元相同，那么 x 和 y 就在同一个集合
        // 这就是代表元的作用：用来快速判断两个元素是否在同一个集合
        return find(x) == find(y);
    }

    // 把 from 所在集合合并到 to 所在集合中
    public void merge(int from, int to) {
        int x = find(from);
        int y = find(to);
        fa[x] = y; // 合并集合
    }

    public void reset(int x) {
        fa[x] = x;
    }
}
