package com.meng.hot100.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ReverseWords151 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了70.25% 的Java用户
     * 	内存消耗:42.7 MB,击败了19.19% 的Java用户
     * @param s
     * @return
     */
    public String reverseWords151(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = words.length -1 ; i >= 0 ; i--){
            if (!words[i].isEmpty()){
                sb.append(words[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了34.91% 的Java用户
     * 	内存消耗:42 MB,击败了60.56% 的Java用户
     * @param s
     * @return
     */
    public String reverseWordsOfficial(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了98.23% 的Java用户
     * 	内存消耗:41.7 MB,击败了84.33% 的Java用户
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] charArray = s.toCharArray();
        int l = 0 , r = charArray.length - 1;
        // 除去开头和末尾的空白字符
        while (l <= r && charArray[l] == ' ') {
            l++;
        }
        while (r >= l && charArray[r] == ' ') {
            r--;
        }
        //去除中间空格
        int index = 0;
        for(int i = l ; i <= r ; i++){
            if (charArray[i] != ' '|| charArray[i] == ' ' && charArray[i - 1] != ' '){
                charArray[index++] = charArray[i];
            }
        }
        //反转整个字符
        reverse (charArray, 0, index - 1);
        //反转每个单词
        int pre = 0;
        for(int i = 0 ; i < index ; i++){
            if (charArray[i] == ' ' || i == index - 1){
                int end = i == index - 1 ? i : i - 1;
                reverse(charArray, pre, end);
                pre = i + 1;
            }
        }
        return new String(charArray, 0, index);
    }

    private void reverse(char[] charArray, int l, int r) {
        while (l < r){
            char temp = charArray[l];
            charArray[l++] = charArray[r];
            charArray[r--] = temp;
        }
    }

}
