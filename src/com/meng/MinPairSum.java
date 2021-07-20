package com.meng;

import java.util.Arrays;

/**
 * 1877. 数组中最大数对和的最小值
 *
 * 一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
 *
 *     比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
 *
 * 给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
 *
 *     nums 中每个元素 恰好 在 一个 数对中，且
 *     最大数对和 的值 最小 。
 *
 * 请你在最优数对划分的方案下，返回最小的 最大数对和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,5,2,3]
 * 输出：7
 * 解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
 * 最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
 *
 * 示例 2：
 *
 * 输入：nums = [3,5,4,2,4,6]
 * 输出：8
 * 解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
 * 最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。
 *
 *
 *
 * 提示：
 *
 *     n == nums.length
 *     2 <= n <= 105
 *     n 是 偶数 。
 *     1 <= nums[i] <= 105
 */
public class MinPairSum {
    /**
     * 执行用时：59 ms, 在所有 Java 提交中击败了96.68% 的用户
     * 内存消耗：54 MB, 在所有 Java 提交中击败了57.42% 的用户
     * @param nums
     * @return
     */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0 , right = nums.length - 1;
        int res = 0;
        while (right > left){
            res = Math.max(res,nums[left] + nums[right]);
            left++;
            right--;
        }
        return res;
    }

    /**
     * 方法一：排序 + 贪心
     *
     * 提示 111
     *
     * 数组内只有两个数的情况是平凡的。我们可以考虑数组中只有四个数 x1≤x2≤x3≤x4x_1 \le x_2 \le x_3 \le x_4x1​≤x2​≤x3​≤x4​ 的情况。此时 (x1,x4),(x2,x3)(x_1, x_4), (x_2, x_3)(x1​,x4​),(x2​,x3​) 的拆分方法对应的最大数对和一定是最小的。
     *
     * 提示 111 解释
     *
     * 我们可以枚举所有的拆分方法。除了上文的拆分方法外还有两种拆分方法：
     *
     *     (x1,x3),(x2,x4)(x_1, x_3), (x_2, x_4)(x1​,x3​),(x2​,x4​)
     *
     *     此时 x2+x4≥x1+x4x_2 + x_4 \ge x_1 + x_4x2​+x4​≥x1​+x4​ 且 x2+x4≥x2+x3x_2 + x_4 \ge x_2 + x_3x2​+x4​≥x2​+x3​。
     *
     *     那么 max⁡(x1+x3,x2+x4)≥x2+x4≥max⁡(x1+x4,x2+x3)\max(x_1+x_3,x_2+x_4) \ge x_2 + x_4 \ge \max(x_1+x_4,x_2+x_3)max(x1​+x3​,x2​+x4​)≥x2​+x4​≥max(x1​+x4​,x2​+x3​)。
     *
     *     (x1,x2),(x3,x4)(x_1, x_2), (x_3, x_4)(x1​,x2​),(x3​,x4​)
     *
     *     同样地，max⁡(x1+x2,x3+x4)≥x3+x4≥max⁡(x1+x4,x2+x3)\max(x_1+x_2,x_3+x_4) \ge x_3 + x_4 \ge \max(x_1+x_4,x_2+x_3)max(x1​+x2​,x3​+x4​)≥x3​+x4​≥max(x1​+x4​,x2​+x3​)。
     *
     * 提示 222
     *
     * 对于 nnn 个数（nnn 为偶数）的情况，上述的条件对应的拆分方法，即第 kkk 大与第 kkk 小组成的 n/2n / 2n/2 个数对，同样可以使得最大数对和最小。
     *
     * 提示 222 解释
     *
     * 我们可以类似 提示 111 对所有数建立全序关系，即 x1≤⋯≤xnx_1 \le \cdots \le x_nx1​≤⋯≤xn​。我们需要证明，任意的拆分方法得到的最大数对和一定大于等于给定的拆分方法得到的最大数对和。
     *
     * 我们可以考虑上述命题的充分条件：假设给定拆分方法中的数对和 xk+xn+1−kx_k + x_{n+1-k}xk​+xn+1−k​ 在 k=k′k = k'k=k′ 时最大，那么对于任意的拆分方法，都存在一组 u,vu, vu,v 使得 xu+xv≥xk′+xn+1−k′x_u + x_v \ge x_{k'} + x_{n+1-k'}xu​+xv​≥xk′​+xn+1−k′​。
     *
     * 我们可以用反证法证明。
     *
     * 同样，我们假设 u<vu < vu<v，那么使得 xv≥xn+1−k′x_v \ge x_{n+1-k'}xv​≥xn+1−k′​ 的 vvv 的取值一共有 k′k'k′ 种。即闭区间 [n+1−k′,n][n+1-k',n][n+1−k′,n] 中的所有整数。对于这些 vvv 组成的数对，如果想使得 xu+xv<xk′+xn+1−k′x_u + x_v < x_{k'} + x_{n+1-k'}xu​+xv​<xk′​+xn+1−k′​ 恒成立，必须要 xu<xk′x_u < x_{k'}xu​<xk′​。此时需要有 k′k'k′ 个不同的 uuu 的取值，但只有闭区间 [1,k′−1][1,k'-1][1,k′−1] 中的 k′−1k'-1k′−1 个整数满足 xu<xk′x_u < x_{k'}xu​<xk′​ 的条件，这就产生了矛盾。
     *
     * 因此，一定存在一组 u,vu, vu,v 使得 xu+xv≥xk′+xn+1−k′x_u + x_v \ge x_{k'} + x_{n+1-k'}xu​+xv​≥xk′​+xn+1−k′​。
     *
     * 思路与算法
     *
     * 根据 提示 222，我们需要将 nums\textit{nums}nums 排序。排序后，我们遍历每一个第 kkk 大与第 kkk 小组成的数对，计算它们的和，并维护这些和的最大值。同样根据 提示 222，遍历完成后求得的最大数对和就是满足要求的最小值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/solution/shu-zu-zhong-zui-da-shu-dui-he-de-zui-xi-cvll/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：71 ms, 在所有 Java 提交中击败了6.96% 的用户
     * 内存消耗：53.4 MB, 在所有 Java 提交中击败了92.81% 的用户
     * @param nums
     * @return
     */
    public int minPairSum1(int[] nums) {
        int n = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n / 2; ++i) {
            res = Math.max(res, nums[i] + nums[n - 1 - i]);
        }
        return res;
    }
}
