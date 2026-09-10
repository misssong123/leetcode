package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

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
class AverageOfSubtree2265 {
    int res2265 = 0;
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.55% 的Java用户
     * 	内存消耗:44.8 MB,击败了56.52% 的Java用户
     * @param root
     * @return
     */
    public int averageOfSubtree2265(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs2265(root);
        return res2265;
    }

    private int[] dfs2265(TreeNode root) {
        if (root == null){
            return new int[]{0,0};
        }
        int[] left = dfs2265(root.left);
        int[] right = dfs2265(root.right);
        int sum = left[0] + right[0] + root.val;
        int cnt = left[1] + right[1] + 1;
        if (sum/cnt == root.val){
            res2265++;
        }
        return new int[]{sum,cnt};
    }

    private int ans = 0;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.55% 的Java用户
     * 	内存消耗:44.8 MB,击败了63.77% 的Java用户
     * @param root
     * @return
     */
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left); // 拆解问题：递归计算左子树的信息
        int[] right = dfs(node.right); // 拆解问题：递归计算右子树的信息
        int sum = left[0] + right[0] + node.val; // node 子树的节点值之和
        int size = left[1] + right[1] + 1; // node 子树的节点个数
        if (node.val == sum / size) { // 题目要求下取整
            ans++;
        }
        return new int[]{sum, size};
    }

}
