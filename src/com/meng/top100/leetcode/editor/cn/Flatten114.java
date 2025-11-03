package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

class Flatten114 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了24.84% 的Java用户
     * 	内存消耗:43.1 MB,击败了5.03% 的Java用户
     * @param root
     */
    public void flatten114(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs114(root);
    }
    private TreeNode dfs114(TreeNode root) {
        if (root == null){
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);
        if (left != null){
            root.right = left;
            root.left = null;
        }
        while (left != null && left.right != null){
            left = left.right;
        }
        if (right != null){
            if (left != null){
                left.right = right;
            }else {
                root.right = right;
            }
        }
        return root;
    }
    private TreeNode head;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了5.03% 的Java用户
     * @param root
     */
    public void flattenOther(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = head; // 头插法，相当于链表的 root.next = head
        head = root; // 现在链表头节点是 root
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了5.03% 的Java用户
     * @param root
     */
    public void flatten(TreeNode root) {
        dfs(root);
    }
    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftTail = dfs(root.left);
        TreeNode rightTail = dfs(root.right);
        if (leftTail != null) {
            leftTail.right = root.right; // 左子树链表 -> 右子树链表
            root.right = root.left; // 当前节点 -> 左右子树合并后的链表
            root.left = null;
        }
        return rightTail != null ? rightTail : leftTail != null ? leftTail : root;
    }
}
