package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Connect117 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了45.07% 的Java用户
     * 	内存消耗:45.5 MB,击败了5.26% 的Java用户
     * @param root
     * @return
     */
    public Node connect117(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            List<Node> next = new ArrayList<>(list.size()*2);
            for(int i = 0 ; i < list.size() ; i++){
                Node node = list.get(i);
                if(i < list.size() - 1){
                    node.next = list.get(i + 1);
                }
                if(node.left != null){
                    next.add(node.left);
                }
                if(node.right != null){
                    next.add(node.right);
                }
            }
            list = next;
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.4 MB,击败了5.26% 的Java用户
     * @param root
     * @return
     */
    private final List<Node> pre = new ArrayList<>();
    public Node connectOther(Node root) {
        dfs(root, 0); // 根节点的深度为 0
        return root;
    }
    private void dfs(Node node, int depth) {
        if (node == null) {
            return;
        }
        if (depth == pre.size()) { // node 是这一层最左边的节点
            pre.add(node);
        } else { // pre[depth] 是 node 左边的节点
            pre.get(depth).next = node; // node 左边的节点指向 node
            pre.set(depth, node);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了45.32% 的Java用户
     * 	内存消耗:45.5 MB,击败了5.26% 的Java用户
     * @param root
     * @return
     */
    public Node connectOther2(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> q = new ArrayList<>(Arrays.asList(root));
        while (!q.isEmpty()) {
            List<Node> tmp = q;
            q = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                Node node = tmp.get(i);
                if (i > 0) { // 连接同一层的两个相邻节点
                    tmp.get(i - 1).next = node;
                }
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.4 MB,击败了5.26% 的Java用户
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node dummy = new Node();
        Node cur = root;
        while (cur != null) {
            dummy.next = null;
            Node nxt = dummy; // 下一层的链表
            while (cur != null) { // 遍历当前层的链表
                if (cur.left != null) {
                    nxt.next = cur.left; // 下一层的相邻节点连起来
                    nxt = cur.left;
                }
                if (cur.right != null) {
                    nxt.next = cur.right; // 下一层的相邻节点连起来
                    nxt = cur.right;
                }
                cur = cur.next; // 当前层链表的下一个节点
            }
            cur = dummy.next; // 下一层链表的头节点
        }
        return root;
    }
}
