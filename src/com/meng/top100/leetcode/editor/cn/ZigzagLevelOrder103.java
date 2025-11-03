package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.*;

class ZigzagLevelOrder103 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了83.14% 的Java用户
     * 	内存消耗:43 MB,击败了5.13% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder103(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            while (size > 0){
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null){
                    queue.add(poll.left);
                }
                if (poll.right != null){
                    queue.add(poll.right);
                }
                size--;
            }
            if(flag){
                Collections.reverse(list);
            }
            res.add(list);
            flag = !flag;

        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了83.14% 的Java用户
     * 	内存消耗:43.1 MB,击败了5.13% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> vals = new ArrayList<>(n); // 容量已知
            while (n-- > 0) {
                TreeNode node = q.poll();
                vals.add(node.val);
                if (node.left != null)  q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            if (ans.size() % 2 > 0) Collections.reverse(vals);
            ans.add(vals);
        }
        return ans;
    }
}
