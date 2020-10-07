package com.meng;

import java.util.ArrayList;
import java.util.List;

/**
 *给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树节点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDiffInBST {
    int min=Integer.MAX_VALUE;
    List<Integer> list = new ArrayList<>();
    public int minDiffInBST(TreeNode root) {
        if (root != null)
            dfs(root);
        for (int i = 0 ; i < list.size()-1;i++)
            min = Math.min(min,list.get(i+1)-list.get(i));
        return min;
    }

    /**
     * 中序取出所有的数字，比较每两个之间的大小差，去最小的那个
     * @param node
     */
    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }
}
