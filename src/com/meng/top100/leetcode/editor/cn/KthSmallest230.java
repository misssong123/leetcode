package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

class KthSmallest230 {
    int val = -1;
    boolean flag = false;
    int num = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了20.26% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest230(TreeNode root, int k) {
        flag = false;
        val = -1;
        num = 0;
        dfs(root,k);
        return val;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null){
            return;
        }
        if (flag){
            return;
        }
        dfs(root.left,k);
        num++;
        if (k == num){
            val = root.val;
            flag = true;
            return;
        }
        dfs(root.right,k);
    }

    private int k;
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了54.44% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        return dfs(root);
    }
    private int dfs(TreeNode node) {
        if (node == null) {
            return -1; // 题目保证节点值非负，用 -1 表示没有找到
        }
        int leftRes = dfs(node.left);
        if (leftRes != -1) { // 答案在左子树中
            return leftRes;
        }
        if (--k == 0) { // 答案就是当前节点
            return node.val;
        }
        return dfs(node.right); // 右子树会返回答案或者 -1
    }
}
