package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class Interview087KthSmallest {
    int ans = -1,num=-1;
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了88.97% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public int kthSmallestMy(TreeNode root, int k) {
        num = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null){
            return;
        }
        dfs(root.left);
        num--;
        if (num == 0 && ans ==-1){
            ans = root.val;
            return;
        }
        dfs(root.right);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了56.85% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

}
