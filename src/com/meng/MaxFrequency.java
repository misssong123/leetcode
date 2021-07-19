package com.meng;

import java.util.Arrays;

/**
 * 1838. 最高频元素的频数
 *
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 *
 * 示例 2：
 *
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 *
 * 示例 3：
 *
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 105
 *     1 <= nums[i] <= 105
 *     1 <= k <= 105
 *
 * 通过次数22,090
 * 提交次数51,942
 */
public class MaxFrequency {
    /**
     * 执行用时：32 ms, 在所有 Java 提交中击败了96.99% 的用户
     * 内存消耗：54.5 MB, 在所有 Java 提交中击败了8.15% 的用户
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        long total = 0;
        int res = 1;
        int left = 0;
        for(int right = 1 ; right < len ; right++){
            total += (nums[right] - nums[right - 1]) * (right - left);
            while (total > k){
                total -= nums[right] - nums[left];
                left++;
            }
            res = Math.max(res,right-left+1);
        }
        return res;
    }

    /**
     * 方法一：排序 + 滑动窗口
     *
     * 提示 111
     *
     * 操作后的最高频元素必定可以是数组中已有的某一个元素。
     *
     * 提示 111 解释
     *
     * 我们用 xix_ixi​ 来表示 numsnumsnums 数组中下标为 iii 的元素。
     *
     * 如果可以将数组内的一系列元素 xi1,…,xik{x_i}_1,\dots,{x_i}_kxi​1​,…,xi​k​ 全部变为 yyy，假设这些元素中的最大值为 xxx，那么我们一定可以将这些数全部变成 xxx，此时频数不变且操作次数更少。
     *
     * 提示 222
     *
     * 优先操作距离目标值最近的（小于目标值的）元素。
     *
     * 提示 222 解释
     *
     * 假设目标值为 yyy，对于数组内任意两个小于 yyy 的元素 xi<xjx_i < x_jxi​<xj​，将较大者（xjx_jxj​）变为 yyy 所需要的操作数为 y−xjy - x_jy−xj​，而对应改变较小者（xix_ixi​）做需要的操作数为 y−xiy - x_iy−xi​，显然有 y−xj<y−xiy - x_j < y - x_iy−xj​<y−xi​。
     *
     * 提示 333
     *
     * 遍历数组中的每个元素作为目标值并进行尝试。此处是否存在一些可以用于优化算法的性质？
     *
     * 思路与算法
     *
     * 我们可以按照提示 111 与提示 222 的贪心策略进行操作。
     *
     * 将数组排序，遍历排序后数组每个元素 xrx_rxr​ 作为目标值，并求出此时按贪心策略可以改变至目标值的元素左边界。
     *
     * 此时考虑到数据范围为 10510^5105，朴素的线性查找显然会超时，因此需要寻找可以优化的性质。
     *
     * 我们可以枚举 xrx_rxr​ 作为目标值。假设 xrx_rxr​ 对应的答案左边界为 xlx_lxl​，定义 Δ(l,r)\Delta(l, r)Δ(l,r) 为将 xl,…,xrx_l,\dots,x_rxl​,…,xr​ 全部变为 xrx_rxr​ 所需要的操作次数：
     *
     * Δ(l,r)=∑i=lr(xr−xi)=(r−l)xr−∑i=lr−1xi.\Delta(l, r) = \sum_{i = l}^{r} (x_r - x_i) = (r - l)x_r - \sum_{i = l}^{r-1} x_i. Δ(l,r)=i=l∑r​(xr​−xi​)=(r−l)xr​−i=l∑r−1​xi​.
     *
     * 考虑右边界 rrr 右移至 r+1r + 1r+1 的过程，此时：
     *
     * Δ(l,r+1)−Δ(l,r)=(xr+1−xr)⋅(r−l+1)≥0.\Delta(l, r + 1) - \Delta(l, r) = (x_{r + 1} - x_{r})\cdot(r - l + 1) \ge 0. Δ(l,r+1)−Δ(l,r)=(xr+1​−xr​)⋅(r−l+1)≥0.
     *
     * 操作数有可能超过限制 kkk，因此在超过限制的情况下，我们需要移动左边界 lll。同样考虑左边界 lll 右移至 l+1l + 1l+1 的过程，此时:
     *
     * Δ(l+1,r+1)−Δ(l,r+1)=−(xr+1−xl)≤0.\Delta(l + 1, r + 1) - \Delta(l, r + 1) = -(x_{r + 1} - x_{l}) \le 0. Δ(l+1,r+1)−Δ(l,r+1)=−(xr+1​−xl​)≤0.
     *
     * 这说明右移左边界会使得答案减小，因此我们需要移动左边界直至对应的 Δ(l′,r+1)\Delta(l', r + 1)Δ(l′,r+1) 不大于 kkk。
     *
     * 我们使用 lll 与 rrr 作为执行操作的左右边界（闭区间），同时用 total\textit{total}total 来维护将 [l,r][l, r][l,r] 区间全部变为末尾元素的操作次数。在顺序枚举目标值（右边界）的同时，我们更新对应的左边界，并用 res\textit{res}res 来维护满足限制的最大区间元素数量即可。
     *
     * 另外要注意，此处 total\textit{total}total 有可能会超过 323232 位整数的范围，因此在 C++\texttt{C++}C++ 等语言中需要使用更高位数的整型变量（long long\texttt{long long}long long 等）。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/solution/zui-gao-pin-yuan-su-de-pin-shu-by-leetco-q5g9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param nums
     * @param k
     * @return
     * 执行用时：31 ms, 在所有 Java 提交中击败了98.97% 的用户
     * 内存消耗：53.8 MB, 在所有 Java 提交中击败了18.99% 的用户
     */
    public int maxFrequency1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
