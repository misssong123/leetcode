package com.meng.DataStructureFundamentals.eighteen18;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 */
public class LowestCommonAncestor {
    /**
     * 执行用时：
     * 24 ms
     * , 在所有 Java 提交中击败了
     * 5.00%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 68.61%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode,TreeNode> cache = new HashMap<>();
        //构建前缀父节点
        dfs(root,cache,p,q,false,false);
        //构建对应的list集合
        List<TreeNode> pList = getPath(cache,p);
        System.out.println(pList.size());
        List<TreeNode> qList = getPath(cache,q);
        System.out.println(qList.size());
        TreeNode res = null;
        int size = pList.size() > qList.size() ? qList.size() : pList.size();
        int index = 0;
        while (index < size && pList.get(index) == qList.get(index)){
            res = pList.get(index);
            index++;
        }
        return res;
    }

    private List<TreeNode> getPath(Map<TreeNode, TreeNode> cache, TreeNode p) {
        List<TreeNode> res = new ArrayList<>();
        res.add(0,p);
        while (cache.get(p) != null){
            res.add(cache.get(p));
            p = cache.get(p);
        }
        return res;
    }

    private void dfs(TreeNode root,Map<TreeNode, TreeNode> cache, TreeNode p, TreeNode q, boolean flagP, boolean flagQ) {
        if (flagP && flagQ){
            return;
        }
        if (root == p){
            flagP = true;
        }
        if (root == q){
            flagQ = true;
        }
        if (root.left != null){
            cache.put(root.left,root);
            dfs(root.left,cache,p,q,flagP,flagQ);
        }
        if (root.right != null){
            cache.put(root.right,root);
            dfs(root.right,cache,p,q,flagP,flagQ);
        }
    }


    /**
     *
     * @param root
     * @param p
     * @param q
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.9 MB
     * , 在所有 Java 提交中击败了
     * 19.84%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }



    /**
     *方法二：存储父节点
     * 思路
     *
     * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     *
     * 算法
     *
     * 从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
     * 从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
     * 同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetc-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param p
     * @param q
     * @return
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 14.66%
     * 的用户
     * 内存消耗：
     * 43.6 MB
     * , 在所有 Java 提交中击败了
     * 5.00%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
