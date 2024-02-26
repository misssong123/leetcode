package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

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
class RangeSumBST938 {
    int res = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:50.1 MB,击败了14.86% 的Java用户
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTMy(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return res;
    }

    private void dfs(TreeNode root, int low, int high) {
        if (root == null){
            return;
        }
        if(root.val >= low && root.left != null){
            dfs(root.left, low, high);
        }
        if(root.val >= low && root.val <= high){
            res += root.val;
        }
        if(root.val <= high && root.right != null){
            dfs(root.right, low, high);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:50 MB,击败了36.96% 的Java用户
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            return rangeSumBST1(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST1(root.right, low, high);
        }
        return root.val + rangeSumBST1(root.left, low, high) + rangeSumBST1(root.right, low, high);
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了32.38% 的Java用户
     * 	内存消耗:50.1 MB,击败了11.15% 的Java用户
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                continue;
            }
            if (node.val > high) {
                q.offer(node.left);
            } else if (node.val < low) {
                q.offer(node.right);
            } else {
                sum += node.val;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return sum;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
