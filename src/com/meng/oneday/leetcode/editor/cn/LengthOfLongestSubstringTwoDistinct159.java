package com.meng.oneday.leetcode.editor.cn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class LengthOfLongestSubstringTwoDistinct159 {
    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了61.79% 的Java用户
     * 	内存消耗:44.4 MB,击败了68.77% 的Java用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct159(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int left = 0 ,count = 0,res = 0;
        for(int i = 0 ; i < s.length();i++){
            char c = s.charAt(i);
            if (map.getOrDefault(c,0) == 0){
                count++;
                map.put(c,1);
                if (count > 2){
                    res = Math.max(res,i-left);
                    while (count > 2){
                        char temp = s.charAt(left);
                        if (map.get(temp) == 1){
                            map.remove(temp);
                            count--;
                        }else{
                            map.put(temp,map.get(temp)-1);
                        }
                        left++;
                    }
                }
            }else {
                map.put(c,map.get(c)+1);
            }
        }
        res = Math.max(res,s.length()-left);
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:92 ms,击败了23.59% 的Java用户
     * 	内存消耗:44.5 MB,击败了56.48% 的Java用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;

        // 滑动窗口的左右指针
        int left = 0;
        int right = 0;
        // hashmap 中的字符 -> 它在滑动窗口中最靠右的位置
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            // 当滑动窗口包含小于 3 个字符
            hashmap.put(s.charAt(right), right++);

            // 滑动窗口包含 3 个字符
            if (hashmap.size() == 3) {
                // 删除最左边的字符
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // 删除滑动窗口的左指针
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }

}
