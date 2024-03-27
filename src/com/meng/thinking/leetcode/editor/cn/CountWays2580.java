package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class CountWays2580 {
    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了5.97% 的Java用户
     * 	内存消耗:77.8 MB,击败了89.55% 的Java用户
     * @param ranges
     * @return
     */
    public int countWaysMy(int[][] ranges) {
        Arrays.sort(ranges,(a,b)->{
            if (a[0]== b[0]){
                return a[1] - b[1];
            }
            return a[0]- b[0];
        });
        List<int[]> cache = new ArrayList<>();
        cache.add(ranges[0]);
        for (int i = 1; i < ranges.length; i++) {
            if (cache.get(cache.size()-1)[1]>= ranges[i][0]){
                cache.get(cache.size()-1)[1] = Math.max(cache.get(cache.size()-1)[1],ranges[i][1]);
            }else {
                cache.add(ranges[i]);
            }
        }
        int size = cache.size();
        long res = 1;
        long  mod = 1000000007;
        for (int i = 1 ; i <= size ; i++){
            res = res * 2 % mod;
        }
        return (int)res;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了92.54% 的Java用户
     * 	内存消耗:79.4 MB,击败了74.63% 的Java用户
     */
    static final int MOD = 1000000007;
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int n = ranges.length;
        int res = 1;
        for (int i = 0; i < n; ) {
            int r = ranges[i][1];
            int j = i + 1;
            while (j < n && ranges[j][0] <= r) {
                r = Math.max(r, ranges[j][1]);
                j++;
            }
            res = res * 2 % MOD;
            i = j;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
