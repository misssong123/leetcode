package com.meng.interview150.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

class Interview075BuildTree {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了39.42% 的Java用户
     * 	内存消耗:43.1 MB,击败了91.84% 的Java用户
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeMy(int[] inorder, int[] postorder) {
        return buildTree(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int l1, int r1, int[] postorder, int l2, int r2) {
        if (l1 > r1 || l2 > r2) {
            return null;
        }
        //构建根节点
        int val = postorder[r2];
        TreeNode node = new TreeNode(val);
        //查找中序遍历该节点的坐标
        int index = l1;
        for(int i = l1; i <= r1; i++) {
            if (inorder[i] == val) {
                index = i;
                break;
            }
        }
        //计算左子树长度
        int leftSize = index - l1;
        //构建左子树
        node.left = buildTree(inorder,l1,index-1,postorder,l2,l2+leftSize-1);
        //构建右子树
        node.right =buildTree(inorder,index+1,r1,postorder,l2+leftSize,r2-1);
        return node;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }
}
