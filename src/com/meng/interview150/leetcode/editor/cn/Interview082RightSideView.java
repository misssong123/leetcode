package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

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
class Interview082RightSideView {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了79.49% 的Java用户
     * 	内存消耗:41.4 MB,击败了5.30% 的Java用户
     * @param root
     * @return
     */
    public List<Integer> rightSideViewMy(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            List<TreeNode> list = new ArrayList<>();
            list.add(root);
            res.add(root.val);
            while (!list.isEmpty()) {
                List<TreeNode> temp = new ArrayList<>();
                for (TreeNode node : list) {
                    if (node.left != null){
                        temp.add(node.left);
                    }
                    if (node.right != null) {
                        temp.add(node.right);
                    }
                }
                list.clear();
                if (!temp.isEmpty()) {
                    list.addAll(temp);
                    res.add(temp.get(temp.size()-1).val);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了6.48% 的Java用户
     * 	内存消耗:41.4 MB,击败了5.30% 的Java用户
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Deque<TreeNode> nodeStack = new LinkedList<TreeNode>();
        Deque<Integer> depthStack = new LinkedList<Integer>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }
}
