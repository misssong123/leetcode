package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class CountTrapezoids3625 {
    /**
     * 导致重复计算
     * @param points
     * @return
     */
    public int countTrapezoidsError(int[][] points) {
        Map<Integer,Integer> xMap = new HashMap<>();
        Map<Integer,Integer> yMap = new HashMap<>();
        Map<Double,Map<Double,Integer>> kMap = new HashMap<>();
        int len = points.length;
        for(int i = 0 ; i < len ; i++){
            int x1 = points[i][0],y1 = points[i][1];
            xMap.put(x1,xMap.getOrDefault(x1,0)+1);
            yMap.put(y1,yMap.getOrDefault(y1,0)+1);
            Map<Double, Set<Double>> bMap = new HashMap<>();
            for(int j = i -1 ; j >= 0 ; j--){
                int x2 = points[j][0],y2 = points[j][1];
                if (x1 != x2 && y1 != y2){
                    double k = (double)(y1 - y2) / (x1 - x2);
                    double b = (double)y1 - k * x1;
                    if (bMap.containsKey(k) && bMap.get(k).contains(b)){
                        continue;
                    }
                    bMap.putIfAbsent(k,new HashSet<>());
                    bMap.get(k).add(b);
                    kMap.putIfAbsent(k,new HashMap<>());
                    kMap.get(k).put(b,kMap.get(k).getOrDefault(b,1)+1);
                    System.out.println("k:"+k+" b:"+b + " i:"+i+" j:"+j);
                }
            }
        }
        System.out.println(kMap);
        int res = 0;
        int pre = 0;
        for (int num : xMap.values()){
            int n = num * (num -1)/2;
            res += n  * pre;
            pre += n;
        }
        pre = 0;
        for (int num : yMap.values()){
            int n = num * (num -1)/2;
            res += n  * pre;
            pre += n;
        }
        for (Map.Entry<Double,Map<Double,Integer>> entry : kMap.entrySet()){
            pre = 0;
            for (int num : entry.getValue().values()){
                int n = num * (num -1)/2;
                res += n  * pre;
                pre += n;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:555 ms,击败了76.19% 的Java用户
     * 	内存消耗:274.3 MB,击败了14.29% 的Java用户
     * @param points
     * @return
     */
    public int countTrapezoidsOther1(int[][] points) {
        Map<Double, Map<Double, Integer>> cnt = new HashMap<>(); // 斜率 -> 截距 -> 个数
        Map<Integer, Map<Double, Integer>> cnt2 = new HashMap<>(); // 中点 -> 斜率 -> 个数

        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int dy = y - y2;
                int dx = x - x2;
                double k = dx != 0 ? 1.0 * dy / dx : Double.MAX_VALUE;
                double b = dx != 0 ? 1.0 * (y * dx - x * dy) / dx : x;

                // 归一化 -0.0 为 0.0
                if (k == -0.0) {
                    k = 0.0;
                }
                if (b == -0.0) {
                    b = 0.0;
                }

                // 按照斜率和截距分组 cnt[k][b]++
                cnt.computeIfAbsent(k, item -> new HashMap<>()).merge(b, 1, Integer::sum);

                int mid = (x + x2 + 2000) * 10000 + (y + y2 + 2000); // 把二维坐标压缩成一个 int
                // 按照中点和斜率分组 cnt2[mid][k]++
                cnt2.computeIfAbsent(mid, item -> new HashMap<>()).merge(k, 1, Integer::sum);
            }
        }

        int ans = 0;
        for (Map<Double, Integer> m : cnt.values()) {
            int s = 0;
            for (int c : m.values()) {
                ans += s * c;
                s += c;
            }
        }

        for (Map<Double, Integer> m : cnt2.values()) {
            int s = 0;
            for (int c : m.values()) {
                ans -= s * c; // 平行四边形会统计两次，减去多统计的一次
                s += c;
            }
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:358 ms,击败了95.24% 的Java用户
     * 	内存消耗:266.5 MB,击败了23.81% 的Java用户
     * @param points
     * @return
     */
    public int countTrapezoidsOther2(int[][] points) {
        Map<Double, List<Double>> groups = new HashMap<>(); // 斜率 -> [截距]
        Map<Integer, List<Double>> groups2 = new HashMap<>(); // 中点 -> [斜率]

        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int dy = y - y2;
                int dx = x - x2;
                double k = dx != 0 ? 1.0 * dy / dx : Double.MAX_VALUE;
                if (k == -0.0) {
                    k = 0.0;
                }
                double b = dx != 0 ? 1.0 * (y * dx - x * dy) / dx : x;

                groups.computeIfAbsent(k, item -> new ArrayList<>()).add(b);
                int mid = (x + x2 + 2000) * 10000 + (y + y2 + 2000); // 把二维坐标压缩成一个 int
                groups2.computeIfAbsent(mid, item -> new ArrayList<>()).add(k);
            }
        }

        int ans = 0;
        Map<Double, Integer> cnt = new HashMap<>();
        for (List<Double> g : groups.values()) {
            if (g.size() == 1) {
                continue;
            }
            cnt.clear();
            for (double b : g) {
                if (b == -0.0) {
                    b = 0.0;
                }
                cnt.merge(b, 1, Integer::sum);
            }
            int s = 0;
            for (int c : cnt.values()) {
                ans += s * c;
                s += c;
            }
        }

        for (List<Double> g : groups2.values()) {
            if (g.size() == 1) {
                continue;
            }
            cnt.clear();
            for (double k : g) {
                cnt.merge(k, 1, Integer::sum);
            }
            int s = 0;
            for (int c : cnt.values()) {
                ans -= s * c; // 平行四边形会统计两次，减去多统计的一次
                s += c;
            }
        }

        return ans;
    }

}
