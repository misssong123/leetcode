package com.meng.weeklycompetition.week309;

import javafx.util.Pair;

import java.util.*;

/**
 * 2402. 会议室 III
 * 给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。
 *
 * 给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi] 表示一场会议将会在 半闭 时间区间 [starti, endi) 举办。所有 starti 的值 互不相同 。
 *
 * 会议将会按以下方式分配给会议室：
 *
 * 每场会议都会在未占用且编号 最小 的会议室举办。
 * 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同 。
 * 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
 * 返回举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。
 *
 * 半闭区间 [a, b) 是 a 和 b 之间的区间，包括 a 但 不包括 b 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * 输出：0
 * 解释：
 * - 在时间 0 ，两个会议室都未占用，第一场会议在会议室 0 举办。
 * - 在时间 1 ，只有会议室 1 未占用，第二场会议在会议室 1 举办。
 * - 在时间 2 ，两个会议室都被占用，第三场会议延期举办。
 * - 在时间 3 ，两个会议室都被占用，第四场会议延期举办。
 * - 在时间 5 ，会议室 1 的会议结束。第三场会议在会议室 1 举办，时间周期为 [5,10) 。
 * - 在时间 10 ，两个会议室的会议都结束。第四场会议在会议室 0 举办，时间周期为 [10,11) 。
 * 会议室 0 和会议室 1 都举办了 2 场会议，所以返回 0 。
 * 示例 2：
 *
 * 输入：n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * 输出：1
 * 解释：
 * - 在时间 1 ，所有三个会议室都未占用，第一场会议在会议室 0 举办。
 * - 在时间 2 ，会议室 1 和 2 未占用，第二场会议在会议室 1 举办。
 * - 在时间 3 ，只有会议室 2 未占用，第三场会议在会议室 2 举办。
 * - 在时间 4 ，所有三个会议室都被占用，第四场会议延期举办。
 * - 在时间 5 ，会议室 2 的会议结束。第四场会议在会议室 2 举办，时间周期为 [5,10) 。
 * - 在时间 6 ，所有三个会议室都被占用，第五场会议延期举办。
 * - 在时间 10 ，会议室 1 和 2 的会议结束。第五场会议在会议室 1 举办，时间周期为 [10,12) 。
 * 会议室 1 和会议室 2 都举办了 2 场会议，所以返回 1 。
 *
 *
 * 提示：
 *
 * 1 <= n <= 100
 * 1 <= meetings.length <= 105
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 105
 * starti 的所有值 互不相同
 */
public class MostBooked {
    //结果错误，未发现错误点
    public int mostBooked(int n, int[][] meetings) {
        if (n == 1){
            return 0;
        }
        int[] nums = new int[n];
        PriorityQueue<int[]> rooms =  new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        for(int i = 0 ; i < n ; i++){
            rooms.add(new int[]{i,0});
        }
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int len = meetings.length;
        for(int i = 0 ; i < len ; i++){
            int[] meet = meetings[i];
            int[] poll = findMeet(rooms,meet[0]);
            nums[poll[0]]++;
            if (poll[1] <= meet[0]){
                poll[1] = meet[1];
            }else {
                poll[1] = meet[1] - meet[0] + poll[1];
            }
            rooms.add(poll);
        }
        int max = Arrays.stream(nums).max().getAsInt();
        for(int i = 0 ; i < n ; i++){
            if (nums[i] == max){
                return i;
            }
        }
        return 0;
    }

