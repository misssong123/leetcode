package com.meng.origin;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 *
 * 输出:
 *
 * 2
 *
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 *
 * 输出:
 *
 * 2
 *
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * 通过次数24,403
 * 提交次数58,272
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestUnivaluePath {
    int maxPath = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root != null)
            result(root);
        return maxPath;
    }
    public int result(TreeNode root) {
        if (root==null)
            return 0;
        //获取左子树的长度
        int left = result(root.left);
        //获取右子树的长度
        int right = result(root.right);
        //初始化该节点最长左子树和最长右子树的长度为0
        int maxLeft=0,maxRight=0;
        //如果当前节点存在左节点，且左节点的值与当前节点相同，则该节点的左子树最长路径为left+1
        if (root.left != null && root.val == root.left.val)
            maxLeft=left+1;
        //如果当前节点存在右节点，且右节点的值与当前节点相同，则该节点的右子树最长路径为left+1
        if (root.right != null && root.val == root.right.val)
            maxRight=right+1;
        //更改最大路径的值
        maxPath = Math.max(maxPath,maxLeft+maxRight);
        //返回以该节点作为边的最大长度
        return Math.max(maxLeft,maxRight);

    }
}
