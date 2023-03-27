package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

/**
 * 1032字符流
 * 暴力解法
 * 执行用时：
 * 790 ms
 * , 在所有 Java 提交中击败了
 * 17.69%
 * 的用户
 * 内存消耗：
 * 58.4 MB
 * , 在所有 Java 提交中击败了
 * 99.32%
 * 的用户
 * 通过测试用例：
 * 18 / 18
 */
class StreamChecker {
    Map<Character,Set<String>> cache = new HashMap<>();
    StringBuffer sb = new StringBuffer();
    int len = 0;
    public StreamChecker(String[] words) {
        for(String word : words){
            char c = word.charAt(word.length() - 1);
            if (!cache.containsKey(c)){
                cache.put(c, new HashSet<>());
            }
            cache.get(c).add(word);
            len = Math.max(len,word.length());
        }
    }
    public boolean query(char letter) {
        sb.append(letter);
        if (cache.get(letter) == null){
            return false;
        }
        return containsKey(sb,cache.get(letter));
    }

    private boolean containsKey(StringBuffer sb,  Set<String> words) {
        if (sb.length() >= len*2){
            sb.delete(0,sb.length()-len);
        }
        for(String word : words){
            int num = word.length();
            if (num <= sb.length() && word.equals(sb.substring(sb.length() - num))){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        System.out.println(streamChecker.query('a')); // 返回 False
        System.out.println(streamChecker.query('b')); // 返回 False
        System.out.println(streamChecker.query('c')); // 返回n False
        System.out.println(streamChecker.query('d')); // 返回 True ，因为 'cd' 在 words 中
        System.out.println(streamChecker.query('e')); // 返回 False
        System.out.println(streamChecker.query('f')); // 返回 True ，因为 'f' 在 words 中
        System.out.println(streamChecker.query('g')); // 返回 False
        System.out.println(streamChecker.query('h')); // 返回 False
        System.out.println(streamChecker.query('i')); // 返回 False
        System.out.println(streamChecker.query('j')); // 返回 False
        System.out.println(streamChecker.query('k')); // 返回 False
        System.out.println(streamChecker.query('l'));//返回 True
    }
}

/**
 * 执行用时：
 * 65 ms
 * , 在所有 Java 提交中击败了
 * 55.10%
 * 的用户
 * 内存消耗：
 * 70.5 MB
 * , 在所有 Java 提交中击败了
 * 80.95%
 * 的用户
 * 通过测试用例：
 * 18 / 18
 */
class StreamChecker1 {
    TrieNode root;
    TrieNode temp;

    public StreamChecker1(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.getChild(index) == null) {
                    cur.setChild(index, new TrieNode());
                }
                cur = cur.getChild(index);
            }
            cur.setIsEnd(true);
        }
        root.setFail(root);
        Queue<TrieNode> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if(root.getChild(i) != null) {
                root.getChild(i).setFail(root);
                q.add(root.getChild(i));
            } else {
                root.setChild(i, root);
            }
        }
        while (!q.isEmpty()) {
            TrieNode node = q.poll();
            node.setIsEnd(node.getIsEnd() || node.getFail().getIsEnd());
            for (int i = 0; i < 26; i++) {
                if(node.getChild(i) != null) {
                    node.getChild(i).setFail(node.getFail().getChild(i));
                    q.offer(node.getChild(i));
                } else {
                    node.setChild(i, node.getFail().getChild(i));
                }
            }
        }

        temp = root;
    }

    public boolean query(char letter) {
        temp = temp.getChild(letter - 'a');
        return temp.getIsEnd();
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    TrieNode fail;

    public TrieNode() {
        children = new TrieNode[26];
    }

    public TrieNode getChild(int index) {
        return children[index];
    }

    public void setChild(int index, TrieNode node) {
        children[index] = node;
    }

    public boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean b) {
        isEnd = b;
    }

    public TrieNode getFail() {
        return fail;
    }

    public void setFail(TrieNode node) {
        fail = node;
    }
}