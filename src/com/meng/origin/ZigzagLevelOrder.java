package com.meng.origin;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class ZigzagLevelOrder {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了13.10% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了71.87% 的用户
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root!=null){
            Deque<TreeNode> form = new LinkedList<>();
            Deque<TreeNode> to = new LinkedList<>();
            form.add(root);
            int cur = 0;
            boolean flag =true;
            while (form.size()>0){
                cur = form.size();
                List<Integer> list = new ArrayList<>();
                while (cur>0){
                    if (flag){
                        TreeNode treeNode = form.removeFirst();
                        list.add(treeNode.val);
                        if (treeNode.left!=null)
                            to.addLast(treeNode.left);
                        if (treeNode.right!=null)
                            to.addLast(treeNode.right);
                    }else {
                        TreeNode treeNode = form.removeLast();
                        list.add(treeNode.val);
                        if (treeNode.right!=null)
                            to.addFirst(treeNode.right);
                        if (treeNode.left!=null)
                            to.addFirst(treeNode.left);
                    }
                    cur--;
                }
                res.add(list);
                form.addAll(to);
                to.removeAll(form);
                flag=!flag;
            }
        }
        return res;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.42% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了62.85% 的用户
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root!=null){
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root);
            int cur = 0;
            boolean flag =true;
            while (deque.size()>0){
                cur = deque.size();
                Deque<Integer>list = new LinkedList<>();
                while (cur>0){
                    TreeNode curNode = deque.poll();
                    if (flag){
                        list.addLast(curNode.val);
                    }else {
                       list.addFirst(curNode.val);
                    }
                    if (curNode.left!=null)
                        deque.add(curNode.left);
                    if (curNode.right!=null)
                        deque.add(curNode.right);
                    cur--;
                }
                res.add(new ArrayList<>(list));
                flag = !flag;
            }
        }
        return res;
    }
    /**
     * 方法一：广度优先遍历
     *
     * 此题是「102. 二叉树的层序遍历」的变种，最后输出的要求有所变化，要求我们按层数的奇偶来决定每一层的输出顺序。规定二叉树的根节点为第 000 层，如果当前层数是偶数，从左至右输出当前层的节点值，否则，从右至左输出当前层的节点值。
     *
     * 我们依然可以沿用第 102 题的思想，修改广度优先搜索，对树进行逐层遍历，用队列维护当前层的所有元素，当队列不为空的时候，求得当前队列的长度 size\textit{size}size，每次从队列中取出 size\textit{size}size 个元素进行拓展，然后进行下一次迭代。
     *
     * 为了满足题目要求的返回值为「先从左往右，再从右往左」交替输出的锯齿形，我们可以利用「双端队列」的数据结构来维护当前层节点值输出的顺序。
     *
     * 双端队列是一个可以在队列任意一端插入元素的队列。在广度优先搜索遍历当前层节点拓展下一层节点的时候我们仍然从左往右按顺序拓展，但是对当前层节点的存储我们维护一个变量 isOrderLeft\textit{isOrderLeft}isOrderLeft 记录是从左至右还是从右至左的：
     *
     *     如果从左至右，我们每次将被遍历到的元素插入至双端队列的末尾。
     *
     *     如果从右至左，我们每次将被遍历到的元素插入至双端队列的头部。
     *
     * 当遍历结束的时候我们就得到了答案数组。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/solution/er-cha-shu-de-ju-chi-xing-ceng-xu-bian-l-qsun/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.42% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了65.31% 的用户
     */
    public List<List<Integer>> zigzagLevelOrderOffice(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

    public static void main(String[] args) {
        ZigzagLevelOrder demo = new ZigzagLevelOrder();
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left =  node3;
        node2.right = node4;
        System.out.println(demo.zigzagLevelOrder(root));
        System.out.println(demo.zigzagLevelOrder1(root));
        System.out.println(demo.zigzagLevelOrderOffice(root));
    }
}
