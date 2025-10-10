package com.meng.oneday.leetcode.editor.cn;

class IsAlienSorted953 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了33.91% 的Java用户
     * 	内存消耗:41.2 MB,击败了56.52% 的Java用户
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted953(String[] words, String order) {
        int[] orderIndex = new int[26];
        for(int i = 0; i < order.length(); i++){
            orderIndex[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0 ; i < words.length - 1; i++){
            if(!check (words[i],words[i+1],orderIndex)){
                return false;
            }
        }
        return true;
    }

    private boolean check(String word, String word1, int[] orderIndex) {
        int len = Math.min(word.length(),word1.length());
        for(int i = 0 ; i < len ; i++){
            if(word.charAt(i) != word1.charAt(i)){
                return orderIndex[word.charAt(i) - 'a'] < orderIndex[word1.charAt(i) - 'a'];
            }
        }
        return word.length() <= word1.length();
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了63.48% 的Java用户
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            boolean valid = false;
            for (int j = 0; j < words[i - 1].length() && j < words[i].length(); j++) {
                int prev = index[words[i - 1].charAt(j) - 'a'];
                int curr = index[words[i].charAt(j) - 'a'];
                if (prev < curr) {
                    valid = true;
                    break;
                } else if (prev > curr) {
                    return false;
                }
            }
            if (!valid) {
                /* 比较两个字符串的长度 */
                if (words[i - 1].length() > words[i].length()) {
                    return false;
                }
            }
        }
        return true;
    }
}
