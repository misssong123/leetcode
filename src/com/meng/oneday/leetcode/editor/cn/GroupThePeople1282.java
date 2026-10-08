package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupThePeople1282 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了40.63% 的Java用户
     * 	内存消耗:46.6 MB,击败了12.50% 的Java用户
     * @param groupSizes
     * @return
     */
    public List<List<Integer>> groupThePeople1282(int[] groupSizes) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            map.computeIfAbsent(size,k->new ArrayList<>()).add(i);
            if (map.get(size).size()==size){
                res.add(new ArrayList<>(map.get(size)));
                map.get(size).clear();
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了50.00% 的Java用户
     * 	内存消耗:46.7 MB,击败了6.25% 的Java用户
     * @param gs
     * @return
     */
    public List<List<Integer>> groupThePeople(int[] gs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < gs.length; i++) {
            List<Integer> list = map.getOrDefault(gs[i], new ArrayList<>());
            list.add(i);
            map.put(gs[i], list);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int k : map.keySet()) {
            List<Integer> list = map.get(k), cur = new ArrayList<>();
            for (Integer integer : list) {
                cur.add(integer);
                if (cur.size() == k) {
                    ans.add(cur);
                    cur = new ArrayList<>();
                }
            }
        }
        return ans;
    }

}
