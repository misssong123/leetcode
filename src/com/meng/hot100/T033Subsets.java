package com.meng.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 中等
 * 1.8K
 * 相关企业
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
public class T033Subsets {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    /**
     * 时间
     * 0 ms
     * 击败
     * 100%
     * 内存
     * 41.2 MB
     * 击败
     * 97.43%
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        int  len = nums.length;
        dfs(0,len,nums);
        return list;
    }

    private void dfs(int index, int len, int[] nums) {
        if (index == len){
            list.add(new ArrayList<>(temp));
            return;
        }
        //不存放当前元素
        dfs(index+1,len,nums);
        //存放当前数据
        temp.add(nums[index]);
        dfs(index+1,len,nums);
        temp.remove(temp.size()-1);
    }


    /**
     * 方法一：迭代法实现子集枚举
     * 思路与算法
     *
     * 记原序列中元素的总数为 nnn。原序列中的每个数字 aia_ia
     * i
     * ​
     *   的状态可能有两种，即「在子集中」和「不在子集中」。我们用 111 表示「在子集中」，000 表示不在子集中，那么每一个子集可以对应一个长度为 nnn 的 0/10/10/1 序列，第 iii 位表示 aia_ia
     * i
     * ​
     *   是否在子集中。例如，n=3n = 3n=3 ，a={5,2,9}a = \{ 5, 2, 9 \}a={5,2,9} 时：
     *
     * 0/10/10/1 序列	子集	0/10/10/1 序列对应的二进制数
     * 000000000	{}\{ \}{}	000
     * 001001001	{9}\{ 9 \}{9}	111
     * 010010010	{2}\{ 2 \}{2}	222
     * 011011011	{2,9}\{ 2, 9 \}{2,9}	333
     * 100100100	{5}\{ 5 \}{5}	444
     * 101101101	{5,9}\{ 5, 9 \}{5,9}	555
     * 110110110	{5,2}\{ 5, 2 \}{5,2}	666
     * 111111111	{5,2,9}\{ 5, 2, 9 \}{5,2,9}	777
     * 可以发现 0/10/10/1 序列对应的二进制数正好从 000 到 2n−12^n - 12
     * n
     *  −1。我们可以枚举 mask∈[0,2n−1]\textit{mask} \in [0, 2^n - 1]mask∈[0,2
     * n
     *  −1]，mask\textit{mask}mask 的二进制表示是一个 0/10/10/1 序列，我们可以按照这个 0/10/10/1 序列在原集合当中取数。当我们枚举完所有 2n2^n2
     * n
     *   个 mask\textit{mask}mask，我们也就能构造出所有的子集。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/subsets/solutions/420294/zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 时间
     * 1 ms
     * 击败
     * 27.24%
     * 内存
     * 41.4 MB
     * 击败
     * 68.33%
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

}
