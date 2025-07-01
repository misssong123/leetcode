package com.meng.oneday.leetcode.editor.cn;

class CanPermutePalindrome266 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了75.86% 的Java用户
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        int[] cnt =  new int[26];
        for(char c : s.toCharArray()){
            cnt[c-'a']++;
        }
        int num = 0;
        for(int n : cnt){
            if (n % 2 == 1){
                num++;
            }
        }
        return num <= 1;
    }
}
