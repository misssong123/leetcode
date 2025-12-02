package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountTrapezoids3623 {
    /**
     * 解答成功:
     * 	执行耗时:34 ms,击败了70.66% 的Java用户
     * 	内存消耗:201.9 MB,击败了28.18% 的Java用户
     * @param points
     * @return
     */
    public int countTrapezoids3623(int[][] points) {
        int mod = 1_000_000_007;
        //划分分组
        Map<Integer,Integer> yMap = new HashMap<>();
        for (int[] point : points) {
            yMap.merge(point[1], 1, Integer::sum);
        }
        long ans = 0;
        long pre = 0;
        //遍历分组
        for(int num : yMap.values()){
            if (num > 1){
                long n = (long)num * (num -1)/2;
                ans = (ans + n * pre ) % mod;
                pre += n;
            }
        }
        return (int) (ans % mod);
    }
    private static final int MOD = 1_000_000_007;

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了98.83% 的Java用户
     * 	内存消耗:185.8 MB,击败了31.23% 的Java用户
     * @param points
     * @return
     */
    public int countTrapezoidsOther(int[][] points) {
        Map<Integer, Integer> cnt = new HashMap<>(points.length, 1); // 预分配空间
        for (int[] p : points) {
            cnt.merge(p[1], 1, Integer::sum); // 统计每一行（水平线）有多少个点
        }

        long ans = 0, s = 0;
        for (int c : cnt.values()) {
            long k = (long) c * (c - 1) / 2;
            ans += s * k;
            s += k;
        }
        return (int) (ans % MOD);
    }

}
