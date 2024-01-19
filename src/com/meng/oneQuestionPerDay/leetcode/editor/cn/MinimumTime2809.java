package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumTime2809 {
    /**
     * 暂无思路
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    public int minimumTimeMy(List<Integer> nums1, List<Integer> nums2, int x) {
        return 3;
    }

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了52.83% 的Java用户
     * 	内存消耗:46.5 MB,击败了26.41% 的Java用户
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    public int minimumTime1(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = 0, s2 = 0;
        int[][] dp = new int[n + 1][n + 1];
        List<List<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i), b = nums2.get(i);
            nums.add(Arrays.asList(b, a));
            s1 += a;
            s2 += b;
        }
        Collections.sort(nums, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));

        for (int j = 1; j <= n; ++j) {
            int b = nums.get(j - 1).get(0), a = nums.get(j - 1).get(1);
            for (int i = j; i > 0; --i) {
                dp[j][i] = Math.max(dp[j - 1][i], dp[j - 1][i - 1] + i * b + a);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (s2 * i + s1 - dp[n][i] <= x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了90.57% 的Java用户
     * 	内存消耗:43.9 MB,击败了64.15% 的Java用户
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    public int minimumTime2(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = 0, s2 = 0;
        int[] dp = new int[n + 1];
        List<List<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i), b = nums2.get(i);
            nums.add(Arrays.asList(b, a));
            s1 += a;
            s2 += b;
        }
        Collections.sort(nums, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));

        for (int j = 1; j <= n; ++j) {
            int b = nums.get(j - 1).get(0), a = nums.get(j - 1).get(1);
            for (int i = j; i > 0; --i) {
                dp[i] = Math.max(dp[i], dp[i - 1] + i * b + a);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (s2 * i + s1 - dp[i] <= x) {
                return i;
            }
        }
        return -1;
    }

    /**
     *执行用时分布
     * 14
     * ms
     * 击败
     * 100.00%
     * 使用 Java 的用户
     * 消耗内存分布
     * 43.84
     * MB
     * 击败
     * 64.15%
     * 使用 Java 的用户
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    public int minimumTime3(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = 0, s2 = 0;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i);
            int b = nums2.get(i);
            pairs[i][0] = a;
            pairs[i][1] = b;
            s1 += a;
            s2 += b;
        }
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int a = pairs[i][0];
            int b = pairs[i][1];
            for (int j = i + 1; j > 0; j--) {
                f[j] = Math.max(f[j], f[j - 1] + a + b * j);
            }
        }

        for (int t = 0; t <= n; t++) {
            if (s1 + s2 * t - f[t] <= x) {
                return t;
            }
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
