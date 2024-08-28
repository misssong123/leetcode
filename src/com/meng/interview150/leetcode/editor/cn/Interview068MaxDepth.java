package com.meng.interview150.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.Queue;

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
class Interview068MaxDepth {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了80.83% 的Java用户
     * @param root
     * @return
     */
    public int maxDepthMy(TreeNode root) {
        return dfs(root,0);
    }

    private int dfs(TreeNode root, int i) {
        if (root == null){
            return i;
        }
        return Math.max(dfs(root.left,i+1),dfs(root.right,i+1));
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了20.98% 的Java用户
     * 	内存消耗:41.7 MB,击败了27.71% 的Java用户
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
