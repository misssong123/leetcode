package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

class IsUnivalTree965 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了67.03% 的Java用户
     * @param root
     * @return
     */
    public boolean isUnivalTree965(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean left = true,right = true;
        if (root.left != null){
            left = root.val == root.left.val && isUnivalTree(root.left);
        }
        if (left && root.right != null){
            right = root.val == root.right.val && isUnivalTree(root.right);
        }
        return left && right;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了88.71% 的Java用户
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.val != root.left.val || !isUnivalTree(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.val != root.right.val || !isUnivalTree(root.right)) {
                return false;
            }
        }
        return true;
    }

}
