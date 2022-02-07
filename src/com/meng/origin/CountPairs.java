package com.meng.origin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1711. 大餐计数
 *
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 *
 * 你可以搭配 任意 两道餐品做一顿大餐。
 *
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 *
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 *
 *
 *
 * 示例 1：
 *
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 *
 * 示例 2：
 *
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 *
 *
 *
 * 提示：
 *
 *     1 <= deliciousness.length <= 105
 *     0 <= deliciousness[i] <= 220
 */
public class CountPairs {
    /**
     * 超出时间限制
     * @param deliciousness
     * @return
     */
    public int countPairs(int[] deliciousness) {
        int len = deliciousness.length;
        int res = 0;
        Set<Integer> cache = new HashSet<>();
        for(int i = 0 ; i < len - 1 ; i++){
            for(int j = i+1 ; j < len ; j++){
                int temp = deliciousness[i] + deliciousness[j];
                if (cache.contains(temp)){
                    res++;
                    continue;
                }
                if (Integer.bitCount(temp) == 1){
                    res++;
                    cache.add(temp);
                }
                res %= 1000000009;
            }
        }
        return res ;
    }

    /**
     *方法一：哈希表
     *
     * 朴素的解法是遍历数组 deliciousness\textit{deliciousness}deliciousness 中的每对元素，对于每对元素，计算两个元素之和是否等于 222 的幂。该解法的时间复杂度为 O(n2)O(n^2)O(n2)，会超出时间限制。
     *
     * 上述朴素解法存在同一个元素被重复计算的情况，因此可以使用哈希表减少重复计算，降低时间复杂度。具体做法是，使用哈希表存储数组中的每个元素的出现次数，遍历到数组 deliciousness\textit{deliciousness}deliciousness 中的某个元素时，在哈希表中寻找与当前元素的和等于 222 的幂的元素个数，然后用当前元素更新哈希表。由于遍历数组时，哈希表中已有的元素的下标一定小于当前元素的下标，因此任意一对元素之和等于 222 的幂的元素都不会被重复计算。
     *
     * 令 maxVal\textit{maxVal}maxVal 表示数组 deliciousness\textit{deliciousness}deliciousness 中的最大元素，则数组中的任意两个元素之和都不会超过 maxVal×2\textit{maxVal} \times 2maxVal×2。令 maxSum=maxVal×2\textit{maxSum} = \textit{maxVal} \times 2maxSum=maxVal×2，则任意一顿大餐的美味程度之和为不超过 maxSum\textit{maxSum}maxSum 的某个 222 的幂。
     *
     * 对于某个特定的 222 的幂 sum\textit{sum}sum，可以在 O(n)O(n)O(n) 的时间内计算数组 deliciousness\textit{deliciousness}deliciousness 中元素之和等于 sum\textit{sum}sum 的元素对的数量。数组 deliciousness\textit{deliciousness}deliciousness 中的最大元素 maxVal\textit{maxVal}maxVal 满足 maxVal≤C\textit{maxVal} \le CmaxVal≤C，其中 C=220C=2^{20}C=220，则不超过 maxSum\textit{maxSum}maxSum 的 222 的幂有 O(log⁡maxSum)=O(log⁡maxVal)=O(log⁡C)O(\log \textit{maxSum})=O(\log \textit{maxVal})=O(\log C)O(logmaxSum)=O(logmaxVal)=O(logC) 个，因此可以在 O(nlog⁡C)O(n \log C)O(nlogC) 的时间内计算数组 deliciousness\textit{deliciousness}deliciousness 中的大餐数量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-good-meals/solution/da-can-ji-shu-by-leetcode-solution-fvg9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param deliciousness
     * @return
     * 执行用时：137 ms, 在所有 Java 提交中击败了68.47% 的用户
     * 内存消耗：46.7 MB, 在所有 Java 提交中击败了98.64% 的用户
     */
    public int countPairs1(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }
}
