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
class CheckTree2236 {
    /**
     * 时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 38.14MB
     * 击败 59.32%使用 Java 的用户
     * @param root
     * @return
     */
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}

