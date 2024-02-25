package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)


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
class ReplaceValueInTree2641 {
    /**
     * 解答成功:
     * 	执行耗时:48 ms,击败了11.89% 的Java用户
     * 	内存消耗:75.8 MB,击败了28.71% 的Java用户
     * @param root
     * @return
     */
    public TreeNode replaceValueInTreeMy(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        root.val = 0;
        while (true){
            List<TreeNode> temp = new ArrayList<>();
            for (TreeNode node : list) {
                if(node == null){
                    continue;
                }
                temp.add(node.left);
                temp.add(node.right);
            }
            int sum = 0;
            for(TreeNode node : temp){
                sum += node == null ? 0 : node.val;
            }
            int left = 0;
            for(int i = 0 ; i < temp.size() ; i++){
                if (i % 2 == 0){
                    left = temp.get(i) == null?0:temp.get(i).val;
                }
                if(temp.get(i) == null){
                    continue;
                }
                temp.get(i).val = sum - temp.get(i).val;
                if (i % 2 == 0 && temp.get(i+1)!= null){
                    temp.get(i).val = temp.get(i).val - temp.get(i+1).val;
                }
                if (i % 2 == 1 && temp.get(i-1)!= null){
                    temp.get(i).val = temp.get(i).val - left;
                }
            }
            if (sum==0){
                break;
            }
            list.clear();
            list.addAll(temp);
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了71.63% 的Java用户
     * 	内存消耗:75.5 MB,击败了50.63% 的Java用户
     * @param root
     * @return
     */
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        root.val = 0;
        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new ArrayDeque<>();
            int sum = 0;
            for (TreeNode fa : queue) {
                if (fa.left != null) {
                    queue2.offer(fa.left);
                    sum += fa.left.val;
                }
                if (fa.right != null) {
                    queue2.offer(fa.right);
                    sum += fa.right.val;
                }
            }
            for (TreeNode fa : queue) {
                int childSum = (fa.left != null ? fa.left.val : 0) +
                        (fa.right != null ? fa.right.val : 0);
                if (fa.left != null) {
                    fa.left.val = sum - childSum;
                }
                if (fa.right != null) {
                    fa.right.val = sum - childSum;
                }
            }
            queue = queue2;
        }
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
