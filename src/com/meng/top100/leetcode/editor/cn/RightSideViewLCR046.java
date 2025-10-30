package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.ArrayList;
import java.util.List;


class RightSideViewLCR046 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了76.92% 的Java用户
     * 	内存消耗:41.3 MB,击败了60.49% 的Java用户
     * @param root
     * @return
     */
    public List<Integer> rightSideViewLCR046(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<TreeNode> next = new ArrayList<>();
        while(!list.isEmpty()){
            res.add(list.get(list.size()-1).val);
            for(TreeNode node : list){
                if(node.left != null){
                    next.add(node.left);
                }
                if(node.right != null){
                    next.add(node.right);
                }
            }
            list.clear();
            list.addAll(next);
            next.clear();
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了34.26% 的Java用户
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (depth == ans.size()) { // 这个深度首次遇到
            ans.add(root.val);
        }
        dfs(root.right, depth + 1, ans); // 先递归右子树，保证首次遇到的一定是最右边的节点
        dfs(root.left, depth + 1, ans);
    }
}
