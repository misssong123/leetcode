package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class AvoidFlood1488 {
    /**
     * 解答成功:
     * 	执行耗时:88 ms,击败了18.43% 的Java用户
     * 	内存消耗:61 MB,击败了46.45% 的Java用户
     * @param rains
     * @return
     */
    public int[] avoidFlood1488(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Arrays.fill(res,-1);
        //存储为0的下标
        TreeSet<Integer> zero = new TreeSet<>();
        //存在的湖泊
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            if (rains[i] == 0){
                zero.add(i);
            }else {
                if(map.containsKey(rains[i])){
                    //找到第一个比当前湖泊下雨时间大的0
                    Integer index = zero.higher(map.get(rains[i]));
                    if (index == null){
                        return new int[0];
                    }
                    res[index] = rains[i];
                    zero.remove(index);
                    map.put(rains[i],i);
                }else {
                    map.put(rains[i],i);
                }
            }
        }
        for(int num : zero){
            res[num] = 1;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:70 ms,击败了75.87% 的Java用户
     * 	内存消耗:61.1 MB,击败了37.26% 的Java用户
     * @param rains
     * @return
     */
    public int[] avoidFloodOther(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> fullDay = new HashMap<>(); // lake -> 装满日
        TreeSet<Integer> dryDay = new TreeSet<>(); // 未被使用的抽水日
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                ans[i] = 1; // 先随便选一个湖抽干
                dryDay.add(i); // 保存抽水日
                continue;
            }
            Integer j = fullDay.get(lake);
            if (j != null) {
                // 必须在 j 之后，i 之前把 lake 抽干
                // 选一个最早的未被使用的抽水日，如果选晚的，可能会导致其他湖没有可用的抽水日
                Integer d = dryDay.higher(j);
                if (d == null) {
                    return new int[]{}; // 无法阻止洪水
                }
                ans[d] = lake;
                dryDay.remove(d); // 移除已使用的抽水日
            }
            ans[i] = -1;
            fullDay.put(lake, i); // 插入或更新装满日
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了97.86% 的Java用户
     * 	内存消耗:58.2 MB,击败了96.50% 的Java用户
     * @param rains
     * @return
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }

        int[] ans = new int[n];
        Map<Integer, Integer> fullDay = new HashMap<>(); // lake -> 装满日
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                ans[i] = 1; // 先随便选一个湖抽干
                continue;
            }
            Integer j = fullDay.get(lake);
            if (j != null) {
                // 必须在 j 之后，i 之前把 lake 抽干
                // 选一个最早的未被使用的抽水日，如果选晚的，可能会导致其他湖没有可用的抽水日
                int dryDay = find(j + 1, fa);
                if (dryDay >= i) {
                    return new int[]{}; // 无法阻止洪水
                }
                ans[dryDay] = lake;
                fa[dryDay] = find(dryDay + 1, fa); // 删除 dryDay
            }
            ans[i] = -1;
            fa[i] = i + 1; // 删除 i
            fullDay.put(lake, i); // 插入或更新装满日
        }
        return ans;
    }

    private int find(int x, int[] fa) {
        if (fa[x] != x) {
            fa[x] = find(fa[x], fa);
        }
        return fa[x];
    }

}
