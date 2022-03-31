package com.meng.DataStructureFundamentals.five05;

/**
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 *
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 *
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 */
public class IncreasingTriplet {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 25.47%
     * 的用户
     * 内存消耗：
     * 82.8 MB
     * , 在所有 Java 提交中击败了
     * 5.09%
     * 的用户
     * 通过测试用例：
     * 76 / 76
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if (len < 3){
            return false;
        }
        int[] min = new int[len];
        int[] max = new int[len];
        min[0] = nums[0];
        max[len-1] = nums[len-1];
        for(int i = 1 ; i < len ; i++){
            min[i] = Math.min(min[i-1],nums[i]);
        }
        for(int i = len - 2 ; i >= 0 ; i--){
            max[i] = Math.max(max[i+1],nums[i]);
        }
        for(int i = 1 ; i < len - 1 ; i++){
            if (nums[i] > min[i-1] && nums[i] < max[i+1]){
                return true;
            }
        }
        return false;
    }

    /**
     * 方法一：双向遍历
     * 如果数组 \textit{nums}nums 中存在一个下标 ii 满足 1 \le i < n - 11≤i<n−1，使得在 \textit{nums}[i]nums[i] 的左边存在一个元素小于 \textit{nums}[i]nums[i] 且在 \textit{nums}[i]nums[i] 的右边存在一个元素大于 \textit{nums}[i]nums[i]，则数组 \textit{nums}nums 中存在递增的三元子序列。
     *
     * 在 \textit{nums}[i]nums[i] 的左边存在一个元素小于 \textit{nums}[i]nums[i] 等价于在 \textit{nums}[i]nums[i] 的左边的最小元素小于 \textit{nums}[i]nums[i]，在 \textit{nums}[i]nums[i] 的右边存在一个元素大于 \textit{nums}[i]nums[i] 等价于在 \textit{nums}[i]nums[i] 的右边的最大元素大于 \textit{nums}[i]nums[i]，因此可以维护数组 \textit{nums}nums 中的每个元素左边的最小值和右边的最大值。
     *
     * 创建两个长度为 nn 的数组 \textit{leftMin}leftMin 和 \textit{rightMax}rightMax，对于 0 \le i < n0≤i<n，\textit{leftMin}[i]leftMin[i] 表示 \textit{nums}[0]nums[0] 到 \textit{nums}[i]nums[i] 中的最小值，\textit{rightMax}[i]rightMax[i] 表示 \textit{nums}[i]nums[i] 到 \textit{nums}[n - 1]nums[n−1] 中的最大值。
     *
     * 数组 \textit{leftMin}leftMin 的计算方式如下：
     *
     * \textit{leftMin}[0] = \textit{nums}[0]leftMin[0]=nums[0]；
     *
     * 从左到右遍历数组 \textit{nums}nums，对于 1 \le i < n1≤i<n，\textit{leftMin}[i] = \min(\textit{leftMin}[i - 1], \textit{nums}[i])leftMin[i]=min(leftMin[i−1],nums[i])。
     *
     * 数组 \textit{rightMax}rightMax 的计算方式如下：
     *
     * \textit{rightMax}[n - 1] = \textit{nums}[n - 1]rightMax[n−1]=nums[n−1]；
     *
     * 从右到左遍历数组 \textit{nums}nums，对于 0 \le i < n - 10≤i<n−1，\textit{rightMax}[i] = \max(\textit{rightMax}[i + 1], \textit{nums}[i])rightMax[i]=max(rightMax[i+1],nums[i])。
     *
     * 得到数组 \textit{leftMin}leftMin 和 \textit{rightMax}rightMax 之后，遍历 1 \le i < n - 11≤i<n−1 的每个下标 ii，如果存在一个下标 ii 满足 \textit{leftMin}[i - 1] < \textit{nums}[i] < \textit{rightMax}[i + 1]leftMin[i−1]<nums[i]<rightMax[i+1]，则返回 \text{true}true，如果不存在这样的下标 ii，则返回 \text{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/di-zeng-de-san-yuan-zi-xu-lie-by-leetcod-dp2r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 25.47%
     * 的用户
     * 内存消耗：
     * 82.7 MB
     * , 在所有 Java 提交中击败了
     * 5.09%
     * 的用户
     * 通过测试用例：
     * 76 / 76
     */
    public boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二：贪心
     * 可以使用贪心的方法将空间复杂度降到 O(1)O(1)。从左到右遍历数组 \textit{nums}nums，遍历过程中维护两个变量 \textit{first}first 和 \textit{second}second，分别表示递增的三元子序列中的第一个数和第二个数，任何时候都有 \textit{first} < \textit{second}first<second。
     *
     * 初始时，\textit{first} = \textit{nums}[0]first=nums[0]，\textit{second} = +\inftysecond=+∞。对于 1 \le i < n1≤i<n，当遍历到下标 ii 时，令 \textit{num} = \textit{nums}[i]num=nums[i]，进行如下操作：
     *
     * 如果 \textit{num} > \textit{second}num>second，则找到了一个递增的三元子序列，返回 \text{true}true；
     *
     * 否则，如果 \textit{num} > \textit{first}num>first，则将 \textit{second}second 的值更新为 \textit{num}num；
     *
     * 否则，将 \textit{first}first 的值更新为 \textit{num}num。
     *
     * 如果遍历结束时没有找到递增的三元子序列，返回 \text{false}false。
     *
     * 上述做法的贪心思想是：为了找到递增的三元子序列，\textit{first}first 和 \textit{second}second 应该尽可能地小，此时找到递增的三元子序列的可能性更大。
     *
     * 假设 (\textit{first}, \textit{second}, \textit{num})(first,second,num) 是一个递增的三元子序列，如果存在 \textit{second'}second’ 满足 \textit{first} < \textit{second'} < \textit{second}first<second’<second 且 \textit{second'}second’ 的下标位于 \textit{first}first 的下标和 \textit{num}num 的下标之间，则 (\textit{first}, \textit{second'}, \textit{num})(first,second’,num) 也是一个递增的三元子序列。但是当 (\textit{first}, \textit{second'}, \textit{num})(first,second’,num) 是递增的三元子序列时，由于 \textit{num}num 不一定大于 \textit{second}second，因此 (\textit{first}, \textit{second}, \textit{num})(first,second,num) 未必是递增的三元子序列。由此可见，为了使找到递增的三元子序列的可能性更大，三元子序列的第二个数应该尽可能地小，将 \textit{second'}second’ 作为三元子序列的第二个数优于将 \textit{second}second 作为三元子序列的第二个数。
     *
     * 同理可得，三元子序列的第一个数也应该尽可能地小。
     *
     * 如果遍历过程中遇到的所有元素都大于 \textit{first}first，则当遇到 \textit{num} > \textit{second}num>second 时，\textit{first}first 一定出现在 \textit{second}second 的前面，\textit{second}second 一定出现在 \textit{num}num 的前面，(\textit{first}, \textit{second}, \textit{num})(first,second,num) 即为递增的三元子序列。
     *
     * 如果遍历过程中遇到小于 \textit{first}first 的元素，则会用该元素更新 \textit{first}first，虽然更新后的 \textit{first}first 出现在 \textit{second}second 的后面，但是在 \textit{second}second 的前面一定存在一个元素 \textit{first'}first’ 小于 \textit{second}second，因此当遇到 \textit{num} > \textit{second}num>second 时，(\textit{first'}, \textit{second}, \textit{num})(first’,second,num) 即为递增的三元子序列。
     *
     * 根据上述分析可知，当遇到 \textit{num} > \textit{second}num>second 时，一定存在一个递增的三元子序列，该三元子序列的第二个数和第三个数分别是 \textit{second}second 和 \textit{num}num，因此返回 \text{true}true。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/di-zeng-de-san-yuan-zi-xu-lie-by-leetcod-dp2r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.94%
     * 的用户
     * 内存消耗：
     * 82.4 MB
     * , 在所有 Java 提交中击败了
     * 5.52%
     * 的用户
     * 通过测试用例：
     * 76 / 76
     */
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

}
