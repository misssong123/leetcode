package com.meng;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 *
 *
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 *
 *
 * 提示：
 *
 *     2 <= A.length <= 20000
 *     A.length % 2 == 0
 *     0 <= A[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortArrayByParityII {
    /**
     * 新创建一个数组返回
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.68% 的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了38.93% 的用户
     */

    public int[] sortArrayByParityII(int[] A) {
        int len = A.length,index = 0 ,left = 0 ,right = 1;
        int [] ans = new int[len];
        while(left<len-1&&right<len){
            if (A[index]%2==0){
                ans[left] = A[index++];
                left+=2;
            }else {
                ans[right] = A[index++];
                right+=2;
            }
        }
        while (left<len-1){
            ans[left]=A[index++];
            left+=2;
        }
        while (right<len){
            ans[right] = A[index++];
            right+=2;
        }
        return ans;
    }

    /**
     * 原数组修改
     * @param A
     * @return
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了87.40% 的用户
     */
    public int[] sortArrayByParityII1(int[] A) {
        int len = A.length,left = 0 ,right = 1;
        while (left<len-1){
            while (A[left]%2==1){
                if (A[right]%2==0){
                    int temp = A[left];
                    A[left] = A[right];
                    A[right] = temp;
                }
                right+=2;
            }
            left+=2;
        }
        return A;
    }
    /**
     * 官方解法1
     * 方法一： 两次遍历
     *
     * 思路和算法
     *
     * 遍历一遍数组把所有的偶数放进 ans[0]，ans[2]，ans[4]，依次类推。
     *
     * 再遍历一遍数组把所有的奇数依次放进 ans[1]，ans[3]，ans[5]，依次类推
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii/solution/an-qi-ou-pai-xu-shu-zu-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.68% 的用户
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了36.05% 的用户
     */
    public int[] sortArrayByParityII2(int[] A) {
        int n = A.length;
        int[] ans = new int[n];

        int i = 0;
        for (int x : A) {
            if (x % 2 == 0) {
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x : A) {
            if (x % 2 == 1) {
                ans[i] = x;
                i += 2;
            }
        }
        return ans;
    }
    /**
     * 官方解法2
     * 方法二： 双指针
     *
     * 思路与算法
     *
     * 如果原数组可以修改，则可以使用就地算法求解。
     *
     * 为数组的偶数下标部分和奇数下标部分分别维护指针 i,ji, ji,j。随后，在每一步中，如果 A[i]A[i]A[i] 为奇数，则不断地向前移动 jjj（每次移动两个单位），直到遇见下一个偶数。此时，可以直接将 A[i]A[i]A[i] 与 A[j]A[j]A[j] 交换。我们不断进行这样的过程，最终能够将所有的整数放在正确的位置上。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii/solution/an-qi-ou-pai-xu-shu-zu-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.68% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了81.47% 的用户
     */
    public int[] sortArrayByParityII3(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }
    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

