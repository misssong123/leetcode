package com.meng.origin;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GetMinimumDifference {
    int result = Integer.MAX_VALUE,pre=-1;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return result;
    }
    private void inOrder(TreeNode node) {
        if (node == null)
            return;
        inOrder(node.left);
        if (pre==-1)
            pre = node.val;
        else{
            result = Math.min(result,node.val - pre);
            pre=node.val;
        }
        inOrder(node.right);
    }
}
