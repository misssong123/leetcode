package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

class SumNumbersLCR049 {

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了6.38% 的Java用户
     * @param root
     * @return
     */
    int sum;
    public int sumNumbersLCR049(TreeNode root) {
        sum = 0;
         dfsLCR049(root,0);
         return sum;
    }

    private void dfsLCR049(TreeNode root, int pre) {
        if (root.left == null && root.right == null) {
            sum += pre * 10 + root.val;
        }
        if (root.left != null) {
            dfs(root.left, pre * 10 + root.val);
        }
        if (root.right != null) {
            dfs(root.right, pre * 10 + root.val);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了6.38% 的Java用户
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int x) {
        if (node == null) {
            return 0;
        }
        x = x * 10 + node.val;
        if (node.left == null && node.right == null) { // node 是叶子节点
            return x;
        }
        return dfs(node.left, x) + dfs(node.right, x);
    }
}
