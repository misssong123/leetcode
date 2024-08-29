package com.meng.interview150.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Interview073CountNodes {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.4 MB,击败了71.13% 的Java用户
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return  1 + countNodes(root.left) + countNodes(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
