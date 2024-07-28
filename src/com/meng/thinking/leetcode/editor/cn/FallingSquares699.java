package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class FallingSquares699 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了48.65% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
            if(root.val>p.val&&root.val>q.val){
                root =  root.left;
            }else if (root.val<p.val&&root.val<q.val){
                root = root.right;
            }else {
                return root;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
