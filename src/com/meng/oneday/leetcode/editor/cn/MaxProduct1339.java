package com.meng.oneday.leetcode.editor.cn;

import com.meng.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

class MaxProduct1339 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了9.42% 的Java用户
     * 	内存消耗:62.8 MB,击败了5.23% 的Java用户
     * @param root
     * @return
     */
    public int maxProduct1339(TreeNode root) {
        //计算每个节点包含子节点的大小
        List<Integer> vals = new ArrayList<>();
        int sum = dfs(root,vals);
        long res = 0L;
        for (Integer val : vals) {
            res = Math.max(res,(long)val*(sum-val));
        }
        return (int) (res%MOD);
    }

    private int dfs(TreeNode node, List<Integer> vals) {
        if (node == null){
            return 0;
        }
        int sum = node.val + dfs(node.left,vals) + dfs(node.right,vals);
        vals.add(sum);
        return sum;
    }

    private static final int MOD = 1_000_000_007;
    private long ans = 0;
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了45.03% 的Java用户
     * 	内存消耗:59.1 MB,击败了30.36% 的Java用户
     * @param root
     * @return
     */
    public int maxProductOther(TreeNode root) {
        int total = dfs1(root);
        dfs2(root, total);
        return (int) (ans % MOD);
    }

    private int dfs1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.val + dfs1(node.left) + dfs1(node.right);
    }

    private int dfs2(TreeNode node, int total) {
        if (node == null) {
            return 0;
        }
        int s = node.val + dfs2(node.left, total) + dfs2(node.right, total);
        ans = Math.max(ans, (long) s * (total - s));
        return s;
    }

}
