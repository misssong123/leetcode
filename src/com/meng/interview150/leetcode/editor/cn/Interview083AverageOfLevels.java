package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview083AverageOfLevels {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了10.10% 的Java用户
     * 	内存消耗:44.4 MB,击败了88.13% 的Java用户
     * @param root
     * @return
     */
    public List<Double> averageOfLevelsMy(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root != null){
            res.add((double)root.val);
            List<TreeNode> list = new ArrayList<>();
            list.add(root);
            while (!list.isEmpty()){
                List<TreeNode> temp = new ArrayList<>();
                long sum = 0L;
                for(TreeNode node : list){
                    if (node.left != null){
                        temp.add(node.left);
                        sum += node.left.val;
                    }
                    if (node.right != null){
                        temp.add(node.right);
                        sum += node.right.val;
                    }
                }
                list.clear();
                if (!temp.isEmpty()){
                    res.add((double)sum/temp.size());
                    list.addAll(temp);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.87% 的Java用户
     * 	内存消耗:44.5 MB,击败了63.33% 的Java用户
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<Double>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }

    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }
}
