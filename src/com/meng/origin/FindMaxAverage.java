package com.meng.origin;

/**
 * 643. 子数组最大平均数 I
 *
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 *
 *
 * 示例：
 *
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 *
 *
 * 提示：
 *
 *     1 <= k <= n <= 30,000。
 *     所给数据范围 [-10,000，10,000]。
 */
public class FindMaxAverage {
    /**
     *
     * @param nums
     * @param k
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了82.47% 的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了37.58% 的用户
     */
    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length,max = 0 ,sum = 0;
        for(int i = 0 ; i <  k ; i++){
            sum += nums[i];
        }
        max = sum;
        for(int i = k ; i < len ; i++){
            sum = sum - nums[i-k] + nums[i];
            max = Math.max(sum,max);
        }
        return (double)max/k;
    }
    /**
     * 方法一：滑动窗口
     *
     * 由于规定了子数组的长度为 kkk，因此可以通过寻找子数组的最大元素和的方式寻找子数组的最大平均数，元素和最大的子数组对应的平均数也是最大的。证明如下：
     *
     *     假设两个不同的子数组的长度都是 kkk，这两个子数组的元素和分别是 xxx 和 yyy，则这两个子数组的平均数分别是 x/kx/kx/k 和 y/ky/ky/k。如果 x≥yx \ge yx≥y，则有 x/k≥y/kx/k \ge y/kx/k≥y/k，即如果一个子数组的元素和更大，则该子数组的平均数也更大。
     *
     * 为了找到子数组的最大元素和，需要对数组中的每个长度为 kkk 的子数组分别计算元素和。对于长度为 nnn 的数组，当 k≤nk \le nk≤n 时，有 n−k+1n-k+1n−k+1 个长度为 kkk 的子数组。如果直接计算每个子数组的元素和，则时间复杂度过高，无法通过全部测试用例，因此需要使用时间复杂度更低的方法计算每个子数组的元素和。
     *
     * 用 sumi\textit{sum}_isumi​ 表示数组 nums\textit{nums}nums 以下标 iii 结尾的长度为 kkk 的子数组的元素和，其中 i≥k−1i \ge k-1i≥k−1，则 sumi\textit{sum}_isumi​ 的计算方法如下：
     *
     * sumi=∑j=i−k+1inums[j]\textit{sum}_i=\sum\limits_{j=i-k+1}^i \textit{nums}[j] sumi​=j=i−k+1∑i​nums[j]
     *
     * 当 i>k−1i>k-1i>k−1 时，将上式的 iii 替换成 i−1i-1i−1，可以得到以下算式：
     *
     * sumi−1=∑j=i−ki−1nums[j]\textit{sum}_{i-1}=\sum\limits_{j=i-k}^{i-1} \textit{nums}[j] sumi−1​=j=i−k∑i−1​nums[j]
     *
     * 将上述两个算式相减，可以得到如下关系：
     *
     * sumi−sumi−1=nums[i]−nums[i−k]\textit{sum}_i-\textit{sum}_{i-1}=\textit{nums}[i]-\textit{nums}[i-k] sumi​−sumi−1​=nums[i]−nums[i−k]
     *
     * 整理得到：
     *
     * sumi=sumi−1−nums[i−k]+nums[i]\textit{sum}_i=\textit{sum}_{i-1}-\textit{nums}[i-k]+\textit{nums}[i] sumi​=sumi−1​−nums[i−k]+nums[i]
     *
     * 上述过程可以看成维护一个长度为 kkk 的滑动窗口。当滑动窗口从下标范围 [i−k,i−1][i-k,i-1][i−k,i−1] 移动到下标范围 [i−k+1,i][i-k+1,i][i−k+1,i] 时，nums[i−k]\textit{nums}[i-k]nums[i−k] 从窗口中移出，nums[i]\textit{nums}[i]nums[i] 进入到窗口内。
     *
     * 利用上述关系，可以在 O(1)O(1)O(1) 的时间内通过 sumi−1\textit{sum}_{i-1}sumi−1​ 得到 sumi\textit{sum}_isumi​。
     *
     * 第一个子数组的元素和 sumk−1\textit{sum}_{k-1}sumk−1​ 需要通过计算 nums\textit{nums}nums 的前 kkk 个元素之和得到，从 sumk\textit{sum}_ksumk​ 到 sumn−1\textit{sum}_{n-1}sumn−1​ 的值则可利用上述关系快速计算得到。只需要遍历数组 nums\textit{nums}nums 一次即可得到每个长度为 kkk 的子数组的元素和，时间复杂度是 O(n)O(n)O(n)。
     *
     * 在上述过程中维护最大的子数组元素和，记为 maxSum\textit{maxSum}maxSum，子数组的最大平均数即为 maxSum/k\textit{maxSum}/kmaxSum/k。需要注意返回值是浮点型，因此计算除法时需要进行数据类型转换。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-i/solution/zi-shu-zu-zui-da-ping-jun-shu-i-by-leetc-us1k/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了16.54% 的用户
     */
    public double findMaxAverage1(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }
}
