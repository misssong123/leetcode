package com.meng.oneday.leetcode.editor.cn;

class MinCostTickets983 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了84.43% 的Java用户
     * 	内存消耗:42.5 MB,击败了71.70% 的Java用户
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets983(int[] days, int[] costs) {
        int[] res = new int[366];
        int index = 0;
        for (int i = 1; i < 366; i++) {
            if (index >= days.length){
                break;
            }
            if (days[index] == i){
                int temp = res[i-1] + costs[0];
                // 7天
                temp = Math.min(costs[1]+ (i>= 7 ? res[i-7] : 0),temp);
                // 30天
                temp = Math.min(costs[2]+ (i>= 30 ? res[i-30] : 0),temp);
                res[i] = temp;
                index++;
            }else{
                res[i] = res[i-1];
            }
        }
        return res[days[days.length-1]];
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了84.43% 的Java用户
     * 	内存消耗:42.5 MB,击败了65.57% 的Java用户
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] f = new int[n + 1];
        int j = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int d = days[i];
            while (days[j] <= d - 7) {
                j++;
            }
            while (days[k] <= d - 30) {
                k++;
            }
            f[i + 1] = Math.min(f[i] + costs[0], Math.min(f[j] + costs[1], f[k] + costs[2]));
        }
        return f[n];
    }

}
