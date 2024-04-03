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
 *     TreeNode(int x) { val = x; }
 * }
 */

class GetTargetCopy1379 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了14.29% 的Java用户
     * 	内存消耗:46.4 MB,击败了100.00% 的Java用户
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopyMy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        List<TreeNode> list = new ArrayList<>();
        list.add(cloned);
        while (list.size() > 0){
            List<TreeNode> temp = new ArrayList<>();
            for(TreeNode node : list){
                if (node.val ==target.val){
                    return node;
                }
                if (node.left != null){
                    temp.add(node.left);
                }
                if (node.right != null){
                    temp.add(node.right);
                }
            }
            list.clear();
            list.addAll(temp);
        }
        return null;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:48.5 MB,击败了21.24% 的Java用户
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了6.56% 的Java用户
     * 	内存消耗:48.2 MB,击败了73.75% 的Java用户
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> q1 = new ArrayDeque<TreeNode>(), q2 = new ArrayDeque<TreeNode>();
        q1.offer(original);
        q2.offer(cloned);
        while (q1.size() > 0) {
            TreeNode node1 = q1.poll(), node2 = q2.poll();
            if (node1 == target) {
                return node2;
            }
            if (node1.left != null) {
                q1.offer(node1.left);
                q2.offer(node2.left);
            }
            if (node1.right != null) {
                q1.offer(node1.right);
                q2.offer(node2.right);
            }
        }
        return null; // impossible case
    }

}
//leetcode submit region end(Prohibit modification and deletion)
