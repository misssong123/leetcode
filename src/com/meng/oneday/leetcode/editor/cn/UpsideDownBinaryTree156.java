package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class UpsideDownBinaryTree156 {
    TreeNode newHead = null;
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了52.63% 的Java用户
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree156(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        dfs(root);
        return newHead;
    }

    private TreeNode dfs(TreeNode root) {
        if(root.left == null){
            newHead = root;
        }else {
            TreeNode parent = dfs(root.left);
            parent.right = root;
            parent.left = root.right;
            root.left = null;
            root.right = null;
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了14.03% 的Java用户
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        TreeNode newRoot = stack.pop();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode prevLeft = node.left, prevRight = node.right;
            prevLeft.left = prevRight;
            prevLeft.right = node;
            if (prevRight != null) {
                prevRight.left = null;
                prevRight.right = null;
            }
            node.left = null;
            node.right = null;
        }
        return newRoot;
    }

}
