package com.meng.oneQuestionPerDay.leetcode.editor.cn;



class IsCircularSentence2490 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 96.22%
     * 的用户
     * 内存消耗：
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 59.66%
     * 的用户
     * 通过测试用例：
     * 266 / 266
     * @param sentence
     * @return
     */
    public boolean isCircularSentence(String sentence) {
        //首位是否相等
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }
        //判断空格前后的字母是否相等
        for(int i = 1 ; i < sentence.length() ; i++){
            if (sentence.charAt(i) == ' '){
                if (sentence.charAt(i-1) != sentence.charAt(i+1)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 96.22%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 87.39%
     * 的用户
     * 通过测试用例：
     * 266 / 266
     * @param sentence
     * @return
     */
    public boolean isCircularSentence1(String sentence) {
        if (sentence.charAt(sentence.length() - 1) != sentence.charAt(0)) {
            return false;
        }
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i + 1) != sentence.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

}

