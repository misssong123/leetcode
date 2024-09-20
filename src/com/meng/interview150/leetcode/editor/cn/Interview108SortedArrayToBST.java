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
class Interview108SortedArrayToBST {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了90.53% 的Java用户
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums,0,nums.length-1);
    }

    private TreeNode dfs(int[] nums, int l, int r) {
        if(l > r){
            return null;
        }
        // 选取中间位置为根节点
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        //左子树
        root.left = dfs(nums,l,mid-1);
        //右子树
        root.right = dfs(nums,mid+1,r);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
