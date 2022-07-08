package com.meng.level2.day07;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class PathSum437 {
    int sum = 0;

    /**
     * 递归
     * @param root
     * @param targetSum
     * @return
     * 执行用时：
     * 19 ms
     * , 在所有 Java 提交中击败了
     * 57.63%
     * 的用户
     * 内存消耗：
     * 40.9 MB
     * , 在所有 Java 提交中击败了
     * 67.07%
     * 的用户
     * 通过测试用例：
     * 127 / 127
     */
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return sum;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root == null){
            return;
        }
        //递归当前节点
        getTarget(root,targetSum,root.val);
        //递归左节点
        dfs(root.left,targetSum);
        //递归右节点
        dfs(root.right,targetSum);
    }

    private void getTarget(TreeNode root, int targetSum,int num) {
        if (root==null){
            return;
        }
        if (targetSum == num){
            sum++;
        }
        if (root.left!=null){
            getTarget(root.left,targetSum,num+root.left.val);
        }
        if (root.right!=null){
            getTarget(root.right,targetSum,num+root.right.val);
        }
    }

    /**
     * 方法一：深度优先搜索
     * 思路与算法
     *
     * 我们首先想到的解法是穷举所有的可能，我们访问每一个节点 \textit{node}node，检测以 \textit{node}node 为起始节点且向下延深的路径有多少种。我们递归遍历每一个节点的所有可能的路径，然后将这些路径数目加起来即为返回结果。
     *
     * 我们首先定义 \textit{rootSum}(p,\textit{val})rootSum(p,val) 表示以节点 pp 为起点向下且满足路径总和为 valval 的路径数目。我们对二叉树上每个节点 pp 求出 \textit{rootSum}(p,\textit{targetSum})rootSum(p,targetSum)，然后对这些路径数目求和即为返回结果。
     *
     * 我们对节点 pp 求 \textit{rootSum}(p,\textit{targetSum})rootSum(p,targetSum) 时，以当前节点 pp 为目标路径的起点递归向下进行搜索。假设当前的节点 pp 的值为 \textit{val}val，我们对左子树和右子树进行递归搜索，对节点 pp 的左孩子节点 p_{l}p
     * l
     * ​
     *   求出 \textit{rootSum}(p_{l},\textit{targetSum}-\textit{val})rootSum(p
     * l
     * ​
     *  ,targetSum−val)，以及对右孩子节点 p_{r}p
     * r
     * ​
     *   求出 \textit{rootSum}(p_{r},\textit{targetSum}-\textit{val})rootSum(p
     * r
     * ​
     *  ,targetSum−val)。节点 pp 的 \textit{rootSum}(p,\textit{targetSum})rootSum(p,targetSum) 即等于 \textit{rootSum}(p_{l},\textit{targetSum}-\textit{val})rootSum(p
     * l
     * ​
     *  ,targetSum−val) 与 \textit{rootSum}(p_{r},\textit{targetSum}-\textit{val})rootSum(p
     * r
     * ​
     *  ,targetSum−val) 之和，同时我们还需要判断一下当前节点 pp 的值是否刚好等于 \textit{targetSum}targetSum。
     *
     * 我们采用递归遍历二叉树的每个节点 pp，对节点 pp 求 \textit{rootSum}(p,\textit{val})rootSum(p,val)，然后将每个节点所有求的值进行相加求和返回。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param targetSum
     * @return
     * 执行用时：
     * 32 ms
     * , 在所有 Java 提交中击败了
     * 22.46%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 77.73%
     * 的用户
     * 通过测试用例：
     * 127 / 127
     */
    public int pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = rootSum(root, targetSum);
        ret += pathSum1(root.left, targetSum);
        ret += pathSum1(root.right, targetSum);
        return ret;
    }

    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }

    /**
     * 方法二: 前缀和
     * 思路与算法
     *
     * 我们仔细思考一下，解法一中应该存在许多重复计算。我们定义节点的前缀和为：由根结点到当前结点的路径上所有节点的和。我们利用先序遍历二叉树，记录下根节点 \textit{root}root 到当前节点 pp 的路径上除当前节点以外所有节点的前缀和，在已保存的路径前缀和中查找是否存在前缀和刚好等于当前节点到根节点的前缀和 currcurr 减去 \textit{targetSum}targetSum。
     *
     * 对于空路径我们也需要保存预先处理一下，此时因为空路径不经过任何节点，因此它的前缀和为 00。
     *
     * 假设根节点为 \textit{root}root，我们当前刚好访问节点 \textit{node}node，则此时从根节点 \textit{root}root 到节点 \textit{node}node 的路径（无重复节点）刚好为 \textit{root} \rightarrow p_1 \rightarrow p_2 \rightarrow \ldots \rightarrow p_k \rightarrow \textit{node}root→p
     * 1
     * ​
     *  →p
     * 2
     * ​
     *  →…→p
     * k
     * ​
     *  →node，此时我们可以已经保存了节点 p_1, p_2, p_3, \ldots, p_kp
     * 1
     * ​
     *  ,p
     * 2
     * ​
     *  ,p
     * 3
     * ​
     *  ,…,p
     * k
     * ​
     *   的前缀和，并且计算出了节点 \textit{node}node 的前缀和。
     *
     * 假设当前从根节点 \textit{root}root 到节点 \textit{node}node 的前缀和为 \textit{curr}curr，则此时我们在已保存的前缀和查找是否存在前缀和刚好等于 \textit{curr} - \textit{targetSum}curr−targetSum。假设从根节点 \textit{root}root 到节点 \textit{node}node 的路径中存在节点 p_ip
     * i
     * ​
     *   到根节点 \textit{root}root 的前缀和为 \textit{curr} - \textit{targetSum}curr−targetSum，则节点 p_{i+1}p
     * i+1
     * ​
     *   到 \textit{node}node 的路径上所有节点的和一定为 \textit{targetSum}targetSum。
     *
     * 我们利用深度搜索遍历树，当我们退出当前节点时，我们需要及时更新已经保存的前缀和。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/path-sum-iii/solution/lu-jing-zong-he-iii-by-leetcode-solution-z9td/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param targetSum
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 5.15%
     * 的用户
     * 通过测试用例：
     * 127 / 127
     */
    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }



}
