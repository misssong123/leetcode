package com.meng.origin;

/**
 *665. 非递减数列
 *
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 *
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *
 *
 *
 * 说明：
 *
 *     1 <= n <= 10 ^ 4
 *     - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class CheckPossibility {
    /**
     *
     * @param nums
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.54% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了49.28% 的用户
     */
    public boolean checkPossibility(int[] nums) {
        int len = nums.length , count = 0;
        for(int i = 0 ; i < len-1 ; i++){
            if (nums[i] > nums[i+1]){
                if (count == 1)
                    return false;
                if (i>0 && nums[i+1]>=nums[i-1]){
                    count++;
                    continue;
                }else if(i == 0 && i < len -2 && nums[i+2]>=nums[i+1]){
                    count++;
                    continue;
                }else if (i<len - 2 && nums[i+2]>=nums[i]){
                    count++;
                    continue;
                }else if (i == len-2){
                    count++;
                    continue;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 方法一：数组
     *
     * 首先思考如下问题：
     *
     *     要使数组 nums\textit{nums}nums 变成一个非递减数列，数组中至多有多少个 iii 满足 nums[i]>nums[i+1]\textit{nums}[i]>\textit{nums}[i+1]nums[i]>nums[i+1]？
     *
     * 假设有两个不同的下标 iii, jjj 满足上述不等式，不妨设 i<ji<ji<j.
     *
     * 若 i+1<ji+1<ji+1<j，则无论怎么修改 nums[i]\textit{nums}[i]nums[i] 或 nums[i+1]\textit{nums}[i+1]nums[i+1]，都不会影响 nums[j]\textit{nums}[j]nums[j] 与 nums[j+1]\textit{nums}[j+1]nums[j+1] 之间的大小关系；修改 nums[j]\textit{nums}[j]nums[j] 或 nums[j+1]\textit{nums}[j+1]nums[j+1] 也同理。因此，这种情况下，我们无法将 nums\textit{nums}nums 变成非递减数列。
     *
     * 若 i+1=ji+1=ji+1=j，则有 nums[i]>nums[i+1]>nums[i+2]\textit{nums}[i]>\textit{nums}[i+1]>\textit{nums}[i+2]nums[i]>nums[i+1]>nums[i+2]。同样地，无论怎么修改其中一个数，都无法使这三个数变为 nums[i]≤nums[i+1]≤nums[i+2]\textit{nums}[i]\le\textit{nums}[i+1]\le\textit{nums}[i+2]nums[i]≤nums[i+1]≤nums[i+2] 的大小关系。
     *
     * 因此，上述问题的结论是:
     *
     *     至多一个。
     *
     * 满足这个条件就足够了吗？并不，对于满足该条件的数组 [3,4,1,2][3,4,1,2][3,4,1,2] 而言，无论怎么修改也无法将其变成非递减数列。
     *
     * 因此，若找到了一个满足 nums[i]>nums[i+1]\textit{nums}[i]>\textit{nums}[i+1]nums[i]>nums[i+1] 的 iii，在修改 nums[i]\textit{nums}[i]nums[i] 或 nums[i+1]\textit{nums}[i+1]nums[i+1] 之后，还需要检查 nums\textit{nums}nums 是否变成了非递减数列。
     *
     * 我们可以将 nums[i]\textit{nums}[i]nums[i] 修改成小于或等于 nums[i+1]\textit{nums}[i+1]nums[i+1] 的数，但由于还需要保证 nums[i]\textit{nums}[i]nums[i] 不小于它之前的数，nums[i]\textit{nums}[i]nums[i] 越大越好，所以将 nums[i]\textit{nums}[i]nums[i] 修改成 nums[i+1]\textit{nums}[i+1]nums[i+1] 是最佳的；同理，对于 nums[i+1]\textit{nums}[i+1]nums[i+1]，修改成 nums[i]\textit{nums}[i]nums[i] 是最佳的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/non-decreasing-array/solution/fei-di-jian-shu-lie-by-leetcode-solution-zdsm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.54% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了65.73% 的用户
     */
    public boolean checkPossibility1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i] = y;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x; // 复原
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }
        return true;
    }

    public boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 优化
     *
     * 上面的代码多次遍历了 nums\textit{nums}nums 数组，能否只遍历一次呢？
     *
     * 修改 nums[i]\textit{nums}[i]nums[i] 为 nums[i+1]\textit{nums}[i+1]nums[i+1] 后，还需要保证 nums[i−1]≤nums[i]\textit{nums}[i-1]\le\textit{nums}[i]nums[i−1]≤nums[i] 仍然成立，即 nums[i−1]≤nums[i+1]\textit{nums}[i-1]\le\textit{nums}[i+1]nums[i−1]≤nums[i+1]，若该不等式不成立则整个数组必然不是非递减的，则需要修改 nums[i+1]\textit{nums}[i+1]nums[i+1] 为 nums[i]\textit{nums}[i]nums[i]。修改完后，接着判断后续数字的大小关系。在遍历中统计 nums[i]>nums[i+1]\textit{nums}[i]>\textit{nums}[i+1]nums[i]>nums[i+1] 的次数，若超过一次可以直接返回 false\text{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/non-decreasing-array/solution/fei-di-jian-shu-lie-by-leetcode-solution-zdsm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param nums
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.54% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了66.98% 的用户
     */
    public boolean checkPossibility2(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckPossibility demo = new CheckPossibility();
        int[] nums = {4,3,3};
        System.out.println(demo.checkPossibility2(nums));
    }
}
