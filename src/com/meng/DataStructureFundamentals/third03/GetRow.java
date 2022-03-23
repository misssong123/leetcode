package com.meng.DataStructureFundamentals.third03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 119. 杨辉三角 II
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
 */
public class GetRow {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 18.48%
     * 的用户
     * 内存消耗：
     * 39.3 MB
     * , 在所有 Java 提交中击败了
     * 8.64%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0){
            return Arrays.asList(1);
        }
        if (rowIndex == 1){
            return Arrays.asList(1,1);
        }
        List<Integer> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>(Arrays.asList(1,1));
        for(int i = 2 ; i <= rowIndex ; i++){
            list.add(1);
            for(int j = 1 ; j < i ; j++ ){
                list.add(temp.get(j-1) + temp.get(j));
            }
            list.add(1);
            temp.removeAll(temp);
            temp.addAll(list);
            list.removeAll(list);
        }
        return temp;
    }

    public static void main(String[] args) {
        GetRow demo = new GetRow();
        System.out.println(demo.getRow(0));
        System.out.println(demo.getRow(1));
        System.out.println(demo.getRow(2));
        System.out.println(demo.getRow(3));
        System.out.println(demo.getRow(4));
        System.out.println(demo.getRow(5));
        System.out.println(demo.getRow(6));
        System.out.println(demo.getRow(7));
        System.out.println(demo.getRow(8));
        System.out.println(demo.getRow(9));
        System.out.println(demo.getRow(10));
        System.out.println(demo.getRow(11));
        System.out.println(demo.getRow(12));

    }

    /**
     * 方法一：递推
     * 杨辉三角，是二项式系数在三角形中的一种几何排列。它是中国古代数学的杰出研究成果之一，它把二项式系数图形化，把组合数内在的一些代数性质直观地从图形中体现出来，是一种离散型的数与形的结合。
     *
     * 杨辉三角具有以下性质：
     *
     * 每行数字左右对称，由 11 开始逐渐变大再变小，并最终回到 11。
     *
     * 第 nn 行（从 00 开始编号）的数字有 n+1n+1 项，前 nn 行共有 \frac{n(n+1)}{2}
     * 2
     * n(n+1)
     * ​
     *   个数。
     *
     * 第 nn 行的第 mm 个数（从 00 开始编号）可表示为可以被表示为组合数 \mathcal{C}(n,m)C(n,m)，记作 \mathcal{C}_n^mC
     * n
     * m
     * ​
     *   或 \binom{n}{m}(
     * m
     * n
     * ​
     *  )，即为从 nn 个不同元素中取 mm 个元素的组合数。我们可以用公式来表示它：\mathcal{C}_n^m=\dfrac{n!}{m!(n-m)!}C
     * n
     * m
     * ​
     *  =
     * m!(n−m)!
     * n!
     * ​
     *
     *
     * 每个数字等于上一行的左右两个数字之和，可用此性质写出整个杨辉三角。即第 nn 行的第 ii 个数等于第 n-1n−1 行的第 i-1i−1 个数和第 ii 个数之和。这也是组合数的性质之一，即 \mathcal{C}_n^i=\mathcal{C}_{n-1}^i+\mathcal{C}_{n-1}^{i-1}C
     * n
     * i
     * ​
     *  =C
     * n−1
     * i
     * ​
     *  +C
     * n−1
     * i−1
     * ​
     *  。
     *
     * (a+b)^n(a+b)
     * n
     *   的展开式（二项式展开）中的各项系数依次对应杨辉三角的第 nn 行中的每一项。
     *
     * 依据性质 44，我们可以一行一行地计算杨辉三角。每当我们计算出第 ii 行的值，我们就可以在线性时间复杂度内计算出第 i+1i+1 行的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param rowIndex
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.86%
     * 的用户
     * 内存消耗：
     * 39.3 MB
     * , 在所有 Java 提交中击败了
     * 13.77%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> C = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(C.get(i - 1).get(j - 1) + C.get(i - 1).get(j));
                }
            }
            C.add(row);
        }
        return C.get(rowIndex);
    }

    /**
     * 注意到对第 i+1i+1 行的计算仅用到了第 ii 行的数据，因此可以使用滚动数组的思想优化空间复杂度。
     * @param rowIndex
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.86%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 44.94%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    public List<Integer> getRow2(int rowIndex) {
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
     *进一步优化
     *
     * 能否只用一个数组呢？
     *
     * 递推式 \mathcal{C}_n^i=\mathcal{C}_{n-1}^i+\mathcal{C}_{n-1}^{i-1}C
     * n
     * i
     * ​
     *  =C
     * n−1
     * i
     * ​
     *  +C
     * n−1
     * i−1
     * ​
     *   表明，当前行第 ii 项的计算只与上一行第 i-1i−1 项及第 ii 项有关。因此我们可以倒着计算当前行，这样计算到第 ii 项时，第 i-1i−1 项仍然是上一行的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param rowIndex
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 76.86%
     * 的用户
     * 内存消耗：
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 35.10%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    /**
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
     * @param rowIndex
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 58.26%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    public List<Integer> getRow4(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }


}
