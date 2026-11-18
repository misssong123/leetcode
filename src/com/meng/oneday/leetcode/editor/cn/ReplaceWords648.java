package com.meng.oneday.leetcode.editor.cn;

import java.util.List;

class ReplaceWords648 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:61.3 MB,击败了39.03% 的Java用户
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords648(List<String> dictionary, String sentence) {
        Dict648[] roots = new Dict648[26];
        //构建字典树
        for (String s : dictionary) {
            buildTree(roots,s);
        }
        String[] split = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            String str = find (roots,s);
            if(str != null){
                sb.append(str).append(" ");
            }else {
                sb.append(s).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private String find(Dict648[] roots, String s) {
        for (int i = 0 ; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (roots[index] == null){
                return null;
            }
            if (roots[index].end){
                return s.substring(0,i+1);
            }
            roots = roots[index].next;
        }
        return null;
    }

    private void buildTree(Dict648[] roots, String s) {
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (roots[index] == null){
                roots[index] = new Dict648();
                roots[index].next = new Dict648[26];
            }
            if (i == s.length() - 1){
                roots[index].end = true;
            }
            roots = roots[index].next;
        }
    }
}
class Dict648{
    public boolean end;
    public Dict648[] next;
}
class Solution648Other {
    class Node {
        boolean isEnd;
        Node[] tns = new Node[26];
    }
    Node root = new Node();
    void add(String s) {
        Node p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) p.tns[u] = new Node();
            p = p.tns[u];
        }
        p.isEnd = true;
    }
    String query(String s) {
        Node p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) break;
            if (p.tns[u].isEnd) return s.substring(0, i + 1);
            p = p.tns[u];
        }
        return s;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:61.6 MB,击败了33.33% 的Java用户
     * @param ds
     * @param s
     * @return
     */
    public String replaceWords(List<String> ds, String s) {
        for (String str : ds) add(str);
        StringBuilder sb = new StringBuilder();
        for (String str : s.split(" ")) sb.append(query(str)).append(" ");
        return sb.substring(0, sb.length() - 1);
    }
}
