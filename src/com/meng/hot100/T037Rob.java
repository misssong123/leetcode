package com.meng.hot100;

import com.meng.hot100.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 示例 2:
 *
 *
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 *
 * 提示：
 *
 * 树的节点数在 [1, 104] 范围内
 * 0 <= Node.val <= 104
 */
public class T037Rob {
    //超出时间限制
    public int rob(TreeNode root) {
        int c = dfs(root, true);
        int u = dfs(root, false);
        int num = Math.max(c,u);
        return num;
    }

    private int dfs(TreeNode root, boolean flag) {
        if (root == null){
            return 0;
        }
        if (flag){
            int num1 = root.val + dfs(root.left,false)+dfs(root.right,false);
            int num2 = dfs(root.left,true) + dfs(root.right,true);
            return Math.max(num1,num2);
        }else {
            return dfs(root.left,true) + dfs(root.right,true);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.right = node4;
        node3.right = node5;
        T037Rob demo = new T037Rob();
        System.out.println(demo.rob(root));
    }



    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 简化一下这个问题：一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），问在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。
     *
     * 我们可以用 f(o)f(o) 表示选择 oo 节点的情况下，oo 节点的子树上被选择的节点的最大权值和；g(o)g(o) 表示不选择 oo 节点的情况下，oo 节点的子树上被选择的节点的最大权值和；ll 和 rr 代表 oo 的左右孩子。
     *
     * 当 oo 被选中时，oo 的左右孩子都不能被选中，故 oo 被选中情况下子树上被选中点的最大权值和为 ll 和 rr 不被选中的最大权值和相加，即 f(o) = g(l) + g(r)f(o)=g(l)+g(r)。
     * 当 oo 不被选中时，oo 的左右孩子可以被选中，也可以不被选中。对于 oo 的某个具体的孩子 xx，它对 oo 的贡献是 xx 被选中和不被选中情况下权值和的较大值。故 g(o) = \max \{ f(l) , g(l)\}+\max\{ f(r) , g(r) \}g(o)=max{f(l),g(l)}+max{f(r),g(r)}。
     * 至此，我们可以用哈希表来存 ff 和 gg 的函数值，用深度优先搜索的办法后序遍历这棵二叉树，我们就可以得到每一个节点的 ff 和 gg。根节点的 ff 和 gg 的最大值就是我们要找的答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 18.20%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 5.15%
     * 的用户
     * 通过测试用例：
     * 124 / 124
     */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    public int rob1(TreeNode root) {
        dfs1(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }
    public void dfs1(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs1(node.left);
        dfs1(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /**
     * 假设二叉树的节点个数为 nn。
     *
     * 我们可以看出，以上的算法对二叉树做了一次后序遍历，时间复杂度是 O(n)O(n)；由于递归会使用到栈空间，空间代价是 O(n)O(n)，哈希表的空间代价也是 O(n)O(n)，故空间复杂度也是 O(n)O(n)。
     *
     * 我们可以做一个小小的优化，我们发现无论是 f(o)f(o) 还是 g(o)g(o)，他们最终的值只和 f(l)f(l)、g(l)g(l)、f(r)f(r)、g(r)g(r) 有关，所以对于每个节点，我们只关心它的孩子节点们的 ff 和 gg 是多少。我们可以设计一个结构，表示某个节点的 ff 和 gg 值，在每次递归返回的时候，都把这个点对应的 ff 和 gg 返回给上一级调用，这样可以省去哈希表的空间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 34.21%
     * 的用户
     * 通过测试用例：
     * 124 / 124
     */
    public int rob2(TreeNode root) {
        int[] rootStatus = dfs2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs2(node.left);
        int[] r = dfs2(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }


}
