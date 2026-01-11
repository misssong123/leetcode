package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class MaxLevelSum1161 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了55.87% 的Java用户
     * 	内存消耗:48.6 MB,击败了12.25% 的Java用户
     * @param root
     * @return
     */
    public int maxLevelSum1161(TreeNode root) {
        int res = 1 ,level = 1;
        long val = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            long sum = 0L;
            for (int i = 0 ; i < size ; i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left!= null){
                    queue.add(node.left);
                }
                if (node.right!= null){
                    queue.add(node.right);
                }
            }
            if (sum > val){
                res = level;
                val = sum;
            }
            level++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了22.70% 的Java用户
     * 	内存消耗:47.8 MB,击败了55.10% 的Java用户
     * @param root
     * @return
     */
    public int maxLevelSumOther1(TreeNode root) {
        int max_sum = Integer.MIN_VALUE;
        int ans = 0;

        List<TreeNode> q = new ArrayList<>();
        q.add(root);

        for (int level = 1; !q.isEmpty(); level++) {
            List<TreeNode> tmp = q;
            q = new ArrayList<>();
            int s = 0;

            for (TreeNode node : tmp) {
                s += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            if (s > max_sum) {
                max_sum = s;
                ans = level;
            }
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了91.84% 的Java用户
     * 	内存消耗:47.6 MB,击败了59.18% 的Java用户
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        List<Integer> rowSum = new ArrayList<>();
        dfs(root, 0, rowSum);

        int ans = 0;
        for (int i = 1; i < rowSum.size(); i++) {
            if (rowSum.get(i) > rowSum.get(ans)) {
                ans = i;
            }
        }
        return ans + 1; // 层号从 1 开始
    }
    private void dfs(TreeNode node, int level, List<Integer> rowSum) {
        if (node == null) {
            return;
        }

        if (rowSum.size() == level) { // 首次访问 level 层
            rowSum.add(node.val); // 节点值作为层和的初始值
        } else {
            rowSum.set(level, rowSum.get(level) + node.val);
        }

        dfs(node.left, level + 1, rowSum);
        dfs(node.right, level + 1, rowSum);
    }

}
