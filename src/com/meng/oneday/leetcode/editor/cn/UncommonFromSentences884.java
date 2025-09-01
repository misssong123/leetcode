package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UncommonFromSentences884 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了49.61% 的Java用户
     * 	内存消耗:41.5 MB,击败了33.86% 的Java用户
     * @param s1
     * @param s2
     * @return
     */
    public String[] uncommonFromSentences884(String s1, String s2) {
        Map<String,Integer> s1Map = new HashMap<>();
        Map<String,Integer> s2Map = new HashMap<>();
        for (String s : s1.split(" ")) {
            s1Map.put(s,s1Map.getOrDefault(s,0)+1);
        }
        for (String s : s2.split(" ")) {
            s2Map.put(s,s2Map.getOrDefault(s,0)+1);
        }
        List<String> list = new ArrayList<>();
        for (String s : s1Map.keySet()) {
            if (s1Map.get(s) == 1 && !s2Map.containsKey(s)) {
                list.add(s);
            }
        }
        for (String s : s2Map.keySet()) {
            if (s2Map.get(s) == 1 && !s1Map.containsKey(s)) {
                list.add(s);
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了8.66% 的Java用户
     * 	内存消耗:41.5 MB,击败了40.16% 的Java用户
     * @param s1
     * @param s2
     * @return
     */
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        String str = s1 + " " + s2;
        String[] ss = str.split(" ");
        for (String s : ss) map.put(s, map.getOrDefault(s, 0) + 1);
        List<String> list = new ArrayList<>();
        for (String s : map.keySet()) if (map.get(s) == 1) list.add(s);
        return list.toArray(new String[list.size()]);
    }

}
