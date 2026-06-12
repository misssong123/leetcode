package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InvalidTransactions1169 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了93.75% 的Java用户
     * 	内存消耗:46.2 MB,击败了100.00% 的Java用户
     * @param transactions
     * @return
     */
    public List<String> invalidTransactions1169(String[] transactions) {
        //记录同名交易
        Map<String, Map<String,List<Integer>>> map = new HashMap<>();
        //初始化
        for (String transaction : transactions) {
            String[] split = transaction.split(",");
            String name = split[0];
            int time = Integer.parseInt(split[1]);
            String city = split[3];
            map.computeIfAbsent(name,(k)->new HashMap<>()).computeIfAbsent(city,(k)->new ArrayList<>()).add(time);
        }
        List<String> res = new ArrayList<>();
        //遍历
        for (String transaction : transactions) {
            String[] split = transaction.split(",");
            String name = split[0];
            int time = Integer.parseInt(split[1]);
            String city = split[3];
            int money = Integer.parseInt(split[2]);
            if (money > 1000 || containsOther(map.get(name),city,time)){
                res.add(transaction);
            }
        }
        return res;

    }

    private boolean containsOther(Map<String, List<Integer>> stringListMap,
                                  String city, int time) {
        for (Map.Entry<String, List<Integer>> entry : stringListMap.entrySet()) {
            if (!entry.getKey().equals(city)){
                for (Integer integer : entry.getValue()) {
                    if (Math.abs(integer-time)<=60){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
