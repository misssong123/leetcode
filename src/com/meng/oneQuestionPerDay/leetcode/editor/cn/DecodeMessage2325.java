package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class DecodeMessage2325 {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 66.42%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 22.26%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     * @param key
     * @param message
     * @return
     */
    public String decodeMessage(String key, String message) {
        char[] chars = key.toCharArray();
        Map<Character,Character> dicts = new HashMap<>();
        char c = 'a';
        dicts.put(' ',' ');
        for(char chr : chars){
            if (chr != ' ' && dicts.get(chr) == null){
                dicts.put(chr,c);
                c++;
            }
        }
        StringBuilder sb = new StringBuilder();
        chars = message.toCharArray();
        for(char chr : chars){
            sb.append(dicts.get(chr));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeMessage2325 demo = new DecodeMessage2325();
        System.out.println(demo.decodeMessage("the quick brown fox jumps over the lazy dog","abc"));
        System.out.println(demo.decodeMessage1("the quick brown fox jumps over the lazy dog","abc"));
    }

    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 40.51%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 16.79%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     * @param key
     * @param message
     * @return
     */
    public String decodeMessage1(String key, String message) {
        char cur = 'a';
        Map<Character, Character> rules = new HashMap<Character, Character>();

        for (int i = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (c != ' ' && !rules.containsKey(c)) {
                rules.put(c, cur);
                ++cur;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); ++i) {
            char c = message.charAt(i);
            if (c != ' ') {
                c = rules.get(c);
            }
            sb.append(c);
        }

        return sb.toString();
    }

}
