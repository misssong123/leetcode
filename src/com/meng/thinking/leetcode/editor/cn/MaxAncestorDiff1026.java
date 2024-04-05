package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

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
class MaxAncestorDiff1026 {
    int res= 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了21.91% 的Java用户
     * @param root
     * @return
     */
    public int maxAncestorDiffMy(TreeNode root) {
        dfsMy(root,root.val,root.val);
        return res;
    }
    private  void dfsMy(TreeNode root, int max, int min){
        res = Math.max(Math.max(res,Math.abs(max-root.val)),
                Math.abs(min-root.val));
        if (root.left != null){
            dfs(root.left,Math.max(max,root.val),Math.min(min,root.val));
        }
        if (root.right != null){
            dfs(root.right,Math.max(max,root.val),Math.min(min,root.val));
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41 MB,击败了27.47% 的Java用户
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    public int dfs(TreeNode root, int mi, int ma) {
        if (root == null) {
            return 0;
        }
        int diff = Math.max(Math.abs(root.val - mi), Math.abs(root.val - ma));
        mi = Math.min(mi, root.val);
        ma = Math.max(ma, root.val);
        diff = Math.max(diff, dfs(root.left, mi, ma));
        diff = Math.max(diff, dfs(root.right, mi, ma));
        return diff;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
