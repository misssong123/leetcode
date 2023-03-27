package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.*;


class MergeSimilarItems2363 {
    /**
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 39.30%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 48.42%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     * @param items1
     * @param items2
     * @return
     */
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer,Integer> cache = new TreeMap<>();
        for(int [] item : items1){
            cache.put(item[0],item[1]);
        }
        for (int[] item : items2){
            cache.put(item[0],cache.getOrDefault(item[0],0)+item[1]);
        }
        for (Map.Entry<Integer,Integer> entry : cache.entrySet()){
            List<Integer> list = new ArrayList<>();
            list.add(entry.getKey());
            list.add(entry.getValue());
            res.add(list);
        }
        return res;
    }

    /**
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 39.30%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 22.10%
     * 的用户
     * 通过测试用例：
     * 49 / 49
     * @param items1
     * @param items2
     * @return
     */
    public List<List<Integer>> mergeSimilarItems1(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int[] v : items1) {
            map.put(v[0], map.getOrDefault(v[0], 0) + v[1]);
        }
        for (int[] v : items2) {
            map.put(v[0], map.getOrDefault(v[0], 0) + v[1]);
        }

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey(), v = entry.getValue();
            List<Integer> pair = new ArrayList<Integer>();
            pair.add(k);
            pair.add(v);
            res.add(pair);
        }
        Collections.sort(res, (a, b) -> a.get(0) - b.get(0));
        return res;
    }


}
