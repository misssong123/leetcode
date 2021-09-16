package com.meng.algorithm;

import java.util.*;

public class FindWords2 {
    /**
     * 思路和算法
     *
     * 考虑以下情况。假设给定一个所有单元格都是 a 的二维字符网格和单词列表 ["a", "aa", "aaa", "aaaa"] 。当我们使用方法一来找出所有同时在二维网格和单词列表中出现的单词时，我们需要遍历每一个单元格的所有路径，会找到大量重复的单词。
     *
     * 为了缓解这种情况，我们可以将匹配到的单词从前缀树中移除，来避免重复寻找相同的单词。因为这种方法可以保证每个单词只能被匹配一次；所以我们也不需要再对结果集去重了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/word-search-ii/solution/dan-ci-sou-suo-ii-by-leetcode-solution-7494/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：17 ms, 在所有 Java 提交中击败了84.32% 的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了70.43% 的用户
     */
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie2 trie = new Trie2();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<String>(ans);
    }

    public void dfs(char[][] board, Trie2 now, int i1, int j1, Set<String> ans) {
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        Trie2 nxt = now.children.get(ch);
        if (!"".equals(nxt.word)) {
            ans.add(nxt.word);
            nxt.word = "";
        }

        if (!nxt.children.isEmpty()) {
            board[i1][j1] = '#';
            for (int[] dir : dirs) {
                int i2 = i1 + dir[0], j2 = j1 + dir[1];
                if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                    dfs(board, nxt, i2, j2, ans);
                }
            }
            board[i1][j1] = ch;
        }

        if (nxt.children.isEmpty()) {
            now.children.remove(ch);
        }
    }
}

class Trie2 {
    String word;
    Map<Character, Trie2> children;
    boolean isWord;

    public Trie2() {
        this.word = "";
        this.children = new HashMap<Character, Trie2>();
    }

    public void insert(String word) {
        Trie2 cur = this;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Trie2());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }
}
