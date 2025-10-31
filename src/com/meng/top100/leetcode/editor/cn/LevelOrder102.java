package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class LevelOrder102 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了1.84% 的Java用户
     * 	内存消耗:46.1 MB,击败了5.09% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder102(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()){
            List<Integer> temp = new ArrayList<>(list.size());
            List<TreeNode> listNode = new ArrayList<>();
            for (TreeNode node : list) {
                temp.add(node.val);
                if (node.left != null){
                    listNode.add(node.left);
                }
                if (node.right != null){
                    listNode.add(node.right);
                }
            }
            res.add(new ArrayList<>(temp));
            list  = listNode;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了94.79% 的Java用户
     * 	内存消耗:46 MB,击败了5.09% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> vals = new ArrayList<>(n); // 预分配空间
            while (n-- > 0) {
                TreeNode node = q.poll();
                vals.add(node.val);
                if (node.left != null)  q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            ans.add(vals);
        }
        return ans;
    }
}
