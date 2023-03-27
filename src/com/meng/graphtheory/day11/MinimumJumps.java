package com.meng.graphtheory.day11;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1654. 到家的最少跳跃次数(中等)
 * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
 *
 * 跳蚤跳跃的规则如下：
 *
 * 它可以 往前 跳恰好 a 个位置（即往右跳）。
 * 它可以 往后 跳恰好 b 个位置（即往左跳）。
 * 它不能 连续 往后跳 2 次。
 * 它不能跳到任何 forbidden 数组中的位置。
 * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
 *
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 x 的可行方案，请你返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * 输出：3
 * 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
 * 示例 2：
 *
 * 输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * 输出：-1
 * 示例 3：
 *
 * 输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * 输出：2
 * 解释：往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
 *
 *
 * 提示：
 *
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * forbidden 中所有位置互不相同。
 * 位置 x 不在 forbidden 中。
 */
public class MinimumJumps {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        return -1;
    }


    /**
     * 执行用时：
     * 21 ms
     * , 在所有 Java 提交中击败了
     * 54.25%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 31.96%
     * 的用户
     * 通过测试用例：
     * 95 / 95
     * @param forbidden
     * @param a
     * @param b
     * @param x
     * @return
     */
    public int minimumJumps1(int[] forbidden, int a, int b, int x) {
        forbi = new HashSet<>();
        for (int num : forbidden) forbi.add(num);
        dis = new int[maxn];
        vis = new boolean[maxn];
        for (int i = 0; i < maxn; ++i) dis[i] = Integer.MAX_VALUE;
        spfa(0, x, a, b);
        return dis[x] == Integer.MAX_VALUE ? -1 : dis[x];
    }
    private Set<Integer> forbi;
    private int maxn = 6010;
    private int[] dis;
    private boolean[] vis;

    public void spfa(int start, int end, int a, int b){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0}); // 0表示上一步是前进，1表示上一步是后退
        dis[start] = 0;
        vis[0] = true;
        while (!q.isEmpty()){
            int[] cur = q.poll();
            if (cur[0] == end) return;
            if (cur[0] + a < maxn && !forbi.contains(cur[0] + a) && !vis[cur[0] + a]){
                // 松弛
                if (dis[cur[0]] + 1 < dis[cur[0] + a]){
                    dis[cur[0] + a] = dis[cur[0]] + 1;
                }
                // 前进到达的节点标记
                vis[cur[0] + a] = true;
                q.offer(new int[]{cur[0] + a, 0});
            }
            // 上一步后退过了不允许继续后退
            if (cur[1] == 0 && cur[0] - b >= 0 && !forbi.contains(cur[0] - b) && !vis[cur[0] - b]){
                // 松弛
                if (dis[cur[0]] + 1 < dis[cur[0] - b]){
                    dis[cur[0] - b] = dis[cur[0]] + 1;
                }
                q.offer(new int[]{cur[0] - b, 1});
            }
        }
    }

}
