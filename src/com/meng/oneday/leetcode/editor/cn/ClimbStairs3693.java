package com.meng.oneday.leetcode.editor.cn;

class ClimbStairs3693 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了50.76% 的Java用户
     * 	内存消耗:114.8 MB,击败了41.88% 的Java用户
     * @param n     台阶的总数
     * @param costs 每个台阶的代价数组
     * @return      到达顶部台阶的最小总代价
     */
    public int climbStairs3693(int n, int[] costs) {
    // dp数组用于存储到达每个台阶的最小总代价
        int[] dp = new int[n+1];
    // 遍历每个台阶，从1到n
        for (int i = 1; i <= n; i++) {
        // 初始化当前台阶的代价为最大整数值
            int cost = Integer.MAX_VALUE;
        // 检查前三个台阶，寻找最小代价的跳跃
            for (int j = i-1 ; j >= 0 && j >= i-3; j--) {
            // 计算从台阶j跳到台阶i的总代价，包括跳跃代价和台阶代价
                cost = Math.min(cost,costs[i-1] + (j-i)* (j-i) + dp[j]);
            }
        // 将计算得到的最小代价存储到dp数组中
            dp[i] = cost;
        }
    // 返回到达顶部台阶的最小总代价
        return dp[n];
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:108.1 MB,击败了92.89% 的Java用户
     * @param n
     * @param costs
     * @return
     */
    public int climbStairs(int n, int[] costs) {
        int f0 = 0, f1 = 0, f2 = 0;
        for (int c : costs) {
            int newF = Math.min(Math.min(f0 + 9, f1 + 4), f2 + 1) + c;
            f0 = f1;
            f1 = f2;
            f2 = newF;
        }
        return f2;
    }

}
