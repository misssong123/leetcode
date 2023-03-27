package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;


class BestTeamScore1626 {
    /**
     * 按照分数和年龄进行降序排列
     * 依次放入完成排序的人员
     * 将当前人员于之前所有人员进行比较
     * 若之前人员年龄小于等于当前人员年龄，选取dp[i],dp[j]的最大值
     * 最后计算当前人员进入球队的最大值，最后取出所有人员进行球队的最大值
     * @param scores
     * @param ages
     * @return
     * 执行用时：
     * 46 ms
     * , 在所有 Java 提交中击败了
     * 26.87%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 68.66%
     * 的用户
     * 通过测试用例：
     * 149 / 149
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        int res = 0;
        int[] dp = new int[len];
        int[][]cache = new int[len][2];
       for(int i = 0 ; i < len ; i++){
          cache[i] = new int[]{scores[i],ages[i]};
       }
       Arrays.sort(cache,(a,b)->a[0]!=b[0] ? a[0] - b[0] : a[1] - b[1]);
       for(int i = 0 ; i < len ; i++){
           for(int j = i - 1 ; j >=0 ; j--){
               if (cache[j][1] <= cache[i][1]){
                   dp[i] = Math.max(dp[i],dp[j]);
               }
           }
           dp[i] += cache[i][0];
           res = Math.max(res,dp[i]);
       }
       return res;
    }

    /**
     *执行用时：
     * 43 ms
     * , 在所有 Java 提交中击败了
     * 37.31%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 73.88%
     * 的用户
     * 通过测试用例：
     * 149 / 149
     */
    public int bestTeamScore1(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; ++i) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (people[j][1] <= people[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += people[i][0];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    /**
     *执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 91.79%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 74.63%
     * 的用户
     * 通过测试用例：
     * 149 / 149
     */
    int maxAge;
    int[] t;
    int[][] people;

    public int bestTeamScore2(int[] scores, int[] ages) {
        maxAge = Arrays.stream(ages).max().getAsInt();
        t = new int[maxAge + 1];
        int res = 0;
        int n = scores.length;
        people = new int[n][2];
        for (int i = 0; i < n; ++i) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        for (int i = 0; i < n; ++i) {
            int score = people[i][0], age = people[i][1], cur = score + query(age);
            update(age, cur);
            res = Math.max(res, cur);
        }
        return res;
    }

    public int lowbit(int x) {
        return x & (-x);
    }

    public void update(int i, int val) {
        for (; i <= maxAge; i += lowbit(i)) {
            t[i] = Math.max(t[i], val);
        }
    }

    public int query(int i) {
        int ret = 0;
        for (; i > 0; i -= lowbit(i)) {
            ret = Math.max(ret, t[i]);
        }
        return ret;
    }
}
