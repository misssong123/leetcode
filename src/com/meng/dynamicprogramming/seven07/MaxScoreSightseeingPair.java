package com.meng.dynamicprogramming.seven07;

/**
 * 1014. 最佳观光组合
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 示例 2：
 *
 * 输入：values = [1,2]
 * 输出：2
 *
 *
 * 提示：
 *
 * 2 <= values.length <= 5 * 104
 * 1 <= values[i] <= 1000
 */
public class MaxScoreSightseeingPair {
    /**
     * 思路错误，在进行两段有最小值确定是可以这么做
     * 思路与题意不符
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair(int[] values) {
        int left = 0 , right = values.length-1,max = 0;
        while (left < right){
            max = Math.max(max,values[left] + values[right] + left -right);
            if (values[right] > values[left]){
                left++;
            }else {
                right--;
            }
        }
        return max;
    }

    /**
     * 方法一：遍历
     * 思路和算法
     *
     * 我们考虑从前往后遍历 jj 来统计答案，对于每个观光景点 jj 而言，我们需要遍历 [0,j-1][0,j−1] 的观光景点 ii 来计算组成观光组合 (i,j)(i,j) 得分的最大值 \textit{cnt}_jcnt
     * j
     * ​
     *   来作为第 jj 个观光景点的值，那么最后的答案无疑就是所有观光景点值的最大值，即 \max_{j=0..n-1}\{cnt_j\}max
     * j=0..n−1
     * ​
     *  {cnt
     * j
     * ​
     *  }。但是遍历 jj 需要 O(n)O(n) 的时间复杂度，遍历 [0,j-1][0,j−1] 的观光景点 ii 也需要 O(n)O(n) 的时间复杂度，因此该方法总复杂度为 O(n^2)O(n
     * 2
     *  )，不能通过所有测试用例，我们需要进一步优化时间复杂度。
     *
     * 我们回过头来看得分公式，我们可以将其拆分成 \textit{values}[i]+ivalues[i]+i 和 \textit{values}[j]-jvalues[j]−j 两部分，这样对于统计景点 jj 答案的时候，由于 \textit{values}[j]-jvalues[j]−j 是固定不变的，因此最大化 \textit{values}[i]+i+\textit{values}[j]-jvalues[i]+i+values[j]−j 的值其实就等价于求 [0,j-1][0,j−1] 中 \textit{values}[i]+ivalues[i]+i 的最大值 \textit{mx}mx，景点 jj 的答案即为 \textit{mx}+\textit{values}[j]-jmx+values[j]−j 。而 \textit{mx}mx 的值我们只要从前往后遍历 jj 的时候同时维护即可，这样每次遍历到景点 jj 的时候，寻找使得得分最大的 ii 就能从 O(n)O(n) 降至 O(1)O(1) 的时间复杂度，总时间复杂度就能从 O(n^2)O(n
     * 2
     *  ) 降至 O(n)O(n)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair/solution/zui-jia-guan-guang-zu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param values
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 95.57%
     * 的用户
     * 内存消耗：
     * 50.1 MB
     * , 在所有 Java 提交中击败了
     * 19.42%
     * 的用户
     * 通过测试用例：
     * 53 / 53
     */
    public int maxScoreSightseeingPair1(int[] values) {
        int ans = 0, mx = values[0] + 0;
        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }


}
