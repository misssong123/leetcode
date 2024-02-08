package com.meng.thinking.leetcode.editor.cn;

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;
import java.util.LinkedList;
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
class IsCousins993 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.2 MB,击败了38.21% 的Java用户
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousinsMy(TreeNode root, int x, int y) {
        if (x == root.val || y == root.val){
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        int size = queue.size();
        while (!queue.isEmpty()){
            boolean xFlag = false;
            boolean yFlag = false;
            for(int i = 0 ; i < size ; i++){
                TreeNode temp = queue.poll();
                int num = 0;
                if(temp.left != null){
                    queue.add(temp.left);
                    if (temp.left.val == x){
                        xFlag = true;
                        num++;
                    }
                    if (temp.left.val == y){
                        yFlag = true;
                        num++;
                    }
                }
                if(temp.right != null){
                    queue.add(temp.right);
                    if (temp.right.val == x){
                        xFlag = true;
                        num++;
                    }
                    if (temp.right.val == y){
                        yFlag = true;
                        num++;
                    }
                }
                if (num == 2){
                    return false;
                }
            }
            size = queue.size();
            if (xFlag && yFlag){
                return true;
            }
            if (xFlag || yFlag){
                return false;
            }
        }
        return false;
    }

    // x 的信息
    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound = false;

    // y 的信息
    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound = false;
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.3 MB,击败了33.04% 的Java用户
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    public void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }

        // 如果两个节点都找到了，就可以提前退出遍历
        // 即使不提前退出，对最坏情况下的时间复杂度也不会有影响
        if (xFound && yFound) {
            return;
        }

        dfs(node.left, depth + 1, node);

        if (xFound && yFound) {
            return;
        }

        dfs(node.right, depth + 1, node);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
