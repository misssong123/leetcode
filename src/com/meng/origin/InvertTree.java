package com.meng.origin;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        //交换左右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //交换左子树
        invertTree(root.left);
        //交换右子树
        invertTree(root.right);
        return root;
    }
}
