package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class TemperatureTrendLCP61 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了63.01% 的Java用户
     * @param temperatureA
     * @param temperatureB
     * @return
     */
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ans = 0 ,n = temperatureA.length;
        int temp = 0;
        for(int i = 1 ; i < n ; i++){
            if (Integer.compare(temperatureA[i], temperatureA[i - 1]) ==
                    Integer.compare(temperatureB[i],temperatureB[i - 1])){
                temp++;
                ans = Math.max(temp,ans);
            }else {
                temp = 0;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了93.15% 的Java用户
     * @param temperatureA
     * @param temperatureB
     * @return
     */
    public int temperatureTrendOfficial(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int ans = 0, cur = 0;
        for (int i = 1; i < n; ++i) {
            int ta = getTrend(temperatureA[i - 1], temperatureA[i]);
            int tb = getTrend(temperatureB[i - 1], temperatureB[i]);
            if (ta == tb) {
                ++cur;
                ans = Math.max(ans, cur);
            } else {
                cur = 0;
            }
        }
        return ans;
    }

    public int getTrend(int x, int y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
