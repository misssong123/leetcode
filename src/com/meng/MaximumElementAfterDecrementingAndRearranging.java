package com.meng;

import java.util.Arrays;

/**
 * 1846. 减小和重新排列数组后的最大元素
 *
 * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 *
 *     arr 中 第一个 元素必须为 1 。
 *     任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
 *
 * 你可以执行以下 2 种操作任意次：
 *
 *     减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
 *     重新排列 arr 中的元素，你可以以任意顺序重新排列。
 *
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [2,2,1,2,1]
 * 输出：2
 * 解释：
 * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
 * arr 中最大元素为 2 。
 *
 * 示例 2：
 *
 * 输入：arr = [100,1,1000]
 * 输出：3
 * 解释：
 * 一个可行的方案如下：
 * 1. 重新排列 arr 得到 [1,100,1000] 。
 * 2. 将第二个元素减小为 2 。
 * 3. 将第三个元素减小为 3 。
 * 现在 arr = [1,2,3] ，满足所有条件。
 * arr 中最大元素为 3 。
 *
 * 示例 3：
 *
 * 输入：arr = [1,2,3,4,5]
 * 输出：5
 * 解释：数组已经满足所有条件，最大元素为 5 。
 *
 *
 *
 * 提示：
 *
 *     1 <= arr.length <= 105
 *     1 <= arr[i] <= 109
 */
public class MaximumElementAfterDecrementingAndRearranging {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.35% 的用户
     * 内存消耗：55.1 MB, 在所有 Java 提交中击败了90.18% 的用户
     * @param arr
     * @return
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int res = 1;
        for(int i = 1 ; i < arr.length ; i++){
            if (arr[i] > res){
                res++;
            }
        }
        return res;
    }

    /**
     *方法一：排序 + 贪心
     *
     * 提示 111
     *
     * 如果一个数组是满足要求的，那么将它的元素按照升序排序后得到的数组也是满足要求的。
     *
     * 提示 111 解释
     *
     * 假设数组中出现了元素 xxx 和 yyy，且 x<yx<yx<y，由于相邻元素差值的绝对值小于等于 111，那么区间 [x,y][x,y][x,y] 内的所有整数应该都出现过。
     *
     * 只要我们令 xxx 和 yyy 分别为数组中元素的最小值和最大值，就说明了将数组升序排序后，得到的结果是不会出现「断层」的，也就是满足要求的。
     *
     * 提示 222
     *
     * 在提示 111 的基础上，我们得到了一个单调递增的数组。那么数组中相邻两个元素，要么后者等于前者，要么后者等于前者加上 111。
     *
     * 我们可以先将数组进行升序排序，随后对数组进行遍历，将 arr[i]\textit{arr}[i]arr[i] 更新为其自身与 arr[i−1]+1\textit{arr}[i-1]+1arr[i−1]+1 中的较小值即可。
     *
     * 最终的答案（最大值）即为 arr\textit{arr}arr 中的最后一个元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/solution/jian-xiao-he-zhong-xin-pai-lie-shu-zu-ho-mzee/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arr
     * @return
     * 执行用时：5 ms, 在所有 Java 提交中击败了84.95% 的用户
     * 内存消耗：55 MB, 在所有 Java 提交中击败了92.91% 的用户
     */
    public int maximumElementAfterDecrementingAndRearranging1(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < n; ++i) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[n - 1];
    }

    /**
     * 方法二：计数排序 + 贪心
     *
     * 深挖题目隐含的性质，我们可以将时间复杂度优化至 O(n)O(n)O(n)。
     *
     * 记 arr\textit{arr}arr 的长度为 nnn。由于第一个元素必须为 111，且任意两个相邻元素的差的绝对值小于等于 111，故答案不会超过 nnn。所以我们只需要对 arr\textit{arr}arr 中值不超过 nnn 的元素进行计数排序，而对于值超过 nnn 的元素，由于其至少要减小到 nnn，我们可以将其视作 nnn。
     *
     * 读者可据此修改方法一中的排序代码，此处不再赘述，我们将重点转到另一种计算策略上。
     *
     * 从另一个视角来看，为了尽可能地构造出最大的答案，我们相当于是在用 arr\textit{arr}arr 中的元素去填补自身在 [1,n][1,n][1,n] 中缺失的元素。
     *
     * 首先，我们用一个长为 n+1n+1n+1 的数组 cnt\textit{cnt}cnt 统计 arr\textit{arr}arr 中的元素个数（将值超过 nnn 的元素视作 nnn）。然后，从 111 到 nnn 遍历 cnt\textit{cnt}cnt 数组，若 cnt[i]=0\textit{cnt}[i]=0cnt[i]=0，则说明缺失元素 iii，我们需要在后续找一个大于 iii 的元素，将其变更为 iii。我们可以用一个变量 miss\textit{miss}miss 记录 cnt[i]=0\textit{cnt}[i]=0cnt[i]=0 的出现次数，当遇到 cnt[i]>0\textit{cnt}[i]>0cnt[i]>0 时，则可以将多余的 cnt[i]−1\textit{cnt}[i]-1cnt[i]−1 个元素减小，补充到之前缺失的元素上。
     *
     * 遍历 cnt\textit{cnt}cnt 结束后，若此时 miss=0\textit{miss}=0miss=0，则说明修改后的 arr\textit{arr}arr 包含了 [1,n][1,n][1,n] 内的所有整数；否则，对于不同大小的缺失元素，我们总是优先填补较小的，因此剩余缺失元素必然是 [n−miss+1,n][n-\textit{miss}+1,n][n−miss+1,n] 这一范围内的 miss\textit{miss}miss 个数，因此答案为 n−missn-\textit{miss}n−miss。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/solution/jian-xiao-he-zhong-xin-pai-lie-shu-zu-ho-mzee/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arr
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.35% 的用户
     * 内存消耗：54.6 MB, 在所有 Java 提交中击败了99.24% 的用户
     */
    public int maximumElementAfterDecrementingAndRearranging2(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] == 0) {
                ++miss;
            } else {
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        return n - miss;
    }

}
