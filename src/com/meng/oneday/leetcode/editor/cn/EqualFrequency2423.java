package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class EqualFrequency2423 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了46.88% 的Java用户
     * @param word
     * @return
     */
    public boolean equalFrequency2423(String word) {
        int[] cnts = new int[26];
        for (char c : word.toCharArray()) {
            cnts[c - 'a']++;
        }
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for (int cnt : cnts) {
            if (cnt!=0){
                map.put(cnt,map.getOrDefault(cnt,0)+1);
                max = Math.max(max,cnt);
            }
        }
        //只有一个字母
        if (map.size()==1 ){
            return map.containsKey(1) || map.get(max)==1;
        }
        if (map.size()==2){
            if (map.containsKey(1) && map.get(1)==1){
                return true;
            }
            return map.get(max) == 1 && map.containsKey(max - 1);

        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了34.38% 的Java用户
     * 	内存消耗:42.2 MB,击败了46.88% 的Java用户
     * @param word
     * @return
     */
    public boolean equalFrequency(String word) {
        Map<Character, Integer> mCnt = new HashMap<Character, Integer>();
        for (char c : word.toCharArray())
            mCnt.merge(c, 1, Integer::sum);
        List<Integer> cnt = new ArrayList<>(mCnt.values());
        Collections.sort(cnt); // 出现次数从小到大排序
        int m = cnt.size();
        // 只有一种字符 or 去掉次数最少的 or 去掉次数最多的
        return m == 1 ||
                cnt.get(0) == 1 && isSame(cnt.subList(1, m)) ||
                cnt.get(m - 1) == cnt.get(m - 2) + 1 && isSame(cnt.subList(0, m - 1));
    }

    private boolean isSame(List<Integer> cnt) {
        int c0 = cnt.get(0);
        for (int c : cnt)
            if (c != c0)
                return false;
        return true;
    }

}
