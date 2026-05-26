package com.meng.oneday.leetcode.editor.cn;

class NumberOfSpecialChars3120 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了68.18% 的Java用户
     * @param word
     * @return
     */
    public int numberOfSpecialChars3120(String word) {
        int[] cnts = new int[26];
        for (char c : word.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                cnts[c - 'A'] |= 2;
            }else {
                cnts[c - 'a'] |= 1;
            }
        }
        int cnt = 0;
        for (int num : cnts){
            if (num == 3){
                cnt ++;
            }
        }
        return cnt;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了54.54% 的Java用户
     * @param word
     * @return
     */
    public int numberOfSpecialChars(String word) {
        int[] mask = new int[2]; // 大写字母集合、小写字母集合
        for (char c : word.toCharArray()) {
            // 用 c>>5&1 区分大小写，c&31 获取 c 是第几个字母
            mask[c >> 5 & 1] |= 1 << (c & 31);
        }
        return Integer.bitCount(mask[0] & mask[1]); // 计算交集大小
    }
}
