package com.meng;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 671. 二叉树中第二小的节点
 *
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 *
 * 示例 2：
 *
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 *
 *
 *
 * 提示：
 *
 *     树中节点数目在范围 [1, 25] 内
 *     1 <= Node.val <= 231 - 1
 *     对于树中每个节点 root.val == min(root.left.val, root.right.val)
 */
public class FindSecondMinimumValue {
    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了6.44% 的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了5.34% 的用户
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null ){
            return -1;
        }
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        Set<Integer> set = new HashSet<>();
        while (queue.size() > 0){
            TreeNode temp = queue.poll();
            set.add(temp.val);
            if (temp.left != null){
                queue.add(temp.left);
                queue.add(temp.right);
            }
        }
        if (set.size() == 1){
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        return list.get(1);
    }

    int ans;
    int rootvalue;

    /**
     * 方法一：深度优先搜索
     *
     * 思路
     *
     * 根据题目中的描述「如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个」，我们可以知道，对于二叉树中的任意节点 xxx，xxx 的值不大于其所有子节点的值，因此：
     *
     *     对于二叉树中的任意节点 xxx，xxx 的值不大于以 xxx 为根的子树中所有节点的值。
     *
     * 令 xxx 为二叉树的根节点，此时我们可以得出结论：
     *
     *     二叉树根节点的值即为所有节点中的最小值。
     *
     * 因此，我们可以对整棵二叉树进行一次遍历。设根节点的值为 rootvalue\textit{rootvalue}rootvalue，我们只需要通过遍历，找出严格大于 rootvalue\textit{rootvalue}rootvalue 的最小值，即为「所有节点中的第二小的值」。
     *
     * 算法
     *
     * 我们可以使用深度优先搜索的方法对二叉树进行遍历。
     *
     * 假设当前遍历到的节点为 node\textit{node}node，如果 node\textit{node}node 的值严格大于 rootvalue\textit{rootvalue}rootvalue，那么我们就可以用 node\textit{node}node 的值来更新答案 ans\textit{ans}ans。
     *
     * 当我们遍历完整棵二叉树后，即可返回 ans\textit{ans}ans。
     *
     * 细节
     *
     * 根据题目要求，如果第二小的值不存在的话，输出 −1-1−1，那么我们可以将 ans\textit{ans}ans 的初始值置为 −1-1−1。在遍历的过程中，如果当前节点的值严格大于 rootvalue\textit{rootvalue}rootvalue 的节点时，那么只要 ans\textit{ans}ans 的值为 −1-1−1 或者当前节点的值严格小于 ans\textit{ans}ans，我们就需要对 ans\textit{ans}ans 进行更新。
     *
     * 此外，如果当前节点的值大于等于 ans\textit{ans}ans，那么根据「思路」部分，以当前节点为根的子树中所有节点的值都大于等于 ans\textit{ans}ans，我们就直接回溯，无需对该子树进行遍历。这样做可以省去不必要的遍历过程。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/solution/er-cha-shu-zhong-di-er-xiao-de-jie-dian-bhxiw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了92.45% 的用户
     */
    public int findSecondMinimumValue1(TreeNode root) {
        ans = -1;
        rootvalue = root.val;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (ans != -1 && node.val >= ans) {
            return;
        }
        if (node.val > rootvalue) {
            ans = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }
}
