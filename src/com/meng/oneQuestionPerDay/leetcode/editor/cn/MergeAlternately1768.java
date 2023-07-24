package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MergeAlternately1768 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.4 MB
     * , 在所有 Java 提交中击败了
     * 94.52%
     * 的用户
     * 通过测试用例：
     * 108 / 108
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        // 取最小长度
        int len = Math.min(word1.length(), word2.length());
        StringBuilder sb = new StringBuilder();
        int index = 0 ;
        while (index < len){
            sb.append(word1.charAt(index));
            sb.append(word2.charAt(index));
            index++;
        }
        // 拼接剩余字符串
        if (word1.length()>len){
            sb.append(word1.substring(index));
        }
        if (word2.length()>len){
            sb.append(word2.substring(index));
        }
        return sb.toString();
    }

    /**
     *执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 63.86%
     * 的用户
     * 通过测试用例：
     * 108 / 108
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;

        StringBuilder ans = new StringBuilder();
        while (i < m || j < n) {
            if (i < m) {
                ans.append(word1.charAt(i));
                ++i;
            }
            if (j < n) {
                ans.append(word2.charAt(j));
                ++j;
            }
        }
        return ans.toString();
    }

}

