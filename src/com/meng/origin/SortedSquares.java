package com.meng.origin;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 *
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length <= 10000
 *     -10000 <= A[i] <= 10000
 *     A 已按非递减顺序排序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedSquares {
    /**
     * 1.求平方
     * 2.排序
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        for(int i = 0 ; i< n;i++){
            A[i] = A[i]*A[i];
        }
        Arrays.sort(A);
        return A;
    }
    public int[] sortedSquares1(int[] A) {
        int n = A.length;
        int [] ans = new int[n];
        /**
         * 其他情况,即存在大于的数据又存在小于0的数据
         * 思路:
         * 因为数组为非递减数组，则在平方后，数组一定是先递减，后递增，中间点为第一个整数
         * 1.比较第一个数的绝对值与最后一个数的绝对值比较大小
         * 2.将较大的那个数的平方放到数组的后面（依次向前排放）
         * 3.若第一个的的绝对值较大，则将第一个坐标前移
         * 4.若最后一个数较大，将将最后一个最后后移
         * 5.直到两者相遇
         */
        //first 前一个数的坐标，last 后一个数的坐标，index 应该存放的位置
        int first = 0 ,last = n-1 ,index = n-1;
        while(first != last){
            if (Math.abs(A[first])>Math.abs(A[last])){
                ans[index] = A[first] * A[first];
                first ++;
            }else {
                ans[index] = A[last] * A[last];
                last--;
            }
            index --;
        }
        ans[index] = A[first] * A[first];
        return ans;
    }
    /**
     * 官方解法1：
     *方法二：双指针
     *
     * 思路与算法
     *
     * 方法一没有利用「数组 AAA 已经按照升序排序」这个条件。显然，如果数组 AAA 中的所有数都是非负数，那么将每个数平方后，数组仍然保持升序；如果数组 AAA 中的所有数都是负数，那么将每个数平方后，数组会保持降序。
     *
     * 这样一来，如果我们能够找到数组 AAA 中负数与非负数的分界线，那么就可以用类似「归并排序」的方法了。具体地，我们设 neg\textit{neg}neg 为数组 AAA 中负数与非负数的分界线，也就是说，A[0]A[0]A[0] 到 A[neg]A[\textit{neg}]A[neg] 均为负数，而 A[neg+1]A[\textit{neg}+1]A[neg+1] 到 A[n−1]A[n-1]A[n−1] 均为非负数。当我们将数组 AAA 中的数平方后，那么 A[0]A[0]A[0] 到 A[neg]A[\textit{neg}]A[neg] 单调递减，A[neg+1]A[\textit{neg}+1]A[neg+1] 到 A[n−1]A[n-1]A[n−1] 单调递增。
     *
     * 由于我们得到了两个已经有序的子数组，因此就可以使用归并的方法进行排序了。具体地，使用两个指针分别指向位置 neg\textit{neg}neg 和 neg+1\textit{neg}+1neg+1，每次比较两个指针对应的数，选择较小的那个放入答案并移动指针。当某一指针移至边界时，将另一指针还未遍历到的数依次放入答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array/solution/you-xu-shu-zu-de-ping-fang-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] sortedSquares2(int[] A) {
        int n = A.length;
        int negative = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] < 0) {
                negative = i;
            } else {
                break;
            }
        }

        int[] ans = new int[n];
        int index = 0, i = negative, j = negative + 1;
        while (i >= 0 || j < n) {
            if (i < 0) {
                ans[index] = A[j] * A[j];
                ++j;
            } else if (j == n) {
                ans[index] = A[i] * A[i];
                --i;
            } else if (A[i] * A[i] < A[j] * A[j]) {
                ans[index] = A[i] * A[i];
                --i;
            } else {
                ans[index] = A[j] * A[j];
                ++j;
            }
            ++index;
        }

        return ans;
    }

    /**
     * 官方解法2
     * 双指针
     *
     * 思路与算法
     *
     * 同样地，我们可以使用两个指针分别指向位置 000 和 n−1n-1n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array/solution/you-xu-shu-zu-de-ping-fang-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @return
     */
    public int[] sortedSquares3(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
            if (A[i] * A[i] > A[j] * A[j]) {
                ans[pos] = A[i] * A[i];
                ++i;
            } else {
                ans[pos] = A[j] * A[j];
                --j;
            }
            --pos;
        }
        return ans;
    }
}
