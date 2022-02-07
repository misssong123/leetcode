package com.meng.origin;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1365. 有多少小于当前数字的数字
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 *
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 *
 * 以数组形式返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 *
 * 示例 2：
 *
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 *
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 *
 *
 *
 * 提示：
 *
 *     2 <= nums.length <= 500
 *     0 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallerNumbersThanCurrent {
    /**
     * 遍历
     * @param nums
     * @return
     * 执行用时：15 ms, 在所有 Java 提交中击败了56.07% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了96.29% 的用户
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length,count = 0;
        int [] ans = new int[len];
        for (int i = 0 ; i < len ;i++){
            count = 0;
            for (int j = 0 ; j < len ; j++){
                if (nums[i] > nums[j])
                    count++;
            }
            ans[i] = count;
        }
        return ans;
    }
    /**
     * 官方解法1
     * 方法二：快速排序
     *
     * 我们也可以将数组排序，并记录每一个数在原数组中的位置。对于排序后的数组中的每一个数，我们找出其左侧第一个小于它的数，这样就能够知道数组中小于该数的数量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/solution/you-duo-shao-xiao-yu-dang-qian-shu-zi-de-shu-zi--2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：6 ms, 在所有 Java 提交中击败了66.41% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了97.03% 的用户
     */
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, new Comparator<int[]>() {
            public int compare(int[] data1, int[] data2) {
                return data1[0] - data2[0];
            }
        });

        int[] ret = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            ret[data[i][1]] = prev;
        }
        return ret;
    }
    /**
     * 官方解法2
     * 方法三：计数排序
     *
     * 注意到数组元素的值域为 [0,100][0,100][0,100]，所以可以考虑建立一个频次数组 cntcntcnt ，cnt[i]cnt[i]cnt[i] 表示数字 iii 出现的次数。那么对于数字 iii 而言，小于它的数目就为 cnt[0...i−1]cnt[0...i-1]cnt[0...i−1] 的总和。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/solution/you-duo-shao-xiao-yu-dang-qian-shu-zi-de-shu-zi--2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了97.16% 的用户
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int[] cnt = new int[101];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ret;
    }
}
