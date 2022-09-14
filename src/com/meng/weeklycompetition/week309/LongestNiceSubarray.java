package com.meng.weeklycompetition.week309;

/**
 * 2401. 最长优雅子数组(中等)
 * 给你一个由 正 整数组成的数组 nums 。
 *
 * 如果 nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。
 *
 * 返回 最长 的优雅子数组的长度。
 *
 * 子数组 是数组中的一个 连续 部分。
 *
 * 注意：长度为 1 的子数组始终视作优雅子数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,8,48,10]
 * 输出：3
 * 解释：最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
 * - 3 AND 8 = 0
 * - 3 AND 48 = 0
 * - 8 AND 48 = 0
 * 可以证明不存在更长的优雅子数组，所以返回 3 。
 * 示例 2：
 *
 * 输入：nums = [3,1,5,11,13]
 * 输出：1
 * 解释：最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */
public class LongestNiceSubarray {
    /**
     * 需要保证下一个数据与之前出现的数据｜的值求&为0
     * @param nums
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 78.68%
     * 的用户
     * 内存消耗：
     * 53.6 MB
     * , 在所有 Java 提交中击败了
     * 67.12%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    public int longestNiceSubarray(int[] nums) {
        int len = nums.length;
        int max = 0;
        for(int i = 0 ; i < len ; i++){
            int num = 0 ,k = i ;
            while (k<len && ((num&nums[k])==0)){
                num = num | nums[k++];
            }
            max = Math.max(max,k-i);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestNiceSubarray demo = new LongestNiceSubarray();
        int[] nums = {1,3,8,48,10};
        System.out.println(demo.longestNiceSubarray(nums));
    }
    /**
     * 暴力枚举即可。
     *
     * 代码实现时可以把在优雅子数组中的元素按位或起来，这样可以 O(1)判断当前元素是否与前面的元素按位与的结果为 0。
     * @param nums
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 78.68%
     * 的用户
     * 内存消耗：
     * 54 MB
     * , 在所有 Java 提交中击败了
     * 40.76%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    public int longestNiceSubarray1(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int or = 0, j = i;
            while (j >= 0 && (or & nums[j]) == 0){
                or |= nums[j--];
            }
            ans = Math.max(ans, i - j);
        }
        return ans;
    }

    /**
     * 进一步地，由于优雅子数组的所有元素按位与均为 00（可以理解成这些二进制数对应的集合没有交集），我们可以用双指针来优化上述过程，如果当前 \textit{or}or 与 \textit{nums}[\textit{right}]nums[right] 按位与的结果不为 00，则从 \textit{or}or 中去掉 \textit{nums}[\textit{left}]nums[left]，并将 \textit{left}left 右移。
     *
     * 作者：endlesscheng
     * 链接：https://leetcode.cn/problems/longest-nice-subarray/solution/bao-li-mei-ju-pythonjavacgo-by-endlessch-z6t6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 99.54%
     * 的用户
     * 内存消耗：
     * 53.4 MB
     * , 在所有 Java 提交中击败了
     * 84.41%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    public int longestNiceSubarray2(int[] nums) {
        int ans = 0;
        for (int left = 0, right = 0, or = 0; right < nums.length; right++) {
            while ((or & nums[right]) > 0){
                or ^= nums[left++];
            }
            or |= nums[right];
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * 方法一：或运算、滑动窗口
     * 用滑动窗口维护区间内的情况。对于新加入的元素 xx，可以通过将窗口中之前的元素全部进行或运算，如果此时的与运算结果为 00，就说明当前新的数字和前面的所有数字与运算都为 00，可以添加到子数组中；否则就需要开始缩小滑动窗口，将窗口左侧的数去除掉。因为要去除掉某些位位，所以需要用 bits 数组记录当前窗口中存在的数的或运算结果每一位的 11 出现的次数，getBits(bits) 来获取当前数组对应的数字，addBits() 将新加入的数的数字逐位统计上去，removeBits() 将要抹去的数字逐位除去。
     *
     * 作者：wangzhizhi
     * 链接：https://leetcode.cn/problems/longest-nice-subarray/solution/by-wangzhizhi-bv8p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 221 ms
     * , 在所有 Java 提交中击败了
     * 7.51%
     * 的用户
     * 内存消耗：
     * 55.6 MB
     * , 在所有 Java 提交中击败了
     * 13.19%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    public int longestNiceSubarray3(int[] nums) {
        int n = nums.length;
        int res = 1;
        int[] bits = new int[32];
        for (int i = 0, j = 0; j < n; j++) {
            // 如果先前数字或运算的结果和当前数字与运算不为 0，说明不满足条件，开始压缩窗口
            while ((getBits(bits) & nums[j]) != 0)
                removeBits(bits, nums[i++]);
            // 将当前数字添加到窗口中
            addBits(bits, nums[j]);
            // 更新最大长度
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
    // 将 bits 翻译成对应的数字
    private int getBits(int[] bits) {
        int res = 0;
        for (int i = 0; i < 32; i++)
            if (bits[i] != 0)
                res |= 1 << i;
        return res;
    }

    // 去除某个数字对应的全部位
    private void removeBits(int[] bits, int num) {
        for (int i = 0; i < 32; i++)
            if ((num & (1 << i)) != 0)
                bits[i]--;
    }

    // 增加某个数字对应的全部位
    private void addBits(int[] bits, int num) {
        for (int i = 0; i < 32; i++)
            if ((num & (1 << i)) != 0)
                bits[i]++;
    }

}
