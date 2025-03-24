package com.meng.oneday.leetcode.editor.cn;

class CountPrefixes2255 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了47.50% 的Java用户
     * 	内存消耗:42.5 MB,击败了61.25% 的Java用户
     * @param words
     * @param s
     * @return
     */
    public int countPrefixes2255(String[] words, String s) {
        int res = 0;
        for(String word : words){
            if(word.length() <= s.length() && s.startsWith(word)){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了17.50% 的Java用户
     * @param words
     * @param s
     * @return
     */
    int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                ans++;
            }
        }
        return ans;
    }

}
