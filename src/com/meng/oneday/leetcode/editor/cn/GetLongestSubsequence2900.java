package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
class GetLongestSubsequence2900 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了40.87% 的Java用户
     * @param words
     * @param groups
     * @return
     */
    public List<String> getLongestSubsequence2900(String[] words, int[] groups) {
        List<String> res = new ArrayList<>();
        for(int i = 0 ; i < words.length ; i++){
            if(i > 0 && groups[i] == groups[i-1]){
                continue;
            }
            res.add(words[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了47.83% 的Java用户
     * 	内存消耗:44.2 MB,击败了33.04% 的Java用户
     * @param words
     * @param groups
     * @return
     */
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        int n = groups.length;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || groups[i] != groups[i + 1]) { // i 是连续相同段的末尾
                ans.add(words[i]);
            }
        }
        return ans;
    }

}
