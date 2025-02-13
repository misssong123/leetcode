package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CountBalls1742 {
    /**
     * 解答成功:
     * 	执行耗时:47 ms,击败了7.14% 的Java用户
     * 	内存消耗:43.7 MB,击败了22.62% 的Java用户
     * @param lowLimit
     * @param highLimit
     * @return
     */
    public int countBallsMy(int lowLimit, int highLimit) {
        Map<Integer,Integer> cache = new HashMap<>();
        int max = 0;
        for (int index = lowLimit; index <= highLimit; index++) {
            int temp = 0,num = index;
            while (num != 0){
                temp += num % 10;
                num /= 10;
            }
            cache.put(temp,cache.getOrDefault(temp,0)+1);
            max = Math.max(max,cache.get(temp));
        }
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了71.43% 的Java用户
     * 	内存消耗:39.5 MB,击败了86.90% 的Java用户
     * @param lowLimit
     * @param highLimit
     * @return
     */
    public int countBalls1(int lowLimit, int highLimit) {
        int ans = 0;
        int[] cnt = new int[46]; // 99999 的数位和 = 45
        for (int i = lowLimit; i <= highLimit; i++) {
            int s = 0;
            for (int x = i; x > 0; x /= 10) {
                s += x % 10;
            }
            cnt[s]++;
            ans = Math.max(ans, cnt[s]);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了60.71% 的Java用户
     * 	内存消耗:66.8 MB,击败了5.95% 的Java用户
     * @param lowLimit
     * @param highLimit
     * @return
     */
    private final static int[][] s = new int[100_001][46];
    static {
        for (int i = 1; i < s.length; i++) {
            System.arraycopy(s[i - 1], 0, s[i], 0, s[i].length);
            int sum = 0;
            for (int x = i; x > 0; x /= 10) {
                sum += x % 10;
            }
            s[i][sum]++;
        }
    }
    public int countBalls2(int lowLimit, int highLimit) {
        int ans = 0;
        for (int j = 1; j < s[0].length; j++) {
            ans = Math.max(ans, s[highLimit][j] - s[lowLimit - 1][j]);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了52.38% 的Java用户
     * 	内存消耗:67.3 MB,击败了5.95% 的Java用户
     * @param lowLimit
     * @param highLimit
     * @return
     */
    public int countBalls(int lowLimit, int highLimit) {
        String highS = String.valueOf(highLimit);
        int n = highS.length();
        String lowS = String.format("%0" + n + "d", lowLimit);
        int m = highS.charAt(0) - '0' + (n - 1) * 9; // 数位和的上界
        int[][] memo = new int[n][m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = Math.max(ans, dfs(0, j, true, true, lowS.toCharArray(), highS.toCharArray(), memo));
        }
        return ans;
    }

    private int dfs(int i, int j, boolean limitLow, boolean limitHigh, char[] lowS, char[] highS, int[][] memo) {
        if (i == highS.length) { // 填完了
            return j == 0 ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }

        int lo = limitLow ? lowS[i] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        int res = 0;
        for (int d = lo; d <= Math.min(hi, j); d++) { // 枚举当前数位填 d，但不能超过 j
            res += dfs(i + 1, j - d, limitLow && d == lo, limitHigh && d == hi, lowS, highS, memo);
        }

        if (!limitLow && !limitHigh) {
            memo[i][j] = res; // 记忆化
        }
        return res;
    }

}
