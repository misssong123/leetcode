package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinTime3494 {
    /**
     * 解答成功:
     * 	执行耗时:236 ms,击败了39.08% 的Java用户
     * 	内存消耗:43.5 MB,击败了95.25% 的Java用户
     * @param skill
     * @param mana
     * @return
     */
    public long minTime3494(int[] skill, int[] mana) {
        int n = skill.length;
        long[] dp = new long[n];
        int[] diff = new int[n];
        dp[0] = (long) mana[0] * skill[0];
        //初始化
        for(int i = 1 ; i < n ; i++){
            dp[i] = dp[i-1] + (long) mana[0] * skill[i];
        }
        //计算每一种药剂的时间
        for(int i = 1 ; i < mana.length ; i++){
            long suf = dp[n-1];
            dp[n-1] = suf + (long) mana[i] * skill[n-1];
            for(int j = n - 2 ; j >= 0 ; j--){
                long add = (long) mana[i] * skill[j];
                dp[j] = Math.max(suf , dp[j] + add);
                diff[j] = (int) (dp[j] - suf);
                suf = dp[j] - add;
            }
            long temp = 0;
            for(int j = 0 ; j < n ; j++){
                dp[j] += temp;
                temp += diff[j];
            }
            Arrays.fill(diff, 0);
        }
        return dp[n-1];
    }
    public long minTimeOther1(int[] skill, int[] mana) {
        int n = skill.length;
        long[] lastFinish = new long[n]; // 第 i 名巫师完成上一瓶药水的时间
        for (int m : mana) {
            // 按题意模拟
            long sumT = 0;
            for (int i = 0; i < n; i++) {
                sumT = Math.max(sumT, lastFinish[i]) + skill[i] * m;
            }
            // 倒推：如果酿造药水的过程中没有停顿，那么 lastFinish[i] 应该是多少
            lastFinish[n - 1] = sumT;
            for (int i = n - 2; i >= 0; i--) {
                lastFinish[i] = lastFinish[i + 1] - skill[i + 1] * m;
            }
        }
        return lastFinish[n - 1];
    }

    /**
     * 解答成功:
     * 	执行耗时:125 ms,击败了85.03% 的Java用户
     * 	内存消耗:44 MB,击败了25.53% 的Java用户
     * @param skill
     * @param mana
     * @return
     */
    public long minTimeOther2(int[] skill, int[] mana) {
        int n = skill.length;
        int[] s = new int[n + 1]; // skill 的前缀和
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + skill[i];
        }

        int m = mana.length;
        long start = 0;
        for (int j = 1; j < m; j++) {
            long mx = 0;
            for (int i = 0; i < n; i++) {
                mx = Math.max(mx, (long) mana[j - 1] * s[i + 1] - (long) mana[j] * s[i]);
            }
            start += mx;
        }
        return start + (long) mana[m - 1] * s[n];
    }
}
