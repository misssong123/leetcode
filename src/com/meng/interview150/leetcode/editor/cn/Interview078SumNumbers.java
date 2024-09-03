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
class Interview078SumNumbers {
    int sum = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了69.73% 的Java用户
     * @param root
     * @return
     */
    public int sumNumbersMy(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root,0);
        return sum;
    }

    private void dfs(TreeNode root, int val) {
        val = val * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += val;
            return ;
        }
        if (root.left != null) {
            dfs(root.left, val);
        }
        if (root.right != null) {
            dfs(root.right, val);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了39.71% 的Java用户
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfsOfficial(root, 0);
    }

    public int dfsOfficial(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfsOfficial(root.left, sum) + dfsOfficial(root.right, sum);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
