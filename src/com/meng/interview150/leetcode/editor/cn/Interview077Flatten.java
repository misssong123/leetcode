package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview077Flatten {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了21.92% 的Java用户
     * 	内存消耗:41 MB,击败了92.74% 的Java用户
     * @param root
     */
    public void flattenMy(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        for(int i = 0; i < list.size(); i++) {
            TreeNode cur = list.get(i);
            cur.left = null;
            cur.right = null;
            if (i < list.size() - 1) {
                cur.right = list.get(i + 1);
            }
        }
    }

    private void dfs(TreeNode root, List<TreeNode> list) {
        if(root == null) {
            return;
        }
        list.add(root);
        dfs(root.left, list);
        dfs(root.right, list);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了60.46% 的Java用户
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}
