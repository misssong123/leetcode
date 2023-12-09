package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxTaxiEarnings2008 {
    /**
     * 解答成功:
     * 	执行耗时:88 ms,击败了10.46% 的Java用户
     * 	内存消耗:67.3 MB,击败了13.40% 的Java用户
     * @param n
     * @param rides
     * @return
     */
    public long maxTaxiEarnings1(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));
        int m = rides.length;
        long[] dp = new long[m + 1];
        for (int i = 0; i < m; i++) {
            int j = binarySearch(rides, i, rides[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[j] + rides[i][1] - rides[i][0] + rides[i][2]);
        }
        return dp[m];
    }

    public int binarySearch(int[][] rides, int high, int target) {
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (rides[mid][1] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了44.77% 的Java用户
     * 	内存消耗:67.6 MB,击败了9.48% 的Java用户
     * @param n
     * @param rides
     * @return
     */
    public long maxTaxiEarnings(int n, int[][] rides) {
        long[] dp = new long[n + 1];
        Map<Integer, List<int[]>> rideMap = new HashMap<Integer, List<int[]>>();
        for (int[] ride : rides) {
            rideMap.putIfAbsent(ride[1], new ArrayList<int[]>());
            rideMap.get(ride[1]).add(ride);
        }
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int[] ride : rideMap.getOrDefault(i, new ArrayList<int[]>())) {
                dp[i] = Math.max(dp[i], dp[ride[0]] + ride[1] - ride[0] + ride[2]);
            }
        }
        return dp[n];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
