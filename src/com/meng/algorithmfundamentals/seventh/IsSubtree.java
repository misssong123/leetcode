package com.meng.algorithmfundamentals.seventh;

import java.util.*;

/**
 * 572. 另一棵树的子树
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 *  
 *
 * 提示：
 *
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSubtree {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 80.58%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 16.09%
     * 的用户
     * 通过测试用例：
     * 182 / 182
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null){
            return false;
        }
        return dfsTree(root,subRoot);
    }

    private boolean dfsTree(TreeNode root, TreeNode subRoot) {
        if (root == null){
            return false;
        }

        return judgeEqual(root,subRoot)|| dfsTree(root.left,subRoot) || dfsTree(root.right,subRoot);
    }

    private boolean judgeEqual(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null){
            return true;
        }
        if (root == null || subRoot == null || root.val != subRoot.val){
            return false;
        }
        return judgeEqual(root.left,subRoot.left) && judgeEqual(root.right,subRoot.right);
    }

    /**
     * 方法一：深度优先搜索暴力匹配
     * 思路和算法
     *
     * 这是一种最朴素的方法——深度优先搜索枚举 ss 中的每一个节点，判断这个点的子树是否和 tt 相等。如何判断一个节点的子树是否和 tt 相等呢，我们又需要做一次深度优先搜索来检查，即让两个指针一开始先指向该节点和 tt 的根，然后「同步移动」两根指针来「同步遍历」这两棵树，判断对应位置是否相等。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 80.58%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 31.34%
     * 的用户
     * 通过测试用例：
     * 182 / 182
     */
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    public boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }



    /**
     * 方法二：深度优先搜索序列上做串匹配
     * 思路和算法
     *
     * 这个方法需要我们先了解一个「小套路」：一棵子树上的点在深度优先搜索序列（即先序遍历）中是连续的。了解了这个「小套路」之后，我们可以确定解决这个问题的方向就是：把 ss 和 tt 先转换成深度优先搜索序列，然后看 tt 的深度优先搜索序列是否是 ss 的深度优先搜索序列的「子串」。
     *
     * 这样做正确吗？ 假设 ss 由两个点组成，11 是根，22 是 11 的左孩子；tt 也由两个点组成，11 是根，22 是 11 的右孩子。这样一来 ss 和 tt 的深度优先搜索序列相同，可是 tt 并不是 ss 的某一棵子树。由此可见「ss 的深度优先搜索序列包含 tt 的深度优先搜索序列」是「tt 是 ss 子树」的必要不充分条件，所以单纯这样做是不正确的。
     *
     * 为了解决这个问题，我们可以引入两个空值 lNull 和 rNull，当一个节点的左孩子或者右孩子为空的时候，就插入这两个空值，这样深度优先搜索序列就唯一对应一棵树。处理完之后，就可以通过判断「ss 的深度优先搜索序列包含 tt 的深度优先搜索序列」来判断答案。
     *在判断「ss 的深度优先搜索序列包含 tt 的深度优先搜索序列」的时候，可以暴力匹配，也可以使用 \text{KMP}KMP 或者 \text{Rabin-Karp}Rabin-Karp 算法，在使用 \text{Rabin-Karp}Rabin-Karp 算法的时候，要注意串中可能有负值。
     *
     * 这里给出用 \text{KMP}KMP 判断的代码实现。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 15.78%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 44.31%
     * 的用户
     * 通过测试用例：
     * 182 / 182
     */
    List<Integer> sOrder = new ArrayList<Integer>();
    List<Integer> tOrder = new ArrayList<Integer>();
    int maxElement, lNull, rNull;
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        maxElement = Integer.MIN_VALUE;
        getMaxElement(s);
        getMaxElement(t);
        lNull = maxElement + 1;
        rNull = maxElement + 2;

        getDfsOrder(s, sOrder);
        getDfsOrder(t, tOrder);

        return kmp();
    }

    public void getMaxElement(TreeNode t) {
        if (t == null) {
            return;
        }
        maxElement = Math.max(maxElement, t.val);
        getMaxElement(t.left);
        getMaxElement(t.right);
    }

    public void getDfsOrder(TreeNode t, List<Integer> tar) {
        if (t == null) {
            return;
        }
        tar.add(t.val);
        if (t.left != null) {
            getDfsOrder(t.left, tar);
        } else {
            tar.add(lNull);
        }
        if (t.right != null) {
            getDfsOrder(t.right, tar);
        } else {
            tar.add(rNull);
        }
    }

    public boolean kmp() {
        int sLen = sOrder.size(), tLen = tOrder.size();
        int[] fail = new int[tOrder.size()];
        Arrays.fill(fail, -1);
        for (int i = 1, j = -1; i < tLen; ++i) {
            while (j != -1 && !(tOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (tOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            fail[i] = j;
        }
        for (int i = 0, j = -1; i < sLen; ++i) {
            while (j != -1 && !(sOrder.get(i).equals(tOrder.get(j + 1)))) {
                j = fail[j];
            }
            if (sOrder.get(i).equals(tOrder.get(j + 1))) {
                ++j;
            }
            if (j == tLen - 1) {
                return true;
            }
        }
        return false;
    }



    /**
     * 方法三：树哈希
     * 思路和算法
     *
     * 考虑把每个子树都映射成一个唯一的数，如果 tt 对应的数字和 ss 中任意一个子树映射的数字相等，则 tt 是 ss 的某一棵子树。如何映射呢？我们可以定义这样的哈希函数：
     *
     * f_o = v_o + 31 \cdot f_l \cdot p(s_l) + 179 \cdot f_r \cdot p(s_r)
     * f
     * o
     * ​
     *  =v
     * o
     * ​
     *  +31⋅f
     * l
     * ​
     *  ⋅p(s
     * l
     * ​
     *  )+179⋅f
     * r
     * ​
     *  ⋅p(s
     * r
     * ​
     *  )
     *
     * 这里 f_xf
     * x
     * ​
     *   表示节点 xx 的哈希值，s_xs
     * x
     * ​
     *   表示节点 xx 对应的子树大小，v_xv
     * x
     * ​
     *   代表节点 xx 的 val，p(n)p(n) 表示第 nn 个素数，oo 表示当前节点，ll 和 rr 分别表示左右孩子。这个式子的意思是：当前节点 oo 的哈希值等于这个点的 val 加上 3131 倍左子树的哈希值乘以第 s_ls
     * l
     * ​
     *   个素数，再加上 179179 倍右子树的哈希值乘以第 s_rs
     * r
     * ​
     *   个素数。这里的 3131 和 179179 这两个数字只是为了区分左右子树，你可以自己选择你喜欢的权值。
     *
     * 这样做为什么可行呢？ 回到我们的初衷，我们希望把每个子树都映射成一个唯一的数，这样真的能够确保唯一吗？实际上未必。但是我们在这个哈希函数中考虑到每个点的 val、子树哈希值、子树大小以及左右子树的不同权值，所以这些因素共同影响一个点的哈希值，所以出现冲突的几率较小，一般我们可以忽略。当然你也可以设计你自己的哈希函数，只要考虑到这些因素，就可以把冲突的可能性设计得比较小。可是如果还是出现了冲突怎么办呢？ 我们可以设计两个哈希函数 f_1f
     * 1
     * ​
     *   和 f_2f
     * 2
     * ​
     *  ，用这两个哈希函数生成第三个哈希函数，比如 f = f_1 + f_2f=f
     * 1
     * ​
     *  +f
     * 2
     * ​
     *  、f = f_1 \times f_2f=f
     * 1
     * ​
     *  ×f
     * 2
     * ​
     *   等等，这样可以进一步缩小冲突，如果 f_1f
     * 1
     * ​
     *   的冲突概率是 P_1P
     * 1
     * ​
     *  ，f_2f
     * 2
     * ​
     *   的冲突概率是 P_2P
     * 2
     * ​
     *  ，那么 ff 的冲突概率就是 P_1 \times P_2P
     * 1
     * ​
     *  ×P
     * 2
     * ​
     *  ，理论上已经非常小了，这就是「双哈希」。当然，为了减少冲突，你也可以设计「三哈希」、「四哈希」等，可是这样编程的复杂度就会增加。实际上，一般情况下，只要运气不是太差，一个哈希函数就足够了。
     *
     * 我们可以用「埃氏筛法」或者「欧拉筛法」求出前 \arg \pi (\max \{ |s|, |t| \})argπ(max{∣s∣,∣t∣}) 个素数（其中 \pi (x)π(x) 表示 xx 以内素数个数，\arg \pi (x)argπ(x) 为它的反函数，表示有多少以内包含 xx 个素数，这个映射是不唯一的，我们取最小值），然后深度优先搜索计算哈希值，最后比较 ss 的所有子树是否有和 tt 相同的哈希值即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 5.28%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 12.55%
     * 的用户
     * 通过测试用例：
     * 182 / 182
     */
    static final int MAX_N = 1005;
    static final int MOD = 1000000007;
    boolean[] vis = new boolean[MAX_N];
    int[] p = new int[MAX_N];
    int tot;
    Map<TreeNode, int[]> hS = new HashMap<TreeNode, int[]>();
    Map<TreeNode, int[]> hT = new HashMap<TreeNode, int[]>();
    public boolean isSubtree3(TreeNode s, TreeNode t) {
        getPrime();
        dfs(s, hS);
        dfs(t, hT);

        int tHash = hT.get(t)[0];
        for (Map.Entry<TreeNode, int[]> entry : hS.entrySet()) {
            if (entry.getValue()[0] == tHash) {
                return true;
            }
        }

        return false;
    }

    public void getPrime() {
        vis[0] = vis[1] = true;
        tot = 0;
        for (int i = 2; i < MAX_N; ++i) {
            if (!vis[i]) {
                p[++tot] = i;
            }
            for (int j = 1; j <= tot && i * p[j] < MAX_N; ++j) {
                vis[i * p[j]] = true;
                if (i % p[j] == 0) {
                    break;
                }
            }
        }
    }

    public void dfs(TreeNode o, Map<TreeNode, int[]> h) {
        h.put(o, new int[]{o.val, 1});
        if (o.left == null && o.right == null) {
            return;
        }
        if (o.left != null) {
            dfs(o.left, h);
            int[] val = h.get(o);
            val[1] += h.get(o.left)[1];
            val[0] = (int) ((val[0] + (31L * h.get(o.left)[0] * p[h.get(o.left)[1]]) % MOD) % MOD);
        }
        if (o.right != null) {
            dfs(o.right, h);
            int[] val = h.get(o);
            val[1] += h.get(o.right)[1];
            val[0] = (int) ((val[0] + (179L * h.get(o.right)[0] * p[h.get(o.right)[1]]) % MOD) % MOD);
        }
    }


}
