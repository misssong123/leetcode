package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;

import java.util.*;

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
class KthLargestLevelSum2583 {
    /**
     * 解答成功:
     * 	执行耗时:36 ms,击败了38.55% 的Java用户
     * 	内存消耗:61.1 MB,击败了88.83% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public long kthLargestLevelSumMy(TreeNode root, int k) {
        List<Long> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        int size = 1;
        while (!queue.isEmpty()){
            int num = 0;
            long temp = 0L;
            for(int i = 0 ; i< size ; i++){
                TreeNode poll = queue.poll();
                temp += poll.val;
                if (poll.left != null){
                    queue.add(poll.left);
                    num++;
                }
                if (poll.right != null){
                    queue.add(poll.right);
                    num++;
                }
            }
            res.add(temp);
            size = num;
        }
        Collections.sort(res);
        if (k > res.size()){
            return -1;
        }
        return  res.get(res.size() - k);
    }

    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了12.29% 的Java用户
     * 	内存消耗:55.9 MB,击败了100.00% 的Java用户
     * @param root
     * @param k
     * @return
     */
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        List<Long> levelSumArray = new ArrayList<Long>();
        while (!queue.isEmpty()) {
            List<TreeNode> levelNodes = new ArrayList<TreeNode>(queue);
            long levelSum = 0;
            queue.clear();
            for (TreeNode node : levelNodes) {
                levelSum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            levelSumArray.add(levelSum);
        }
        if (levelSumArray.size() < k) {
            return -1;
        }
        Collections.sort(levelSumArray);
        return levelSumArray.get(levelSumArray.size() - k);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
