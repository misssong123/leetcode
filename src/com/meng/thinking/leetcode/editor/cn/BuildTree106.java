package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.HashMap;
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
class BuildTree106 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.67% 的Java用户
     * 	内存消耗:43.3 MB,击败了24.87% 的Java用户
     */
    public Map<Integer,Integer> inAndSufOrderMap = new HashMap<>();
    public TreeNode buildTreeMy(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for(int i = 0 ; i < n ; i++){
            inAndSufOrderMap.put(inorder[i],i);
        }
        TreeNode root = inAndSufOrderBuildDfs(postorder,0,n-1,0);
        return root;
    }

    private TreeNode inAndSufOrderBuildDfs(int[] postorder, int preLeft, int preRight,int inLeft) {
        if(preLeft > preRight){
            return null;
        }
        int val = postorder[preRight];
        int  inIndex = inAndSufOrderMap.get(val);
        int count  = inIndex - inLeft;
        TreeNode root = new TreeNode(val);
        root.left = inAndSufOrderBuildDfs(postorder,preLeft,preLeft+count-1,inLeft);
        root.right = inAndSufOrderBuildDfs(postorder,preLeft+count,preRight-1,inIndex+1);
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了67.60% 的Java用户
     * 	内存消耗:43.3 MB,击败了32.97% 的Java用户
     */
    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // 如果这里没有节点构造二叉树了，就结束
        if (in_left > in_right) {
            return null;
        }

        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两棵子树
        int index = idx_map.get(root_val);

        // 下标减一
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
