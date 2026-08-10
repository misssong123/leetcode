package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
class DelNodes1110 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了49.16% 的Java用户
     * 	内存消耗:46 MB,击败了35.19% 的Java用户
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes1110(TreeNode root, int[] to_delete) {
        List<TreeNode> list = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();
        for (int num : to_delete){
            deleteSet.add(num);
        }
        dfs(root,deleteSet,list,true);
        return list;
    }

    private void dfs(TreeNode root,  Set<Integer> deleteSet, List<TreeNode> list, boolean isRoot) {
        if (root == null) {
            return;
        }
        //构建递归
        if(deleteSet.contains(root.val)){
            dfs(root.left,deleteSet,list,true);
            dfs(root.right,deleteSet,list,true);
        }else{
            if (isRoot){
                list.add(root);
            }
            dfs(root.left,deleteSet,list,false);
            dfs(root.right,deleteSet,list,false);
        }
        //删除节点
        if (root.left != null && deleteSet.contains(root.left.val)){
            root.left = null;
        }
        if (root.right != null && deleteSet.contains(root.right.val)){
            root.right = null;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46 MB,击败了36.87% 的Java用户
     * @param root
     * @param toDelete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        Set<Integer> s = new HashSet<Integer>();
        for (int x : toDelete) s.add(x);
        if (dfs(ans, s, root) != null) ans.add(root);
        return ans;
    }

    private TreeNode dfs(List<TreeNode> ans, Set<Integer> s, TreeNode node) {
        if (node == null) return null;
        node.left = dfs(ans, s, node.left);
        node.right = dfs(ans, s, node.right);
        if (!s.contains(node.val)) return node;
        if (node.left != null) ans.add(node.left);
        if (node.right != null) ans.add(node.right);
        return null;
    }

}
