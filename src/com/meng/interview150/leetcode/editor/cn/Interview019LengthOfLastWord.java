package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview019LengthOfLastWord {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了27.54% 的Java用户
     * @param s
     * @return
     */
    public int lengthOfLastWordMy(String s) {
        int first = s.length()-1;
        int res = 0;
        while (first>0 && s.charAt(first)==' '){
            first--;
        }
        while (first>=0 && s.charAt(first)!=' '){
            first--;
            res++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了18.92% 的Java用户
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
