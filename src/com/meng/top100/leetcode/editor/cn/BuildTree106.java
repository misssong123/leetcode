package com.meng.top100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.TreeNode;

import java.util.HashMap;
import java.util.Map;

class BuildTree106 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.08% 的Java用户
     * 	内存消耗:43.8 MB,击败了19.69% 的Java用户
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree106(int[] inorder, int[] postorder) {
        Map<Integer,Integer> cache = new HashMap<>(inorder.length,1);
        for(int i = 0 ; i < inorder.length ; i++){
            cache.put(inorder[i],i);
        }
        return buildTree(postorder,0,postorder.length - 1,0,inorder.length - 1,cache);
    }

    private TreeNode buildTree(int[] postorder, int s1, int e1, int s2, int e2, Map<Integer, Integer> cache) {
        if(s1 > e1 || s2 > e2){
            return null;
        }
        //头节点的值
        int rootVal = postorder[e1];
        TreeNode root = new TreeNode(rootVal);
        //计算左节点的数量
        int leftSize = cache.get(rootVal) - s2;
        //左子树
        root.left = buildTree(postorder,s1,s1 + leftSize -1,s2,cache.get(rootVal) - 1,cache);
        //右子树
        root.right = buildTree(postorder,s1 + leftSize,e1 -1,cache.get(rootVal) + 1, e2,cache);
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.08% 的Java用户
     * 	内存消耗:43.5 MB,击败了49.28% 的Java用户
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> index = new HashMap<>(n, 1); // 预分配空间
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return dfs(0, n, postorder, 0, n, index); // 左闭右开区间
    }

    private TreeNode dfs(int inL, int inR, int[] postorder, int postL, int postR, Map<Integer, Integer> index) {
        if (postL == postR) { // 空节点
            return null;
        }
        int leftSize = index.get(postorder[postR - 1]) - inL; // 左子树的大小
        TreeNode left = dfs(inL, inL + leftSize, postorder, postL, postL + leftSize, index);
        TreeNode right = dfs(inL + leftSize + 1, inR, postorder, postL + leftSize, postR - 1, index);
        return new TreeNode(postorder[postR - 1], left, right);
    }
}
