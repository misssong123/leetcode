package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;
import java.util.ArrayList;
import java.util.List;

class ConvertBST538 {
    int index;
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了7.16% 的Java用户
     * 	内存消耗:44.7 MB,击败了6.84% 的Java用户
     * @param root
     * @return
     */
    public TreeNode convertBST538(TreeNode root) {
        if(root == null){
            return null;
        }
        index = 0;
        List<Integer> vals = new ArrayList<>();
        dfs(root,vals,true);
        for(int i = vals.size()-2; i>=0 ; i--){
            vals.set(i,vals.get(i)+vals.get(i+1));
        }
        dfs(root,vals,false);
        return root;
    }

    private void dfs(TreeNode root, List<Integer> vals, boolean isAdd) {
        if (root == null) {
            return;
        }
        dfs(root.left,vals,isAdd);
        if (isAdd){
            vals.add(root.val);
        }else {
            root.val = vals.get(index++);
        }
        dfs(root.right,vals,isAdd);
    }
    int tot = 0;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.5 MB,击败了47.60% 的Java用户
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    void dfs(TreeNode root) {
        if (root == null)
            return ;
        dfs(root.right);
        tot += root.val;
        root.val = tot;
        dfs(root.left);
    }
}
