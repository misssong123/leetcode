package com.meng.algorithmfundamentals.tenth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermuteUnique {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    boolean[] flags = null;

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.70%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 45.94%
     * 的用户
     * 通过测试用例：
     * 33 / 33
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        flags = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums,0,nums.length);
        return res;
    }

    private void dfs(int[] nums, int index, int len) {
        if (index == len){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0 ; i < len ; i++){
            if (flags[i] || (i>0 && nums[i] == nums[i-1] && !flags[i-1])){
                continue;
            }
            flags[i] = true;
            temp.add(nums[i]);
            dfs(nums,index+1,len);
            flags[i] = false;
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 方法一：搜索回溯
     * 思路和算法
     *
     * 此题是「46. 全排列」的进阶，序列中包含了重复的数字，要求我们返回不重复的全排列，那么我们依然可以选择使用搜索回溯的方法来做。
     *
     * 我们将这个问题看作有 nn 个排列成一行的空格，我们需要从左往右依次填入题目给定的 nn 个数，每个数只能使用一次。那么很直接的可以想到一种穷举的算法，即从左往右每一个位置都依此尝试填入一个数，看能不能填完这 nn 个空格，在程序中我们可以用「回溯法」来模拟这个过程。
     *
     * 我们定义递归函数 backtrack(idx, perm) 表示当前排列为 \textit{perm}perm，下一个待填入的位置是第 \textit{idx}idx 个位置（下标从 00 开始）。那么整个递归函数分为两个情况：
     *
     * 如果 \textit{idx}==nidx==n，说明我们已经填完了 nn 个位置，找到了一个可行的解，我们将 \textit{perm}perm 放入答案数组中，递归结束。
     * 如果 \textit{idx}<nidx<n，我们要考虑第 \textit{idx}idx 个位置填哪个数。根据题目要求我们肯定不能填已经填过的数，因此很容易想到的一个处理手段是我们定义一个标记数组 \textit{vis}vis 来标记已经填过的数，那么在填第 \textit{idx}idx 个数的时候我们遍历题目给定的 nn 个数，如果这个数没有被标记过，我们就尝试填入，并将其标记，继续尝试填下一个位置，即调用函数 backtrack(idx + 1, perm)。搜索回溯的时候要撤销该个位置填的数以及标记，并继续尝试其他没被标记过的数。
     * 但题目解到这里并没有满足「全排列不重复」 的要求，在上述的递归函数中我们会生成大量重复的排列，因为对于第 \textit{idx}idx 的位置，如果存在重复的数字 ii，我们每次会将重复的数字都重新填上去并继续尝试导致最后答案的重复，因此我们需要处理这个情况。
     *
     * 要解决重复问题，我们只要设定一个规则，保证在填第 \textit{idx}idx 个数的时候重复数字只会被填入一次即可。而在本题解中，我们选择对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」，即如下的判断条件：
     *
     * C++
     *
     * if (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]) {
     *     continue;
     * }
     * 这个判断条件保证了对于重复数的集合，一定是从左往右逐个填入的。
     *
     * 假设我们有 33 个重复数排完序后相邻，那么我们一定保证每次都是拿从左往右第一个未被填过的数字，即整个数组的状态其实是保证了 [未填入，未填入，未填入] 到 [填入，未填入，未填入]，再到 [填入，填入，未填入]，最后到 [填入，填入，填入] 的过程的，因此可以达到去重的目标。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/permutations-ii/solution/quan-pai-lie-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.70%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 40.80%
     * 的用户
     * 通过测试用例：
     * 33 / 33
     */
    boolean[] vis;

    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

}
