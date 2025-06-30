package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

class CountUnivalSubtrees250 {
    int ans = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了93.75% 的Java用户
     * @param root
     * @return
     */
    public int countUnivalSubtrees250(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        if(root.left == null && root.right == null){
            ans++;
            return true;
        }
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        if(left && right && (root.left == null || root.left.val == root.val) && (root.right == null || root.right.val == root.val)){
            ans++;
            return true;
        }
        return false;
    }
    int count = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了28.57% 的Java用户
     * @param root
     * @return
     */
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        isUnivalSubtree(root, root.val);
        return count;
    }

    public boolean isUnivalSubtree(TreeNode node, int val) {
        if (node == null) {
            return true;
        } else if (!isUnivalSubtree(node.left, node.val) | !isUnivalSubtree(node.right, node.val)) {
            return false;
        } else {
            count++;
            return node.val == val;
        }
    }

}
