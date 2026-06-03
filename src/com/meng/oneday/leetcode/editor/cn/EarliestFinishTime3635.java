package com.meng.oneday.leetcode.editor.cn;

class EarliestFinishTime3635 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:99.3 MB,击败了32.26% 的Java用户
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int resOne = cal(landStartTime,landDuration,waterStartTime,waterDuration);
        int resTwo = cal(waterStartTime,waterDuration,landStartTime,landDuration);
        return Math.min(resOne,resTwo);
    }

    private int cal(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        //寻找最早结束时间
        int earliestEnd = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            earliestEnd = Math.min(earliestEnd,landStartTime[i]+landDuration[i]);
        }
        int res = Integer.MAX_VALUE;
        //寻找最晚结束时间
        for (int i = 0; i < waterStartTime.length; i++) {
            if (waterStartTime[i]>=earliestEnd){
                res = Math.min(res,waterStartTime[i]+waterDuration[i]);
            }else{
                res = Math.min(res,earliestEnd+waterDuration[i]);
            }
        }
        return res;
    }
}
