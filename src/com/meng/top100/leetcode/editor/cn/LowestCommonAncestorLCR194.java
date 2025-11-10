package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.ArrayList;
import java.util.List;

class LowestCommonAncestorLCR194 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了16.64% 的Java用户
     * 	内存消耗:45 MB,击败了10.93% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorLCR194(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pRoot = new ArrayList<>();
        dfs(root,p,pRoot);
        List<TreeNode> qRoot = new ArrayList<>();
        dfs(root,q,qRoot);
        int min = Math.min(pRoot.size(),qRoot.size());
        for(int i = 0 ; i < min; i++){
            if (pRoot.get(i) != qRoot.get(i)){
                return pRoot.get(i-1);
            }
        }
        return pRoot.get(min-1);
    }

    private boolean dfs(TreeNode root, TreeNode p,List<TreeNode> rootList) {
        if(root == p){
            rootList.add(root);
            return true;
        }
        if (root.left != null){
            rootList.add(root);
            if (dfs(root.left,p,rootList)){
                return true;
            }
            rootList.remove(root);
        }
        if (root.right != null){
            rootList.add(root);
            if (dfs(root.right,p,rootList)){
                return true;
            }
            rootList.remove(root);
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了99.67% 的Java用户
     * 	内存消耗:45.3 MB,击败了7.18% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root; // 找到 p 或 q 就不往下递归了，原因见上面答疑
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) { // 左右都找到
            return root; // 当前节点是最近公共祖先
        }
        // 如果只有左子树找到，就返回左子树的返回值
        // 如果只有右子树找到，就返回右子树的返回值
        // 如果左右子树都没有找到，就返回 null（注意此时 right = null）
        return left != null ? left : right;
    }
}
