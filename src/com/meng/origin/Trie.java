package com.meng.origin;

/**
 * 208. 实现 Trie (前缀树)
 *
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 *     Trie() 初始化前缀树对象。
 *     void insert(String word) 向前缀树中插入字符串 word 。
 *     boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 *     boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
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
 *
 * 提示：
 *
 *     1 <= word.length, prefix.length <= 2000
 *     word 和 prefix 仅由小写英文字母组成
 *     insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class Trie {
    /**
     * 方法一：字典树
     *
     * Trie\text{Trie}Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：
     *
     *     指向子节点的指针数组 children\textit{children}children。对于本题而言，数组长度为 262626，即小写英文字母的数量。此时 children[0]\textit{children}[0]children[0] 对应小写字母 aaa，children[1]\textit{children}[1]children[1] 对应小写字母 bbb，…，children[25]\textit{children}[25]children[25] 对应小写字母 zzz。
     *     布尔字段 isEnd\textit{isEnd}isEnd，表示该节点是否为字符串的结尾。
     *
     * 插入字符串
     *
     * 我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
     *
     *     子节点存在。沿着指针移动到子节点，继续处理下一个字符。
     *     子节点不存在。创建一个新的子节点，记录在 children\textit{children}children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
     *
     * 重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。
     *
     * 查找前缀
     *
     * 我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
     *
     *     子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
     *     子节点不存在。说明字典树中不包含该前缀，返回空指针。
     *
     * 重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。
     *
     * 若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的 isEnd\textit{isEnd}isEnd 为真，则说明字典树中存在该字符串。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：47 ms, 在所有 Java 提交中击败了39.01% 的用户
     * 内存消耗：47.5 MB, 在所有 Java 提交中击败了79.33% 的用户
     */
    private Trie[]cache;
    private boolean isWord;
    public Trie() {
        cache = new Trie[26];
        isWord = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for(int i = 0 ; i < word.length() ; i++){
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.cache[index] == null){
                node.cache[index] = new Trie();
            }
            node = node.cache[index];
        }
        node.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchWord(word);
        return node != null && node.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchWord(prefix) != null;
    }
    public Trie searchWord(String word){
        Trie node = this;
        for(int i = 0 ; i < word.length() ; i++){
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.cache[index] == null){
                return null;
            }
            node = node.cache[index];
        }
        return node;
    }
}
