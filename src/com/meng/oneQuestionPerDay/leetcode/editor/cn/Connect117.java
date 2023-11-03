package com.meng.oneQuestionPerDay.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/


import com.meng.oneQuestionPerDay.Node;

import java.util.ArrayList;
import java.util.List;

class Connect117 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了81.71% 的Java用户
     * 	内存消耗:42.3 MB,击败了5.63% 的Java用户
     * @param root
     * @return
     */
    public Node connectMy(Node root) {
        if (root == null||(root.left==null&&root.right==null)){
            return root;
        }
        List<Node> cache = new ArrayList<>();
        cache.add(root);
        int size = 1;
        int index = 0;
        while (size > 0){
            int temp = 0;
            for(int i = 0 ; i < size ; i++){
                Node node = cache.get(index + i);
                if (node.left != null){
                    cache.add(node.left);
                    temp++;
                }
                if (node.right != null){
                    cache.add(node.right);
                    temp++;
                }
                if (i < size -1){
                    node.next = cache.get(index+i+1);
                }
            }
            index += size;
            size = temp;
        }
        return root;
    }

    public static void main(String[] args) {
        Connect117 solution = new Connect117();
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        root.left = node2;
        root.right = node3;
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node2.left = node4;
        node2.right = node5;
        Node node7 = new Node(7);
        node3.right = node7;
        solution.connect(root);
    }
    Node last = null, nextStart = null;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了92.48% 的Java用户
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
//leetcode submit region end(Prohibit modification and deletion)
