package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Interview084LevelOrder {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了13.77% 的Java用户
     * 	内存消耗:43.9 MB,击败了74.52% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderMy(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
            List<TreeNode> list = new ArrayList<>();
            list.add(root);
            while (!list.isEmpty()){
                List<TreeNode> temp = new ArrayList<>();
                List<Integer> val = new ArrayList<>();
                for (TreeNode node : list) {
                    val.add(node.val);
                    if (node.left != null){
                        temp.add(node.left);
                    }
                    if (node.right != null){
                        temp.add(node.right);
                    }
                }
                res.add(val);
                list.clear();
                if (!temp.isEmpty()){
                    list.addAll(temp);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了92.64% 的Java用户
     * 	内存消耗:44 MB,击败了47.82% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }
}
