package com.meng.algorithm;

/**
 * 413. 等差数列划分
 *
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 *     例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 *
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 *
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 5000
 *     -1000 <= nums[i] <= 1000
 */
public class NumberOfArithmeticSlices {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了72.89% 的用户
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int res = 0;
        int len = nums.length;
        if (len < 3){
            return res;
        }
        int dif = nums[1] - nums[0];
        int left = 0 , right = 0;
        while (right < len -1){
            if ((nums[right+1] - nums[right]) == dif){
                right++;
            }else {
                if (right - left > 1){
                    int num = right - left + 1;
                    res += (num -1) * (num - 2) / 2;
                }
                dif = nums[right+1] - nums[right];
                left = right;
            }
        }
        if (right - left > 1){
            int num = right - left + 1;
            res += (num -1) * (num - 2) / 2;
        }
        return res;
    }

    /**
     * 方法一：差分 + 计数
     *
     * 思路与算法
     *
     * 考虑一个比较直观的做法：
     *
     *     我们枚举等差数列的最后两项 nums[i−1]\textit{nums}[i - 1]nums[i−1] 以及 nums[i]\textit{nums}[i]nums[i]，那么等差数列的公差 ddd 即为 nums[i−1]−nums[i]\textit{nums}[i - 1] - \textit{nums}[i]nums[i−1]−nums[i]；
     *
     *     随后我们使用一个指针 jjj 从 i−2i - 2i−2 开始逆序地遍历数组的前缀部分 nums[0..i−2]\textit{nums}[0 .. i-2]nums[0..i−2]：
     *
     *         如果 nums[j]−nums[j+1]=d\textit{nums}[j] - \textit{nums}[j + 1] = dnums[j]−nums[j+1]=d，那么说明 nums[j],⋯ ,nums[i]\textit{nums}[j], \cdots, \textit{nums}[i]nums[j],⋯,nums[i] 组成了一个长度至少为 333 的等差数列，答案增加 111；
     *
     *         否则更小的 jjj 也无法作为等差数列的首个位置了，我们直接退出遍历。
     *
     * 这个做法的时间复杂度是 O(n2)O(n^2)O(n2) 的，即枚举最后两项的时间复杂度为 O(n)O(n)O(n)，使用指针 jjj 遍历的时间复杂度也为 O(n)O(n)O(n)，相乘得到总时间复杂度 O(n2)O(n^2)O(n2)。对于一些运行较慢的语言，该方法可能会超出时间限制，因此我们需要进行优化。
     *
     * 优化
     *
     * 如果我们已经求出了 nums[i−1]\textit{nums}[i - 1]nums[i−1] 以及 nums[i]\textit{nums}[i]nums[i] 作为等差数列的最后两项时，答案增加的次数 tit_iti​，那么能否快速地求出 ti+1t_{i+1}ti+1​ 呢？
     *
     * 答案是可以的：
     *
     *     如果 nums[i]−nums[i+1]=d\textit{nums}[i] - \textit{nums}[i + 1] = dnums[i]−nums[i+1]=d，那么在这一轮遍历中，jjj 会遍历到与上一轮相同的位置，答案增加的次数相同，并且额外多出了 nums[i−1],nums[i],nums[i+1]\textit{nums}[i-1], \textit{nums}[i], \textit{nums}[i+1]nums[i−1],nums[i],nums[i+1] 这一个等差数列，因此有：
     *
     * ti+1=ti+1t_{i+1} = t_i + 1 ti+1​=ti​+1
     *
     *     如果 nums[i]−num[i+1]≠d\textit{nums}[i] - \textit{num}[i + 1] \neq dnums[i]−num[i+1]​=d，那么 jjj 从初始值 i−1i-1i−1 开始就会直接退出遍历，答案不会增加，因此有：
     *
     * ti+1=0t_{i+1} = 0 ti+1​=0
     *
     * 这样一来，我们通过上述简单的递推，即可在 O(1)O(1)O(1) 的时间计算等差数列数量的增量，总时间复杂度减少至 O(n)O(n)O(n)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/arithmetic-slices/solution/deng-chai-shu-lie-hua-fen-by-leetcode-so-g7os/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param nums
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了90.39% 的用户
     */
    public int numberOfArithmeticSlices1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}
