package com.meng.hot100.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:45 ms,击败了14.39% 的Java用户
 * 	内存消耗:60.9 MB,击败了5.04% 的Java用户
 */
class TrieLCR062 {
    private TrieLCR062Node[] head;
    public TrieLCR062() {
        head = new TrieLCR062Node[26];
    }
    
    public void insert(String word) {
        TrieLCR062Node[] cur = head;
        for(int i = 0 ; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if (cur[index] == null){
                cur[index] = new TrieLCR062Node();
            }
            if (i == word.length() - 1){
                cur[index].end = true;
            }else {
                cur = cur[index].next;
            }
        }
    }
    
    public boolean search(String word) {
        TrieLCR062Node node = find(word);
        return node != null && node.end;
    }

    private TrieLCR062Node find(String word) {
        TrieLCR062Node[] cur = head;
        for(int i = 0 ; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if (cur[index] == null){
                return null;
            }
            if (i == word.length() - 1){
                return cur[index];
            }
            cur = cur[index].next;
        }
        return null;
    }

    public boolean startsWith(String prefix) {
        TrieLCR062Node node = find(prefix);
        return node != null;
    }
}
class TrieLCR062Node{
    public boolean end;
    public TrieLCR062Node[] next = new TrieLCR062Node[26];
}

class Trie {
    private static class Node {
        Node[] son = new Node[26];
        boolean end = false;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) { // 无路可走？
                cur.son[c] = new Node(); // new 出来！
            }
            cur = cur.son[c];
        }
        cur.end = true;
    }

    public boolean search(String word) {
        return find(word) == 2;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != 0;
    }

    private int find(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) { // 道不同，不相为谋
                return 0;
            }
            cur = cur.son[c];
        }
        // 走过同样的路（2=完全匹配，1=前缀匹配）
        return cur.end ? 2 : 1;
    }
}
