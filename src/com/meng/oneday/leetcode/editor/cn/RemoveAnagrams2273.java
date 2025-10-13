package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class RemoveAnagrams2273 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了49.18% 的Java用户
     * 	内存消耗:43.9 MB,击败了49.18% 的Java用户
     * @param words
     * @return
     */
    public List<String> removeAnagrams2273(String[] words) {
        Stack<String> stack = new Stack<>();
        for(String word : words){
            if (stack.isEmpty() || !isAnagram(stack.peek(), word)){
                stack.push(word);
            }
        }
        List<String> res = new ArrayList<>();
        while (!stack.isEmpty()){
            res.add(0,stack.pop());
        }
        return res;
    }

    private boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()){
            return false;
        }
        int[] cnt = new int[26];
        for(char c : word1.toCharArray()){
            cnt[c - 'a']++;
        }
        for(char c : word2.toCharArray()){
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了49.18% 的Java用户
     * 	内存消耗:43.1 MB,击败了90.16% 的Java用户
     * @param words
     * @return
     */
    public List<String> removeAnagrams(String[] words) {
        char[] base = words[0].toCharArray();
        Arrays.sort(base);
        int k = 1;
        for (int i = 1; i < words.length; i++) {
            char[] s = words[i].toCharArray();
            Arrays.sort(s);
            if (!Arrays.equals(s, base)) {
                base = s;
                words[k++] = words[i]; // 保留 words[i]
            }
        }
        return Arrays.asList(Arrays.copyOf(words, k));
    }

}
