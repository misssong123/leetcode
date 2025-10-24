package com.meng.hot100.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class LengthOfLongestSubstring3 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了41.20% 的Java用户
     * 	内存消耗:43.7 MB,击败了69.46% 的Java用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int l = 0,max =0;
        Set<Character> set = new HashSet<>();
        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            if (!set.add(c)){
                max = Math.max(max,set.size());
                while (set.contains(c)){
                    set.remove(s.charAt(l++));
                }
                set.add(c);
            }
        }
        return Math.max(max,set.size());
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了98.11% 的Java用户
     * @param S
     * @return
     */
    public int lengthOfLongestSubstring(String S) {
        char[] s = S.toCharArray(); // 转换成 char[] 加快效率（忽略带来的空间消耗）
        int n = s.length;
        int ans = 0;
        int left = 0;
        boolean[] has = new boolean[128]; // 也可以用 HashSet<Character>，这里为了效率用的数组
        for (int right = 0; right < n; right++) {
            char c = s[right];
            // 如果窗口内已经包含 c，那么再加入一个 c 会导致窗口内有重复元素
            // 所以要在加入 c 之前，先移出窗口内的 c
            while (has[c]) { // 窗口内有 c
                has[s[left]] = false;
                left++; // 缩小窗口
            }
            has[c] = true; // 加入 c
            ans = Math.max(ans, right - left + 1); // 更新窗口长度最大值
        }
        return ans;
    }
}
