package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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
class MergeTrees617 {
    /**
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 41.60mb
     * 击败 51.60%使用 Java 的用户
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //非空判断
        if(root1 == null ){
            return root2;
        }
        if(root2 == null ){
            return root1;
        }
        //合并根节点
        TreeNode root = new TreeNode(root1.val + root2.val);
        dfs(root,root1,root2);
        return root;
    }

    private void dfs(TreeNode root, TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null){
            return;
        }
        //合并左节点
        if (root1.left == null) {
            root.left = root2.left;
        }else if (root2.left == null) {
            root.left = root1.left;
        }else {
            root.left = new TreeNode(root1.left.val + root2.left.val);
            dfs(root.left,root1.left,root2.left);
        }
        //合并右节点
        if (root1.right == null) {
            root.right = root2.right;
        }else if (root2.right == null) {
            root.right = root1.right;
        }else {
            root.right = new TreeNode(root1.right.val + root2.right.val);
            dfs(root.right,root1.right,root2.right);
        }
    }

    /**
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 41.62mb
     * 击败 47.68%使用 Java 的用户
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees1(t1.left, t2.left);
        merged.right = mergeTrees1(t1.right, t2.right);
        return merged;
    }

    /**
     * 详情
     * 1ms
     * 击败 14.49%使用 Java 的用户
     * 内存
     * 详情
     * 41.42mb
     * 击败 86.14%使用 Java 的用户
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue.offer(merged);
        queue1.offer(t1);
        queue2.offer(t2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    queue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else if (left2 != null) {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    queue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return merged;
    }

}
