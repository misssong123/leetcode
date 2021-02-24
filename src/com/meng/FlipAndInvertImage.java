package com.meng;

/**
 * 832. 翻转图像
 *
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * 示例 2：
 *
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length = A[0].length <= 20
 *     0 <= A[i][j] <= 1
 */
public class FlipAndInvertImage {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了73.54% 的用户
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int height = A.length;
        int len = A[0].length;
        int mid = (len+1)/2;
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < mid ; j++){
                int temp = A[i][j] ;
                A[i][j] = A[i][len-j-1] ^ 1 ;
                A[i][len-j-1]= temp ^ 1 ;
            }
        }
        return A;
    }
    /**
     * 方法一：模拟优化 + 双指针
     *
     * 最直观的做法是首先对矩阵 AAA 的每一行进行水平翻转操作，然后对矩阵中的每个元素进行反转操作。该做法需要遍历矩阵两次。
     *
     * 是否可以只遍历矩阵一次就完成上述操作？答案是肯定的。
     *
     * 假设矩阵的行数和列数都是 nnn，考虑列下标 left\textit{left}left 和 right\textit{right}right，其中 left<right\textit{left}<\textit{right}left<right 且 left+right=n−1\textit{left}+\textit{right}=n-1left+right=n−1，当 0≤i<n0 \le i<n0≤i<n 时，对第 iii 行进行水平翻转之后，A[i][left]A[i][\textit{left}]A[i][left] 和 A[i][right]A[i][\textit{right}]A[i][right] 的元素值会互换，进行反转之后，A[i][left]A[i][\textit{left}]A[i][left] 和 A[i][right]A[i][\textit{right}]A[i][right] 的元素值都会改变。
     *
     * 具体而言，考虑以下四种情况。
     *
     *     情况一：A[i][left]=0,A[i][right]=0A[i][\textit{left}]=0,A[i][\textit{right}]=0A[i][left]=0,A[i][right]=0。对第 iii 行进行水平翻转之后，A[i][left]=0,A[i][right]=0A[i][\textit{left}]=0,A[i][\textit{right}]=0A[i][left]=0,A[i][right]=0。进行反转之后，A[i][left]=1,A[i][right]=1A[i][\textit{left}]=1,A[i][\textit{right}]=1A[i][left]=1,A[i][right]=1。
     *
     *     情况二：A[i][left]=1,A[i][right]=1A[i][\textit{left}]=1,A[i][\textit{right}]=1A[i][left]=1,A[i][right]=1。对第 iii 行进行水平翻转之后，A[i][left]=1,A[i][right]=1A[i][\textit{left}]=1,A[i][\textit{right}]=1A[i][left]=1,A[i][right]=1。进行反转之后，A[i][left]=0,A[i][right]=0A[i][\textit{left}]=0,A[i][\textit{right}]=0A[i][left]=0,A[i][right]=0。
     *
     *     情况三：A[i][left]=0,A[i][right]=1A[i][\textit{left}]=0,A[i][\textit{right}]=1A[i][left]=0,A[i][right]=1。对第 iii 行进行水平翻转之后，A[i][left]=1,A[i][right]=0A[i][\textit{left}]=1,A[i][\textit{right}]=0A[i][left]=1,A[i][right]=0。进行反转之后，A[i][left]=0,A[i][right]=1A[i][\textit{left}]=0,A[i][\textit{right}]=1A[i][left]=0,A[i][right]=1。
     *
     *     情况四：A[i][left]=1,A[i][right]=0A[i][\textit{left}]=1,A[i][\textit{right}]=0A[i][left]=1,A[i][right]=0。对第 iii 行进行水平翻转之后，A[i][left]=0,A[i][right]=1A[i][\textit{left}]=0,A[i][\textit{right}]=1A[i][left]=0,A[i][right]=1。进行反转之后，A[i][left]=1,A[i][right]=0A[i][\textit{left}]=1,A[i][\textit{right}]=0A[i][left]=1,A[i][right]=0。
     *
     * 情况一和情况二是 A[i][left]=A[i][right]A[i][\textit{left}]=A[i][\textit{right}]A[i][left]=A[i][right] 的情况。在进行水平翻转和反转之后，A[i][left]A[i][\textit{left}]A[i][left] 和 A[i][right]A[i][\textit{right}]A[i][right] 的元素值都发生了改变，即元素值被反转。
     *
     * 情况三和情况四是 A[i][left]≠A[i][right]A[i][\textit{left}]\ne A[i][\textit{right}]A[i][left]​=A[i][right] 的情况。在进行水平翻转和反转之后，A[i][left]A[i][\textit{left}]A[i][left] 和 A[i][right]A[i][\textit{right}]A[i][right] 的元素值都发生了两次改变，恢复原状。
     *
     * 因此，可以遍历矩阵一次即完成水平翻转和反转。
     *
     * 遍历矩阵的每一行。对于矩阵的第 iii 行，初始化 left=0\textit{left}=0left=0 和 right=n−1\textit{right}=n-1right=n−1，进行如下操作：
     *
     *     当 left<right\textit{left}<\textit{right}left<right 时，判断 A[i][left]A[i][\textit{left}]A[i][left] 和 A[i][right]A[i][\textit{right}]A[i][right] 是否相等，如果相等则对 A[i][left]A[i][\textit{left}]A[i][left] 和 A[i][right]A[i][\textit{right}]A[i][right] 的值进行反转，如果不相等则不进行任何操作；
     *
     *     将 left\textit{left}left 的值加 111，将 right\textit{right}right 的值减 111，重复上述操作，直到 left≥right\textit{left} \ge \textit{right}left≥right；
     *
     *     如果 nnn 是奇数，则上述操作结束时，left\textit{left}left 和 right\textit{right}right 的值相等，都指向第 iii 行的中间元素，此时需要对中间元素的值进行反转。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/flipping-an-image/solution/fan-zhuan-tu-xiang-by-leetcode-solution-yljd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了24.07% 的用户
     */
    public int[][] flipAndInvertImage1(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right) {
                A[i][left] ^= 1;
            }
        }
        return A;
    }
}
