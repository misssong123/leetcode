package com.meng.DataStructureFundamentals.sixteen16;

import java.util.*;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSum {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.99%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 31.65%
     * 的用户
     * 通过测试用例：
     * 115 / 115
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null){
            return res;
        }
        dfs(root,targetSum,0);
        return res;
    }

    private void dfs(TreeNode root, int targetSum, int sum) {
        if (root.left == null && root.right == null){
            if (sum + root.val == targetSum){
                temp.add(root.val);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size()-1);
            }
            return;
        }
        if (root.left != null){
            temp.add(root.val);
            dfs(root.left,targetSum,sum + root.val);
            temp.remove(temp.size()-1);
        }
        if (root.right != null){
            temp.add(root.val);
            dfs(root.right,targetSum,sum + root.val);
            temp.remove(temp.size()-1);
        }
    }




    /**
     * 方法二：广度优先搜索
     * 思路及算法
     *
     * 我们也可以采用广度优先搜索的方式，遍历这棵树。当我们遍历到叶子节点，且此时路径和恰为目标和时，我们就找到了一条满足条件的路径。
     *
     * 为了节省空间，我们使用哈希表记录树中的每一个节点的父节点。每次找到一个满足条件的节点，我们就从该节点出发不断向父节点迭代，即可还原出从根节点到当前节点的路径。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/path-sum-ii/solution/lu-jing-zong-he-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param targetSum
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 6.82%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 33.04%
     * 的用户
     * 通过测试用例：
     * 115 / 115
     */
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        Queue<Integer> queueSum = new LinkedList<Integer>();
        queueNode.offer(root);
        queueSum.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;

            if (node.left == null && node.right == null) {
                if (rec == targetSum) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }

        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<Integer>();
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(temp);
        ret.add(new LinkedList<Integer>(temp));
    }

}
