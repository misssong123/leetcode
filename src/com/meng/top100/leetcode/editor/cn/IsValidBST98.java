package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

class IsValidBST98 {
    long minValue = -1;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.5 MB,击败了5.03% 的Java用户
     * @param root
     * @return
     */
    public boolean isValidBST98(TreeNode root) {
        minValue = Long.MIN_VALUE;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null){
            return true;
        }
        boolean left = dfs(root.left);
        if (!left){
            return false;
        }
        if (root.val <= minValue){
            return false;
        }
        minValue = root.val;
        return dfs(root.right);
    }
    private long pre = Long.MIN_VALUE;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.5 MB,击败了5.03% 的Java用户
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) { // 左
            return false;
        }
        if (root.val <= pre) { // 中
            return false;
        }
        pre = root.val;
        return isValidBST(root.right); // 右
    }

}
