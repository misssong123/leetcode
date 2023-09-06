package com.meng.route.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)



import com.meng.oneQuestionPerDay.leetcode.editor.cn.util.TreeNode;
import javafx.util.Pair;

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
class SolutionLcaDeepestLeaves1123 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了8.96% 的Java用户
     * 	内存消耗:42.1 MB,击败了36.79% 的Java用户
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves1(TreeNode root) {
        LinkedList<TreeNode> cache = new LinkedList<>();
        Map<TreeNode,TreeNode> map = new HashMap<>();
        cache.add(root);
        List<TreeNode> pre = new ArrayList<>();
        while (!cache.isEmpty()){
            int size = cache.size();
            for (int i = 0 ; i < size ; i++){
                TreeNode pop = cache.pop();
                if (pop.left != null){
                    cache.add(pop.left);
                    map.put(pop.left,pop);
                }
                if (pop.right != null){
                    cache.add(pop.right);
                    map.put(pop.right,pop);
                }
                pre.add(pop);
            }
            if (!cache.isEmpty()){
                pre.clear();
            }
        }
        if (pre.size()==1){
            return pre.get(0);
        }
        Set<TreeNode> res = new HashSet<>();
        while (cache.size() > 1 || pre.size() >0){
            if (pre.size()>0){
                for(TreeNode node : pre){
                    if (res.add(map.get(node))){
                        cache.add(map.get(node));
                    }
                }
                pre.clear();
            }else{
                int size = cache.size();
                for(int i = 0 ; i < size ; i++){
                    TreeNode node = cache.pop();
                    if (res.add(map.get(node))){
                        cache.add(map.get(node));
                    }
                }
            }
        }
        return cache.pop();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了36.32% 的Java用户
     * 	内存消耗:41.8 MB,击败了85.38% 的Java用户
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return f(root).getKey();
    }

    private Pair<TreeNode, Integer> f(TreeNode root) {
        if (root == null) {
            return new Pair<>(root, 0);
        }

        Pair<TreeNode, Integer> left = f(root.left);
        Pair<TreeNode, Integer> right = f(root.right);

        if (left.getValue() > right.getValue()) {
            return new Pair<>(left.getKey(), left.getValue() + 1);
        }
        if (left.getValue() < right.getValue()) {
            return new Pair<>(right.getKey(), right.getValue() + 1);
        }
        return new Pair<>(root, left.getValue() + 1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
