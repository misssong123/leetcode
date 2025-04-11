package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountSymmetricIntegers2843 {
    /**
     * 解答成功:
     * 	执行耗时:40 ms,击败了20.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了90.00% 的Java用户
     * @param low
     * @param high
     * @return
     */
    public int countSymmetricIntegers2843(int low, int high) {
        int res = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = low ; i <= high ; i++){
            int num = i ;
            while (num != 0){
                list.add(num%10);
                num /= 10;
            }
            if(list.size() % 2 == 0){
                int mid = list.size()/2;
                int temp = 0;
                for(int j = 0 ; j < mid ; j++){
                    temp += (list.get(j) - list.get(j+mid));
                }
                if(temp == 0){
                    res++;
                }
            }
            list.clear();
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了43.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了59.00% 的Java用户
     * @param low
     * @param high
     * @return
     */
    public int countSymmetricIntegersOther1(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            char[] s = Integer.toString(i).toCharArray();
            int n = s.length;
            if (n % 2 > 0) {
                continue;
            }
            int diff = 0;
            for (int j = 0; j < n / 2; j++) {
                diff += s[j];
            }
            for (int j = n / 2; j < n; j++) {
                diff -= s[j];
            }
            if (diff == 0) {
                ans++;
            }
        }
        return ans;
    }

    private char[] lowS, highS;
    private int n, m, diffLh;
    private int[][][] memo;

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了92.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了71.00% 的Java用户
     * @param low
     * @param high
     * @return
     */
    public int countSymmetricIntegers(int low, int high) {
        lowS = String.valueOf(low).toCharArray();
        highS = String.valueOf(high).toCharArray();
        n = highS.length;
        m = n / 2;
        diffLh = n - lowS.length;

        memo = new int[n][diffLh + 1][m * 18 + 1]; // 注意 start <= diffLh
        for (int[][] mat : memo) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        // 初始化 diff = m * 9，避免出现负数导致 memo 下标越界
        return dfs(0, -1, m * 9, true, true);
    }

    private int dfs(int i, int start, int diff, boolean limitLow, boolean limitHigh) {
        if (i == n) {
            return diff == m * 9 ? 1 : 0;
        }

        // start 当 isNum 用
        if (start != -1 && !limitLow && !limitHigh && memo[i][start][diff] != -1) {
            return memo[i][start][diff];
        }

        int lo = limitLow && i >= diffLh ? lowS[i - diffLh] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        // 如果前面没有填数字，且剩余数位个数是奇数，那么当前数位不能填数字
        if (start < 0 && (n - i) % 2 > 0) {
            // 如果必须填数字（lo > 0），不合法，返回 0
            return lo > 0 ? 0 : dfs(i + 1, start, diff, true, false);
        }

        int res = 0;
        boolean isLeft = start < 0 || i < (start + n) / 2;
        for (int d = lo; d <= hi; d++) {
            res += dfs(i + 1,
                    start < 0 && d > 0 ? i : start, // 记录第一个填数字的位置
                    diff + (isLeft ? d : -d), // 左半 +，右半 -
                    limitLow && d == lo,
                    limitHigh && d == hi);
        }

        if (start != -1 && !limitLow && !limitHigh) {
            memo[i][start][diff] = res;
        }
        return res;
    }

}
