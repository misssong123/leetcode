package com.meng.algorithmfundamentals.ninth;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
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
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subsets {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 42.88%
     * 的用户
     * 通过测试用例：
     * 10 / 10
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        int len = nums.length;
        dfs(0,len,temp,nums);
        return res;
    }

    private void dfs(int index, int len, List<Integer> temp,int[] nums) {
        if (index == len){
            res.add(new ArrayList<>(temp));
            return;
        }
        //不放当前元素
        dfs(index+1,len,temp,nums);
        //存放当前元素
        temp.add(nums[index]);
        dfs(index+1,len,temp,nums);
        temp.remove(temp.size()-1);
    }

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 方法一：迭代法实现子集枚举
     * 思路与算法
     *
     * 记原序列中元素的总数为 nn。原序列中的每个数字 a_ia
     * i
     * ​
     *   的状态可能有两种，即「在子集中」和「不在子集中」。我们用 11 表示「在子集中」，00 表示不在子集中，那么每一个子集可以对应一个长度为 nn 的 0/10/1 序列，第 ii 位表示 a_ia
     * i
     * ​
     *   是否在子集中。例如，n = 3n=3 ，a = \{ 5, 2, 9 \}a={5,2,9} 时：
     *
     * 0/10/1 序列	子集	0/10/1 序列对应的二进制数
     * 000000	\{ \}{}	00
     * 001001	\{ 9 \}{9}	11
     * 010010	\{ 2 \}{2}	22
     * 011011	\{ 2, 9 \}{2,9}	33
     * 100100	\{ 5 \}{5}	44
     * 101101	\{ 5, 9 \}{5,9}	55
     * 110110	\{ 5, 2 \}{5,2}	66
     * 111111	\{ 5, 2, 9 \}{5,2,9}	77
     * 可以发现 0/10/1 序列对应的二进制数正好从 00 到 2^n - 12
     * n
     *  −1。我们可以枚举 \textit{mask} \in [0, 2^n - 1]mask∈[0,2
     * n
     *  −1]，\textit{mask}mask 的二进制表示是一个 0/10/1 序列，我们可以按照这个 0/10/1 序列在原集合当中取数。当我们枚举完所有 2^n2
     * n
     *   个 \textit{mask}mask，我们也就能构造出所有的子集。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 25.30%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 45.50%
     * 的用户
     * 通过测试用例：
     * 10 / 10
     */
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
     *方法二：递归法实现子集枚举
     * 思路与算法
     *
     * 我们也可以用递归来实现子集枚举。
     *
     * 假设我们需要找到一个长度为 nn 的序列 aa 的所有子序列，代码框架是这样的：
     *
     * C++
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
     * 上面的代码中，\text{dfs}(\textit{cur}, n)dfs(cur,n) 参数表示当前位置是 \textit{cur}cur，原序列总长度为 nn。原序列的每个位置在答案序列中的状态有被选中和不被选中两种，我们用 tt 数组存放已经被选出的数字。在进入 \text{dfs}(\textit{cur}, n)dfs(cur,n) 之前 [0, \textit{cur} - 1][0,cur−1] 位置的状态是确定的，而 [\textit{cur}, n - 1][cur,n−1] 内位置的状态是不确定的，\text{dfs}(\textit{cur}, n)dfs(cur,n) 需要确定 \textit{cur}cur 位置的状态，然后求解子问题 {\text{dfs}(cur + 1}, n)dfs(cur+1,n)。对于 \textit{cur}cur 位置，我们需要考虑 a[\textit{cur}]a[cur] 取或者不取，如果取，我们需要把 a[\textit{cur}]a[cur] 放入一个临时的答案数组中（即上面代码中的 tt），再执行 {\text{dfs}(cur + 1}, n)dfs(cur+1,n)，执行结束后需要对 tt 进行回溯；如果不取，则直接执行 {\text{dfs}(cur + 1}, n)dfs(cur+1,n)。在整个递归调用的过程中，\textit{cur}cur 是从小到大递增的，当 \textit{cur}cur 增加到 nn 的时候，记录答案并终止递归。可以看出二进制枚举的时间复杂度是 O(2 ^ n)O(2
     * n
     *  )。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.6 MB
     * , 在所有 Java 提交中击败了
     * 14.56%
     * 的用户
     * 通过测试用例：
     * 10 / 10
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
