package com.meng.origin;

/**
 * 941. 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 *
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *
 *     A.length >= 3
 *     在 0 < i < A.length - 1 条件下，存在 i 使得：
 *         A[0] < A[1] < ... A[i-1] < A[i]
 *         A[i] > A[i+1] > ... > A[A.length - 1]
 *
 *示例 1：
 *
 * 输入：[2,1]
 * 输出：false
 *
 * 示例 2：
 *
 * 输入：[3,5,5]
 * 输出：false
 *
 * 示例 3：
 *
 * 输入：[0,3,2,1]
 * 输出：true
 *
 *
 *
 * 提示：
 *
 *     0 <= A.length <= 10000
 *     0 <= A[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidMountainArray_941 {
    public static void main(String[] args) {
        int [] A = {0,3,2,1};
        ValidMountainArray_941 demo = new ValidMountainArray_941();
        System.out.println(demo.validMountainArray(A));
    }
    /**
     * 从前面寻找第一个不大于后面数据的坐标，
     * 从后面寻找第一个不小于前面的坐标
     * 比较是否为同一元素
     * @param A
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了73.11% 的用户
     */
    public boolean validMountainArray(int[] A) {
        int pre = 0 , last = A.length-1;
        while (pre+1<=last && A[pre]<A[pre+1])
            pre++;
        if (pre == 0 || pre == last)
            return false;
        while (last-1>= 0 && A[last] < A[last-1])
            last--;
        if (pre==last)
            return true;
        return false;
    }
    /**
     * 官方解法1
     *方法一：线性扫描
     *
     * 按题意模拟即可。我们从数组的最左侧开始向右扫描，直到找到第一个不满足 A[i]<A[i+1]A[i] < A[i + 1]A[i]<A[i+1] 的下标 iii，那么 iii 就是这个数组的最高点的下标。如果 i=0i = 0i=0 或者不存在这样的 iii（即整个数组都是单调递增的），那么就返回 false\text{false}false。否则从 iii 开始继续向右扫描，判断接下来的的下标 jjj 是否都满足 A[j]>A[j+1]A[j] > A[j + 1]A[j]>A[j+1]，若都满足就返回 true\text{true}true，否则返回 false\text{false}false。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/valid-mountain-array/solution/you-xiao-de-shan-mai-shu-zu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了63.80% 的用户
     */
    public boolean validMountainArray1(int[] A) {
        int N = A.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

}
