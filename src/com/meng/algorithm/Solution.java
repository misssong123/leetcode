package com.meng.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
 *
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 *
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * 输出：
 * [null,0]
 * 解释：
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 *
 * 示例 2：
 *
 * 输入：
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * 输出：
 * [null,1,1,1,1,0]
 * 解释：
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 *
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 *
 *
 *
 * 提示：
 *
 *     1 <= w.length <= 10000
 *     1 <= w[i] <= 10^5
 *     pickIndex 将被调用不超过 10000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    int[] pre;
    int total;

    /**
     * 方法一：前缀和 + 二分查找
     *
     * 思路与算法
     *
     * 设数组 www 的权重之和为 total\textit{total}total。根据题目的要求，我们可以看成将 [1,total][1, \textit{total}][1,total] 范围内的所有整数分成 nnn 个部分（其中 nnn 是数组 www 的长度），第 iii 个部分恰好包含 w[i]w[i]w[i] 个整数，并且这 nnn 个部分两两的交集为空。随后我们在 [1,total][1, \textit{total}][1,total] 范围内随机选择一个整数 xxx，如果整数 xxx 被包含在第 iii 个部分内，我们就返回 iii。
     *
     * 一种较为简单的划分方法是按照从小到大的顺序依次划分每个部分。例如 w=[3,1,2,4]w = [3, 1, 2, 4]w=[3,1,2,4] 时，权重之和 total=10\textit{total} = 10total=10，那么我们按照 [1,3],[4,4],[5,6],[7,10][1, 3], [4, 4], [5, 6], [7, 10][1,3],[4,4],[5,6],[7,10] 对 [1,10][1, 10][1,10] 进行划分，使得它们的长度恰好依次为 3,1,2,43, 1, 2, 43,1,2,4。可以发现，每个区间的左边界是在它之前出现的所有元素的和加上 111，右边界是到它为止的所有元素的和。因此，如果我们用 pre[i]\textit{pre}[i]pre[i] 表示数组 www 的前缀和：
     *
     * pre[i]=∑k=0iw[k]\textit{pre}[i] = \sum_{k=0}^i w[k] pre[i]=k=0∑i​w[k]
     *
     * 那么第 iii 个区间的左边界就是 pre[i]−w[i]+1\textit{pre}[i] - w[i] + 1pre[i]−w[i]+1，右边界就是 pre[i]\textit{pre}[i]pre[i]。
     *
     * 当划分完成后，假设我们随机到了整数 xxx，我们希望找到满足：
     *
     * pre[i]−w[i]+1≤x≤pre[i]\textit{pre}[i] - w[i] + 1 \leq x \leq \textit{pre}[i] pre[i]−w[i]+1≤x≤pre[i]
     *
     * 的 iii 并将其作为答案返回。由于 pre[i]\textit{pre}[i]pre[i] 是单调递增的，因此我们可以使用二分查找在 O(log⁡n)O(\log n)O(logn) 的时间内快速找到 iii，即找出最小的满足 x≤pre[i]x \leq \textit{pre}[i]x≤pre[i] 的下标 iii。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/random-pick-with-weight/solution/an-quan-zhong-sui-ji-xuan-ze-by-leetcode-h13t/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param w
     */
    public Solution(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; ++i) {
            pre[i] = pre[i - 1] + w[i];
        }
        total = Arrays.stream(w).sum();
    }

    public int pickIndex() {
        int x = (int) (Math.random() * total) + 1;
        return binarySearch(x);
    }

    private int binarySearch(int x) {
        int low = 0, high = pre.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (pre[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}



/**
 * 384. 打乱数组
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 *
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 *
 *
 * 示例：
 *
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 */
class Solution384 {
    int [] origin;
    int [] nums;

    /**
     * 执行用时：
     * 86 ms
     * , 在所有 Java 提交中击败了
     * 45.53%
     * 的用户
     * 内存消耗：
     * 46.4 MB
     * , 在所有 Java 提交中击败了
     * 83.85%
     * 的用户
     * 通过测试用例：
     * 10 / 10
     * @param nums
     */
    public Solution384(int[] nums) {
        this.nums = nums;
        origin = new int[nums.length];
        System.arraycopy(nums,0,origin,0,origin.length);
    }

    public int[] reset() {
        System.arraycopy(origin,0,nums,0,origin.length);
        return nums;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }
        Random random = new Random();
        for(int i = 0 ; i < nums.length;i++){
            int index = random.nextInt(list.size());
            nums[i] = list.remove(index);
        }
        return nums;
    }
}

/**
 * 方法二：Fisher-Yates 洗牌算法
 * 思路和算法
 *
 * 考虑通过调整 \textit{waiting}waiting 的实现方式以优化方法一。
 *
 * 我们可以在移除 \textit{waiting}waiting 的第 kk 个元素时，将第 kk 个元素与数组的最后 11 个元素交换，然后移除交换后数组的最后 11 个元素，这样我们只需要 O(1)O(1) 的时间复杂度即可完成移除第 kk 个元素的操作。此时，被移除的交换后数组的最后 11 个元素即为我们根据随机下标获取的元素。
 *
 * 在此基础上，我们也可以不移除最后 11 个元素，而直接将其作为乱序后的结果，并更新待乱序数组的长度，从而实现数组的原地乱序。因为我们不再需要从数组中移除元素，所以也可以将第 kk 个元素与第 11 个元素交换。
 *
 * 具体地，实现算法如下：
 *
 * 设待原地乱序的数组 \textit{nums}nums。
 * 循环 nn 次，在第 ii 次循环中（0 \le i < n0≤i<n）：
 * 在 [i,n)[i,n) 中随机抽取一个下标 jj；
 * 将第 ii 个元素与第 jj 个元素交换。
 * 其中数组中的 \textit{nums}[i \ .. \ n-1]nums[i .. n−1] 的部分为待乱序的数组，其长度为 n-in−i；\textit{nums}[0 \ .. \ i-1]nums[0 .. i−1] 的部分为乱序后的数组，其长度为 ii。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array/solution/da-luan-shu-zu-by-leetcode-solution-og5u/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：
 * 81 ms
 * , 在所有 Java 提交中击败了
 * 83.04%
 * 的用户
 * 内存消耗：
 * 46.5 MB
 * , 在所有 Java 提交中击败了
 * 81.41%
 * 的用户
 * 通过测试用例：
 * 10 / 10
 */
class Solutions{
    int[] nums;
    int[] original;

    public Solutions(int[] nums) {
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            int j = i + random.nextInt(nums.length - i);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }


}