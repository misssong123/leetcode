package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class MaxFreeTime3439 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了12.52% 的Java用户
     * 	内存消耗:56.1 MB,击败了97.01% 的Java用户
     * @param eventTime
     * @param k
     * @param startTime
     * @param endTime
     * @return
     */
    public int maxFreeTime3439(int eventTime, int k, int[] startTime, int[] endTime) {
        //计算空格数
        List<Integer> diffList = new ArrayList<>();
        for(int i = 0 ; i <= startTime.length ; i++){
            if (i == 0){
                diffList.add(startTime[i]);
            }else if (i < startTime.length){
                diffList.add(startTime[i] - endTime[i-1]);
            }else {
                diffList.add(eventTime - endTime[i-1]);
            }
        }
        if (diffList.isEmpty()){
            return 0;
        }
        //滑动窗口
        int res = 0;
        int temp = 0;
        for(int i = 0 ; i < diffList.size(); i++){
            if(i > k){
                temp -= diffList.get(i-k-1);
            }
            temp += diffList.get(i);
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了34.56% 的Java用户
     * 	内存消耗:62.2 MB,击败了32.11% 的Java用户
     * @param eventTime
     * @param k
     * @param startTime
     * @param endTime
     * @return
     */
    public int maxFreeTimeOther(int eventTime, int k, int[] startTime, int[] endTime) {
        int ans = 0;
        int s = 0;
        for (int i = 0; i <= startTime.length; i++) {
            s += get(i, eventTime, startTime, endTime);
            if (i < k) {
                continue;
            }
            ans = Math.max(ans, s);
            s -= get(i - k, eventTime, startTime, endTime);
        }
        return ans;
    }

    private int get(int i, int eventTime, int[] startTime, int[] endTime) {
        if (i == 0) {
            return startTime[0]; // 最左边的空余时间段
        }
        int n = startTime.length;
        if (i == n) {
            return eventTime - endTime[n - 1]; // 最右边的空余时间段
        }
        return startTime[i] - endTime[i - 1]; // 中间的空余时间段
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:59.3 MB,击败了91.70% 的Java用户
     * @param eventTime
     * @param k
     * @param startTime
     * @param endTime
     * @return
     */
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] free = new int[n + 1];
        free[0] = startTime[0]; // 最左边的空余时间段
        for (int i = 1; i < n; i++) {
            free[i] = startTime[i] - endTime[i - 1]; // 中间的空余时间段
        }
        free[n] = eventTime - endTime[n - 1]; // 最右边的空余时间段

        // 套定长滑窗模板（窗口长为 k+1）
        int ans = 0;
        int s = 0;
        for (int i = 0; i <= n; i++) {
            s += free[i];
            if (i < k) {
                continue;
            }
            ans = Math.max(ans, s);
            s -= free[i - k];
        }
        return ans;
    }


}
