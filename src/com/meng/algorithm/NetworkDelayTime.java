package com.meng.algorithm;

import java.util.*;

/**
 * 743. 网络延迟时间
 *
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 *
 * 示例 2：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 *
 * 示例 3：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *
 *
 *
 * 提示：
 *
 *     1 <= k <= n <= 100
 *     1 <= times.length <= 6000
 *     times[i].length == 3
 *     1 <= ui, vi <= n
 *     ui != vi
 *     0 <= wi <= 100
 *     所有 (ui, vi) 对都 互不相同（即，不含重复边）
 */
public class NetworkDelayTime {
    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了86.32% 的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了58.07% 的用户
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int F = Integer.MAX_VALUE / 2;
        int [] dist = new int[n];
        Arrays.fill(dist,F);
        dist[k-1] = 0;
       int[][] nums = new int[n][n];
       for(int i = 0 ; i < n ; i++){
           Arrays.fill(nums[i],F);
       }
       for(int i = 0 ; i < times.length ; i++){
           int[] time = times[i];
           nums[time[0]-1][time[1]-1] = time[2];
       }
       Set<Integer> cache = new HashSet<>();
       for(int i = 0 ; i < n ; i++){
           int x = -1;
           for(int j = 0 ; j < n ; j++){
               if (!cache.contains(j) && (x == -1 || dist[j] < dist[x])){
                   x = j;
               }
           }
           cache.add(x);
           for(int j = 0 ; j < n ; j++){
               dist[j] = Math.min(dist[j],dist[x]+nums[x][j]);
           }
       }
        int res = Arrays.stream(dist).max().getAsInt();
        return res == F ? -1 : res;
    }

    /**
     * 方法一：Dijkstra\text{Dijkstra}Dijkstra 算法
     *
     * 根据题意，从节点 kkk 发出的信号，到达节点 xxx 的时间就是节点 kkk 到节点 xxx 的最短路的长度。因此我们需要求出节点 kkk 到其余所有点的最短路，其中的最大值就是答案。若存在从 kkk 出发无法到达的点，则返回 −1-1−1。
     *
     * 下面的代码将节点编号减小了 111，从而使节点编号位于 [0,n−1][0,n-1][0,n−1] 范围。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/network-delay-time/solution/wang-luo-yan-chi-shi-jian-by-leetcode-so-6phc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param times
     * @param n
     * @param k
     * @return
     * 执行用时：3 ms, 在所有 Java 提交中击败了97.16% 的用户
     * 内存消耗：42.1 MB, 在所有 Java 提交中击败了48.03% 的用户
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        System.out.println("原始数据:");
        Arrays.stream(g).forEach(e -> System.out.println(Arrays.toString(e)));
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }
        System.out.println("已有边长:");
        Arrays.stream(g).forEach(e -> System.out.println(Arrays.toString(e)));
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        System.out.println("初始距离:"+Arrays.toString(dist));
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int x = -1;
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            System.out.println("节点("+i+")后的变化情况");
            System.out.println(x);
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
            System.out.println("初始距离:"+Arrays.toString(dist));
            System.out.println("初始距离:"+Arrays.toString(used));
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    /**
     *使用一个小根堆来寻找「未确定节点」中与起点距离最近的点。
     * @param times
     * @param n
     * @param k
     * @return
     * 执行用时：10 ms, 在所有 Java 提交中击败了81.08% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了63.02% 的用户
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<int[]>();
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x].add(new int[]{y, t[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int time = p[0], x = p[1];
            if (dist[x] < time) {
                continue;
            }
            for (int[] e : g[x]) {
                int y = e[0], d = dist[x] + e[1];
                if (d < dist[y]) {
                    dist[y] = d;
                    pq.offer(new int[]{d, y});
                }
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        int[][] times = new int[5][3];
        times[0] = new int[]{2,1,10};
        times[1] = new int[]{2,3,1};
        times[2] = new int[]{1,4,2};
        times[3] = new int[]{4,1,3};
        times[4] = new int[]{3,4,3};
        NetworkDelayTime demo = new NetworkDelayTime();
        System.out.println(demo.networkDelayTime1(times,4,2));
        System.out.println("-----------------------------------------------");
        System.out.println(demo.networkDelayTime(times,4,2));
    }
}
