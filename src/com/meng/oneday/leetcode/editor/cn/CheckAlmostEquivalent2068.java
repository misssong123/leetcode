package com.meng.oneday.leetcode.editor.cn;

class CheckAlmostEquivalent2068 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了77.78% 的Java用户
     * @param word1
     * @param word2
     * @return
     */
    public boolean checkAlmostEquivalent2068(String word1, String word2) {
        int[] cnts = new int[26];
        for (char c : word1.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (Math.abs(cnt) > 3) {
                return false;
            }
        }
        return true;
    }
}
