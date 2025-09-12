package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class LongestSubstring395 {
    /**
     * 解答成功:
     * 	执行耗时:323 ms,击败了5.06% 的Java用户
     * 	内存消耗:41.5 MB,击败了38.68% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring395(String s, int k) {
        if (k > s.length()){
            return 0;
        }
        if(k < 2){
            return s.length();
        }
        int max = 0;
        //记录每个元素的下标
        Map<Character, List<Integer>> cache = new HashMap<>();
        for(int i = 0 ; i < s.length(); i++){
            cache.computeIfAbsent(s.charAt(i),key -> new ArrayList<>()).add(i);
            max = Math.max(max,cache.get(s.charAt(i)).size());
        }
        if (max < k){
            return 0;
        }
        //如果只有一个元素，则直接返回
        if (cache.size() == 1){
            return s.length();
        }
        if (k == s.length()){
            return 0;
        }
        char[] chars = s.toCharArray();
        //从后往前遍历
        for (int i = s.length(); i >= k ; i--){
            if (check(i,k,cache,chars)){
                return i ;
            }
        }
        return 0;
    }

    private boolean check(int n,int k, Map<Character, List<Integer>> cache, char[] chars) {
        Set<Character> set = new HashSet<>();
        for(int i = 0 ; i <= chars.length - n ; i++){
            if (cache.get(chars[i]).size() < k){
                continue;
            }
            boolean flag = true;
            for(int j = i ; j < i + n ; j++){
                char c = chars[j];
                if (set.contains(c)){
                    continue;
                }
                set.add(c);
                //获取当前元素的下标
                List<Integer> list = cache.get(c);
                int index = list.indexOf(j);
                if (index + k > list.size() || list.get(index + k -1) > i + n){
                    i = j;
                    flag = false;
                    break;
                }
            }
            if (flag){
                return true;
            }
            set.clear();
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了37.72% 的Java用户
     * 	内存消耗:41.7 MB,击败了34.11% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringOther(String s, int k) {
        if (s.length() < k) return 0;
        HashMap<Character, Integer> counter = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c : counter.keySet()) {
            if (counter.get(c) < k) {
                int res = 0;
                for (String t : s.split(String.valueOf(c))) {
                    res = Math.max(res, longestSubstringOther(t, k));
                }
                return res;
            }
        }
        return s.length();
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了24.08% 的Java用户
     * 	内存消耗:40.7 MB,击败了71.43% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int ret = 0;
        int n = s.length();
        for (int t = 1; t <= 26; t++) {
            int l = 0, r = 0;
            int[] cnt = new int[26];
            int tot = 0;
            int less = 0;
            while (r < n) {
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    tot++;
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    less--;
                }

                while (tot > t) {
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        tot--;
                        less--;
                    }
                    l++;
                }
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

}
