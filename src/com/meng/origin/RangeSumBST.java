package com.meng.origin;

import java.util.Stack;

/**
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 *
 * 二叉搜索树保证具有唯一的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 *
 * 示例 2：
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 *
 *
 *
 * 提示：
 *
 *     树中的结点数量最多为 10000 个。
 *     最终的答案保证小于 2^31。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RangeSumBST {
    int sum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root != null )
            dfs(root,L,R);
        return sum;
    }

    private void dfs(TreeNode node, int l, int r) {
        if (node != null){
            if (node.val>= l && node.val<=r)
                sum +=node.val;
            if (node.val>l)
                dfs(node.left,l,r);
            if (node.val<=r)
                dfs(node.right,l,r);
        }
    }

    /**
     * 非递归做法
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST2(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }

}

