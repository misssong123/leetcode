package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class ReverseWords151 {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 73.31%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 26.87%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuffer sb = new StringBuffer();
        String[] strs = s.split(" ");
        for(int i = strs.length-1 ; i >=0  ; i--){
            if (strs[i].length()==0){
                continue;
            }
            sb.append(strs[i]+" ");
        }
        return sb.substring(0,sb.length()-1);
    }

    /**
     *执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 19.96%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 67.83%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     *执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 50.54%
     * 的用户
     * 内存消耗：
     * 40.7 MB
     * , 在所有 Java 提交中击败了
     * 75.36%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }

}
