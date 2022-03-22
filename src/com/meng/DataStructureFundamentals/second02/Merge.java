package com.meng.DataStructureFundamentals.second02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class Merge {
    /**
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 94.75%
     * 的用户
     * 内存消耗：
     * 45.8 MB
     * , 在所有 Java 提交中击败了
     * 57.40%
     * 的用户
     * 通过测试用例：
     * 169 / 169
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0){
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> cache = new ArrayList<>();
        cache.add(intervals[0]);
        int size = intervals.length;
        for(int i = 1 ; i < size ; i++){
            int[] temp = intervals[i];
            int[] ints = cache.get(cache.size() - 1);
            if (ints[1] < temp[0]){
                cache.add(temp);
            }else {
                ints[1] = Math.max(ints[1],temp[1]);
            }
        }
        int[][] res = new int[cache.size()][2];
        for(int i = 0 ; i < cache.size() ; i++){
            res[i] = cache.get(i);
        }
        return res;
    }

    /**
     * 方法一：排序
     * 思路
     *
     * 如果我们按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的。如下图所示，标记为蓝色、黄色和绿色的区间分别可以合并成一个大区间，它们在排完序的列表中是连续的：
     *
     *
     *
     * 算法
     *
     * 我们用数组 merged 存储最终的答案。
     *
     * 首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
     *
     * 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
     *
     * 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
     *
     * 正确性证明
     *
     * 上述算法的正确性可以用反证法来证明：在排完序后的数组中，两个本应合并的区间没能被合并，那么说明存在这样的三元组 (i, j, k)(i,j,k) 以及数组中的三个区间 a[i], a[j], a[k]a[i],a[j],a[k] 满足 i < j < ki<j<k 并且 (a[i], a[k])(a[i],a[k]) 可以合并，但 (a[i], a[j])(a[i],a[j]) 和 (a[j], a[k])(a[j],a[k]) 不能合并。这说明它们满足下面的不等式：
     *
     * a[i].end < a[j].start \quad (a[i] \text{ 和 } a[j] \text{ 不能合并}) \\ a[j].end < a[k].start \quad (a[j] \text{ 和 } a[k] \text{ 不能合并}) \\ a[i].end \geq a[k].start \quad (a[i] \text{ 和 } a[k] \text{ 可以合并}) \\
     * a[i].end<a[j].start(a[i] 和 a[j] 不能合并)
     * a[j].end<a[k].start(a[j] 和 a[k] 不能合并)
     * a[i].end≥a[k].start(a[i] 和 a[k] 可以合并)
     *
     * 我们联立这些不等式（注意还有一个显然的不等式 a[j].start \leq a[j].enda[j].start≤a[j].end），可以得到：
     *
     * a[i].end < a[j].start \leq a[j].end < a[k].start
     * a[i].end<a[j].start≤a[j].end<a[k].start
     *
     * 产生了矛盾！这说明假设是不成立的。因此，所有能够合并的区间都必然是连续的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param intervals
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 94.75%
     * 的用户
     * 内存消耗：
     * 46.2 MB
     * , 在所有 Java 提交中击败了
     * 31.56%
     * 的用户
     * 通过测试用例：
     * 169 / 169
     */
    public int[][] merge1(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}
