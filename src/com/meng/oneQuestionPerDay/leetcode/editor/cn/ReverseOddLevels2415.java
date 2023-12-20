package com.meng.oneQuestionPerDay.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
class ReverseOddLevels2415 {
    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了11.50% 的Java用户
     * 	内存消耗:48.2 MB,击败了39.94% 的Java用户
     * @param root
     * @return
     */
    public TreeNode reverseOddLevelsMy(TreeNode root) {
        if (root == null || root.left == null){
            return root;
        }
        int index = 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        List<TreeNode> temp = new ArrayList<>();
        while (list.size() > 0){
            index++;
            for(TreeNode node : list){
                if (node.left!=null){
                    temp.add(node.left);
                    temp.add(node.right);
                }
            }
            if (index % 2 == 1){
                int left = 0,right =temp.size() - 1;
                while (left < right){
                    temp.get(left).val = temp.get(left).val ^ temp.get(right).val;
                    temp.get(right).val = temp.get(left).val ^ temp.get(right).val;
                    temp.get(left).val = temp.get(left).val ^ temp.get(right).val;
                    left++;
                    right--;
                }
            }
            list.clear();
            if (temp.size()>0){
                list.addAll(temp);
            }
            temp.clear();
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了19.22% 的Java用户
     * 	内存消耗:46.8 MB,击败了84.80% 的Java用户
     * @param root
     * @return
     */
    public TreeNode reverseOddLevels1(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        boolean isOdd = false;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<TreeNode> arr = new ArrayList<TreeNode>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (isOdd) {
                    arr.add(node);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            if (isOdd) {
                for (int l = 0, r = sz - 1; l < r; l++, r--) {
                    int temp = arr.get(l).val;
                    arr.get(l).val = arr.get(r).val;
                    arr.get(r).val = temp;
                }
            }
            isOdd ^= true;
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了75.10% 的Java用户
     * 	内存消耗:47.1 MB,击败了75.20% 的Java用户
     * @param root
     * @return
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        dfs(root.left, root.right, true);
        return root;
    }

    public void dfs(TreeNode root1, TreeNode root2, boolean isOdd) {
        if (root1 == null) {
            return;
        }
        if (isOdd) {
            int temp = root1.val;
            root1.val = root2.val;
            root2.val = temp;
        }
        dfs(root1.left, root2.right, !isOdd);
        dfs(root1.right, root2.left, !isOdd);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
