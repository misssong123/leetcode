package com.meng.origin;

import java.util.Arrays;

public class LargestPerimeter {
    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了23.65% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了85.29% 的用户
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int len = A.length,maxIndex = len-1,ans=0;
        while (maxIndex>=2){
            if (A[maxIndex]<(A[maxIndex-1]+A[maxIndex-2])){
                ans = A[maxIndex]+A[maxIndex-1]+A[maxIndex-2];
                break;
            }
            maxIndex--;
        }
        return ans;
    }
    /**
     * 官方解法
     * 方法一：贪心 + 排序
     *
     * 不失一般性，我们假设三角形的边长满足 a≤b≤ca \leq b \leq ca≤b≤c，那么这三条边组成面积不为零的三角形的充分必要条件为 a+b>ca+b>ca+b>c。
     *
     * 基于此，我们可以选择枚举三角形的最长边 ccc，而从贪心的角度考虑，我们一定是选「小于 ccc 的最大的两个数」作为边长 aaa 和 bbb，此时最有可能满足 a+b>ca+b>ca+b>c，使得三条边能够组成一个三角形，且此时的三角形的周长是最大的。
     *
     * 因此，我们先对整个数组排序，倒序枚举第 iii 个数作为最长边，那么我们只要看其前两个数 A[i−2]A[i-2]A[i−2] 和 A[i−1]A[i-1]A[i−1]，判断 A[i−2]+A[i−1]A[i-2]+A[i-1]A[i−2]+A[i−1] 是否大于 A[i]A[i]A[i] 即可，如果能组成三角形我们就找到了最大周长的三角形，返回答案 A[i−2]+A[i−1]+A[i]A[i-2]+A[i-1]+A[i]A[i−2]+A[i−1]+A[i] 即可。如果对于任何数作为最长边都不存在面积不为零的三角形，则返回答案 000。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/largest-perimeter-triangle/solution/san-jiao-xing-de-zui-da-zhou-chang-by-leetcode-sol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：9 ms, 在所有 Java 提交中击败了82.92% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了89.85% 的用户
     */
    public int largestPerimeter1(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
