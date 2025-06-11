package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class FindTheLongestSubstring1371 {
    /**
     * 解答成功:
     * 	执行耗时:49 ms,击败了26.09% 的Java用户
     * 	内存消耗:48.7 MB,击败了11.74% 的Java用户
     * @param s
     * @return
     */
    public int findTheLongestSubstring1371(String s) {
        int[] nums = new int[5];
        int[] base = {1,10,100,1000,10000};
        Map<Character,Integer> indexMap = new HashMap<>();
        indexMap.put('a',0);
        indexMap.put('e',1);
        indexMap.put('i',2);
        indexMap.put('o',3);
        indexMap.put('u',4);
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int num = 0;
        int res = 0;
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if (indexMap.containsKey(c)){
                int index = indexMap.get(c);
                nums[index] = nums[index] ^ 1;
                num += (nums[index] == 1 ? 1 : -1) * base[index];
                if (map.containsKey(num)){
                    res = Math.max(res,i-map.get(num));
                }else{
                    map.put(num,i);
                }
            }else {//janrudfu
                res = Math.max(res,i-map.get(num));
            }
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了66.96% 的Java用户
     * 	内存消耗:44.3 MB,击败了85.65% 的Java用户
     * @param s
     * @return
     */
    public int findTheLongestSubstringOfficial(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }
}
