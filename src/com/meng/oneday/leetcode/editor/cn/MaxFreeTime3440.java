package com.meng.oneday.leetcode.editor.cn;

class MaxFreeTime3440 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了68.29% 的Java用户
     * 	内存消耗:58.4 MB,击败了97.56% 的Java用户
     * @param eventTime
     * @param startTime
     * @param endTime
     * @return
     */
    public int maxFreeTime3440(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        if (n == 1){
            return eventTime - (endTime[0] - startTime[0]);
        }
        int[] times = new int[n+1];
        times[0] = startTime[0];
        times[n] = eventTime - endTime[n-1];
        int res = Math.max(times[0],times[n]);
        //计算当前的间隔
        for(int i = 1 ; i <n ; i++){
            times[i] = startTime[i]-endTime[i-1];
            res = Math.max(res,times[i]);
        }
        if (res == 0){
            return 0;
        }
        //后缀
        int[] suffix = new int[n+1];
        for(int i = n -2 ; i >=0 ; i--){
            suffix[i] = Math.max(suffix[i+1],times[i+2]);
        }
        int pre = 0;
        for(int i = 0 ; i < n ; i++){
            int len = endTime[i] -startTime[i];
            //检查是否存在可替代位置
            if (pre >= len || suffix[i] >= len){
                len = 0;
            }
            if (i == 0){
                res = Math.max(res,startTime[i+1]-len);
            }else if(i < n -1){
                res = Math.max(res,startTime[i+1] - endTime[i-1]-len);
            }else {
                res = Math.max(res,eventTime - endTime[i-1]-len);
            }
            //更新前缀
            pre = Math.max(pre,times[i]);
        }
        return res;
    }

    private int eventTime;
    private int[] startTime, endTime;

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了56.10% 的Java用户
     * 	内存消耗:60.3 MB,击败了75.61% 的Java用户
     * @param eventTime
     * @param startTime
     * @param endTime
     * @return
     */
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        this.eventTime = eventTime;
        this.startTime = startTime;
        this.endTime = endTime;
        int n = startTime.length;

        // 有 n+1 个空位，计算前三大的空位在哪
        int a = 0, b = -1, c = -1;
        for (int i = 1; i <= n; i++) {
            int sz = get(i);
            if (sz > get(a)) {
                c = b; b = a; a = i;
            } else if (b < 0 || sz > get(b)) {
                c = b; b = i;
            } else if (c < 0 || sz > get(c)) {
                c = i;
            }
        }

        int ans = 0;
        // 枚举桌子
        for (int i = 0; i < n; i++) {
            int sz = endTime[i] - startTime[i];
            if (i != a && i + 1 != a && sz <= get(a) ||
                    i != b && i + 1 != b && sz <= get(b) ||
                    sz <= get(c)) { // 可以移出去
                ans = Math.max(ans, get(i) + sz + get(i + 1));
            } else {
                ans = Math.max(ans, get(i) + get(i + 1));
            }
        }
        return ans;
    }

    // 计算空位长度
    private int get(int i) {
        if (i == 0) {
            return startTime[0];
        }
        int n = startTime.length;
        if (i == n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    }

}
