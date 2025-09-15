package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class CanBeTypedWords1935 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了75.68% 的Java用户
     * 	内存消耗:41.4 MB,击败了35.13% 的Java用户
     * @param text
     * @param brokenLetters
     * @return
     */
    public int canBeTypedWords1935(String text, String brokenLetters) {
        String[] texts = text.split(" ");
        int res = texts.length;
        for(String str : texts){
            for(char c : brokenLetters.toCharArray()){
                if(str.indexOf(c) != -1){
                    res--;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了13.51% 的Java用户
     * 	内存消耗:41.1 MB,击败了74.32% 的Java用户
     * @param text
     * @param brokenLetters
     * @return
     */
    public int canBeTypedWordsOfficial(String text, String brokenLetters) {
        Set<Character> broken = new HashSet<>();   // 无法输入的字符集合
        for (char ch : brokenLetters.toCharArray()) {
            broken.add(ch);
        }
        int res = 0;   // 可以完全输入的单词数目
        boolean flag = true;   // 当前字符所在单词是否可被完全输入
        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                // 当前字符为空格，检查上一个单词状态，更新数目并初始化 flag
                if (flag) {
                    ++res;
                }
                flag = true;
            } else if (broken.contains(ch)) {
                // 当前字符不可被输入，所在单词无法被完全输入，更新 flag
                flag = false;
            }
        }
        // 判断最后一个单词状态并更新数目
        if (flag) {
            ++res;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了33.78% 的Java用户
     * @param text
     * @param brokenLetters
     * @return
     */
    public int canBeTypedWordsOther1(String text, String brokenLetters) {
        int ans = 0;
        for (String word : text.split(" ")) {
            if (!containsAny(word, brokenLetters)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean containsAny(String word, String brokenLetters) {
        for (char c : word.toCharArray()) {
            if (brokenLetters.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了64.86% 的Java用户
     * @param text
     * @param brokenLetters
     * @return
     */
    public int canBeTypedWordsOther2(String text, String brokenLetters) {
        int brokenMask = 0;
        for (char c : brokenLetters.toCharArray()) {
            brokenMask |= 1 << (c - 'a'); // 把 c 加到集合中
        }

        int ans = 0;
        int ok = 1;
        for (char c : text.toCharArray()) {
            if (c == ' ') { // 上一个单词遍历完毕
                ans += ok;
                ok = 1;
            } else if ((brokenMask >> (c - 'a') & 1) > 0) { // c 在 brokenLetters 中
                ok = 0;
            }
        }
        ans += ok; // 最后一个单词
        return ans;
    }

}
