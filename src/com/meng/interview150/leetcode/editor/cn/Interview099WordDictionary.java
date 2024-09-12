package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 724
 * ms
 * 击败
 * 5.04%
 * 复杂度分析
 * 消耗内存分布
 * 54.27
 * MB
 * 击败
 * 99.31%
 */
class Interview099WordDictionary {

    Map<Integer, Set<String>> cache;
    public Interview099WordDictionary() {
        cache = new HashMap<>(26);
    }

    public void addWord(String word) {
        if (!cache.containsKey(word.length())){
            cache.put(word.length(),new HashSet<>());
        }
        cache.get(word.length()).add(word);
    }

    public boolean search(String word) {
        if (!cache.containsKey(word.length())){
            return false;
        }
        boolean flag = existPoint(word);
        if (!flag){
            return cache.get(word.length()).contains(word);
        }else {
            return search(word,cache.get(word.length()));
        }
    }
    private boolean search(String word, Set<String> strings) {
        for (String str : strings){
            boolean flag = true;
            for (int i = 0; i < word.length(); i++){
                if (word.charAt(i) != '.' && word.charAt(i) != str.charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }
        return false;
    }
    private boolean existPoint(String word){
        for (char c : word.toCharArray()){
            if (c == '.'){
                return true;
            }
        }
        return false;
    }
}

/**
 * 执行用时分布
 * 209
 * ms
 * 击败
 * 41.64%
 * 复杂度分析
 * 消耗内存分布
 * 87.71
 * MB
 * 击败
 * 71.50%
 */
class WordDictionary {
    private Trie099 root;

    public WordDictionary() {
        root = new Trie099();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Trie099 node) {
        if (index == word.length()) {
            return node.isEnd();
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            Trie099 child = node.getChildren()[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                Trie099 child = node.getChildren()[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Trie099 {
    private Trie099[] children;
    private boolean isEnd;

    public Trie099() {
        children = new Trie099[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie099 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie099();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public Trie099[] getChildren() {
        return children;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

