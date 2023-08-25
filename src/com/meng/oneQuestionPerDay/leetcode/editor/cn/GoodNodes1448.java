package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

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
class GoodNodes1448 {
    int res = 0;

    /**
     * 时间
     * 详情
     * 2ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 48.07MB
     * 击败 49.16%使用 Java 的用户
     * @param root
     * @return
     */
    public int goodNodes(TreeNode root) {
        int max = Integer.MIN_VALUE;
        dfs(root, max);
        return res;
    }

    private void dfs(TreeNode root, int max) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            res++;
            max = root.val;
        }
        dfs(root.left, max);
        dfs(root.right,max);
    }
}

