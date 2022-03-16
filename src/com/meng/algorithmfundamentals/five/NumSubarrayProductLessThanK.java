package com.meng.algorithmfundamentals.five;

/**
 * 713. 乘积小于K的子数组
 * 给定一个正整数数组 nums和整数 k 。
 *
 * 请找出该数组内乘积小于 k 的连续的子数组的个数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 示例 2:
 *
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class NumSubarrayProductLessThanK {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 99.97%
     * 的用户
     * 内存消耗：
     * 47.6 MB
     * , 在所有 Java 提交中击败了
     * 43.53%
     * 的用户
     * 通过测试用例：
     * 97 / 97
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int len = nums.length;
        int left = 0 ,sum = 1;
        int res = 0 ;
        for(int i = 0 ; i < len ; i++){
            sum *= nums[i];
            while (sum>=k){
                sum /= nums[left++];
            }
            res = res + i - left + 1;//记录以i为节点，left-i范围内的子数据
        }
        return res;
    }

    /**
     * 方法一：二分查找
     * 分析
     *
     * 我们可以使用二分查找解决这道题目，即对于固定的 ii，二分查找出最大的 jj 满足 \mathrm{nums}[i]nums[i] 到 \mathrm{nums}[j]nums[j] 的乘积小于 kk。但由于乘积可能会非常大（在最坏情况下会达到 1000^{50000}1000
     * 50000
     *  ），会导致数值溢出，因此我们需要对 \mathrm{nums}nums 数组取对数，将乘法转换为加法，即 \log(\prod_i \mathrm{nums}[i]) = \sum_i \log \mathrm{nums}[i]log(∏
     * i
     * ​
     *  nums[i])=∑
     * i
     * ​
     *  lognums[i]，这样就不会出现数值溢出的问题了。
     *
     * 算法
     *
     * 对 \mathrm{nums}nums 中的每个数取对数后，我们存储它的前缀和 \mathrm{prefix}prefix，即 \mathrm{prefix}[i + 1] = \sum_{x=0}^i \mathrm{nums}[x]prefix[i+1]=∑
     * x=0
     * i
     * ​
     *  nums[x]，这样在二分查找时，对于 ii 和 jj，我们可以用 \mathrm{prefix}[j + 1] - \mathrm{prefix}[i]prefix[j+1]−prefix[i] 得到 \mathrm{nums}[i]nums[i] 到 \mathrm{nums}[j]nums[j] 的乘积的对数。对于固定的 ii，当找到最大的满足条件的 jj 后，它会包含 j-i+1j−i+1 个乘积小于 kk 的连续子数组。
     *
     * 下面的代码和算法中下标的定义略有不同。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/cheng-ji-xiao-yu-kde-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 33 ms
     * , 在所有 Java 提交中击败了
     * 22.36%
     * 的用户
     * 内存消耗：
     * 46 MB
     * , 在所有 Java 提交中击败了
     * 51.17%
     * 的用户
     * 通过测试用例：
     * 97 / 97
     */
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int lo = i + 1, hi = prefix.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (prefix[mi] < prefix[i] + logk - 1e-9) lo = mi + 1;
                else hi = mi;
            }
            ans += lo - i - 1;
        }
        return ans;
    }

    /**
     * 方法二：双指针
     * 分析
     *
     * 对于每个 \mathrm{right}right，我们需要找到最小的 \mathrm{left}left，满足 \prod_{i=\mathrm{left}}^\mathrm{right} \mathrm{nums}[i] < k∏
     * i=left
     * right
     * ​
     *  nums[i]<k。由于当 \mathrm{left}left 增加时，这个乘积是单调不增的，因此我们可以使用双指针的方法，单调地移动 \mathrm{left}left。
     *
     * 算法
     *
     * 我们使用一重循环枚举 \mathrm{right}right，同时设置 \mathrm{left}left 的初始值为 0。在循环的每一步中，表示 \mathrm{right}right 向右移动了一位，将乘积乘以 \mathrm{nums}[\mathrm{right}]nums[right]。此时我们需要向右移动 \mathrm{left}left，直到满足乘积小于 kk 的条件。在每次移动时，需要将乘积除以 \mathrm{nums}[\mathrm{left}]nums[left]。当 \mathrm{left}left 移动完成后，对于当前的 \mathrm{right}right，就包含了 \mathrm{right} - \mathrm{left} + 1right−left+1 个乘积小于 kk 的连续子数组。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/cheng-ji-xiao-yu-kde-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 99.97%
     * 的用户
     * 内存消耗：
     * 48.1 MB
     * , 在所有 Java 提交中击败了
     * 8.55%
     * 的用户
     * 通过测试用例：
     * 97 / 97
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }

}
