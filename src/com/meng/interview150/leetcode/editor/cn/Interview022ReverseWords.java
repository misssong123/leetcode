package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Interview022ReverseWords {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了92.57% 的Java用户
     * 	内存消耗:41.6 MB,击败了74.63% 的Java用户
     * @param s
     * @return
     */
    public String reverseWordsMy(String s) {
        int len = s.length()-1;
        StringBuilder sb = new StringBuilder();
        while (len >=0){
            while (len >=0 && s.charAt(len) == ' '){
                len--;
            }
            int end = len;
            while (len >=0 && s.charAt(len) != ' '){
                len--;
            }
            sb.append(s, len+1, end+1).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了13.93% 的Java用户
     * 	内存消耗:41.8 MB,击败了67.87% 的Java用户
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
