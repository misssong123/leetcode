package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.HashMap;
import java.util.Map;

class BuildTree105 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了37.60% 的Java用户
     * 	内存消耗:43.4 MB,击败了67.78% 的Java用户
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree105(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if(s1 > e1 || s2 > e2){
            return null;
        }
        //构建头节点
        TreeNode root = new TreeNode(preorder[s1]);
        int rootVal = preorder[s1];
        //找到根节点在中序遍历中的位置
        int middleIndex = -1;
        for (int i = s2 ; i <= e2 ; i++){
            if (inorder[i] == rootVal){
                middleIndex = i;
                break;
            }
        }
        //元素个数
        int leftSize = middleIndex - s2;
        //构建左子树
        root.left = buildTree(preorder,s1+1,s1+leftSize,inorder,s2,middleIndex-1);
        //构建右子树
        root.right = buildTree(preorder,s1+leftSize+1,e1,inorder,middleIndex+1,e2);
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了93.31% 的Java用户
     * 	内存消耗:43.4 MB,击败了56.18% 的Java用户
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> index = new HashMap<>(n, 1); // 预分配空间
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return dfs(preorder, 0, n, 0, n, index); // 左闭右开区间
    }

    private TreeNode dfs(int[] preorder, int preL, int preR, int inL, int inR, Map<Integer, Integer> index) {
        if (preL == preR) { // 空节点
            return null;
        }
        int leftSize = index.get(preorder[preL]) - inL; // 左子树的大小
        TreeNode left = dfs(preorder, preL + 1, preL + 1 + leftSize, inL, inL + leftSize, index);
        TreeNode right = dfs(preorder, preL + 1 + leftSize, preR, inL + 1 + leftSize, inR, index);
        return new TreeNode(preorder[preL], left, right);
    }

}
