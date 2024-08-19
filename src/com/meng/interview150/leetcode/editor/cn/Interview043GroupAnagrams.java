package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview043GroupAnagrams {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了34.03% 的Java用户
     * 	内存消耗:47.2 MB,击败了12.57% 的Java用户
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsMy(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        int index = 0;
        Map<String,Integer> cache = new HashMap<>();
        for (String str : strs) {
            String s = getStr(str);
            if (cache.containsKey(s)){
                res.get(cache.get(s)).add(str);
            }else {
                cache.put(s,index++);
                List<String> list = new ArrayList<>();
                list.add(str);
                res.add(list);
            }
        }
        return res;
    }

    private String getStr(String str) {
        int[] chars = new int[26];
        for (char c : str.toCharArray()) {
            chars[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i]; j++) {
                sb.append((char) ('a'+i));
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了98.30% 的Java用户
     * 	内存消耗:46.6 MB,击败了87.69% 的Java用户
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
