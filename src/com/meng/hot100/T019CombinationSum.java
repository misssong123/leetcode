package com.meng.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 */
public class T019CombinationSum {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 95.77%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 86.13%
     * 的用户
     * 通过测试用例：
     * 160 / 160
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,0,0,target,candidates.length);
        return res;
    }

    private void dfs(int[] candidates, int index, int sum, int target, int len) {
        if (index >= len || sum > target){
            return;
        }
        if (sum == target){
            res.add(new ArrayList<>(temp));
            return;
        }

        if (sum + candidates[index] <= target){
            //存放当前下标
            temp.add(candidates[index]);
            dfs(candidates,index,sum + candidates[index],target,len);
            temp.remove(temp.size()-1);
        }
        //不存放当前下标
        dfs(candidates,index+1,sum ,target,len);
    }

    /**
     *方法一：搜索回溯
     * 思路与算法
     *
     * 对于这类寻找所有可行解的题，我们都可以尝试用「搜索回溯」的方法来解决。
     *
     * 回到本题，我们定义递归函数 \textit{dfs}(\textit{target}, \textit{combine}, \textit{idx})dfs(target,combine,idx) 表示当前在 \textit{candidates}candidates 数组的第 \textit{idx}idx 位，还剩 \textit{target}target 要组合，已经组合的列表为 \textit{combine}combine。递归的终止条件为 \textit{target} \le 0target≤0 或者 \textit{candidates}candidates 数组被全部用完。那么在当前的函数中，每次我们可以选择跳过不用第 \textit{idx}idx 个数，即执行 \textit{dfs}(\textit{target}, \textit{combine}, \textit{idx} + 1)dfs(target,combine,idx+1)。也可以选择使用第 \textit{idx}idx 个数，即执行 \textit{dfs}(\textit{target} - \textit{candidates}[\textit{idx}], \textit{combine}, \textit{idx})dfs(target−candidates[idx],combine,idx)，注意到每个数字可以被无限制重复选取，因此搜索的下标仍为 \textit{idx}idx。
     *
     * 更形象化地说，如果我们将整个搜索过程用一个树来表达，即如下图呈现，每次的搜索都会延伸出两个分叉，直到递归的终止条件，这样我们就能不重复且不遗漏地找到所有可行解：
     *
     *
     *
     * 当然，搜索回溯的过程一定存在一些优秀的剪枝方法来使得程序运行得更快，而这里只给出了最朴素不含剪枝的写法，因此欢迎各位读者在评论区分享自己的见解。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 64.70%
     * 的用户
     * 通过测试用例：
     * 160 / 160
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

}
