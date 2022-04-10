package com.meng.DataStructureFundamentals.twentyone21;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. 最接近原点的 K 个点
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点。
 *
 * 这里，平面上两点之间的距离是 欧几里德距离（ √(x1 - x2)2 + (y1 - y2)2 ）。
 *
 * 你可以按 任何顺序 返回答案。除了点坐标的顺序之外，答案 确保 是 唯一 的。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[1,3],[-2,2]], k = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 *
 * 输入：points = [[3,3],[5,-1],[-2,4]], k = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 *
 * 提示：
 *
 * 1 <= k <= points.length <= 104
 * -104 < xi, yi < 104
 */
public class KClosest {
    /**
     * 执行用时：
     * 21 ms
     * , 在所有 Java 提交中击败了
     * 86.43%
     * 的用户
     * 内存消耗：
     * 49.1 MB
     * , 在所有 Java 提交中击败了
     * 72.18%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, k);
    }

    /**
     * 方法二：堆
     * 思路和算法
     *
     * 我们可以使用一个大根堆实时维护前 kk 个最小的距离平方。
     *
     * 首先我们将前 kk 个点的编号（为了方便最后直接得到答案）以及对应的距离平方放入大根堆中，随后从第 k+1k+1 个点开始遍历：如果当前点的距离平方比堆顶的点的距离平方要小，就把堆顶的点弹出，再插入当前的点。当遍历完成后，所有在大根堆中的点就是前 kk 个距离最小的点。
     *
     * 不同的语言提供的堆的默认情况不一定相同。在 C++ 语言中，堆（即优先队列）为大根堆，但在 Python 语言中，堆为小根堆，因此我们需要在小根堆中存储（以及比较）距离平方的相反数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/zui-jie-jin-yuan-dian-de-k-ge-dian-by-leetcode-sol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param points
     * @param k
     * @return
     * 执行用时：
     * 28 ms
     * , 在所有 Java 提交中击败了
     * 55.20%
     * 的用户
     * 内存消耗：
     * 49.6 MB
     * , 在所有 Java 提交中击败了
     * 21.00%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     */
    public int[][] kClosest1(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] array1, int[] array2) {
                return array2[0] - array1[0];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        int n = points.length;
        for (int i = k; i < n; ++i) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; ++i) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }

}
