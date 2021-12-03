package com.meng.algorithm;

import java.util.*;

/**
 * 1005. K 次取反后最大化的数组和
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * 示例 2：
 *
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * 示例 3：
 *
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -100 <= nums[i] <= 100
 * 1 <= k <= 104
 */
public class LargestSumAfterKNegations {
    /**
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 31.46%
     * 的用户
     * 内存消耗：
     * 37.7 MB
     * , 在所有 Java 提交中击败了
     * 85.05%
     * 的用户
     * 通过测试用例：
     * 80 / 80
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> cache = new PriorityQueue<>(Integer::compare);
        for(int i = 0 ; i < nums.length ; i++){
            cache.add(nums[i]);
        }
        for(int i = 0 ; i < k ; i++){
            cache.add(-cache.poll());
        }
        int sum = 0;
        while (!cache.isEmpty()){
            sum += cache.poll();
        }
        return sum;
    }

    /**
     *方法一：从小到大修改每个负数
     * 思路与算法
     *
     * 由于我们希望数组的和尽可能大，因此除非万不得已，我们应当总是修改负数，并且优先修改值最小的负数。因为将负数 -x−x 修改成 xx 会使得数组的和增加 2x2x，所以这样的贪心操作是最优的。
     *
     * 当给定的 KK 小于等于数组中负数的个数时，我们按照上述方法从小到大依次修改每一个负数即可。但如果 KK 的值较大，那么我们不得不去修改非负数（即正数或者 00）了。由于修改 00 对数组的和不会有影响，而修改正数会使得数组的和减小，因此：
     *
     * 如果数组中存在 00，那么我们可以对它进行多次修改，直到把剩余的修改次数用完；
     *
     * 如果数组中不存在 00 并且剩余的修改次数是偶数，由于对同一个数修改两次等价于不进行修改，因此我们也可以在不减小数组的和的前提下，把修改次数用完；
     *
     * 如果数组中不存在 00 并且剩余的修改次数是奇数，那么我们必然需要使用单独的一次修改将一个正数变为负数（剩余的修改次数为偶数，就不会减小数组的和）。为了使得数组的和尽可能大，我们就选择那个最小的正数。
     *
     * 需要注意的是，在之前将负数修改为正数的过程中，可能出现了（相较于原始数组中最小的正数）更小的正数，这一点不能忽略。
     *
     * 细节
     *
     * 为了实现上面的算法，我们可以对数组进行升序排序，首先依次遍历每一个负数（将负数修改为正数），再遍历所有的数（将 00 或最小的正数进行修改）。
     *
     * 然而注意到本题中数组元素的范围为 [-100, 100][−100,100]，因此我们可以使用计数数组（桶）或者哈希表，直接统计每个元素出现的次数，再升序遍历元素的范围，这样就省去了排序需要的时间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/solution/k-ci-qu-fan-hou-zui-da-hua-de-shu-zu-he-4r5lb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 31.46%
     * 的用户
     * 内存消耗：
     * 37.7 MB
     * , 在所有 Java 提交中击败了
     * 75.61%
     * 的用户
     * 通过测试用例：
     * 80 / 80
     */
    public int largestSumAfterKNegations1(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = Arrays.stream(nums).sum();
        for (int i = -100; i < 0; ++i) {
            if (freq.containsKey(i)) {
                int ops = Math.min(k, freq.get(i));
                ans += (-i) * ops * 2;
                freq.put(i, freq.get(i) - ops);
                freq.put(-i, freq.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && k % 2 == 1 && !freq.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (freq.containsKey(i)) {
                    ans -= i * 2;
                    break;
                }
            }
        }
        return ans;
    }

}
