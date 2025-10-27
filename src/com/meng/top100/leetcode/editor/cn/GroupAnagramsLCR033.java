package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class GroupAnagramsLCR033 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了63.75% 的Java用户
     * 	内存消耗:46.6 MB,击败了91.88% 的Java用户
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsLCR033(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了99.06% 的Java用户
     * 	内存消耗:46.9 MB,击败了35.31% 的Java用户
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            // 把 s 排序，作为哈希表的 key
            char[] sortedS = s.toCharArray();
            Arrays.sort(sortedS);
            // 排序后相同的字符串分到同一组
            // computeIfAbsent：如果 key 不在哈希表中，则插入一个新的 ArrayList
            m.computeIfAbsent(new String(sortedS), key -> new ArrayList<>()).add(s);
        }
        // 哈希表的 value 保存分组后的结果
        return new ArrayList<>(m.values());
    }
}
