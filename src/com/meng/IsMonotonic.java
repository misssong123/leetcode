package com.meng;

/**
 * 896. 单调数列
 *
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,2,3]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：[6,5,4,4]
 * 输出：true
 *
 * 示例 3：
 *
 * 输入：[1,3,2]
 * 输出：false
 *
 * 示例 4：
 *
 * 输入：[1,2,4,5]
 * 输出：true
 *
 * 示例 5：
 *
 * 输入：[1,1,1]
 * 输出：true
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length <= 50000
 *     -100000 <= A[i] <= 100000
 */
public class IsMonotonic {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了26.63% 的用户
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        int cnt = 0;
        for(int i = 0 ; i < len -1 ; i++){
            if (cnt == 0){
                if (A[i+1] > A[i]){
                    cnt = 1;
                    continue;
                }
                if (A[i+1]  < A[i]){
                    cnt = -1;
                    continue;
                }
            }else if (cnt == -1){
                if (A[i+1] > A[i])
                    return false;
            }else {
                if (A[i+1] < A[i])
                    return false;
            }
        }
        return true;
    }
    /**
     * 方法一：两次遍历
     *
     * 遍历两次数组，分别判断其是否为单调递增或单调递减。
     * 官方解法
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了25.88% 的用户
     */
    public boolean isMonotonic1(int[] A) {
        return isSorted(A, true) || isSorted(A, false);
    }

    public boolean isSorted(int[] A, boolean increasing) {
        int n = A.length;
        if (increasing) {
            for (int i = 0; i < n - 1; ++i) {
                if (A[i] > A[i + 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < n - 1; ++i) {
                if (A[i] < A[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 方法二：一次遍历
     *
     * 遍历数组 AAA，若既遇到了 A[i]>A[i+1]A[i]>A[i+1]A[i]>A[i+1] 又遇到了 A[i′]<A[i′+1]A[i']<A[i'+1]A[i′]<A[i′+1]，则说明 AAA 既不是单调递增的，也不是单调递减的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/monotonic-array/solution/dan-diao-shu-lie-by-leetcode-solution-ysex/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了40.70% 的用户
     * 内存消耗：46.6 MB, 在所有 Java 提交中击败了66.21% 的用户
     */
    public boolean isMonotonic2(int[] A) {
        boolean inc = true, dec = true;
        int n = A.length;
        for (int i = 0; i < n - 1; ++i) {
            if (A[i] > A[i + 1]) {
                inc = false;
            }
            if (A[i] < A[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }
}
