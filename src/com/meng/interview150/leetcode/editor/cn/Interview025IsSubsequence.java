package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview025IsSubsequence {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了89.34% 的Java用户
     * 	内存消耗:40.4 MB,击败了78.02% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while(i < n && j < m){
            if (s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        return i == n;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了89.34% 的Java用户
     * 	内存消耗:40.5 MB,击败了52.37% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceOfficial(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
