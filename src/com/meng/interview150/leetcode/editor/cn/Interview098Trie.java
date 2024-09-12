package com.meng.interview150.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:32 ms,击败了93.37% 的Java用户
 * 	内存消耗:54 MB,击败了73.00% 的Java用户
 */
class Interview097Trie {
    TrieNode root;
    public Interview097Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (temp.next[index] == null) {
                temp.next[index] = new TrieNode();
            }
            temp = temp.next[index];
        }
        temp.end = true;
    }
    
    public boolean search(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (temp.next[index] == null) {
                return false;
            }
            temp = temp.next[index];
        }
        return temp.end;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (temp.next[index] == null) {
                return false;
            }
            temp = temp.next[index];
        }
        return true;
    }
}
class TrieNode{
    public boolean end;
    public TrieNode[] next;
    public TrieNode(){
        end = false;
        next = new TrieNode[26];
    }
}

/**
 * 解答成功:
 * 	执行耗时:35 ms,击败了45.63% 的Java用户
 * 	内存消耗:53.9 MB,击败了88.96% 的Java用户
 */
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}

