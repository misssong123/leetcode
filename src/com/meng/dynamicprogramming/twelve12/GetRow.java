package com.meng.dynamicprogramming.twelve12;

import java.util.ArrayList;
import java.util.List;

/**
 *119. 杨辉三角 II
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 *
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 *
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *
 *
 * 提示:
 *
 * 0 <= rowIndex <= 33
 *
 *
 * 进阶：
 *
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 */
public class GetRow {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.12%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 40.25%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> cur = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return pre;
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.8 MB
     * , 在所有 Java 提交中击败了
     * 84.13%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     * @param rowIndex
     * @return
     * 方法二：线性递推
     * 由组合数公式 \mathcal{C}_n^m=\dfrac{n!}{m!(n-m)!}C
     * n
     * m
     * ​
     *  =
     * m!(n−m)!
     * n!
     * ​
     *  ，可以得到同一行的相邻组合数的关系
     *
     * \mathcal{C}_n^m= \mathcal{C}_n^{m-1} \times \dfrac{n-m+1}{m}
     * C
     * n
     * m
     * ​
     *  =C
     * n
     * m−1
     * ​
     *  ×
     * m
     * n−m+1
     * ​
     *
     *
     * 由于 \mathcal{C}_n^0=1C
     * n
     * 0
     * ​
     *  =1，利用上述公式我们可以在线性时间计算出第 nn 行的所有组合数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

}
