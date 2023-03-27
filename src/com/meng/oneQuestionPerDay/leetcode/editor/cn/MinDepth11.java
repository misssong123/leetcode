package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class MinDepth11 {
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 59.83%
     * 的用户
     * 内存消耗：
     * 61 MB
     * , 在所有 Java 提交中击败了
     * 62.00%
     * 的用户
     * 通过测试用例：
     * 52 / 52
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null){
            return 0;
        }
        return dfs(root,0);
    }

    private int dfs(TreeNode root, int i) {
        if (root.left == null && root.right == null){
            return i+1;
        }
        int left = Integer.MAX_VALUE;
        if (root.left != null){
            left = dfs(root.left,i+1);
        }
        int right = Integer.MAX_VALUE;
        if (root.left != null){
            right = dfs(root.right,i+1);
        }
        return Math.min(left,right);
    }

    /**
     * 方法一：深度优先搜索
     * @param root
     * @return
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 6.97%
     * 的用户
     * 内存消耗：
     * 60.7 MB
     * , 在所有 Java 提交中击败了
     * 75.24%
     * 的用户
     * 通过测试用例：
     * 52 / 52
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }


    /**
     * 方法二：广度优先搜索
     * @param root
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 92.28%
     * 的用户
     * 内存消耗：
     * 60.2 MB
     * , 在所有 Java 提交中击败了
     * 94.40%
     * 的用户
     * 通过测试用例：
     * 52 / 52
     */
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }

        return 0;
    }
    class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
