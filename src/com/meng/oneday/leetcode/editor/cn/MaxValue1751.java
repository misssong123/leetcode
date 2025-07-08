package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class MaxValue1751 {
    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了75.90% 的Java用户
     * 	内存消耗:121.6 MB,击败了37.35% 的Java用户
     * @param events
     * @param k
     * @return
     */
    public int maxValue1751(int[][] events, int k) {
        if (k == 1){
            int res = 0;
            for(int[] event : events){
                res = Math.max(res,event[2]);
            }
            return res;
        }
        int n = events.length;
        int[][] dp = new int[n+1][k+1];
        //按照结束时间排序
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));
        for(int i = 0 ; i < n ; i++){
            //寻找可选的坐标
            int l = 0 ,r = i;
            while(l < r){
                int mid = (l + r) /2;
                if(events[mid][1] < events[i][0]){
                    l = mid + 1;
                }else{
                    r = mid;
                }
            }
            for(int j = 1 ; j <= k ; j++){
                dp[i + 1][j] = Math.max(dp[i][j],dp[l][j - 1] + events[i][2]);
            }
        }
        return dp[n][k];
    }

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了75.90% 的Java用户
     * 	内存消耗:122.2 MB,击败了14.46% 的Java用户
     * @param events
     * @param k
     * @return
     */
    public int maxValue(int[][] events, int k) {
        // 特判 k=1 的情况可以更快
        if (k == 1) {
            int mx = 0;
            for (int[] e : events) {
                mx = Math.max(mx, e[2]);
            }
            return mx;
        }

        Arrays.sort(events, (a, b) -> a[1] - b[1]); // 按照结束时间排序
        int n = events.length;
        int[][] f = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            int p = search(events, i, events[i][0]);
            for (int j = 1; j <= k; j++) {
                f[i + 1][j] = Math.max(f[i][j], f[p + 1][j - 1] + events[i][2]);
            }
        }
        return f[n][k];
    }

    // 返回 endDay[i] < upper 的最大 i
    private int search(int[][] events, int right, int upper) {
        int left = -1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][1] < upper) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
