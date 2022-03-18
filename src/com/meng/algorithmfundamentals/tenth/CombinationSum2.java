package com.meng.algorithmfundamentals.tenth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * 提示:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSum2 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    boolean[] flags = null;

    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 34.93%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 7.08%
     * 的用户
     * 通过测试用例：
     * 175 / 175
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        flags = new boolean[len];
        Arrays.sort(candidates);
        dfs(candidates,0,target,0,len);
        return res;
    }

    private void dfs(int[] candidates, int index, int target, int sum, int size) {
        if (sum == target){
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index >= size){
            return;
        }
        dfs(candidates,index+1,target,sum,size);
        if (sum + candidates[index] > target || (index > 0 && !flags[index-1] && candidates[index] == candidates[index-1])){
            return;
        }
        temp.add(candidates[index]);
        flags[index] = true;
        dfs(candidates,index+1,target,sum+candidates[index],size);
        flags[index] = false;
        temp.remove(temp.size()-1);
    }

    public static void main(String[] args) {
        CombinationSum2 demo = new CombinationSum2();
        int[] nums = {10,1,2,7,6,1,5};
        System.out.println(demo.combinationSum2(nums,8));
    }



    /**
     * 方法一：回溯
     * 思路与算法
     *
     * 由于我们需要求出所有和为 \textit{target}target 的组合，并且每个数只能使用一次，因此我们可以使用递归 + 回溯的方法来解决这个问题：
     *
     * 我们用 \text{dfs}(\textit{pos}, \textit{rest})dfs(pos,rest) 表示递归的函数，其中 \textit{pos}pos 表示我们当前递归到了数组 \textit{candidates}candidates 中的第 \textit{pos}pos 个数，而 \textit{rest}rest 表示我们还需要选择和为 \textit{rest}rest 的数放入列表作为一个组合；
     *
     * 对于当前的第 \textit{pos}pos 个数，我们有两种方法：选或者不选。如果我们选了这个数，那么我们调用 \text{dfs}(\textit{pos}+1, \textit{rest} - \textit{candidates}[\textit{pos}])dfs(pos+1,rest−candidates[pos]) 进行递归，注意这里必须满足 \textit{rest} \geq \textit{candidates}[\textit{pos}]rest≥candidates[pos]。如果我们不选这个数，那么我们调用 \text{dfs}(\textit{pos}+1, \textit{rest})dfs(pos+1,rest) 进行递归；
     *
     * 在某次递归开始前，如果 \textit{rest}rest 的值为 00，说明我们找到了一个和为 \textit{target}target 的组合，将其放入答案中。每次调用递归函数前，如果我们选了那个数，就需要将其放入列表的末尾，该列表中存储了我们选的所有数。在回溯时，如果我们选了那个数，就要将其从列表的末尾删除。
     *
     * 上述算法就是一个标准的递归 + 回溯算法，但是它并不适用于本题。这是因为题目描述中规定了解集不能包含重复的组合，而上述的算法中并没有去除重复的组合。
     *
     * 例如当 \textit{candidates} = [2, 2]candidates=[2,2]，\textit{target} = 2target=2 时，上述算法会将列表 [2][2] 放入答案两次。
     *
     * 因此，我们需要改进上述算法，在求出组合的过程中就进行去重的操作。我们可以考虑将相同的数放在一起进行处理，也就是说，如果数 \textit{x}x 出现了 yy 次，那么在递归时一次性地处理它们，即分别调用选择 0, 1, \cdots, y0,1,⋯,y 次 xx 的递归函数。这样我们就不会得到重复的组合。具体地：
     *
     * 我们使用一个哈希映射（HashMap）统计数组 \textit{candidates}candidates 中每个数出现的次数。在统计完成之后，我们将结果放入一个列表 \textit{freq}freq 中，方便后续的递归使用。
     *
     * 列表 \textit{freq}freq 的长度即为数组 \textit{candidates}candidates 中不同数的个数。其中的每一项对应着哈希映射中的一个键值对，即某个数以及它出现的次数。
     * 在递归时，对于当前的第 \textit{pos}pos 个数，它的值为 \textit{freq}[\textit{pos}][0]freq[pos][0]，出现的次数为 \textit{freq}[\textit{pos}][1]freq[pos][1]，那么我们可以调用
     *
     * \text{dfs}(\textit{pos}+1, \textit{rest} - i \times \textit{freq}[\textit{pos}][0])
     * dfs(pos+1,rest−i×freq[pos][0])
     *
     * 即我们选择了这个数 ii 次。这里 ii 不能大于这个数出现的次数，并且 i \times \textit{freq}[\textit{pos}][0]i×freq[pos][0] 也不能大于 \textit{rest}rest。同时，我们需要将 ii 个 \textit{freq}[\textit{pos}][0]freq[pos][0] 放入列表中。
     *
     * 这样一来，我们就可以不重复地枚举所有的组合了。
     *
     * 我们还可以进行什么优化（剪枝）呢？一种比较常用的优化方法是，我们将 \textit{freq}freq 根据数从小到大排序，这样我们在递归时会先选择小的数，再选择大的数。这样做的好处是，当我们递归到 \text{dfs}(\textit{pos}, \textit{rest})dfs(pos,rest) 时，如果 \textit{freq}[\textit{pos}][0]freq[pos][0] 已经大于 \textit{rest}rest，那么后面还没有递归到的数也都大于 \textit{rest}rest，这就说明不可能再选择若干个和为 \textit{rest}rest 的数放入列表了。此时，我们就可以直接回溯。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/combination-sum-ii/solution/zu-he-zong-he-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 53.13%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 12.43%
     * 的用户
     * 通过测试用例：
     * 175 / 175
     */
    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

}
