package com.meng.interview150.leetcode.editor.cn;

class Interview088IsValidBST {
    boolean flag = true;
    int index = 0;
    int pre = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了36.76% 的Java用户
     * @param root
     * @return
     */
    public boolean isValidBSTMy(TreeNode root) {
        dfs(root);
        return flag;
    }

    private void dfs(TreeNode root) {
        if (root == null || !flag){
            return;
        }
        dfs(root.left);
        if (index != 0 && root.val <= pre){
            flag = false;
            return;
        }
        index++;
        pre = root.val;
        dfs(root.right);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了68.36% 的Java用户
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}
