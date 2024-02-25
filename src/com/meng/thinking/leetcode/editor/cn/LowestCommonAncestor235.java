package com.meng.thinking.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class LowestCommonAncestor235 {
    Map<TreeNode,TreeNode> parent = new HashMap<>();

    /**
     * 超时
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorMy(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> cache = new LinkedList<>();
        cache.add(root);
        int size = 1;
        int find = 0;
        while (!cache.isEmpty()){
            int temp = 0;
            for (int i = 0; i < size; i++){
                TreeNode poll = cache.poll();
                if (poll.left != null){
                    parent.put(poll.left,poll);
                    if (poll.left == p || poll.left == q){
                        find++;
                    }
                    cache.add(poll.left);
                    temp++;
                }
                if (poll.right != null){
                    parent.put(poll.right,poll);
                    if (poll.right == p || poll.right == q){
                        find++;
                    }
                    cache.add(poll.right);
                    temp++;
                }
                if (find==2){
                    break;
                }
            }
            size = temp;
            if (find==2){
                break;
            }
        }
        List<TreeNode> qParent = new ArrayList<>();
        TreeNode temp = q;
        while (true){
            if (parent.containsKey(temp)){
                qParent.add(parent.get(temp));
                temp = parent.get(temp);
            }else {
                break;
            }
        }
        if (qParent.size()==0){
            return q;
        }
        if (qParent.contains(p)){
            return p;
        }
        List<TreeNode> pParent = new ArrayList<>();
        temp = p;
        while (true){
            if (parent.containsKey(temp)){
                pParent.add(parent.get(temp));
                temp = parent.get(temp);
            }else {
                break;
            }
        }
        if (pParent.size()==0){
            return p;
        }
        if (pParent.contains(q)){
            return q;
        }
        int left = qParent.size()-1;
        int right = pParent.size()-1;
        while (left>=0 && right>=0){
            if (qParent.get(left).equals(pParent.get(right))){
                left--;
                right--;
            }
        }
        return qParent.get(left+1);
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了75.22% 的Java用户
     * 	内存消耗:43.9 MB,击败了33.96% 的Java用户
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

}
