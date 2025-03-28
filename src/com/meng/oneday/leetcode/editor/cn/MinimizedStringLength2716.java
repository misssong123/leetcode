package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class MinimizedStringLength2716 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了45.92% 的Java用户
     * 	内存消耗:44.1 MB,击败了38.78% 的Java用户
     * @param s
     * @return
     */
    public int minimizedStringLengthArr(String s) {
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
            cnt[c - 'a']=1;
        }
        int ans = 0;
        for (int num : cnt) {
            ans += num;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了18.37% 的Java用户
     * 	内存消耗:44.6 MB,击败了5.10% 的Java用户
     * @param s
     * @return
     */
    public int minimizedStringLengthSet(String s) {
        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray()){
            set.add(c);
        }
        return set.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了5.10% 的Java用户
     * 	内存消耗:44.5 MB,击败了7.14% 的Java用户
     * @param s
     * @return
     */
    public int minimizedStringLengthDis(String s) {
        return (int) s.chars().distinct().count();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了95.92% 的Java用户
     * 	内存消耗:43.8 MB,击败了87.76% 的Java用户
     * @param s
     * @return
     */
    public int minimizedStringLength(String s) {
        int mask = 0;
        for (char c : s.toCharArray()) {
            mask |= 1 << (c - 'a'); // 把 c-'a' 加到集合中
        }
        return Integer.bitCount(mask); // 集合的大小
    }


}
