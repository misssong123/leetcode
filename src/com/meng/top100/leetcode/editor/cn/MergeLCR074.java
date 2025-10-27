package com.meng.top100.leetcode.editor.cn;

import java.util.*;

class MergeLCR074 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了11.23% 的Java用户
     * 	内存消耗:44.5 MB,击败了31.50% 的Java用户
     * @param intervals
     * @return
     */
    public int[][] mergeLCR074(int[][] intervals) {
        //排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        Stack<int[]> stack = new Stack<>();
        stack.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > stack.peek()[1]) {
                stack.add(intervals[i]);
            }else{
                int[] last = intervals[i];
                while (!stack.isEmpty() && stack.peek()[1]>= last[0]){
                    int[] pop = stack.pop();
                    last[0] = Math.min(last[0], pop[0]);
                    last[1] = Math.max(last[1], pop[1]);
                }
                stack.add(last);
            }
        }
        return stack.toArray(new int[stack.size()][]);

    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了96.26% 的Java用户
     * 	内存消耗:44.6 MB,击败了10.79% 的Java用户
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (p, q) -> p[0] - q[0]); // 按照左端点从小到大排序
        List<int[]> ans = new ArrayList<>();
        for (int[] p : intervals) {
            int m = ans.size();
            if (m > 0 && p[0] <= ans.get(m - 1)[1]) { // 可以合并
                ans.get(m - 1)[1] = Math.max(ans.get(m - 1)[1], p[1]); // 更新右端点最大值
            } else { // 不相交，无法合并
                ans.add(p); // 新的合并区间
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
