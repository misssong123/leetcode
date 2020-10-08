package com.meng;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left==null && root.right==null) )
            return true;
       return dfs(root.left,root.right);
    }
    //思路：若当前left和Right为空则返回true
    //若同时不为空，则比较两节点的值是否相同，且左节点的左节点是否等于右节点的右节点，左节点的右节点是否等于右节点的左节点
    //其他情况均返回false
    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left != null && right != null)
            return left.val == right.val && dfs(left.left,right.right) && dfs(left.right,right.left);
        else
            return false;
    }

}
