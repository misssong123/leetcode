package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NumberOfBoomerangs447 {
    /**
     * 解答成功:
     * 	执行耗时:343 ms,击败了5.36% 的Java用户
     * 	内存消耗:113.7 MB,击败了5.37% 的Java用户
     * @param points
     * @return
     */
    public int numberOfBoomerangsMy(int[][] points) {
        Map<Integer, Map<Integer, List<Integer>>> cache = new HashMap<>();
        int len = points.length;
        for (int i = 0 ; i < len ; i++){
            for (int j = i + 1 ; j < len ; j++){
                int xDis = Math.abs(points[i][0] - points[j][0]);
                int yDis = Math.abs(points[i][1] - points[j][1]);
                int dis = xDis*xDis+yDis*yDis;
                cache.computeIfAbsent(i, k -> new HashMap<>());
                cache.get(i).computeIfAbsent(dis, k -> new ArrayList<>());
                cache.get(i).get(dis).add(j);
                cache.computeIfAbsent(j, k -> new HashMap<>());
                cache.get(j).computeIfAbsent(dis, k -> new ArrayList<>());
                cache.get(j).get(dis).add(i);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> entry : cache.entrySet()){
            for (Map.Entry<Integer, List<Integer>> entryChild :entry.getValue().entrySet()){
                if (entryChild.getValue().size()>= 2){
                    res += entryChild.getValue().size() * (entryChild.getValue().size() - 1);
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:147 ms,击败了59.91% 的Java用户
     * 	内存消耗:43.9 MB,击败了15.57% 的Java用户
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
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
//leetcode submit region end(Prohibit modification and deletion)
