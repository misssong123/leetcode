package com.meng.oneQuestionPerDay.leetcode.editor.cn;
import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

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
class SolutionRob337 {
    /**
     * 超时
     * @param root
     * @return
     */
    public int rob1(TreeNode root) {
        int selected = root.val + dfs(true,root.left) + dfs(true,root.right);
        int unSelected = dfs(false,root.left) + dfs(false,root.right);
        return Math.max(selected,unSelected);
    }

    private int dfs( boolean flag, TreeNode root) {
        if (root == null){
            return 0;
        }
        if (flag){
            return dfs(false,root.left) + dfs(false,root.right);
        }else {
            int selected = root.val + dfs(true,root.left) + dfs(true,root.right);
            int unSelected = dfs(false,root.left) + dfs(false,root.right);
            return Math.max(selected,unSelected);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了13.49% 的Java用户
     * 	内存消耗:42.4 MB,击败了90.54% 的Java用户
     * @param root
     * @return
     */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.5 MB,击败了87.90% 的Java用户
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        int[] rootStatus = dfs2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

}
