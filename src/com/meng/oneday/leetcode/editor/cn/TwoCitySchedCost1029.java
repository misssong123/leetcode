package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class TwoCitySchedCost1029 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了85.71% 的Java用户
     * 	内存消耗:43 MB,击败了36.36% 的Java用户
     * @param costs
     * @return
     */
    public int twoCitySchedCost1029(int[][] costs) {
        //按照差值进行排序
        Arrays.sort(costs, (a,b)-> Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));
        int res = 0,a = 0,b = 0;
        int n = costs.length / 2;
        for (int[] cost : costs) {
            if (b == n || (cost[0] < cost[1]&& a < n)){
                res += cost[0];
                a++;
            }else {
                res += cost[1];
                b++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了85.71% 的Java用户
     * 	内存消耗:42.9 MB,击败了49.35% 的Java用户
     * @param costs
     * @return
     */
    public int twoCitySchedCostOfficial(int[][] costs) {
        // Sort by a gain which company has
        // by sending a person to city A and not to city B
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o1[1] - (o2[0] - o2[1]);
            }
        });

        int total = 0;
        int n = costs.length / 2;
        // To optimize the company expenses,
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < n; ++i) {
            total += costs[i][0] + costs[i + n][1];
        }
        return total;
    }

}
