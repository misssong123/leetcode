package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

class Interview085ZigzagLevelOrder {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了75.94% 的Java用户
     * 	内存消耗:41.3 MB,击败了40.80% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderMy(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        boolean flag = true;
        if (root != null) {
            List<TreeNode> list = new ArrayList<>();
            list.add(root);
            while (!list.isEmpty()) {
                List<Integer> val = new ArrayList<>();
                List<TreeNode> temp = new ArrayList<>();
                if (flag){
                    for (TreeNode node : list) {
                        val.add(node.val);
                        if (node.left != null) {
                            temp.add(node.left);
                        }
                        if (node.right != null) {
                            temp.add(node.right);
                        }
                    }
                }else {
                    for (TreeNode node : list) {
                        val.add(0,node.val);
                        if (node.left != null) {
                            temp.add(node.left);
                        }
                        if (node.right != null) {
                            temp.add(node.right);
                        }
                    }
                }
                flag = !flag;
                res.add(val);
                list = temp;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了75.94% 的Java用户
     * 	内存消耗:41.4 MB,击败了5.58% 的Java用户
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
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

}
