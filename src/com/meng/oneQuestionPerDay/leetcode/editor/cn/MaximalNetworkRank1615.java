package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class MaximalNetworkRank1615 {
    /**
     * 执行用时：
     * 27 ms
     * , 在所有 Java 提交中击败了
     * 10.87%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 79.56%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads.length <= 1){
            return roads.length;
        }
        Map<Integer, List<Integer>> cache = new HashMap<>();
        for(int [] road : roads){
            List<Integer> list1 = cache.getOrDefault(road[0], new ArrayList<>());
            list1.add(road[1]);
            cache.put(road[0],list1);
            List<Integer> list2 = cache.getOrDefault(road[1], new ArrayList<>());
            list2.add(road[0]);
            cache.put(road[1],list2);
        }
        TreeMap<Integer,List<Integer>> treeMap = new TreeMap<>((o1, o2) -> o2 - o1);
        for(Map.Entry<Integer, List<Integer>> entry : cache.entrySet()){
            List<Integer> list = treeMap.getOrDefault(entry.getValue().size(),new ArrayList<>());
            list.add(entry.getKey());
            treeMap.put(entry.getValue().size(),list);
        }
        int res = 0;
        int count = 0;
        int first = -1;
        for(Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()){
            if (count == 0){
                List<Integer> value = entry.getValue();
                if (value.size() == 1){
                    first = value.get(0);
                    res = entry.getKey();
                }else if (value.size() == 2){
                    int diff = cache.get(value.get(0)).contains(value.get(1))?1:0;
                    return entry.getKey()*2-diff;
                }else {
                    for(int index : value){
                        List<Integer> list = cache.get(index);
                        for(int num : value){
                            if (index == num){
                                continue;
                            }
                            if (!list.contains(num)){
                                return entry.getKey()*2;
                            }
                        }
                    }
                    return entry.getKey()*2 -1;
                }
            }
            if (count == 1){
                List<Integer> list = cache.get(first);
                for(int index : entry.getValue()){
                    if (!list.contains(index)){
                        return res + entry.getKey();
                    }
                }
                return res + entry.getKey()-1;
            }
            count++;
        }
        return res;
    }

    public static void main(String[] args) {
       MaximalNetworkRank1615 demo = new MaximalNetworkRank1615();
       int n = 8 ;
       int[][] nums = {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
       System.out.println(demo.maximalNetworkRank(n,nums));
    }

    /**
     * 方法一：枚举
     *
     * 思路与算法
     *
     * 根据题意可知，两座不同城市构成的城市对的网络秩定义为：与这两座城市直接相连的道路总数，这两座城市之间的道路只计算一次。假设城市
     * x
     * x 的度数为
     * degree
     * [
     * x
     * ]
     * degree[x]，则此时我们可以知道城市对
     * (
     * i
     * ,
     * j
     * )
     * (i,j) 的网络秩为如下：
     *
     * 如果
     * i
     * i 与
     * j
     * j 之间没有道路连接，则此时
     * (
     * i
     * ,
     * j
     * )
     * (i,j) 的网络秩为
     * degree
     * [
     * i
     * ]
     * +
     * degree
     * [
     * j
     * ]
     * degree[i]+degree[j]；
     * 如果
     * i
     * i 与
     * j
     * j 之间存在道路连接，则此时
     * (
     * i
     * ,
     * j
     * )
     * (i,j) 的网络秩为
     * degree
     * [
     * i
     * ]
     * +
     * degree
     * [
     * j
     * ]
     * −
     * 1
     * degree[i]+degree[j]−1；
     * 根据以上求网络秩的方法，我们首先求出所有城市在图中的度数，然后枚举所有可能的城市对
     * (
     * i
     * ,
     * j
     * )
     * (i,j)，求出城市对
     * (
     * i
     * ,
     * j
     * )
     * (i,j) 的网络秩，即可找到最大的网络秩。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/maximal-network-rank/solution/zui-da-wang-luo-zhi-by-leetcode-solution-x4gx/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 91.30%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 89.13%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     */
    public int maximalNetworkRank1(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] v : roads) {
            connect[v[0]][v[1]] = true;
            connect[v[1]][v[0]] = true;
            degree[v[0]]++;
            degree[v[1]]++;
        }

        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j] - (connect[i][j] ? 1 : 0);
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

    /**
     *执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 34.78%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank2(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0], y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }

        int first = -1, second = -2;
        List<Integer> firstArr = new ArrayList<Integer>();
        List<Integer> secondArr = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] > first) {
                second = first;
                secondArr = new ArrayList<Integer>(firstArr);
                first = degree[i];
                firstArr.clear();
                firstArr.add(i);
            } else if (degree[i] == first) {
                firstArr.add(i);
            } else if (degree[i] > second){
                secondArr.clear();
                second = degree[i];
                secondArr.add(i);
            } else if (degree[i] == second) {
                secondArr.add(i);
            }
        }
        if (firstArr.size() == 1) {
            int u = firstArr.get(0);
            for (int v : secondArr) {
                if (!connect[u][v]) {
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            int m = roads.length;
            if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
                return first * 2;
            }
            for (int u : firstArr) {
                for (int v : firstArr) {
                    if (u != v && !connect[u][v]) {
                        return first * 2;
                    }
                }
            }
            return first * 2 - 1;
        }
    }

    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 91.30%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 87.39%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank3(int n, int[][] roads) {
        int[] degree = new int[n]; // 保存每个城市与其他城市相连的道路总数
        boolean[][] isConnected = new boolean[n][n]; // 保存每对城市是否相连
        for (int[] road : roads) {
            int city1 = road[0];
            int city2 = road[1];
            degree[city1]++;
            degree[city2]++;
            isConnected[city1][city2] = true;
            isConnected[city2][city1] = true;
        }
        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j];
                if (isConnected[i][j]) {
                    rank--;
                }
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

}
