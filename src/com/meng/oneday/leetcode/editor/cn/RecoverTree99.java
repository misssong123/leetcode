package com.meng.oneday.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import com.meng.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
class RecoverTree99 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了82.94% 的Java用户
     * @param root
     */
    public void recoverTree99(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root,list);
        //寻找逆序对的坐标
        int first = -1,second = -1;
        for(int i = 0 ; i < list.size() -1 ; i++){
            if(list.get(i).val > list.get(i+1).val){
                if (first != -1){
                    second = i;
                    break;
                }
                first = i;
            }
        }
        if(second == -1){
            int temp = list.get(first).val;
            list.get(first).val = list.get(first+1).val;
            list.get(first+1).val = temp;
        }else {
            int temp = list.get(first).val;
            list.get(first).val = list.get(second+1).val;
            list.get(second+1).val = temp;
        }
    }
    private void dfs(TreeNode root, List<TreeNode> list) {
        if(root == null){
            return;
        }
        if (root.left != null){
            dfs(root.left,list);
        }
        list.add(root);
        if (root.right != null){
            dfs(root.right,list);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了99.21% 的Java用户
     * @param root
     */
    public void recoverTree(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }
        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

}
