package com.meng.level2.day16;

/**
 * 208. 实现 Trie (前缀树)(中等)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 *
 * 示例：
 *
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 *
 * 提示：
 *
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class Trie {
    /**
     * 执行用时：
     * 35 ms
     * , 在所有 Java 提交中击败了
     * 43.60%
     * 的用户
     * 内存消耗：
     * 49.7 MB
     * , 在所有 Java 提交中击败了
     * 76.38%
     * 的用户
     * 通过测试用例：
     * 16 / 16
     */
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

/**
 * 执行用时：
 * 38 ms
 * , 在所有 Java 提交中击败了
 * 31.35%
 * 的用户
 * 内存消耗：
 * 50.6 MB
 * , 在所有 Java 提交中击败了
 * 36.22%
 * 的用户
 * 通过测试用例：
 * 16 / 16
 */
class Trie1 {
    public Trie1[] childs;
    public boolean isEnd ;

    public Trie1() {
        childs = new Trie1[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie1 root = this;
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if (root.childs[index] == null){
                root.childs[index] = new Trie1();
            }
            root = root.childs[index];
        }
        root.isEnd = true;
    }

    public boolean search(String word) {
        Trie1 trie = searchActual(word);
        return trie != null && trie.isEnd;
    }


    public boolean startsWith(String prefix) {
        Trie1 trie = searchActual(prefix);
        return trie != null ;
    }
    private Trie1 searchActual(String word) {
        Trie1 trie1 = this;
        for(char c : word.toCharArray()){
            int index = c - 'a';
            if (trie1.childs[index] == null){
                return null;
            }
            trie1 = trie1.childs[index];
        }
        return trie1;
    }
}