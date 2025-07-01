package com.meng.oneday.leetcode.editor.cn;

class PossibleStringCount3330 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了18.18% 的Java用户
     * @param word
     * @return
     */
    public int possibleStringCount3330(String word) {
        int res = 1;
        for(int i = 1 ; i < word.length() ; i++){
            if(word.charAt(i) == word.charAt(i-1)){
                res += 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了53.03% 的Java用户
     * @param word
     * @return
     */
    public int possibleStringCount(String word) {
        int ans = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i - 1) == word.charAt(i)) {
                ans++;
            }
        }
        return ans;
    }

}