    private int[] findMeet(PriorityQueue<int[]> rooms, int start) {
        List<int[]> list = new ArrayList<>();
        int[] ans = null;
        while (!rooms.isEmpty() && rooms.peek()[1] <= start){
            int[] temp = rooms.poll();
            if (ans == null){
                ans = temp;
            }else if (ans[0] > temp[0]){
                ans = temp;
            }
            list.add(temp);
        }
        for(int[] temp : list){
            if (temp != ans){
                rooms.add(temp);
            }
        }
        if (ans == null){
            ans = rooms.poll();
        }
        System.out.println(start+":"+Arrays.toString(ans));
        return ans;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] meetings =  {{0,10},{1,5},{2,7},{3,4}};
        MostBooked demo = new MostBooked();
        System.out.println(demo.mostBooked(n,meetings));
        //System.out.println("----------------------------");
        //System.out.println(demo.mostBooked1(n,meetings));
    }
    /**
     *执行用时：
     * 108 ms
     * , 在所有 Java 提交中击败了
     * 18.76%
     * 的用户
     * 内存消耗：
     * 97.2 MB
     * , 在所有 Java 提交中击败了
     * 73.24%
     * 的用户
     * 通过测试用例：
     * 81 / 81
     * @param n
     * @param meetings
     * @return
     */
    public int mostBooked1(int n, int[][] meetings) {
        int[] cnt = new int[n];
        PriorityQueue<Integer> idle = new PriorityQueue<Integer>();
        for (int i = 0; i < n; ++i) idle.offer(i);
        PriorityQueue<Pair<Long, Integer>> using = new PriorityQueue<Pair<Long, Integer>>((a, b) -> !Objects.equals(a.getKey(), b.getKey()) ? Long.compare(a.getKey(), b.getKey()) : Integer.compare(a.getValue(), b.getValue()));
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        for (int[] m : meetings) {
            long st = m[0], end = m[1];
            while (!using.isEmpty() && using.peek().getKey() <= st) {
                idle.offer(using.poll().getValue()); // 维护在 st 时刻空闲的会议室
            }
            int id;
            if (idle.isEmpty()) {
                Pair<Long, Integer> p = using.poll(); // 没有可用的会议室，那么弹出一个最早结束的会议室（若有多个同时结束的，会弹出下标最小的）
                end += p.getKey() - st; // 更新当前会议的结束时间
                id = p.getValue();
            } else{
                id = idle.poll();
            }
            ++cnt[id];
            using.offer(new Pair<>(end, id)); // 使用一个会议室
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[i] > cnt[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    /**
     *执行用时：
     * 62 ms
     * , 在所有 Java 提交中击败了
     * 85.16%
     * 的用户
     * 内存消耗：
     * 99.1 MB
     * , 在所有 Java 提交中击败了
     * 33.59%
     * 的用户
     * 通过测试用例：
     * 81 / 81
     */
    public int mostBooked2(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });
        Seg root = Seg.create(0, n - 1, 0);
        for (int i = 0; i < n; i++) {
            Seg.update(root, i, 0);
        }

        int[] ret = new int[n];
        for (int[] m: meetings) {
            Seg cur = Seg.query(root, m[0]);
            ret[cur.l]++;
            Seg.update(root, cur.l, Math.max(cur.minVal, (long)m[0]) + (m[1] - m[0]));
        }

        int ans = ret[0], idx = 0;
        for (int i = 1; i < n; i++) {
            if (ret[i] > ans) {
                ans = ret[i];
                idx = i;
            }
        }

        return idx;

    }
    // 线段树版（比较特别）
    static class Seg {
        int l, r, m;
        Seg left, right;
        long minVal;

        static Seg create(int l, int r, long v) {
            Seg seg = new Seg();
            seg.l = l;
            seg.r = r;
            seg.m = l + (r - l) / 2;
            seg.minVal = v;
            return seg;
        }

        static void update(Seg seg, int p, long v) {
            if (seg.l == seg.r) {
                seg.minVal = v;
                return;
            }

            if (seg.left == null) {
                seg.left = Seg.create(seg.l, seg.m, 0);
            }
            if (seg.right == null) {
                seg.right = Seg.create(seg.m + 1, seg.r, 0);
            }

            // push up
            int m = seg.m;
            if (p <= m) {
                Seg.update(seg.left, p, v);
            } else {
                Seg.update(seg.right, p, v);
            }
            seg.minVal = Math.min(seg.left.minVal, seg.right.minVal);
        }

        static Seg query(Seg seg, long at) {
            if (seg.l == seg.r) {
                // 返回的是页节点
                return seg;
            }

            int m = seg.m;

            // 比较 非常特殊
            if (seg.left.minVal <= at) {
                return Seg.query(seg.left, at);
            }
            if (seg.right.minVal <= at) {
                return Seg.query(seg.right, at);
            }

            if (seg.left.minVal <= seg.right.minVal) {
                return Seg.query(seg.left, at);
            } else {
                return Seg.query(seg.right, at);
            }
        }
    }
}
