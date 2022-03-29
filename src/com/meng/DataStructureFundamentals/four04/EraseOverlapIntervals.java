package com.meng.DataStructureFundamentals.four04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 *
 * 提示:
 *
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class EraseOverlapIntervals {
    /**
     *执行用时：
     * 99 ms
     * , 在所有 Java 提交中击败了
     * 5.24%
     * 的用户
     * 内存消耗：
     * 97.9 MB
     * , 在所有 Java 提交中击败了
     * 20.70%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 1){
            return 0;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        for(int[] num : intervals){
            queue.add(num);
        }
        int res = 0;
        int[] pre = queue.poll();
        while (!queue.isEmpty()){
           int[] cur = queue.poll();
            if (cur[0] < pre[1]){
               res++;
               continue;
           }
           pre = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        //{{1,100},{1,11},{2,12},{11,22}}
        //[[0,2],[1,3],[2,4],[3,5],[4,6]]
        int[][] intervals = {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
        EraseOverlapIntervals demo = new EraseOverlapIntervals();
        System.out.println(demo.eraseOverlapIntervals(intervals));
    }

    /**
     * 方法一：动态规划
     * 思路与算法
     *
     * 题目的要求等价于「选出最多数量的区间，使得它们互不重叠」。由于选出的区间互不重叠，因此我们可以将它们按照端点从小到大的顺序进行排序，并且无论我们按照左端点还是右端点进行排序，得到的结果都是唯一的。
     *
     * 这样一来，我们可以先将所有的 nn 个区间按照左端点（或者右端点）从小到大进行排序，随后使用动态规划的方法求出区间数量的最大值。设排完序后这 nn 个区间的左右端点分别为 l_0, \cdots, l_{n-1}l
     * 0
     * ​
     *  ,⋯,l
     * n−1
     * ​
     *   以及 r_0, \cdots, r_{n-1}r
     * 0
     * ​
     *  ,⋯,r
     * n−1
     * ​
     *  ，那么我们令 f_if
     * i
     * ​
     *   表示「以区间 ii 为最后一个区间，可以选出的区间数量的最大值」，状态转移方程即为：
     *
     * f_i = \max_{j < i ~\wedge~ r_j \leq l_i} \{ f_j \} + 1
     * f
     * i
     * ​
     *  =
     * j<i ∧ r
     * j
     * ​
     *  ≤l
     * i
     * ​
     *
     * max
     * ​
     *  {f
     * j
     * ​
     *  }+1
     *
     * 即我们枚举倒数第二个区间的编号 jj，满足 j < ij<i，并且第 jj 个区间必须要与第 ii 个区间不重叠。由于我们已经按照左端点进行升序排序了，因此只要第 jj 个区间的右端点 r_jr
     * j
     * ​
     *   没有越过第 ii 个区间的左端点 l_il
     * i
     * ​
     *  ，即 r_j \leq l_ir
     * j
     * ​
     *  ≤l
     * i
     * ​
     *  ，那么第 jj 个区间就与第 ii 个区间不重叠。我们在所有满足要求的 jj 中，选择 f_jf
     * j
     * ​
     *   最大的那一个进行状态转移，如果找不到满足要求的区间，那么状态转移方程中 \maxmax 这一项就为 00，f_if
     * i
     * ​
     *   就为 11。
     *
     * 最终的答案即为所有 f_if
     * i
     * ​
     *   中的最大值。
     *
     * 代码
     *
     * 由于方法一的时间复杂度较高，因此在下面的 \texttt{Python}Python 代码中，我们尽量使用列表推导优化常数，使得其可以在时间限制内通过所有测试数据。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals/solution/wu-zhong-die-qu-jian-by-leetcode-solutio-cpsb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param intervals
     * @return
     * 超出时间限制
     */
    public int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }

    /**
     *方法二：贪心
     * 思路与算法
     *
     * 我们不妨想一想应该选择哪一个区间作为首个区间。
     *
     * 假设在某一种最优的选择方法中，[l_k, r_k][l
     * k
     * ​
     *  ,r
     * k
     * ​
     *  ] 是首个（即最左侧的）区间，那么它的左侧没有其它区间，右侧有若干个不重叠的区间。设想一下，如果此时存在一个区间 [l_j, r_j][l
     * j
     * ​
     *  ,r
     * j
     * ​
     *  ]，使得 r_j < r_kr
     * j
     * ​
     *  <r
     * k
     * ​
     *  ，即区间 jj 的右端点在区间 kk 的左侧，那么我们将区间 kk 替换为区间 jj，其与剩余右侧被选择的区间仍然是不重叠的。而当我们将区间 kk 替换为区间 jj 后，就得到了另一种最优的选择方法。
     *
     * 我们可以不断地寻找右端点在首个区间右端点左侧的新区间，将首个区间替换成该区间。那么当我们无法替换时，首个区间就是所有可以选择的区间中右端点最小的那个区间。因此我们将所有区间按照右端点从小到大进行排序，那么排完序之后的首个区间，就是我们选择的首个区间。
     *
     * 如果有多个区间的右端点都同样最小怎么办？由于我们选择的是首个区间，因此在左侧不会有其它的区间，那么左端点在何处是不重要的，我们只要任意选择一个右端点最小的区间即可。
     *
     * 当确定了首个区间之后，所有与首个区间不重合的区间就组成了一个规模更小的子问题。由于我们已经在初始时将所有区间按照右端点排好序了，因此对于这个子问题，我们无需再次进行排序，只要找出其中与首个区间不重合并且右端点最小的区间即可。用相同的方法，我们可以依次确定后续的所有区间。
     *
     * 在实际的代码编写中，我们对按照右端点排好序的区间进行遍历，并且实时维护上一个选择区间的右端点 \textit{right}right。如果当前遍历到的区间 [l_i, r_i][l
     * i
     * ​
     *  ,r
     * i
     * ​
     *  ] 与上一个区间不重合，即 l_i \geq \textit{right}l
     * i
     * ​
     *  ≥right，那么我们就可以贪心地选择这个区间，并将 \textit{right}right 更新为 r_ir
     * i
     * ​
     *  。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals/solution/wu-zhong-die-qu-jian-by-leetcode-solutio-cpsb/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param intervals
     * @return
     * 执行用时：
     * 51 ms
     * , 在所有 Java 提交中击败了
     * 50.16%
     * 的用户
     * 内存消耗：
     * 97.7 MB
     * , 在所有 Java 提交中击败了
     * 34.63%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });

        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }


}
