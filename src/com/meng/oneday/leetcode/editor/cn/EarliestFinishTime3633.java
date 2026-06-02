package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class EarliestFinishTime3633 {
    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了8.00% 的Java用户
     * 	内存消耗:46.3 MB,击败了8.00% 的Java用户
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    public int earliestFinishTime3633(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int len1 = landStartTime.length;
        int len2 = waterStartTime.length;
        //初始化
        int[][] index1 = new int[len1][2];
        int[][] index2 = new int[len2][2];
        for (int i = 0; i < len1; i++) {
            index1[i][0] = landStartTime[i];
            index1[i][1] = landDuration[i];
        }
        for (int i = 0; i < len2; i++) {
            index2[i][0] = waterStartTime[i];
            index2[i][1] = waterDuration[i];
        }
        //排序，最早结束
        Arrays.sort(index1, Comparator.comparingInt(a -> a[0]+a[1]));
        Arrays.sort(index2, Comparator.comparingInt(a -> a[0]+a[1]));
        //第一个先开始
        int index11 = index1[0][1]+index1[0][0];
        int res1 = Integer.MAX_VALUE;
        for (int i = 0; i < len2; i++) {
            if (index2[i][0] <= index11) {
                res1 = Math.min(res1, index11 + index2[i][1]);
            }else{
                res1 = Math.min(res1, index2[i][0] + index2[i][1]);
            }
        }
        //第二个先开始
        int index22 = index2[0][1]+index2[0][0];
        int res2 = Integer.MAX_VALUE;
        for (int i = 0; i < len1; i++) {
            if (index1[i][0] <= index22) {
                res2 = Math.min(res2, index22 + index1[i][1]);
            }else {
                res2 = Math.min(res2, index1[i][0] + index1[i][1]);
            }
        }
        return Math.min(res1,res2);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.3 MB,击败了20.00% 的Java用户
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int landWater = solve(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterLand = solve(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(landWater, waterLand);
    }

    private int solve(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minFinish = Math.min(minFinish, landStartTime[i] + landDuration[i]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++) {
            res = Math.min(res, Math.max(waterStartTime[i], minFinish) + waterDuration[i]);
        }
        return res;
    }

}
