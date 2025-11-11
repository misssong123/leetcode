package com.meng.hot100.leetcode.editor.cn;

import java.util.*;

class CalcEquationLCR111 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了80.30% 的Java用户
     * 	内存消耗:45.5 MB,击败了6.06% 的Java用户
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquationLCR111(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String,Double>> map = new HashMap<>();
        Set<String> exist = new HashSet<>();
        for (int i = 0 ; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            exist.add(a);
            exist.add(b);
            map.putIfAbsent(a, new HashMap<>());
            map.putIfAbsent(b, new HashMap<>());
            map.get(a).put(b, values[i]);
            map.get(b).put(a, 1.0 / values[i]);
        }
        double[] res = new double[queries.size()];
        for(int i = 0 ; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!exist.contains(a) || !exist.contains(b)){
                res[i] = -1;
                continue;
            }
            if (map.get(a).containsKey(b)){
                res[i] = map.get(a).get(b);
                continue;
            }
            res[i] = dfs(a, b, map);
        }
        return res;
    }

    private double dfs(String a, String b, Map<String, Map<String,Double>> map) {
        Set<String> visited = new HashSet<>();
        List<String> list = new ArrayList<>();
        list.add(a);
        while (true){
            List<String> temp = new ArrayList<>();
            for(String str : list){
                for(String key : map.get(str).keySet()){
                    if (!visited.contains(key)){
                        temp.add(key);
                        visited.add(key);
                        if (!Objects.equals(a,str)){
                            map.get(a).put(key, map.get(str).get(key) * map.get(a).get(str));
                            map.get(key).put(a,1.0/map.get(a).get(key));
                        }
                    }
                }
                if (map.get(a).containsKey(b)){
                    return map.get(a).get(b);
                }
            }
            list = temp;
            if (list.isEmpty()){
                return -1;
            }
        }
    }
}
class UnionFind {
    private final int[] fa; // 代表元
    public final double[] mul; // x 的值 * mul[x] = x 的代表元的值

    public UnionFind(int n) {
        fa = new int[n];
        // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
        // 集合 i 的代表元是自己，自己 * 1 = 自己
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }

        mul = new double[n];
        Arrays.fill(mul, 1);
    }

    // 返回 x 所在集合的代表元
    // 同时做路径压缩，也就是把 x 所在集合中的所有元素的 fa 都改成代表元
    private int find(int x) {
        if (fa[x] != x) {
            int root = find(fa[x]);
            mul[x] *= mul[fa[x]]; // 更新 x 到其代表元的 mul 值
            fa[x] = root;
        }
        return fa[x];
    }

    // 判断 x 和 y 是否在同一个集合
    public boolean same(int x, int y) {
        // 如果 x 的代表元和 y 的代表元相同，那么 x 和 y 就在同一个集合
        // 这就是代表元的作用：用来快速判断两个元素是否在同一个集合
        return find(x) == find(y);
    }

    // 合并 from 和 to，新增信息 to / from = value
    // 其中 to 和 from 表示未知量，下文的 x 和 y 也表示未知量
    public void merge(int from, int to, double value) {
        int x = find(from);
        int y = find(to);
        if (x == y) { // from 和 to 在同一个集合，不做合并
            return;
        }
        //    x --------- y
        //   /           /
        // from ------- to
        // 已知 x/from = mul[from] 和 y/to = mul[to]，现在合并 from 和 to，新增信息 to/from = value
        // 由于 y/from = (y/x) * (x/from) = (y/to) * (to/from)
        // 所以 y/x = (y/to) * (to/from) / (x/from) = mul[to] * value / mul[from]
        mul[x] = mul[to] * value / mul[from];
        fa[x] = y;
    }
}

/**
 * 解答成功:
 * 	执行耗时:1 ms,击败了80.30% 的Java用户
 * 	内存消耗:45.5 MB,击败了6.06% 的Java用户
 */
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 把不同字符串映射为不同的数字，方便使用并查集
        Map<String, Integer> variableToId = new HashMap<>();
        for (List<String> equation : equations) {
            for (String s : equation) {
                variableToId.putIfAbsent(s, variableToId.size());
            }
        }

        // 初始化并查集
        UnionFind uf = new UnionFind(variableToId.size());
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            uf.merge(variableToId.get(equation.get(1)), variableToId.get(equation.get(0)), values[i]);
        }

        // 回答询问
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            Integer c = variableToId.get(query.get(0));
            Integer d = variableToId.get(query.get(1));
            if (c != null && d != null && uf.same(c, d)) {
                //    c * mul[c] = d * mul[d] = 代表元的值
                // => c / d = mul[d] / mul[c]
                ans[i] = uf.mul[d] / uf.mul[c];
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }
}
