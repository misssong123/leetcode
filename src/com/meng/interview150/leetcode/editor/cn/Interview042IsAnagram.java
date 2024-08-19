package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview042IsAnagram {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了90.63% 的Java用户
     * 	内存消耗:42.1 MB,击败了50.32% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cache = new int[26];
        for(char c : s.toCharArray()){
            cache[c-'a']++;
        }
        for(char c : t.toCharArray()){
            if(cache[c-'a']-- <= 0){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
