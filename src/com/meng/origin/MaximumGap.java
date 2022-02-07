package com.meng.origin;

import java.util.Arrays;

/**
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 *
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 *
 * 说明:
 *
 *     你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 *     请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumGap {
    /**
     * 1.先进行桶排序
     * 2.后比较
     * @param nums
     * @return
     * 执行用时：7 ms, 在所有 Java 提交中击败了12.27% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了92.27% 的用户
     */
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        int max = nums[0];
        for(int i = 1 ; i < len ; i++){
            max = Math.max(max,nums[i]);
        }
        max = (max+"").length();
        int [][] bucket = new int[10][len];
        int [] bucketNum = new int[10];
        for(int i = 0 ,j=1; i < max ; i++,j*=10){
            for(int k = 0 ; k < len ; k++){
                int index = nums[k]/j%10;
                bucket[index][bucketNum[index]] = nums[k];
                bucketNum[index]++;
            }
            int index = 0;
            for(int k = 0 ; k < 10 ; k++){
                for(int m = 0 ; m < bucketNum[k] ; m++){
                    nums[index++] = bucket[k][m];
                }
                bucketNum[k] = 0;
            }
        }
        int diff = 0;
        for(int i = 1 ; i < len ; i++){
            diff = Math.max(diff,nums[i]-nums[i-1]);
        }
        return diff;
    }
    /**
     * 官方解法一
     * 方法一：基数排序
     *
     * 思路与算法
     *
     * 一种最简单的思路是将数组排序后再找出最大间距，但传统的基于比较的排序算法（快速排序、归并排序等）均需要 O(Nlog⁡N)O(N\log N)O(NlogN) 的时间复杂度。我们必须使用其他的排序算法。例如，基数排序可以在 O(N)O(N)O(N) 的时间内完成整数之间的排序。
     *
     复杂度分析

     时间复杂度：O(N)，其中 N 是数组的长度。

     空间复杂度：O(N)，其中 N 是数组的长度。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/maximum-gap/solution/zui-da-jian-ju-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     执行用时：5 ms, 在所有 Java 提交中击败了18.87% 的用户
     内存消耗：38.8 MB, 在所有 Java 提交中击败了71.70% 的用户
     */
    public int maximumGap1(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        long exp = 1;
        int[] buf = new int[n];
        int maxVal = Arrays.stream(nums).max().getAsInt();

        while (maxVal >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int) exp) % 10;
                cnt[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int) exp) % 10;
                buf[cnt[digit] - 1] = nums[i];
                cnt[digit]--;
            }
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }

        int ret = 0;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }
    /**
     * 方法二：基于桶的算法
     *
     * 思路与算法
     *
     * 设长度为 NNN 的数组中最大值为 max,min\textit{max,min}max,min，则不难发现相邻数字的最大间距不会小于 ⌈(max−min)/(N−1)⌉\lceil (\textit{max}-\textit{min}) / (N-1) \rceil⌈(max−min)/(N−1)⌉。
     *
     * 为了说明这一点，我们使用反证法：假设相邻数字的间距都小于 ⌈(max−min)/(N−1)⌉\lceil (\textit{max}-\textit{min}) / (N-1) \rceil⌈(max−min)/(N−1)⌉，并记数组排序后从小到大的数字依次为 A1,A2,...,ANA_1, A_2, ..., A_NA1​,A2​,...,AN​，则有
     *
     * AN−A1=(AN−AN−1)+(AN−1−AN−2)+...+(A2−A1)<⌈(max−min)/(N−1)⌉+⌈(max−min)/(N−1)⌉+...+⌈(max−min)/(N−1)⌉<(N−1)⋅⌈(max−min)/(N−1)⌉=max−min\begin{aligned} A_N - A_1&=(A_N - A_{N-1})+(A_{N-1}-A_{N-2})+ ... + (A_2 - A_1) \\ &< \lceil (\textit{max}-\textit{min}) / (N-1) \rceil + \lceil (\textit{max}-\textit{min}) / (N-1) \rceil + ... + \lceil (\textit{max}-\textit{min}) / (N-1) \rceil \\ &< (N-1) \cdot \lceil (\textit{max}-\textit{min}) / (N-1) \rceil= \textit{max}-\textit{min} \end{aligned} AN​−A1​​=(AN​−AN−1​)+(AN−1​−AN−2​)+...+(A2​−A1​)<⌈(max−min)/(N−1)⌉+⌈(max−min)/(N−1)⌉+...+⌈(max−min)/(N−1)⌉<(N−1)⋅⌈(max−min)/(N−1)⌉=max−min​
     *
     * 但根据 A1,ANA_1, A_NA1​,AN​ 的定义，一定有 A1=minA_1=\textit{min}A1​=min，且 AN=maxA_N=\textit{max}AN​=max，故上式会导出矛盾。
     *
     * 因此，我们可以选取整数 d=⌊(max−min)/(N−1)⌋<⌈(max−min)/(N−1)⌉d = \lfloor (\textit{max}-\textit{min}) / (N-1) \rfloor < \lceil (\textit{max}-\textit{min}) / (N-1) \rceild=⌊(max−min)/(N−1)⌋<⌈(max−min)/(N−1)⌉。随后，我们将整个区间划分为若干个大小为 ddd 的桶，并找出每个整数所在的桶。根据前面的结论，能够知道，元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
     *
     * 因此，在找出每个元素所在的桶之后，我们可以维护每个桶内元素的最大值与最小值。随后，只需从前到后不断比较相邻的桶，用后一个桶的最小值与前一个桶的最大值之差作为两个桶的间距，最终就能得到所求的答案。
     *复杂度分析
     *
     *     时间复杂度：O(N)，其中 N 是数组的长度。注意到桶的数量为 (max−min)/d≈N−1=O(N)(\textit{max}-\textit{min})/d \approx N - 1 =O(N)(max−min)/d≈N−1=O(N)。
     *
     *     空间复杂度：O(N)，其中 N 是数组的长度。我们开辟的空间大小取决于桶的数量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-gap/solution/zui-da-jian-ju-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法二
     * 执行用时：6 ms, 在所有 Java 提交中击败了15.33% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了94.25% 的用户
     */
    public int maximumGap2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }
}
