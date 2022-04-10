package com.meng.DataStructureFundamentals.sixteen16;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class RightSideView {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 80.73%
     * 的用户
     * 内存消耗：
     * 39.8 MB
     * , 在所有 Java 提交中击败了
     * 73.86%
     * 的用户
     * 通过测试用例：
     * 215 / 215
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        List<TreeNode> temp = new ArrayList<>();
        temp.add(root);
        while (temp.size() > 0){
            int size = temp.size();
            for(int i = 0 ; i < size ; i++){
                TreeNode node = temp.remove(0);
                if (i == size -1 ){
                    list.add(node.val);
                }
                if (node.left != null){
                    temp.add(node.left);
                }
                if (node.right != null){
                    temp.add(node.right);
                }
            }
        }
        return list;
    }

    /**
     * 方法二：广度优先搜索
     * 思路
     *
     * 我们可以对二叉树进行层次遍历，那么对于每层来说，最右边的结点一定是最后被遍历到的。二叉树的层次遍历可以用广度优先搜索实现。
     *
     * 算法
     *
     * 执行广度优先搜索，左结点排在右结点之前，这样，我们对每一层都从左到右访问。因此，只保留每个深度最后访问的结点，我们就可以在遍历完整棵树后得到每个深度最右的结点。除了将栈改成队列，并去除了 rightmost_value_at_depth 之前的检查外，算法没有别的改动。
     *
     *
     *
     * 上图表示了同一个示例，红色结点自上而下组成答案，边缘以访问顺序标号。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        Queue<Integer> depthQueue = new ArrayDeque<Integer>();
        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可
                rightmostValueAtDepth.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth + 1);
                depthQueue.add(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

}
