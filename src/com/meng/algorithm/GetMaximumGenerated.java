package com.meng.algorithm;

import java.util.Arrays;

/**
 * 1646. 获取生成数组中的最大值
 *
 * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
 *
 *     nums[0] = 0
 *     nums[1] = 1
 *     当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 *     当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 *
 * 返回生成数组 nums 中的 最大 值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 7
 * 输出：3
 * 解释：根据规则：
 *   nums[0] = 0
 *   nums[1] = 1
 *   nums[(1 * 2) = 2] = nums[1] = 1
 *   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 *   nums[(2 * 2) = 4] = nums[2] = 1
 *   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 *   nums[(3 * 2) = 6] = nums[3] = 2
 *   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
 *
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：1
 * 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
 *
 * 示例 3：
 *
 * 输入：n = 3
 * 输出：2
 * 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
 *
 *
 *
 * 提示：
 *
 *     0 <= n <= 100
 */
public class GetMaximumGenerated {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了39.68% 的用户
     * @param n
     * @return
     */
    public int getMaximumGenerated(int n) {
        if(n<2){
            return n;
        }
        int[] nums = new int[n+1];
        nums[0] = 0;
        nums[1] = 1;
        int res = 1;
        for(int i = 2 ; i < n+1 ; i++){
            if(i%2 == 0){
                nums[i] = nums[i/2];
            }else{
                nums[i] = nums[i/2] + nums[i/2+1];
            }
            res = Math.max(res,nums[i]);
        }
        return res;
    }

    /**
     * 方法一：模拟
     *
     * 我们可以直接根据题目中描述的规则来计算出 nums\textit{nums}nums 数组，并返回其最大元素。
     *
     * 为了简化代码逻辑，我们可以化简题目中的递推式。当 i≥2i\ge 2i≥2 时：
     *
     *     若 iii 为偶数，有 nums[i]=nums[i2]\textit{nums}[i] = \textit{nums}[\dfrac{i}{2}]nums[i]=nums[2i​]；
     *     若 iii 为奇数，有 nums[i]=nums[⌊i2⌋]+nums[⌊i2⌋+1]\textit{nums}[i] = \textit{nums}[\Big\lfloor\dfrac{i}{2}\Big\rfloor] + \textit{nums}[\Big\lfloor\dfrac{i}{2}\Big\rfloor+1]nums[i]=nums[⌊2i​⌋]+nums[⌊2i​⌋+1]。
     *
     * 这两种情况可以合并为：
     *
     * nums[i]=nums[⌊i2⌋]+(i mod 2)⋅nums[⌊i2⌋+1]\textit{nums}[i] = \textit{nums}[\Big\lfloor\dfrac{i}{2}\Big\rfloor] + (i\bmod 2) \cdot \textit{nums}[\Big\lfloor\dfrac{i}{2}\Big\rfloor+1] nums[i]=nums[⌊2i​⌋]+(imod2)⋅nums[⌊2i​⌋+1]
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array/solution/huo-qu-sheng-cheng-shu-zu-zhong-de-zui-d-0z2l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：2 ms, 在所有 Java 提交中击败了18.71% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了5.42% 的用户
     */
    public int getMaximumGenerated1(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i <= n; ++i) {
            nums[i] = nums[i / 2] + i % 2 * nums[i / 2 + 1];
        }
        return Arrays.stream(nums).max().getAsInt();
    }
}
