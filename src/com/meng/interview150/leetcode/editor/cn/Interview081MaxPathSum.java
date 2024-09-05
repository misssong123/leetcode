package com.meng.interview150.leetcode.editor.cn;

class Interview081MaxPathSum {
    int max = -1000;
    //记录根节点
    TreeNode head = null;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了29.52% 的Java用户
     * @param root
     * @return
     */
    public int maxPathSumMy(TreeNode root) {
        if(root == null){
            return 0;
        }
        if (head == null){
            head = root;
        }
        //左子树最大值
        int leftMax = maxPathSum(root.left);
        leftMax = Math.max(leftMax,0);
        //右子树最大值
        int rightMax = maxPathSum(root.right);
        rightMax = Math.max(rightMax,0);
        //当前节点最大值
        max = Math.max(max,leftMax+rightMax+root.val);
        if (root ==head){
            return max;
        }
        //返回当前子树的最大值
        return Math.max(leftMax,rightMax)+root.val;
    }
    int maxSum = Integer.MIN_VALUE;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.92% 的Java用户
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
