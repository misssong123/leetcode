package com.meng.origin;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class GetRow {
    /**
     *
     * @param rowIndex
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了83.95% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了88.61% 的用户
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i <= rowIndex ; i++){
            List<Integer> pre = new ArrayList<>();
            for(int j = 0 ; j <= i ; j++){
                if (j == 0 || j == i){
                    pre.add(1);
                }else {
                    pre.add(result.get(j-1)+result.get(j));
                }
            }
            result = pre;
        }
        return result;
    }
    /**
     * 能否只用一个数组呢？
     *
     * 递推式 Cni=Cn−1i+Cn−1i−1\mathcal{C}_n^i=\mathcal{C}_{n-1}^i+\mathcal{C}_{n-1}^{i-1}Cni​=Cn−1i​+Cn−1i−1​ 表明，当前行第 iii 项的计算只与上一行第 i−1i-1i−1 项及第 iii 项有关。因此我们可以倒着计算当前行，这样计算到第 iii 项时，第 i−1i-1i−1 项仍然是上一行的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * 执行用时：1 ms, 在所有 Java 提交中击败了83.95% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了85.45% 的用户
     */
    public List<Integer> getRow1(int rowIndex) {
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
     *
     * 由组合数公式 Cnm=n!m!(n−m)!\mathcal{C}_n^m=\dfrac{n!}{m!(n-m)!}Cnm​=m!(n−m)!n!​，可以得到同一行的相邻组合数的关系
     *
     * Cnm=Cnm−1×n−m+1m\mathcal{C}_n^m= \mathcal{C}_n^{m-1} \times \dfrac{n-m+1}{m} Cnm​=Cnm−1​×mn−m+1​
     *
     * 由于 Cn0=1\mathcal{C}_n^0=1Cn0​=1，利用上述公式我们可以在线性时间计算出第 nnn 行的所有组合数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/yang-hui-san-jiao-ii-by-leetcode-solutio-shuk/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了19.74% 的用户
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }
}
