package com.meng.oneday.leetcode.editor.cn;

class ShortestWordDistance245 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:62.4 MB,击败了76.19% 的Java用户
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestWordDistance245(String[] wordsDict, String word1, String word2) {
        int min = wordsDict.length;
        if(word1.equals(word2)){
            int pre = -1;
            for(int i = 0; i < wordsDict.length; i++){
                if (wordsDict[i].equals(word1)){
                    if(pre != -1){
                        min = Math.min(min,i-pre);
                    }
                    pre = i;
                }
            }
        }else {
            int first = -1 , second = -1;
            for(int i = 0; i < wordsDict.length; i++){
                if (wordsDict[i].equals(word1)){
                    first = i;
                    if (second != -1){
                        min = Math.min(min,first-second);
                    }
                }else if (wordsDict[i].equals(word2)){
                    second = i;
                    if (first != -1){
                        min = Math.min(min,second-first);
                    }
                }
            }
        }
        return min;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了100.00% 的Java用户
     * 	内存消耗:62.8 MB,击败了14.28% 的Java用户
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int length = wordsDict.length;
        int ans = length;
        if (word1.equals(word2)) {
            int prev = -1;
            for (int i = 0; i < length; i++) {
                String word = wordsDict[i];
                if (word.equals(word1)) {
                    if (prev >= 0) {
                        ans = Math.min(ans, i - prev);
                    }
                    prev = i;
                }
            }
        } else {
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
        }
        return ans;
    }

}
