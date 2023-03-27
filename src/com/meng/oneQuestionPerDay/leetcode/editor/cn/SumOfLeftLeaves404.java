package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class SumOfLeftLeaves404 {

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.8 MB
     * , 在所有 Java 提交中击败了
     * 5.09%
     * 的用户
     * 通过测试用例：
     * 100 / 100
     * @param root
     * @return
     */
    int sum = 0 ;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root,1);
        return sum;
    }

    private void dfs(TreeNode root, int i) {
        if (root.left==null && root.right==null){
            if (i==1){
                sum+=root.val;
            }
            return;
        }
        if (root.left != null){
            dfs(root.left,1);
        }
        if (root.right != null){
            dfs(root.right,2);
        }
    }

    /**
     * 深度遍历
     * @param root
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 10.95%
     * 的用户
     * 通过测试用例：
     * 100 / 100
     */
    public int sumOfLeftLeaves1(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null && !isLeafNode(node.right)) {
            ans += dfs(node.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    /**
     * 广度遍历
     * @param root
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 72.20%
     * 的用户
     * 通过测试用例：
     * 100 / 100
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (isLeafNode2(node.left)) {
                    ans += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null) {
                if (!isLeafNode2(node.right)) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }

    public boolean isLeafNode2(TreeNode node) {
        return node.left == null && node.right == null;
    }

}

