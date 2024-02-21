package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
class BuildTree105 {
    private Map<Integer, Integer> indexMap;

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了66.48% 的Java用户
     * 	内存消耗:43.1 MB,击败了48.86% 的Java用户
     * @param preorder
     * @param inorder
     * @param preorder_left
     * @param preorder_right
     * @param inorder_left
     * @param inorder_right
     * @return
     */
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTreeOfficial(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    /**
     * 2
     * ms
     * 击败
     * 67.07%
     * 使用 Java 的用户
     * 消耗内存分布
     * 43.14
     * MB
     * 击败
     * 48.13%
     * 使用 Java 的用户
     */
    public Map<Integer,Integer> preAndInOrderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for(int i = 0 ; i < n ; i++){
            preAndInOrderMap.put(inorder[i],i);
        }
        TreeNode root = preAndInOrderBuildDfs(preorder,0,n-1,0);
        return root;
    }

    private TreeNode preAndInOrderBuildDfs(int[] preorder, int preLeft, int preRight,int inLeft) {
        if(preLeft > preRight){
            return null;
        }
        int val = preorder[preLeft];
        int  inIndex = preAndInOrderMap.get(val);
        int count  = inIndex - inLeft;
        TreeNode root = new TreeNode(val);
        root.left = preAndInOrderBuildDfs(preorder,preLeft+1,preLeft+count,inLeft);
        root.right = preAndInOrderBuildDfs(preorder,preLeft+count+1,preRight,inIndex+1);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
