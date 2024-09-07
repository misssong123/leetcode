package com.meng.interview150.leetcode.editor.cn;

class Interview086GetMinimumDifference {
    int ans = Integer.MAX_VALUE;
    int pre = -1;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了43.28% 的Java用户
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
       return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null){
            return;
        }
        if(root.left != null){
            dfs(root.left);
        }
        if (pre == -1){
            pre = root.val;
        }else {
            ans = Math.min(ans,root.val-pre);
            pre = root.val;
        }
        if (root.right != null){
            dfs(root.right);
        }
    }

}
