package com.meng.level2.day19;

import java.util.*;

/**
 * 947. 移除最多的同行或同列石头(中等)
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 *
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 *
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 *
 *
 * 提示：
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * 不会有两块石头放在同一个坐标点上
 */
public class RemoveStones {
    //逻辑存在问题
    public int removeStones(int[][] stones) {
        int res = 0;
        int len = stones.length;
        Map<Integer,Integer> xCount = new HashMap<>();
        Map<Integer,Integer> yCount = new HashMap<>();
        Map<int[],Integer> cache = new HashMap<>();
        Map<Integer,List<int[]>> xNumCount = new HashMap<>();
        Map<Integer,List<int[]>> yNumCount = new HashMap<>();
        Map<int[],Integer> indexMap = new HashMap<>();
        for(int i = 0 ; i < len ; i ++){
            int[] stone = stones[i];
            int x = stone[0];
            int y = stone[1];
            xCount.put(x,xCount.getOrDefault(x,0)+1);
            yCount.put(y,yCount.getOrDefault(y,0)+1);
            if (xNumCount.get(x) == null){
                xNumCount.put(x,new ArrayList<>());
            }
            xNumCount.get(x).add(stone);
            if (yNumCount.get(y) == null){
                yNumCount.put(y,new ArrayList<>());
            }
            yNumCount.get(y).add(stone);
            indexMap.put(stone,i);
        }
        for(int i = 0 ; i < len ; i ++){
            int[] stone = stones[i];
            int x = stone[0];
            int y = stone[1];
            int count = xCount.get(x)-1 + yCount.get(y) -1;
            if (count > 0){
                cache.put(stone,count);
                continue;
            }
        }
        while (cache.size() > 0){
            int[] minStone = getMinStone(cache);
            res++;
            removeStone(minStone,xNumCount,yNumCount,cache);
        }
        return res;
    }
    //查询当前最小数据
    public int[] getMinStone(Map<int[],Integer> stones){
        int[] res = null;
        int count = Integer.MAX_VALUE;
        for(Map.Entry<int[],Integer> stone : stones.entrySet()){
            if (stone.getValue() < count){
                res = stone.getKey();
                count = stone.getValue();
            }
        }
        return res;
    }
    //移除相关的count
    public void removeStone(int[] stone,Map<Integer,List<int[]>> xNumCount,Map<Integer,List<int[]>> yNumCount,Map<int[],Integer> cache){
        int x = stone[0];
        int y = stone[1];
        List<int[]> list = xNumCount.get(x);
        for(int[] temp : list){
            Integer count = cache.get(temp);
            if (count == null){
                continue;
            }
            count--;
            if (count == 0){
                cache.remove(temp);
                continue;
            }
            cache.put(temp,count);
        }
        list = yNumCount.get(y);
        for(int[] temp : list){
            Integer count = cache.get(temp);
            if (count == null){
                continue;
            }
            count--;
            if (count == 0){
                cache.remove(temp);
                continue;
            }
            cache.put(temp,count);
        }
        cache.remove(stone);
    }

    public static void main(String[] args) {
        RemoveStones demo = new RemoveStones();
        //int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        int[][] stones = {{0,0},{0,2},{1,1},{2,0},{2,2}};
        System.out.println(demo.removeStones(stones));
    }
    /**
     *
     * @param stones
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 58.06%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 49.89%
     * 的用户
     * 通过测试用例：
     * 68 / 68
     */
    public int removeStones1(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            unionFind.union(stone[0] + 10001, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }

    private class UnionFind {

        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
                count++;
            }

            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            // 两个连通分量合并成为一个，连通分量的总数 -1
            count--;
        }
    }

}
