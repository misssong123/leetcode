package com.meng.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * 难度
 * 中等
 *
 * 174
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 *
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 *
 * 输入：points = [[1,1]]
 * 输出：0
 *
 *
 * 提示：
 *
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * 所有点都 互不相同
 */
public class NumberOfBoomerangs {
    /**
     * 暴力遍历
     * @param points
     * @return
     * 执行用时：
     * 155 ms
     * , 在所有 Java 提交中击败了
     * 44.56%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 80.25%
     * 的用户
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        Map<Integer,Integer> map = null;
        for(int[] out : points){
            map = new HashMap<>();
            for(int[] inner : points){
                int disX = (out[0] - inner[0]) * (out[0] - inner[0]);
                int disY = (out[1] - inner[1]) * (out[1] - inner[1]);
                int dis = disX + disY;
                map.put(dis,map.getOrDefault(dis,0)+1);
            }
            for(int num : map.values()){
                res += num * (num - 1);
            }
        }
        return res;
    }

    /**
     * 方法一：枚举 + 哈希表
     *
     * 题目所描述的回旋镖可以视作一个
     * V
     * V 型的折线。我们可以枚举每个
     * points
     * [
     * i
     * ]
     * points[i]，将其当作
     * V
     * V 型的拐点。设
     * points
     * points 中有
     * m
     * m 个点到
     * points
     * [
     * i
     * ]
     * points[i] 的距离均相等，我们需要从这
     * m
     * m 点中选出
     * 2
     * 2 个点当作回旋镖的
     * 2
     * 2 个端点，由于题目要求考虑元组的顺序，因此方案数即为在
     * m
     * m 个元素中选出
     * 2
     * 2 个不同元素的排列数，即：
     *
     * A
     * m
     * 2
     * =
     * m
     * ⋅
     * (
     * m
     * −
     * 1
     * )
     * A
     * m
     * 2
     * ​
     *  =m⋅(m−1)
     *
     * 据此，我们可以遍历
     * points
     * points，计算并统计所有点到
     * points
     * [
     * i
     * ]
     * points[i] 的距离，将每个距离的出现次数记录在哈希表中，然后遍历哈希表，并用上述公式计算并累加回旋镖的个数。
     *
     * 在代码实现时，我们可以直接保存距离的平方，避免复杂的开方运算。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-boomerangs/solution/hui-xuan-biao-de-shu-liang-by-leetcode-s-lft5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * @param points
     * @return
     * 执行用时：
     * 146 ms
     * , 在所有 Java 提交中击败了
     * 56.71%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 53.42%
     * 的用户
     */
    public int numberOfBoomerangs1(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }
        return ans;
    }
}
