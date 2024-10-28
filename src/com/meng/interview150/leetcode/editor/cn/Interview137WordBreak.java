package com.meng.interview150.leetcode.editor.cn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Interview137WordBreak {
    boolean[] flags;

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了78.44% 的Java用户
     * 	内存消耗:43.6 MB,击败了45.95% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakMy(String s, List<String> wordDict) {
        flags = new boolean[s.length() + 1];
        return wordBreak(s, new HashSet<>(wordDict), 0);
    }

    private  boolean wordBreak(String s, HashSet<String> wordDict, int index) {
        if(index == s.length()){
            return true;
        }
        if(flags[index]){
            return false;
        }
        boolean flag = false;
        for (int i = 1 ; i <= s.length() - index&&!flag; i++){
            if (wordDict.contains(s.substring(index,index+i))){
                flag = wordBreak(s,wordDict,index+i);
                if (!flag){
                    flags[index] = true;
                }
            }
        }
        return flag;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了63.36% 的Java用户
     * 	内存消耗:43.5 MB,击败了51.84% 的Java用户
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
