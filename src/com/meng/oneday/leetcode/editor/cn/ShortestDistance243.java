package com.meng.oneday.leetcode.editor.cn;

class ShortestDistance243 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.85% 的Java用户
     * 	内存消耗:45.1 MB,击败了32.18% 的Java用户
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance243(String[] wordsDict, String word1, String word2) {
        int index1 = -1,index2 = -1,ans = wordsDict.length;
        for(int i = 0 ; i < wordsDict.length ; i++){
            if (wordsDict[i].equals(word1)){
                index1 = i;
                if (index2 != -1){
                    ans = Math.min(ans,Math.abs(index1-index2));
                }
            }else if (wordsDict[i].equals(word2)){
                index2 = i;
                if (index1 != -1){
                    ans = Math.min(ans,Math.abs(index1-index2));
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了67.82% 的Java用户
     * 	内存消耗:45.4 MB,击败了6.90% 的Java用户
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int length = wordsDict.length;
        int ans = length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < length; i++) {
            String word = wordsDict[i];
            if (word.equals(word1)) {
                index1 = i;
            } else if (word.equals(word2)) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                ans = Math.min(ans, Math.abs(index1 - index2));
            }
        }
        return ans;
    }

}
