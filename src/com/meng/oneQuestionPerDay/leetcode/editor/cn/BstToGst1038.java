package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
class BstToGst1038 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了5.07% 的Java用户
     * @param root
     * @return
     */
    public TreeNode bstToGst1(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        index = 0;
        dfs(root,nums);

        for (int i = nums.size()-2;i>=0;i--){
            nums.set(i,nums.get(i)+nums.get(i+1));
        }
        sum(root,nums);
        return root;
    }
    private void dfs(TreeNode root ,List<Integer> nums){
        if (root == null){
            return;
        }
        if (root.left != null){
            dfs(root.left,nums);
        }
        nums.add(root.val);
        if (root.right != null){
            dfs(root.right,nums);
        }
    }
    int index = 0;
    private void sum(TreeNode root ,List<Integer> nums){
        if (root == null){
            return;
        }
        if (root.left != null){
            sum(root.left,nums);
        }
        root.val = nums.get(index++);
        if (root.right != null){
            sum(root.right,nums);
        }
    }

    int sum = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39 MB,击败了50.78% 的Java用户
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            sum += root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
    }
}

