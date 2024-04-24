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
class AmountOfTime2385 {
     int index = 0,num = 0,child = 0;
     TreeNode parent = null;

    /**
     * 思路错误
     * @param root
     * @param start
     * @return
     */
    public int amountOfTime(TreeNode root, int start) {
        if (root.val == start){
            index = 0;
        }
        int left = dfs(root.left,start,-1,1);
        int right = dfs(root.right,start,1,1);
        int c = child == -1 ?dfs(parent.right,-1,-1,-1)+1:
                child == 1 ?dfs(parent.left,-1,-1,-1)+1:0;
        System.out.println(left+" "+right+" " +index + " "+num + " "+c);
        if(index==0){
            return Math.max(left,right);
        }else if (index==-1){//左侧
            return Math.max(Math.max(left-num,num+right),c);
        }else {
            return Math.max(Math.max(right-num,num+left),c);
        }
    }
    private int dfs(TreeNode root,int start,int n,int m) {
        if(root == null){
            return 0;
        }
        if (root.left != null || root.right != null){
            if (root.left!=null&&root.left.val == start){
                parent = root;
                child = -1;
            }
            if (root.right!=null&&root.right.val == start){
                parent = root;
                child = 1;
            }
        }
        int len = 1 + Math.max(dfs(root.left,start,n,m+1) ,dfs(root.right,start,n,m+1));
        if (root.val == start){
            index = n;
            num = m;
        }
        return len;
    }

    /**
     * 解答成功:
     * 	执行耗时:122 ms,击败了15.00% 的Java用户
     * 	内存消耗:129.2 MB,击败了5.00% 的Java用户
     * @param root
     * @param start
     * @return
     */
    public int amountOfTimeOfficial(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        dfs(graph, root);
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{start, 0});
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(start);
        int time = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int nodeVal = arr[0];
            time = arr[1];
            for (int childVal : graph.get(nodeVal)) {
                if (visited.add(childVal)) {
                    queue.offer(new int[]{childVal, time + 1});
                }
            }
        }
        return time;
    }

    public void dfs(Map<Integer, List<Integer>> graph, TreeNode node) {
        graph.putIfAbsent(node.val, new ArrayList<Integer>());
        for (TreeNode child : Arrays.asList(node.left, node.right)) {
            if (child != null) {
                graph.get(node.val).add(child.val);
                graph.putIfAbsent(child.val, new ArrayList<Integer>());
                graph.get(child.val).add(node.val);
                dfs(graph, child);
            }
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
