package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview076Connect {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了6.63% 的Java用户
     * 	内存消耗:43.1 MB,击败了73.49% 的Java用户
     * @param root
     * @return
     */
    public Node connectMy(Node root) {
        if (root == null){
            return null;
        }
        List<Node> cache = new ArrayList<>();
        cache.add(root);
        while (!cache.isEmpty()){
            List<Node> temp = new ArrayList<>();
            for(int i = 0; i < cache.size(); i++){
                Node node = cache.get(i);
                if(node.left != null){
                    temp.add(node.left);
                }
                if (node.right != null){
                    temp.add(node.right);
                }
                if (i > 0){
                    cache.get(i-1).next = node;
                }
            }
            cache.clear();
            if (temp.size() > 0){
                cache.addAll(temp);
            }
        }
        return root;
    }
    Node last = null, nextStart = null;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了53.58% 的Java用户
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }
}
