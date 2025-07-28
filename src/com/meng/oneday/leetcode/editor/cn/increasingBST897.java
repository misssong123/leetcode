package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;
class IncreasingBST897 {
    TreeNode newHead = null;
    TreeNode temp = null;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了29.31% 的Java用户
     * @param root
     * @return
     */
    public TreeNode increasingBST897(TreeNode root) {
        newHead = null;
        temp = null;
        dfs(root);
        return newHead;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            dfs(node.left);
        }
        if(newHead == null) {
            newHead = node;
            temp = node;
        }else {
            temp.right = node;
            temp = temp.right;
        }
        node.left = null;
        if (node.right != null) {
            dfs(node.right);
        }
    }
    private TreeNode resNode;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了98.62% 的Java用户
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }

}
