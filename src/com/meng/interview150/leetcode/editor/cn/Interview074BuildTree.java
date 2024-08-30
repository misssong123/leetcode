package com.meng.interview150.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

class Interview074BuildTree {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了19.85% 的Java用户
     * 	内存消耗:43.1 MB,击败了86.09% 的Java用户
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeMy(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
        if (l1 > r1 || l2 > r2) {
            return null;
        }
        //构建根节点
        int val = preorder[l1];
        TreeNode node = new TreeNode(val);
        //查找中序遍历该节点的坐标
        int index = l2;
        for(int i = l2; i <= r2; i++) {
            if (inorder[i] == val) {
                index = i;
                break;
            }
        }
        //计算左子树长度
        int leftSize = index - l2;
        //计算右子树长度
        int rightSize = r2 - index;
        //构建左子树
        node.left = buildTree(preorder,l1+1,l1+leftSize,inorder,l2,index-1);
        //构建右子树
        node.right =buildTree(preorder,l1+leftSize+1,r1,inorder,index+1,r2);
        return node;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.25% 的Java用户
     * 	内存消耗:43.1 MB,击败了91.18% 的Java用户
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
