package com.meng.hot100.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 解答成功:
 * 	执行耗时:724 ms,击败了5.00% 的Java用户
 * 	内存消耗:275.7 MB,击败了5.07% 的Java用户
 */
class WordDictionary211 {
    private static class Node{
        Node[] sons = new Node[26];
        boolean isEnd = false;
    }
    private Node root;
    public WordDictionary211() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node cur = root;
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if (cur.sons[index] == null){
                cur.sons[index] = new Node();
            }
            cur = cur.sons[index];
        }
        cur.isEnd = true;
    }
    
    public boolean search(String word) {
        Node cur = root;
        Set<Node> set = new HashSet<>();
        set.add(cur);
        for (char c : word.toCharArray()){
            if (set.isEmpty()){
                return false;
            }
            Set<Node> temp = new HashSet<>();
            if (c == '.'){
                for (Node node : set){
                    for (Node next : node.sons){
                        if (next != null){
                            temp.add(next);
                        }
                    }
                }
            }else{
                int index = c - 'a';
                for (Node node : set){
                    if (node.sons[index] != null){
                        temp.add(node.sons[index]);
                    }
                }
            }
            set = temp;
        }
        for (Node node : set){
            if (node.isEnd){
                return true;
            }
        }
        return false;
    }
}

/**
 * 解答成功:
 * 	执行耗时:211 ms,击败了44.59% 的Java用户
 * 	内存消耗:270.6 MB,击败了5.07% 的Java用户
 */
class WordDictionary {
    private WordDictionaryTrie root;

    public WordDictionary() {
        root = new WordDictionaryTrie();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, WordDictionaryTrie node) {
        if (index == word.length()) {
            return node.isEnd();
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            WordDictionaryTrie child = node.getChildren()[childIndex];
            return child != null && dfs(word, index + 1, child);
        } else {
            for (int i = 0; i < 26; i++) {
                WordDictionaryTrie child = node.getChildren()[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class WordDictionaryTrie {
    private WordDictionaryTrie[] children;
    private boolean isEnd;

    public WordDictionaryTrie() {
        children = new WordDictionaryTrie[26];
        isEnd = false;
    }

    public void insert(String word) {
        WordDictionaryTrie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new WordDictionaryTrie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public WordDictionaryTrie[] getChildren() {
        return children;
    }

    public boolean isEnd() {
        return isEnd;
    }
}