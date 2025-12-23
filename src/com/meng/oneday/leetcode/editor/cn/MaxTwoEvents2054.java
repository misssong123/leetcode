package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaxTwoEvents2054 {
    /**
     * 解答成功:
     * 	执行耗时:47 ms,击败了58.93% 的Java用户
     * 	内存消耗:169.3 MB,击败了46.43% 的Java用户
     * @param events
     * @return
     */
    public int maxTwoEvents2054(int[][] events) {
        int len = events.length;
        List<int[]> cache = new ArrayList<>(len);
        cache.add(new int[]{0,0});
        //按照结束时间排序
        Arrays.sort(events,(a,b)->{
            if (a[1] == b[1]){
                return b[2] - a[2];
            }
            return a[1] - b[1];
        });
        int max = 0;
        for (int [] event : events){
            int val = getLastMax(event[0],cache);
            max = Math.max(max,val + event[2]);
            //将当前时间加入缓存
            if (event[2] > cache.get(cache.size() - 1)[1]){
                cache.add(new int[]{event[1],event[2]});
            }
        }
        return max;
    }

    private int getLastMax(int target, List<int[]> cache) {
        int res = 0;
        int left = 0 ,right = cache.size() - 1;
        while (left <= right){
            int mid = (left + right) >> 1;
            if (cache.get(mid)[0] < target){
                res = cache.get(mid)[1];
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了66.07% 的Java用户
     * 	内存消耗:162.9 MB,击败了53.57% 的Java用户
     * @param events
     * @return
     */
    public int maxTwoEventsOther(int[][] events) {
        // 按照结束时间排序
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        // 从栈底到栈顶，结束时间递增，价值递增
        ArrayList<int[]> st = new ArrayList<>(); // (结束时间, 价值)
        st.add(new int[]{0, 0}); // 栈底哨兵

        int ans = 0;
        for (int[] e : events) {
            int i = search(st, e[0]);
            int value = e[2];
            ans = Math.max(ans, st.get(i)[1] + value);
            // 遇到比栈顶更大的价值，入栈
            if (value > st.get(st.size()-1)[1]) {
                st.add(new int[]{e[1], value});
            }
        }
        return ans;
    }

    // 返回最后一个满足 st[i][0] < target 的 i
    private int search(List<int[]> st, int target) {
        int left = -1, right = st.size();
        while (left + 1 < right) { // 开区间二分
            int mid = left + (right - left) / 2;
            if (st.get(mid)[0] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 解答成功:
     * 	执行耗时:33 ms,击败了98.21% 的Java用户
     * 	内存消耗:167.1 MB,击败了51.79% 的Java用户
     * @param events
     * @return
     */
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        int ans = 0;
        int size = 0; // 把 events 当作栈
        for (int[] e : events) {
            int i = search(events, size, e[0]);
            int value = e[2];
            if (i >= 0) {
                ans = Math.max(ans, value + events[i][2]);
            } else {
                ans = Math.max(ans, value);
            }

            if (size == 0 || value > events[size - 1][2]) {
                events[size++] = e;
            }
        }
        return ans;
    }

    // 返回最后一个满足 st[i][1] < target 的 i
    private int search(int[][] st, int right, int target) {
        int left = -1;
        while (left + 1 < right) { // 开区间二分
            int mid = left + (right - left) / 2;
            if (st[mid][1] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
