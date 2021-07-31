package com.meng;

import java.sql.SQLOutput;
import java.util.*;

/**
 * 987. 二叉树的垂序遍历
 *
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 *
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 *
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 *
 * 返回二叉树的 垂序遍历 序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 解释：
 * 列 -1 ：只有结点 9 在此列中。
 * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * 列  1 ：只有结点 20 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 列 -2 ：只有结点 4 在此列中。
 * 列 -1 ：只有结点 2 在此列中。
 * 列  0 ：结点 1 、5 和 6 都在此列中。
 *           1 在上面，所以它出现在前面。
 *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * 列  1 ：只有结点 3 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 *
 * 示例 3：
 *
 * 输入：root = [1,2,3,4,6,5,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 *
 *
 *
 * 提示：
 *
 *     树中结点数目总数在范围 [1, 1000] 内
 *     0 <= Node.val <= 1000
 */
public class VerticalTraversal {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了65.69% 的用户
     */
    List<int[]> cache = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        cache.add(new int[]{0,0,root.val});
        dfs(root,0,0);
        Collections.sort(cache, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]){
                    return o1[1] - o2[1];
                }else if(o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }else {
                    return o1[2] - o2[2];
                }
            }
        });
        cache.stream().forEach(e -> System.out.println(Arrays.toString(e)));
        int last = Integer.MIN_VALUE;
        int size = -1;
        for(int[] s : cache){
            if (s[1] != last){
                last = s[1];
                size++;
                List<Integer> list = new ArrayList<>();
                list.add(s[2]);
                res.add(size,list);
            }else {
                List<Integer> list = res.get(size);
                list.add(s[2]);
                res.set(size,list);
            }
        }
        return res;
    }

    private void dfs(TreeNode root,int row,int col) {
        if (root == null){
            return;
        }
        if (root.left != null){
            cache.add(new int[]{row+1,col-1,root.left.val});
            dfs(root.left,row+1,col-1);
        }
        if (root.right != null){
            cache.add(new int[]{row+1,col+1,root.right.val});
            dfs(root.right,row+1,col+1);
        }
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了67.15% 的用户
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal1(TreeNode root) {
        List<int[]> nodes = new ArrayList<int[]>();
        dfs(root, 0, 0, nodes);
        Collections.sort(nodes, new Comparator<int[]>() {
            @Override
            public int compare(int[] tuple1, int[] tuple2) {
                if (tuple1[0] != tuple2[0]) {
                    return tuple1[0] - tuple2[0];
                } else if (tuple1[1] != tuple2[1]) {
                    return tuple1[1] - tuple2[1];
                } else {
                    return tuple1[2] - tuple2[2];
                }
            }
        });
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int size = 0;
        int lastcol = Integer.MIN_VALUE;
        for (int[] tuple : nodes) {
            int col = tuple[0], row = tuple[1], value = tuple[2];
            if (col != lastcol) {
                lastcol = col;
                ans.add(new ArrayList<Integer>());
                size++;
            }
            ans.get(size - 1).add(value);
        }
        return ans;
    }

    public void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        VerticalTraversal demo = new VerticalTraversal();
        System.out.println(demo.verticalTraversal(root));
    }
}
