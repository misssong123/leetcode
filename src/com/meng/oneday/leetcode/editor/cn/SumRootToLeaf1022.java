package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

class SumRootToLeaf1022 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.9 MB,击败了80.79% 的Java用户
     * @param root
     * @return
     */
    public int sumRootToLeaf1022(TreeNode root) {
        return dfs1022(root,0);
    }

    private int dfs1022(TreeNode root, int pre) {
        if (root == null){
            return pre;
        }
        pre = pre * 2 + root.val;
        if (root.left == null ){
            return dfs1022(root.right,pre);
        }
        if (root.right == null){
            return dfs1022(root.left,pre);
        }
        return dfs1022(root.left,pre) + dfs1022(root.right,pre);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了47.26% 的Java用户
     * @param root
     * @return
     */
    public int sumRootToLeafOther1(TreeNode root) {
        return dfsOther1(root, 0);
    }

    private int dfsOther1(TreeNode node, int num) {
        if (node == null) {
            return 0;
        }
        num = num << 1 | node.val;
        if (node.left == null && node.right == null) {
            return num;
        }
        return dfsOther1(node.left, num) + dfsOther1(node.right, num);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.9 MB,击败了80.79% 的Java用户
     * @param root
     * @return
     */
    private int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    // 从根到 node（不含）的路径值为 num
    private void dfs(TreeNode node, int num) {
        if (node == null) {
            return;
        }
        num = num << 1 | node.val;
        if (node.left == null && node.right == null) {
            ans += num;
            return;
        }
        dfs(node.left, num);
        dfs(node.right, num);
    }

}
