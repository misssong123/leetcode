package com.meng.algorithmfundamentals.ninth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
 */
public class SubsetsWithDup {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 98.29%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 58.43%
     * 的用户
     * 通过测试用例：
     * 20 / 20
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfsRes(false,0,nums,nums.length);
        return res;
    }

    private void dfsRes(boolean flag, int index, int[] nums, int n) {
        if (index == n){
            res.add(new ArrayList<>(temp));
            return;
        }
        //不存在当前元素
        dfsRes(false,index+1,nums,n);
        //若前一个元素未存放，需保证当前元素不能与前一个元素相同
        if (!flag && index > 0 && nums[index] == nums[index-1]){
            return;
        }
        temp.add(nums[index]);
        dfsRes(true,index+1,nums,n);
        temp.remove(temp.size()-1);
    }


    /**
     * 方法一：迭代法实现子集枚举
     * 思路
     *
     * 考虑数组 [1,2,2][1,2,2]，选择前两个数，或者第一、三个数，都会得到相同的子集。
     *
     * 也就是说，对于当前选择的数 xx，若前面有与其相同的数 yy，且没有选择 yy，此时包含 xx 的子集，必然会出现在包含 yy 的所有子集中。
     *
     * 我们可以通过判断这种情况，来避免生成重复的子集。代码实现时，可以先将数组排序；迭代时，若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subsets-ii/solution/zi-ji-ii-by-leetcode-solution-7inq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     *执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 19.72%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 46.04%
     * 的用户
     * 通过测试用例：
     * 20 / 20
     */
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<Integer>(t));
            }
        }
        return ans;
    }


    /**
     * 方法二：递归法实现子集枚举
     * 思路
     *
     * 与方法一类似，在递归时，若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集。
     * @param nums
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 98.29%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 55.80%
     * 的用户
     * 通过测试用例：
     * 20 / 20
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }

    public void dfs(boolean choosePre, int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        dfs(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }

}
