package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class WordBreak_139 {
    public static void main(String[] args) {
        WordBreak_139 demo = new WordBreak_139();
        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aaaa", "aa");
        System.out.println(demo.wordBreak139(s, wordDict));
    }
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了66.97% 的Java用户
     * 	内存消耗:45.3 MB,击败了6.92% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak139(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        int[] cache = new int[n+1];
        Arrays.fill(cache,-1);
        return wordDict(s,set,n,cache) == 1;
    }

    private int wordDict(String s, Set<String> set, int index,int[] cache) {
        if (index == 0){
            return 1;
        }
        if (cache[index] != -1){
            return cache[index];
        }
        for (int i = index - 1 ;i >= 0; i--){
            if (set.contains(s.substring(i,index)) && wordDict(s,set,i,cache) == 1){
                return cache[index] = 1;
            }
        }
        return cache[index] = 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.67% 的Java用户
     * 	内存消耗:42.5 MB,击败了69.47% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakOther(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        Set<String> words = new HashSet<>(wordDict);

        int n = s.length();
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(n, maxLen, s, words, memo) == 1;
    }

    private int dfs(int i, int maxLen, String s, Set<String> words, int[] memo) {
        if (i == 0) { // 成功拆分！
            return 1;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }
        for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
            if (words.contains(s.substring(j, i)) && dfs(j, maxLen, s, words, memo) == 1) {
                return memo[i] = 1; // 记忆化
            }
        }
        return memo[i] = 0; // 记忆化
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了90.96% 的Java用户
     * 	内存消耗:42.2 MB,击败了73.37% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        Set<String> words = new HashSet<>(wordDict);

        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
                if (f[j] && words.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
