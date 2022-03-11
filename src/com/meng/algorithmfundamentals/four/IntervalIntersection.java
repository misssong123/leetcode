package com.meng.algorithmfundamentals.four;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. 区间列表的交集
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
 *
 * 返回这 两个区间列表的交集 。
 *
 * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
 *
 * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * 示例 2：
 *
 * 输入：firstList = [[1,3],[5,9]], secondList = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：firstList = [], secondList = [[4,8],[10,12]]
 * 输出：[]
 * 示例 4：
 *
 * 输入：firstList = [[1,7]], secondList = [[3,10]]
 * 输出：[[3,7]]
 *
 *
 * 提示：
 *
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 109
 * endi < starti+1
 * 0 <= startj < endj <= 109
 * endj < startj+1
 */
public class IntervalIntersection {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 31.08%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 45.36%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int a = firstList.length;
        int b = secondList.length;
        int i = 0 , j = 0;
        while (i<a && j < b){
            int min = Math.max(firstList[i][0],secondList[j][0]);
            int max = Math.min(firstList[i][1],secondList[j][1]);
            if (min <= max){
                res.add(new int[]{min,max});
            }
            if (firstList[i][1]>secondList[j][1]){
                j++;
            }else {
                i++;
            }
        }
        int[][] r = new int[res.size()][2];
        i = 0 ;
        for(int[] t : res){
            r[i++] = t;
        }
        return r;
    }

    public static void main(String[] args) {
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}}, secondList = {{1,5},{8,12},{15,24},{25,26}};
        IntervalIntersection demo = new IntervalIntersection();
        System.out.println(demo.intervalIntersection(firstList,secondList));
    }
    /**
     * 方法：归并区间
     * 思路
     *
     * 我们称 b 为区间 [a, b] 的末端点。
     *
     * 在两个数组给定的所有区间中，假设拥有最小末端点的区间是 A[0]。（为了不失一般性，该区间出现在数组 A 中)
     *
     * 然后，在数组 B 的区间中， A[0] 只可能与数组 B 中的至多一个区间相交。（如果 B 中存在两个区间均与 A[0] 相交，那么它们将共同包含 A[0] 的末端点，但是 B 中的区间应该是不相交的，所以存在矛盾）
     *
     * 算法
     *
     * 如果 A[0] 拥有最小的末端点，那么它只可能与 B[0] 相交。然后我们就可以删除区间 A[0]，因为它不能与其他任何区间再相交了。
     *
     * 相似的，如果 B[0] 拥有最小的末端点，那么它只可能与区间 A[0] 相交，然后我们就可以将 B[0] 删除，因为它无法再与其他区间相交了。
     *
     * 我们用两个指针 i 与 j 来模拟完成删除 A[0] 或 B[0] 的操作。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/interval-list-intersections/solution/qu-jian-lie-biao-de-jiao-ji-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param B
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 94.62%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 32.14%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     */
    public int[][] intervalIntersection1(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

}
