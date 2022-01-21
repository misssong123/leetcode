package com.meng.algorithm;

/**
 * 2022. 将一维数组转变成二维数组
 * 给你一个下标从 0 开始的一维整数数组 original 和两个整数 m 和  n 。你需要使用 original 中 所有 元素创建一个 m 行 n 列的二维数组。
 *
 * original 中下标从 0 到 n - 1 （都 包含 ）的元素构成二维数组的第一行，下标从 n 到 2 * n - 1 （都 包含 ）的元素构成二维数组的第二行，依此类推。
 *
 * 请你根据上述过程返回一个 m x n 的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：original = [1,2,3,4], m = 2, n = 2
 * 输出：[[1,2],[3,4]]
 * 解释：
 * 构造出的二维数组应该包含 2 行 2 列。
 * original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
 * original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
 * 示例 2：
 *
 * 输入：original = [1,2,3], m = 1, n = 3
 * 输出：[[1,2,3]]
 * 解释：
 * 构造出的二维数组应该包含 1 行 3 列。
 * 将 original 中所有三个元素放入第一行中，构成要求的二维数组。
 * 示例 3：
 *
 * 输入：original = [1,2], m = 1, n = 1
 * 输出：[]
 * 解释：
 * original 中有 2 个元素。
 * 无法将 2 个元素放入到一个 1x1 的二维数组中，所以返回一个空的二维数组。
 * 示例 4：
 *
 * 输入：original = [3], m = 1, n = 2
 * 输出：[]
 * 解释：
 * original 中只有 1 个元素。
 * 无法将 1 个元素放满一个 1x2 的二维数组，所以返回一个空的二维数组。
 *
 *
 * 提示：
 *
 * 1 <= original.length <= 5 * 104
 * 1 <= original[i] <= 105
 * 1 <= m, n <= 4 * 104
 */
public class Construct2DArray {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 54.52%
     * 的用户
     * 内存消耗：
     * 48.4 MB
     * , 在所有 Java 提交中击败了
     * 77.22%
     * 的用户
     * 通过测试用例：
     * 107 / 107
     * @param original
     * @param m
     * @param n
     * @return
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        int len = original.length;
        if (len < m * n){
            return new int[0][0];
        }
        int[][] res = new int[m][n];
        for(int i = 0 ; i < m*n ; i++){
            res[i/n][i%n] = original[i];
        }
        return res;
    }

    /**
     * 方法一：模拟
     * 设 \textit{original}original 的长度为 kk，根据题意，如果 k\ne mnk
     * 
     * ​
     *  =mn 则无法构成二维数组，此时返回空数组。否则我们可以遍历 \textit{original}original，每 nn 个元素创建一个一维数组，放入二维数组中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/convert-1d-array-into-2d-array/solution/jiang-yi-wei-shu-zu-zhuan-bian-cheng-er-zt47o/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param original
     * @param m
     * @param n
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 54.52%
     * 的用户
     * 内存消耗：
     * 48.2 MB
     * , 在所有 Java 提交中击败了
     * 91.92%
     * 的用户
     * 通过测试用例：
     * 107 / 107
     */
    public int[][] construct2DArray1(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][];
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < original.length; i += n) {
            System.arraycopy(original, i, ans[i / n], 0, n);
        }
        return ans;
    }

}
