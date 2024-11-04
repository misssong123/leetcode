package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class Interview148MaxPoints {
    /**
     * 执行用时分布
     * 34
     * ms
     * 击败
     * 34.55%
     * 复杂度分析
     * 消耗内存分布
     * 40.36
     * MB
     * 击败
     * 72.79%
     * @param points
     * @return
     */
    public int maxPointsMy(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return len;
        }
        int x = 0;
        int y = 0;
        int max = 0;
        for(int i = 0 ; i < len ; i++){
            if (points[i][0] == 0){
                x++;
            }
            if (points[i][1] == 0){
                y++;
            }
            for (int j = i + 1 ; j < len ; j++){
                double xor = (points[i][1] - points[j][1])*1.0/(points[i][0] - points[j][0]);
                int temp = 2;
                for(int k = j + 1 ; k < len ; k++){
                    double xor2 = (points[i][1] - points[k][1])*1.0/(points[i][0] - points[k][0]);
                    if (xor2 == xor){
                        temp++;
                    }
                }
                max = Math.max(max,temp);
            }
        }
        max = Math.max(max,x);
        max = Math.max(max,y);
        return max;
    }
    /**
     * 执行用时分布
     * 9
     * ms
     * 击败
     * 95.15%
     * 复杂度分析
     * 消耗内存分布
     * 40.28
     * MB
     * 击败
     * 77.07%
     * @param points
     * @return
     */
    public int maxPoints1(int[][] points) {
        int n = points.length, ans = 1;
        for (int i = 0; i < n; i++) {
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] y = points[j];
                // 枚举点对 (i,j) 并统计有多少点在该线上, 起始 cnt = 2 代表只有 i 和 j 两个点在此线上
                int cnt = 2;
                for (int k = j + 1; k < n; k++) {
                    int[] p = points[k];
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (p[1] - y[1]) * (y[0] - x[0]);
                    if (s1 == s2) cnt++;
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    /**
     * 执行用时分布
     * 36
     * ms
     * 击败
     * 23.64%
     * 复杂度分析
     * 消耗内存分布
     * 43.58
     * MB
     * 击败
     * 37.84%
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int n = points.length, ans = 1;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            // 由当前点 i 发出的直线所经过的最多点数量
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int a = x1 - x2, b = y1 - y2;
                int k = gcd(a, b);
                String key = (a / k) + "_" + (b / k);
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            ans = Math.max(ans, max + 1);
        }
        return ans;
    }
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
