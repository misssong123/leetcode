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
class ConstructFromPrePost889 {

    public Map<Integer,Integer> preOrderMap = new HashMap<>();
    public Map<Integer,Integer> sufOrderMap = new HashMap<>();

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了4.99% 的Java用户
     * 	内存消耗:43.2 MB,击败了5.26% 的Java用户
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        for(int i = 0 ; i < n ; i++){
            preOrderMap.put(preorder[i],i);
            sufOrderMap.put(postorder[i],i);
        }
        TreeNode root = preAndSufOrderBuildDfs(preorder,postorder,0,n-1,0,n-1);
        return root;
    }

    private TreeNode preAndSufOrderBuildDfs(int[] preorder,int[] postorder, int preLeft, int preRight,int sufLeft,int sufRight) {
        if(preLeft > preRight){
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        if (preRight - preLeft == 0){
            return root;
        }
        int leftVal =  preLeft + 1 < preorder.length ?preorder[preLeft+1] : -1;
        int rightVal = sufRight - 1 >= 0 ? postorder[sufRight-1] : -1;
        if (preRight - preLeft == 1|| rightVal == leftVal){
            rightVal = -1;
        }
        if (leftVal != -1){
            Integer sufLeftIndex = sufOrderMap.get(leftVal);
            int len = sufLeftIndex - sufLeft;
            root.left = preAndSufOrderBuildDfs(preorder,postorder,preLeft+1,preLeft+len+1,sufLeft,sufLeftIndex);
        }
        if (rightVal != -1){
            Integer preRightIndex = preOrderMap.get(rightVal);
            int len = preRight - preRightIndex;
            root.right = preAndSufOrderBuildDfs(preorder,postorder,preRightIndex,preRight,sufRight-len-1,sufRight-1);
        }
        return root;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了73.28% 的Java用户
     * 	内存消耗:42.5 MB,击败了27.26% 的Java用户
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePostOfficial(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            postMap.put(postorder[i], i);
        }
        return dfs(preorder, postorder, postMap, 0, n - 1, 0, n - 1);
    }

    public TreeNode dfs(int[] preorder, int[] postorder, Map<Integer, Integer> postMap, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return null;
        }
        int leftCount = 0;
        if (preLeft < preRight) {
            leftCount = postMap.get(preorder[preLeft + 1]) - postLeft + 1;
        }
        return new TreeNode(preorder[preLeft],
                dfs(preorder, postorder, postMap, preLeft + 1, preLeft + leftCount, postLeft, postLeft + leftCount - 1),
                dfs(preorder, postorder, postMap, preLeft + leftCount + 1, preRight, postLeft + leftCount, postRight - 1));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
