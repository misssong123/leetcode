package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;
class SubtreeWithAllDeepest865 {
    /**
     * 无思路
     * @param root
     * @return
     */
    int maxLevel = -1;
    TreeNode node = null;
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.6 MB,击败了58.15% 的Java用户
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest865(TreeNode root) {
         dfs865(root, 0);
        return node;
    }

    private int dfs865(TreeNode treeNode, int level) {
        if (treeNode == null) {
            maxLevel = Math.max(maxLevel, level);
            return level;
        }
        int leftLevel = dfs865(treeNode.left, level + 1);
        int rightLevel = dfs865(treeNode.right, level + 1);
        if (leftLevel == rightLevel && leftLevel == maxLevel) {
            node = treeNode;
        }
        return Math.max(leftLevel, rightLevel);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了10.32% 的Java用户
     * @param root
     * @return
     */
    private int maxDepth = -1; // 全局最大深度
    private TreeNode ans;
    public TreeNode subtreeWithAllDeepestOther(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            maxDepth = Math.max(maxDepth, depth); // 维护全局最大深度
            return depth;
        }

        int leftMaxDepth = dfs(node.left, depth + 1); // 获取左子树最深叶节点的深度
        int rightMaxDepth = dfs(node.right, depth + 1); // 获取右子树最深叶节点的深度

        if (leftMaxDepth == rightMaxDepth && leftMaxDepth == maxDepth) {
            ans = node; // node 可能是答案
        }

        return Math.max(leftMaxDepth, rightMaxDepth); // 当前子树最深叶节点的深度
    }
}
