package com.meng;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 10
 *     -10 <= nums[i] <= 10
 *     nums 中的所有元素 互不相同
 */
public class Subsets {
    /**
     * 递归
     * @param nums
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 83.70%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 75.49%
     * 的用户
     */
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(0,nums,res,temp);
        return res;
    }

    /**
     *
     * @param index 当前遍历的下标
     * @param nums
     * @param res
     * @param temp
     */
    private void dfs(int index, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        //不将当前元素放入集合
        dfs(index+1,nums,res,temp);
        //将当前元素放入集合
        temp.add(nums[index]);
        dfs(index+1,nums,res,temp);
        //移除放入的元素
        temp.remove(temp.size()-1);
    }
    /**
     * 方法一：迭代法实现子集枚举
     *
     * 思路与算法
     *
     * 记原序列中元素的总数为 nnn。原序列中的每个数字 aia_iai​ 的状态可能有两种，即「在子集中」和「不在子集中」。我们用 111 表示「在子集中」，000 表示不在子集中，那么每一个子集可以对应一个长度为 nnn 的 0/10/10/1 序列，第 iii 位表示 aia_iai​ 是否在子集中。例如，n=3n = 3n=3 ，a={5,2,9}a = \{ 5, 2, 9 \}a={5,2,9} 时：
     * 0/10/10/1 序列 	子集 	0/10/10/1 序列对应的二进制数
     * 000000000 	{}\{ \}{} 	000
     * 001001001 	{9}\{ 9 \}{9} 	111
     * 010010010 	{2}\{ 2 \}{2} 	222
     * 011011011 	{2,9}\{ 2, 9 \}{2,9} 	333
     * 100100100 	{5}\{ 5 \}{5} 	444
     * 101101101 	{5,9}\{ 5, 9 \}{5,9} 	555
     * 110110110 	{5,2}\{ 5, 2 \}{5,2} 	666
     * 111111111 	{5,2,9}\{ 5, 2, 9 \}{5,2,9} 	777
     *
     * 可以发现 0/10/10/1 序列对应的二进制数正好从 000 到 2n−12^n - 12n−1。我们可以枚举 mask∈[0,2n−1]\textit{mask} \in [0, 2^n - 1]mask∈[0,2n−1]，mask\textit{mask}mask 的二进制表示是一个 0/10/10/1 序列，我们可以按照这个 0/10/10/1 序列在原集合当中取数。当我们枚举完所有 2n2^n2n 个 mask\textit{mask}mask，我们也就能构造出所有的子集。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了83.70% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了89.72% 的用户
     */
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
/**
 * 方法二：递归法实现子集枚举
 *
 * 思路与算法
 *
 * 我们也可以用递归来实现子集枚举。
 *
 * 假设我们需要找到一个长度为 nnn 的序列 aaa 的所有子序列，代码框架是这样的：
 *
 * vector<int> t;
 * void dfs(int cur, int n) {
 *     if (cur == n) {
 *         // 记录答案
 *         // ...
 *         return;
 *     }
 *     // 考虑选择当前位置
 *     t.push_back(cur);
 *     dfs(cur + 1, n, k);
 *     t.pop_back();
 *     // 考虑不选择当前位置
 *     dfs(cur + 1, n, k);
 * }
 *
 * 上面的代码中，dfs(cur,n)\text{dfs}(\textit{cur}, n)dfs(cur,n) 参数表示当前位置是 cur\textit{cur}cur，原序列总长度为 nnn。原序列的每个位置在答案序列中的状态有被选中和不被选中两种，我们用 ttt 数组存放已经被选出的数字。在进入 dfs(cur,n)\text{dfs}(\textit{cur}, n)dfs(cur,n) 之前 [0,cur−1][0, \textit{cur} - 1][0,cur−1] 位置的状态是确定的，而 [cur,n−1][\textit{cur}, n - 1][cur,n−1] 内位置的状态是不确定的，dfs(cur,n)\text{dfs}(\textit{cur}, n)dfs(cur,n) 需要确定 cur\textit{cur}cur 位置的状态，然后求解子问题 dfs(cur+1,n){\text{dfs}(cur + 1}, n)dfs(cur+1,n)。对于 cur\textit{cur}cur 位置，我们需要考虑 a[cur]a[\textit{cur}]a[cur] 取或者不取，如果取，我们需要把 a[cur]a[\textit{cur}]a[cur] 放入一个临时的答案数组中（即上面代码中的 ttt），再执行 dfs(cur+1,n){\text{dfs}(cur + 1}, n)dfs(cur+1,n)，执行结束后需要对 ttt 进行回溯；如果不取，则直接执行 dfs(cur+1,n){\text{dfs}(cur + 1}, n)dfs(cur+1,n)。在整个递归调用的过程中，cur\textit{cur}cur 是从小到大递增的，当 cur\textit{cur}cur 增加到 nnn 的时候，记录答案并终止递归。可以看出二进制枚举的时间复杂度是 O(2n)O(2 ^ n)O(2n)。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 官方解法2
 * 执行用时：1 ms, 在所有 Java 提交中击败了83.70% 的用户
 * 内存消耗：38.6 MB, 在所有 Java 提交中击败了83.10% 的用户
 */
    public List<List<Integer>> subsets2(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }
}
